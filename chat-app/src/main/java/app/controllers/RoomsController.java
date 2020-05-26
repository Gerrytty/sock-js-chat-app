package app.controllers;

import app.repositories.MessagesRepository;
import app.repositories.RoomsRepository;
import app.services.CookiesServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RoomsController {

    private final RoomsRepository roomsRepository;
    private final MessagesRepository messagesRepository;
    private final CookiesServiceImpl cookiesService;

    public RoomsController(RoomsRepository roomsRepository, MessagesRepository messagesRepository, CookiesServiceImpl cookiesService) {
        this.roomsRepository = roomsRepository;
        this.messagesRepository = messagesRepository;
        this.cookiesService = cookiesService;
    }

    @GetMapping("/rooms")
    public String show(Model model, HttpServletRequest request) {
        model.addAttribute("rooms", roomsRepository.getAllByUserId(cookiesService.getUserId(request)));
        return "rooms";
    }

    @GetMapping("/room/{roomId}")
    public String showRoom(Model model, @PathVariable Long roomId, HttpServletRequest request) {
        model.addAttribute("room", roomsRepository.findById(roomId).get());
        model.addAttribute("userId", cookiesService.getUserId(request));
        model.addAttribute("messages", messagesRepository.findAllByRoomId(roomId));
        return "room";
    }

}
