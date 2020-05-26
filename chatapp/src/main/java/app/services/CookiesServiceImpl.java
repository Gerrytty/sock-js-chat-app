package app.services;

import app.Logger;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Service
public class CookiesServiceImpl {

    public boolean cookieIsExists(Cookie[] cookies) {

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
            return false;
        }

        else {
            return true;
        }

    }

    public Long getUserId(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();

        String userId = "";

        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("userId")) {
                userId = cookie.getValue();
            }
        }

        return Long.parseLong(userId);

    }
}
