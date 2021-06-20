package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.ServiceDB;

import java.sql.SQLException;


@Controller
@RequestMapping("/page")
public class UsersController {

    @Autowired
    private ServiceDB serviceDB;

    @GetMapping("/health")
    public String health(){
        return "Health";
    }

    @GetMapping("/")
    public String allUsers(Model model) throws SQLException {
        model.addAttribute("users", serviceDB.listUsers());
        return "/page/show";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable("id") int id, Model model) throws SQLException {
        model.addAttribute("user", serviceDB.getUserById(id));
        return "page/getById";
    }

    @GetMapping(value = "/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "page/new";
    }

    @PostMapping(value = "/addUser")
    public String addUser(@ModelAttribute("user") User user) throws SQLException {
        serviceDB.insertUser(user);
        return "redirect:/page";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) throws SQLException {
        model.addAttribute("user", serviceDB.getUserById(id));
        return "page/edit";
    }

    @PatchMapping("/update")
    public String update(@ModelAttribute("user") User user) throws SQLException {
        serviceDB.updateUser(user);
        return "redirect:/page";
    }

    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id) throws SQLException {
        serviceDB.deleteUser(id);
        return "redirect:/page";
    }
}