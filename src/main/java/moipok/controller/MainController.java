package moipok.controller;

import moipok.models.*;
import moipok.repository.CubeRepository;
import moipok.repository.UserRepository;
import moipok.save.SaveImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CubeRepository cubeRepository;

    @GetMapping("/home")
    public String home(Model model) {
        Iterable<Cube> cubes = cubeRepository.findByOrderByIdDesc();
        model.addAttribute("cubes", cubes);
        return "home";
    }

    @RequestMapping("/home/save")
    @ResponseBody
    public HttpEntity<byte[]> getArticleImage() {

        SaveImage saveImage = new SaveImage(cubeRepository);

        byte[] image =saveImage.getImage();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setContentLength(image.length);

        return new HttpEntity<byte[]>(image, headers);
    }



    @GetMapping("/")
    public String home1(Model model) {
        return "index";
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

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(Cube message) throws Exception {
        cubeRepository.save(message);
//        Thread.sleep(50); // simulated delay
        return new Greeting(HtmlUtils.htmlEscape(message.toString()));
    }

}
