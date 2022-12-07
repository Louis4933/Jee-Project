package fr.cytech.jeeProject.jeeProject.controllers;

import fr.cytech.jeeProject.jeeProject.services.interfaces.SiteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @Autowired
    private SiteUserService siteUserService;

    @RequestMapping("/register")
    public String getRegisterPage(Model model){

        return "users/register";
    }

    @RequestMapping("/login")
    public String getLoginPage(Model model){

        return "users/login";
    }

}
