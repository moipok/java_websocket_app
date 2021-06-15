package moipok.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("title", "main page");
        return "home";
    }

    @GetMapping("/")
    public String home1(Model model) {
        model.addAttribute("title", "123456");
        return "home";
    }

}
