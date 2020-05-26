package app.controllers;

import app.services.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsersController {

    private final UsersServiceImpl usersService;

    @Autowired
    public UsersController(UsersServiceImpl usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/users")
    public String show(Model model) {
        model.addAttribute("users", usersService.getAllUsers());
        return "users";
    }

    @PostMapping("/room/{roomId}/addUser")
    public String add(@PathVariable Long roomId, @RequestParam String username) {
        usersService.addToRoom(username, roomId);

        return "redirect:/room/" + roomId;
    }

}
