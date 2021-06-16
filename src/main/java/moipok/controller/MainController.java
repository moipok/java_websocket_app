package moipok.controller;

import moipok.models.Cube;
import moipok.models.Role;
import moipok.models.User;
import moipok.repository.CubeRepository;
import moipok.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;
import java.util.Collections;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CubeRepository cubeRepository;

    @GetMapping("/home")
    public String home(Model model) {
        Iterable<Cube> cubes = cubeRepository.findAll();
        model.addAttribute("cubes", cubes);
        return "home";
    }

    @GetMapping("/")
    public String home1(Model model) {
        model.addAttribute("title", "lalala");
        return "home";
    }

    @GetMapping("/registration")
    public String registration()
    {
        return "registration";
    }

    @PostMapping("/registration")
    public String user(User user, Model model)
    {
        User userInBD = userRepository.findUserByUsername(user.getUsername());
        if (userInBD != null)
        {
            model.addAttribute("message", "User exist!");
            return "registration";
        }
        user.setActive(true);
        user.setRole(Collections.singleton(Role.USER));
        userRepository.save(user);
        return "redirect:/login";
    }

}
