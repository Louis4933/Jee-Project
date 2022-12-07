package fr.cytech.jeeProject.jeeProject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {

    @RequestMapping("/")
    public String getLibraries(Model model){

        return "index";
    }

}
