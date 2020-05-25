package app.controllers;

import app.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class RootController {

    @GetMapping("/")
    public String root(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {

            Logger.green_write("Cookie != null");

            String username = "";
            String password = "";

            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("username")) {
                    username = cookie.getValue();
                    Logger.green_write("Username is exists and = " + username);
                }
                if(cookie.getName().equals("password")) {
                    password = cookie.getValue();
                    Logger.green_write("Password is exists and = " + password);
                }
            }

            if(username.equals("") || password.equals("")) {
                Logger.red_write("User is not authenficated");
                return "redirect:/reg";
            }

        }

        return "redirect:/reg";

    }

}
