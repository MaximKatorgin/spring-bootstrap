package web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserServiceImp;

import java.sql.SQLOutput;
import java.util.List;

@Controller
@RequestMapping("admin/users")
public class UserController {

    @Autowired
    private UserServiceImp userServiceImp;

    @GetMapping
    public String userList(ModelMap model, @AuthenticationPrincipal User user) {
        List<User> userList = userServiceImp.listUsers();
        model.addAttribute("users", userList);
        model.addAttribute("new_user", new User());
        model.addAttribute("granted_roles", user.getRoles());
        model.addAttribute("roles_list", userServiceImp.getRolesList());
        model.addAttribute("current_user", user);
        return "index";
    }


    @PostMapping
    public String createUser(@ModelAttribute("new_user") User user, ModelMap model,
                              @RequestParam("userRoles") String rolesString) {
        userServiceImp.add(user, rolesString);
        return "redirect:/admin/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userServiceImp.deleteById(id);
        return "redirect:/admin/users";
    }

    @PatchMapping("/update")
    public String update(@ModelAttribute("new_user") User user,@RequestParam("newRoles") String rolesString) {
        userServiceImp.update(user, rolesString);
        return "redirect:/admin/users";
    }


}
