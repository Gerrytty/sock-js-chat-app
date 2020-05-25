package app.controllers;

import app.dto.AuthDto;
//import app.services.AuthServiceImpl;
import app.services.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class RegistrationController {

    private final AuthServiceImpl authService;

    @Autowired
    public RegistrationController(AuthServiceImpl authService) {
        this.authService = authService;
    }

    @GetMapping("/reg")
    public String show() {
        return "reg";
    }

    @PostMapping("/reg")
    public String register(HttpServletResponse response, @ModelAttribute AuthDto authDto) {

        System.out.println(authDto.toString());

        response.addCookie(new Cookie("username", authDto.getUsername()));
        response.addCookie(new Cookie("password", authDto.getPassword()));

        authService.register(authDto);

        return "redirect:/";
    }

}
