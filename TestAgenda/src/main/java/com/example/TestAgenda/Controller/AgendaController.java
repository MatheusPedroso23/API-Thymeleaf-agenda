package com.example.TestAgenda.Controller;



import com.example.TestAgenda.Models.Agenda;
import com.example.TestAgenda.Models.Status;
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




    @GetMapping("/")
    public ModelAndView findAll() {
        ModelAndView mv = new ModelAndView("agendaList");
        List<Agenda> agendas = agendaService.findAll();
        mv.addObject("agendas", agendas);
        return mv;
    }

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

    @GetMapping("/agenda/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id) {
        Agenda agenda = agendaService.findOne(id).orElseThrow(() -> new IllegalArgumentException("Agenda nao encontrada"));
        if (agenda.getStatus() == Status.FINALIZADO) {
            throw  new IllegalStateException("Não é permitido excluir uma agenda com o status FINALIZADO.");
        }
        return add(agendaService.findOne(id).orElse(new Agenda()));
    }

    @GetMapping("/agenda/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
        Agenda agenda = agendaService.findOne(id).orElseThrow(() -> new IllegalArgumentException("Agenda nao encontrada"));
        if (agenda.getStatus() == Status.FINALIZADO) {
            throw new IllegalStateException("Não é permitido excluir uma agenda com status FINALIZADO.");
        }
        agendaService.delete(id);
        return findAll();
    }

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


    // Tratamento de exceções para o controller
    @ExceptionHandler(IllegalStateException.class)
    public ModelAndView handleIllegalStateException(IllegalStateException ex) {
        ModelAndView mv = new ModelAndView("agendaList");
        mv.addObject("agendas", agendaService.findAll());
        mv.addObject("errorMessage", ex.getMessage());
        return mv;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ModelAndView handleIllegalArgumentException(IllegalArgumentException ex) {
        ModelAndView mv = new ModelAndView("agendaList");
        mv.addObject("agendas", agendaService.findAll());
        mv.addObject("errorMessage", ex.getMessage());
        return mv;
    }
}