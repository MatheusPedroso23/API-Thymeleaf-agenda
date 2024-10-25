package com.example.TestAgenda.Controller;



import com.example.TestAgenda.Models.Agenda;
import com.example.TestAgenda.Models.Usuario;
import com.example.TestAgenda.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class AgendaController {

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private com.example.TestAgenda.Services.AgendaService agendaService;


    // Vai para tela principal do CRUD onde são listadas todas as agendas
    @GetMapping("/")
    public ModelAndView findAll() {
        ModelAndView mv = new ModelAndView("agendaList");
        List<Agenda> agendas = agendaService.findAll();
        mv.addObject("agendas", agendas);
        return mv;
    }

    // Vai para a tela de adição de agenda
    @GetMapping("/add")
    public ModelAndView add(Agenda agenda) {
        ModelAndView mv = new ModelAndView("agendaAdd");
        mv.addObject("agenda", agenda);
        mv.addObject("usuarios", usuarioRepository.findAll());
        mv.addObject("clientes", clienteRepository.findAll());
        mv.addObject("funcionarios", funcionarioRepository.findAll());
        mv.addObject("servicos", servicoRepository.findAll());
        return mv;
    }

    // Vai para a tela de edição de agenda (mesma tela de adição, mas com um objeto existente)
    @GetMapping("/agenda/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id) {
        return add(agendaService.findOne(id).orElse(new Agenda()));
    }

    // Exclui uma agenda pelo ID e redireciona para a tela principal
    @GetMapping("/agenda/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
        agendaService.delete(id);
        return findAll();
    }

    // Recebe um objeto preenchido do Thymeleaf e valida. Se estiver ok, salva e redireciona
    @PostMapping("/agenda/save")
    public ModelAndView save(Agenda agenda, BindingResult result) {
        if (result.hasErrors()) {
            return add(agenda);
        }

        Long usuarioId = agenda.getUsuario().getId();
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        agenda.setUsuario(usuario);
        agendaService.save(agenda);
        agendaRepository.save(agenda);
        return findAll();

    }
}