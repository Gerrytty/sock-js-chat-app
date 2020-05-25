package app.controllers;

import app.repositories.RoomsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class RoomsController {

    private final RoomsRepository roomsRepository;

    public RoomsController(RoomsRepository roomsRepository) {
        this.roomsRepository = roomsRepository;
    }

    @GetMapping("/rooms")
    public String show(Model model, HttpServletRequest request) {

        model.addAttribute("rooms", roomsRepository.getAllByUserId(getUserId(request)));
        return "rooms";
    }

    @GetMapping("/room/{roomId}")
    public String showRoom(Model model, @PathVariable Long roomId, HttpServletRequest request) {
        model.addAttribute("room", roomsRepository.findById(roomId).get());
        model.addAttribute("userId", getUserId(request));
        return "room";
    }

    private Long getUserId(HttpServletRequest request) {

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
