package app.controllers;

import app.Logger;
import app.dto.AuthDto;
import app.models.ChatUser;
import app.services.AuthServiceImpl;
import app.services.CookiesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Controller
public class AuthController {

    private final CookiesServiceImpl cookiesService;
    private final AuthServiceImpl authService;

    @Autowired
    public AuthController(CookiesServiceImpl cookiesService, AuthServiceImpl authService) {
        this.cookiesService = cookiesService;
        this.authService = authService;
    }

    @GetMapping("/auth")
    public String show() {
        return "auth";
    }

    @PostMapping("/auth")
    public String auth(HttpServletResponse response,
                       HttpServletRequest request,
                       @ModelAttribute AuthDto authDto) {

        Logger.green_write("Post method AuthController");

        if(!cookiesService.cookieIsExists(request.getCookies())) {

            Optional<ChatUser> chatUser = authService.auth(authDto);

            if(chatUser.isPresent()) {
                response.addCookie(new Cookie("username", chatUser.get().getUsername()));
                response.addCookie(new Cookie("password", chatUser.get().getPassword()));
                response.addCookie(new Cookie("userId", Long.toString(chatUser.get().getChat_user_id())));
                return "redirect:/rooms";
            }

            else {
                return "redirect:/reg";
            }

        }

        return "redirect:/rooms";

    }

    @GetMapping("/logout")
    public String logout(HttpServletResponse response) {

        Cookie[] cookies = {new Cookie("username", ""),
                new Cookie("password","" ),
                new Cookie("userId", "")
        };

        for (Cookie cookie : cookies) {
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }

        return "auth";
    }

}
