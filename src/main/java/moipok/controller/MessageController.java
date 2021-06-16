package moipok.controller;

import moipok.models.Cube;
import moipok.models.Message;
import moipok.repository.CubeRepository;
import moipok.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MessageController {
    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/message")
    public String message(Model model) {
        Iterable<Message> messages = messageRepository.findAll();
        model.addAttribute("mess", messages);
        return "message";
    }

    @PostMapping("/message")
    public String addMessage(@RequestParam String text, Model model){
        Message message = new Message();
        message.setText(text);
        messageRepository.save(message);
        Iterable<Message> messages = messageRepository.findAll();
        model.addAttribute("mess", messages);
        return "message";
    }
}
