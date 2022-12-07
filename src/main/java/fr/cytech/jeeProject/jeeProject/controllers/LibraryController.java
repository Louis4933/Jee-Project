package fr.cytech.jeeProject.jeeProject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LibraryController {

    @RequestMapping("/libraries")
    public String getLibraries(Model model){

        return "librairies/list";
    }

    @RequestMapping("/library")
    public String getLibrary(Model model){

        return "librairies/library";
    }

}
