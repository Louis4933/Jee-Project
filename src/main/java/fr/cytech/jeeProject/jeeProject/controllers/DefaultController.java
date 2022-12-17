package fr.cytech.jeeProject.jeeProject.controllers;

import fr.cytech.jeeProject.jeeProject.controllers.Utils.ModelDefaultAttributes;
import fr.cytech.jeeProject.jeeProject.services.interfaces.SiteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DefaultController {
    @Autowired private SiteUserService siteUserService;

    @RequestMapping("/")
    public String getLibraries(HttpServletRequest request, Model model){

        ModelDefaultAttributes.getDefaultAttributes(model, request, siteUserService);

        return "index";
    }

}
