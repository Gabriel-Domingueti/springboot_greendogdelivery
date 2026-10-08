package com.greendogdelivery.springboot_greendogdelivery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.greendogdelivery.springboot_greendogdelivery.domain.Cliente;
import com.greendogdelivery.springboot_greendogdelivery.repository.ClienteRepository;

import jakarta.validation.Valid;

@Controller 
@RequestMapping("/clientes")
public class ClienteController {
    
    private final ClienteRepository clienteRepository;
    private static final String CLIENTE_URI = "clientes/";

    // 1. Construtor injetando a dependência
    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @GetMapping("/")
    public ModelAndView list() {
        Iterable<Cliente> clientes = this.clienteRepository.findAll();
        return new ModelAndView("clientes/list", "clientes", clientes);
    }
    
    @GetMapping("{id}")
    public ModelAndView view(@PathVariable("id") Cliente cliente) {
        return new ModelAndView("clientes/view", "cliente", cliente);
    }

    @GetMapping("/novo")
    public String createForm(@ModelAttribute Cliente cliente) {
        return "clientes/form";
    }
    
    @PostMapping(params = "form")
    public ModelAndView create(@Valid Cliente cliente, BindingResult result, RedirectAttributes redirect) {
        if(result.hasErrors()) {
            return new ModelAndView(CLIENTE_URI + "form", "formErrors", result.getAllErrors());
        }
        cliente = this.clienteRepository.save(cliente);
        redirect.addFlashAttribute("globalMessage", "Cliente gravado com sucesso");

        return new ModelAndView("redirect:/" + CLIENTE_URI + "{cliente.id}", "cliente.id", cliente.getId());
    }

    // 3. Rota corrigida
    @GetMapping(value = "alterar/{id}")
    public ModelAndView alterarForm(@PathVariable("id") Cliente cliente) {
        return new ModelAndView("clientes/form", "cliente", cliente);
    }

    @GetMapping(value = "remover/{id}")
    public ModelAndView remover(@PathVariable("id") Long id, RedirectAttributes redirect) {
        // 4. Comando atualizado para Spring Boot 3
        this.clienteRepository.deleteById(id);
        Iterable<Cliente> clientes = this.clienteRepository.findAll();

        ModelAndView mv = new ModelAndView("clientes/list", "clientes", clientes);
        mv.addObject("globalMessage", "Cliente removido com sucesso");

        return mv;
    }
}