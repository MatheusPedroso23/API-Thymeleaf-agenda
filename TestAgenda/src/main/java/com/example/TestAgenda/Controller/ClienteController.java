package com.example.TestAgenda.Controller;

import com.example.TestAgenda.Models.Cliente;
import com.example.TestAgenda.Repository.*;
import com.example.TestAgenda.Service.ClienteService;
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
public class ClienteController {

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private TipoAcessoRepository tipoAcessoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping("/clientes")
    public ModelAndView findAll() {
        ModelAndView mv = new ModelAndView("clienteList");
        List<Cliente> clientes = clienteService.findAll();
        mv.addObject("clientes", clientes);
        return mv;
    }

    // Vai para a tela de adição de usuário
    @GetMapping("/cliente/add")
    public ModelAndView add(Cliente cliente) {
        ModelAndView mv = new ModelAndView("clienteAdd");
        mv.addObject("clientes", cliente );
        mv.addObject("enderecos", enderecoRepository.findAll());
        return mv;
    }


    // Vai para a tela de edição de usuário (mesma tela de adição, mas com um objeto existente)
    @GetMapping("/cliente/edit/{id}")
    public String editarCliente(@PathVariable Long id, Model model) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado: " + id));
        model.addAttribute("cliente", cliente);
        return "clienteAdd";
    }

    // Exclui um usuário pelo ID e redireciona para a tela principal
    @GetMapping("/cliente/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
        clienteService.delete(id);
        return findAll();
    }

    // Recebe um objeto preenchido do Thymeleaf e valida. Se estiver ok, salva e redireciona
    @PostMapping("/cliente/save")
    public ModelAndView save(Cliente cliente, BindingResult result) {
        if (result.hasErrors()) {
            return add(cliente);
        }
        clienteService.save(cliente);
        return findAll();
    }

}