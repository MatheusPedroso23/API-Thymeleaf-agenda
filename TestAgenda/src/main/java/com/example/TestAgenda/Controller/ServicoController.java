package com.example.TestAgenda.Controller;

import com.example.TestAgenda.Models.Servico;
import com.example.TestAgenda.Models.TipoAcesso;
import com.example.TestAgenda.Repository.AgendaRepository;
import com.example.TestAgenda.Repository.ServicoRepository;
import com.example.TestAgenda.Service.ServicoService;
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
public class ServicoController {

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private ServicoService servicoService;

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private com.example.TestAgenda.Services.AgendaService agendaService;

    @GetMapping("/servico")
    public ModelAndView findAll() {
        ModelAndView mv = new ModelAndView("servicoList");
        List<Servico> servicos = servicoRepository.findAll();
        mv.addObject("servicos", servicos);
        return mv;
    }

    @GetMapping("/servico/add")
    public ModelAndView add(Servico servico) {
        ModelAndView mv = new ModelAndView("servicoAdd");
        mv.addObject("servicos", servico);
        return mv;
    }

    @GetMapping("/servico/edit/{id}")
    public String editarTipoacesso(@PathVariable Long id, Model model) {
        Servico servico = servicoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Servico não encontrado: " + id));
        model.addAttribute("servicos", servico);
        return "servicoAdd";
    }

    @GetMapping("/servico/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
        servicoService.delete(id);
        return findAll();
    }
    @PostMapping("/servico/save")
    public ModelAndView save(Servico servico, BindingResult result) {
        if (result.hasErrors()) {
            return add(servico);
        }
        servicoService.save(servico);
        return findAll();
    }

}
