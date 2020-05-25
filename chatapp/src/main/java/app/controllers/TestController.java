package app.controllers;

import app.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/chat")
    public String chat(Model model) {
        model.addAttribute("pageId", 1);
        return "chat";
    }
}
