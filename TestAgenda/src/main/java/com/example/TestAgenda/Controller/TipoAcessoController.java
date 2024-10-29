package com.example.TestAgenda.Controller;



import com.example.TestAgenda.Models.TipoAcesso;
import com.example.TestAgenda.Models.Usuario;
import com.example.TestAgenda.Repository.*;
import com.example.TestAgenda.Service.ClienteService;
import com.example.TestAgenda.Service.EnderecoService;
import com.example.TestAgenda.Service.TipoAcessoService;
import com.example.TestAgenda.Service.UsuarioService;
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
public class TipoAcessoController {

    @Autowired
    private TipoAcessoRepository tipoAcessoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TipoAcessoService tipoAcessoService;

    @Autowired
    private UsuarioService usuarioService;


    @GetMapping("/tipoacesso")
    public ModelAndView findAll() {
        ModelAndView mv = new ModelAndView("tipoacessoList");
        List<TipoAcesso> tipoAcesso = tipoAcessoRepository.findAll();
        mv.addObject("tipoacessos", tipoAcesso);
        return mv;
    }
    @GetMapping("/tipoacesso/add")
    public ModelAndView add(TipoAcesso tipoAcesso) {
        ModelAndView mv = new ModelAndView("tipoacessoAdd");
        mv.addObject("tipoacessos", tipoAcesso);
        return mv;
    }
    @GetMapping("/tipoacesso/edit/{id}")
    public String editarTipoacesso(@PathVariable Long id, Model model) {
        TipoAcesso tipoAcesso = tipoAcessoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Acesso não encontrado: " + id));
        model.addAttribute("tipoacessos", tipoAcesso);
        return "tipoacessoAdd";
    }

    @GetMapping("/tipoacesso/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
        tipoAcessoService.delete(id);
        return findAll();
    }

    @PostMapping("/tipoacesso/save")
    public ModelAndView save(TipoAcesso tipoAcesso, BindingResult result) {
        if (result.hasErrors()) {
            return add(tipoAcesso);
        }
        tipoAcessoService.save(tipoAcesso);
        return findAll();
    }
}
