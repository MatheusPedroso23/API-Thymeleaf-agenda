package com.example.TestAgenda.Controller;

import com.example.TestAgenda.Models.Cliente;
import com.example.TestAgenda.Models.Endereco;
import com.example.TestAgenda.Repository.EnderecoRepository;
import com.example.TestAgenda.Service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class EnderecoController {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping("/enderecos")
    public ModelAndView findAll() {
        ModelAndView mv = new ModelAndView("enderecoList");
        List<Endereco> enderecos = enderecoService.findAll();
        mv.addObject("enderecos", enderecos);
        return mv;
    }
    @GetMapping("/endereco/add")
    public ModelAndView add(Endereco endereco) {
        ModelAndView mv = new ModelAndView("enderecoAdd");
        mv.addObject("enderecos", endereco );
        return mv;
    }

    @GetMapping("/endereco/edit/{id}")
    public String editarCliente(@PathVariable Long id, Model model) {
        Endereco endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Endereco não encontrado: " + id));
        model.addAttribute("enderecos", endereco);
        return "enderecoAdd";
    }

    @GetMapping("/endereco/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
        enderecoService.delete(id);
        return findAll();
    }

    @PostMapping("/endereco/save")
    public ModelAndView save(Endereco endereco, BindingResult result) {
        if (result.hasErrors()) {
            return add(endereco);
        }
        enderecoService.save(endereco);
        return findAll();
    }
}