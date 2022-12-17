package fr.cytech.jeeProject.jeeProject.controllers;

import fr.cytech.jeeProject.jeeProject.beans.SiteUser;
import fr.cytech.jeeProject.jeeProject.controllers.Utils.ModelDefaultAttributes;
import fr.cytech.jeeProject.jeeProject.services.interfaces.LibraryService;
import fr.cytech.jeeProject.jeeProject.services.interfaces.SiteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LibraryController {

    @Autowired private LibraryService libraryService;
    @Autowired private SiteUserService siteUserService;

    @RequestMapping("/libraries")
    public String getLibraries(HttpServletRequest request, Model model){

        ModelDefaultAttributes.getDefaultAttributes(model, request, siteUserService);
        model.addAttribute("libraries", libraryService.getLibraries());

        return "librairies/list";
    }

    @RequestMapping("/library/{urlShort}")
    public String getLibrary(@PathVariable(value = "urlShort", required = false) String libraryUrlShort, HttpServletRequest request, Model model){

        ModelDefaultAttributes.getDefaultAttributes(model, request, siteUserService);
        model.addAttribute("library", libraryService.getLibraryByUrlShort(libraryUrlShort));
        SiteUser siteUser = ModelDefaultAttributes.isUserConnected(request, siteUserService);
        if(siteUser != null){
            model.addAttribute("siteUser", siteUser);
        }

        return "librairies/library";
    }

}
