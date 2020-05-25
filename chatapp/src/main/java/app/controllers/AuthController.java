package app.controllers;

import app.Logger;
import app.dto.AuthDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
public class AuthController {

    @GetMapping("/auth")
    public String show() {
        return "auth";
    }

    @PostMapping("/auth")
    public String auth(HttpServletResponse response, @ModelAttribute AuthDto authDto) {
        Logger.green_write("Post method AuthController");

        System.out.println(authDto);

        return "redirect:/";
    }

}
