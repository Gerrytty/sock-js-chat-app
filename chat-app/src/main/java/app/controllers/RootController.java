package app.controllers;

import app.services.CookiesServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RootController {

    private final CookiesServiceImpl cookiesService;

    public RootController(CookiesServiceImpl cookiesService) {
        this.cookiesService = cookiesService;
    }

    @GetMapping("/")
    public String root(HttpServletRequest request) {

        if(cookiesService.cookieIsExists(request.getCookies())) {
            System.out.println("Cookie exists");
            return "redirect:/rooms";
        }

        else {
            System.out.println("Cookie not exists");
            return "redirect:/auth";
        }
    }

    @GetMapping("/chat")
    public String chat() {
        return "chat";
    }

}

