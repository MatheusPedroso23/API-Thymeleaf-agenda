package com.example.TestAgenda.Controller;

import com.example.TestAgenda.Models.Usuario;
import com.example.TestAgenda.Repository.TipoAcessoRepository;
import com.example.TestAgenda.Repository.UsuarioRepository;
import com.example.TestAgenda.Service.TipoAcessoService;
import com.example.TestAgenda.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TipoAcessoRepository tipoAcessoRepository;

    @Autowired
    private TipoAcessoService tipoAcessoService;

    // Vai para a tela principal onde são listados todos os usuários
    @GetMapping("/usuarios")
    public ModelAndView findAll() {
        ModelAndView mv = new ModelAndView("usuarioList");
        List<Usuario> usuarios = usuarioService.findAll();
        mv.addObject("usuarios", usuarios);
        return mv;
    }

    // Vai para a tela de adição de usuário
    @GetMapping("/usuario/add")
    public ModelAndView add(Usuario usuario) {
        ModelAndView mv = new ModelAndView("usuarioAdd");
        mv.addObject("usuario", usuario);
        mv.addObject("tipodeAcessos", tipoAcessoRepository.findAll());
        return mv;
    }

    // Vai para a tela de edição de usuário (mesma tela de adição, mas com um objeto existente)
    @GetMapping("/usuario/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id) {
        return add(usuarioService.findOne(id).orElse(new Usuario()));
    }

    // Exclui um usuário pelo ID e redireciona para a tela principal
    @GetMapping("/usuario/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
        usuarioService.delete(id);
        return findAll();
    }

    // Recebe um objeto preenchido do Thymeleaf e valida. Se estiver ok, salva e redireciona
    @PostMapping("/usuario/save")
    public ModelAndView save(Usuario usuario, BindingResult result) {
        if (result.hasErrors()) {
            return add(usuario);
        }
        usuarioService.save(usuario);
        return findAll();
    }

}
