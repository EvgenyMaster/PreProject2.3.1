package crud.servlet;

import crud.model.User;
import crud.service.ServiceDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/")
public class UsersController {

    private ServiceDB serviceDB;

    @Autowired
    public UsersController(ServiceDB serviceDB) {
        this.serviceDB = serviceDB;
    }

    @GetMapping("/healthCheck")
    public String health() {
        return "Health";
    }

    @GetMapping()
    public String allUsers(Model model) {
        model.addAttribute("users", serviceDB.listUsers());
        return "userList";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", serviceDB.getUserById(id));
        return "userPage";
    }

    @GetMapping(value = "/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping(value = "/addUser")
    public String addUser(@ModelAttribute("user") User user) {
        serviceDB.insertUser(user);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", serviceDB.getUserById(id));
        return "edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("user") User user) {
        serviceDB.updateUser(user);
        return "redirect:/";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        serviceDB.deleteUser(id);
        return "redirect:/";
    }
}