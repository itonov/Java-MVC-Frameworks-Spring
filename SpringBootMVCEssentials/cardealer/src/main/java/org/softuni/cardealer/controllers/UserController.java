package org.softuni.cardealer.controllers;

import org.softuni.cardealer.entities.User;
import org.softuni.cardealer.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/register")
    public String registerUser() {
        return "/users/register";
    }

    @PostMapping("/users/register")
    public String registerUserConfirm(HttpServletRequest request) {
        User newUser = new User();

        newUser.setEmail(request.getParameter("email"));
        newUser.setPassword(request.getParameter("password"));
        newUser.setUsername(request.getParameter("username"));

        this.userService.addUser(newUser);
        return "redirect:/";
    }

    @GetMapping("/users/login")
    public String loginUser() {
        return "/users/login";
    }

    @PostMapping("/users/login")
    public String loginUserConfirm(HttpServletRequest request) {
        if (request.getAttribute("loggedUser") != null) {
            return "redirect:/";
        }

        User user = this.userService.findByUsername(request.getParameter("username"));

        if (user != null && request.getParameter("password").equals(user.getPassword())) {
            request.getSession().setAttribute("loggedUser", user.getUsername());
        }

        return "redirect:/";
    }

    @GetMapping("/users/logout")
    public String logoutUser(HttpServletRequest request) {
        if (request.getSession().getAttribute("loggedUser") == null) {
            return "redirect:/";
        }

        request.getSession().invalidate();

        return "/users/logout-complete";
    }
}
