package com.example.TestAgenda.Controller;

import com.example.TestAgenda.Models.Cliente;
import com.example.TestAgenda.Models.Funcionario;
import com.example.TestAgenda.Repository.EnderecoRepository;
import com.example.TestAgenda.Repository.FuncionarioRepository;
import com.example.TestAgenda.Service.EnderecoService;
import com.example.TestAgenda.Service.FuncionarioService;
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
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping("/funcionarios")
    public ModelAndView findAll() {
        ModelAndView mv = new ModelAndView("funcionarioList");
        List<Funcionario> funcionarios = funcionarioService.findAll();
        mv.addObject("funcionarios", funcionarios);
        return mv;
    }

    @GetMapping("/funcionario/add")
    public ModelAndView add(Funcionario funcionario) {
        ModelAndView mv = new ModelAndView("funcionarioAdd");
        mv.addObject("funcionarios", funcionario );
        mv.addObject("enderecos", enderecoRepository.findAll());
        return mv;
    }

    @GetMapping("/funcionario/edit/{id}")
    public String editarCliente(@PathVariable Long id, Model model) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Funcionario não encontrado: " + id));
        model.addAttribute("funcionario", funcionario);
        return "funcionarioAdd";
    }

    @GetMapping("/funcionario/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
        funcionarioService.delete(id);
        return findAll();
    }

    @PostMapping("/funcionario/save")
    public ModelAndView save(Funcionario funcionario, BindingResult result) {
        if (result.hasErrors()) {
            return add(funcionario);
        }
        funcionarioService.save(funcionario);
        return findAll();
    }
}
