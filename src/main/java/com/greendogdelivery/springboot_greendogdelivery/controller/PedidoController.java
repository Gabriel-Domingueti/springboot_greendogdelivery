package com.greendogdelivery.springboot_greendogdelivery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.greendogdelivery.springboot_greendogdelivery.domain.Pedido;
import com.greendogdelivery.springboot_greendogdelivery.repository.ItemRepository;
import com.greendogdelivery.springboot_greendogdelivery.repository.PedidoRepository;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/pedidos")
public class PedidoController {
    private final PedidoRepository pedidoRepository;
    private static final String PEDIDO_URI = "pedidos/";
    
    public PedidoController(PedidoRepository pedidoRepository, ItemRepository itemRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @GetMapping("/")
    public ModelAndView list() {
        Iterable<Pedido> pedidos = pedidoRepository.findAll();
        return new ModelAndView("pedidos/list", "pedidos", pedidos);
    }
    
    @GetMapping("{id}")
    public ModelAndView view(@PathVariable("id") Pedido pedido) {
        return new ModelAndView("pedidos/view", "pedido", pedido);
    }
    
    @GetMapping("/novo")
    public String createForm(@ModelAttribute Pedido pedido){
        return "pedidos/form";
    }  

    @PostMapping(params = "form")
    public ModelAndView create(@Valid Pedido pedido, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return new ModelAndView(PEDIDO_URI + "form", "formErrors", result.getAllErrors());
        }

        pedido = this.pedidoRepository.save(pedido);
        redirect.addFlashAttribute("globalMessage", "Pedido gravado com sucesso!");

        return new ModelAndView("redirect:/" + PEDIDO_URI + "{pedido.id}", "pedido.id", pedido.getId());
    }

    @GetMapping(value = "alterar/{id}")
    public ModelAndView alterarForm(@PathVariable("id") Pedido pedido) {
        return new ModelAndView("pedidos/form", "pedido", pedido);
    }

    @GetMapping(value = "remover/{id}")
    public ModelAndView remover(@PathVariable long id, RedirectAttributes redirect) {
        this.pedidoRepository.deleteById(id);
        Iterable<Pedido> pedidos = this.pedidoRepository.findAll();

        ModelAndView mv = new ModelAndView("pedidos/list", "pedidos", pedidos);
        mv.addObject("globalMessage", "Pedido removido com sucesso!");

        return mv;
    }
    
    @GetMapping("/pesquisar")
    public ModelAndView pesquisar(@RequestParam("termo") String termo) {
        Iterable<Pedido> pedidos = this.pedidoRepository.findByClienteNomeContainingIgnoreCase(termo);

        ModelAndView mv = new ModelAndView("pedidos/list", "pedidos", pedidos);
        mv.addObject("globalMessage", "Resultado da pesquisa para '" + termo + "'");

        return mv;
    } 
}