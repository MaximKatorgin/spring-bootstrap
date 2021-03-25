package web.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.model.User;


@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping("/user")
    public String userInfoPage(ModelMap modelMap, @AuthenticationPrincipal User user) {
        modelMap.addAttribute("username", SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal());
        modelMap.addAttribute("current_user", user);
        return "index";
    }

    @GetMapping
    public String homePage() {
        return "redirect:/login";
    }
}
