package fr.cytech.jeeProject.jeeProject.controllers.Utils;

import fr.cytech.jeeProject.jeeProject.beans.SiteUser;
import fr.cytech.jeeProject.jeeProject.services.interfaces.SiteUserService;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Component
public class ModelDefaultAttributes {

    public static Model getDefaultAttributes(Model model, HttpServletRequest request, SiteUserService siteUserService){

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if(cookie.getName().equalsIgnoreCase("cookie-id")){
                    SiteUser siteUser = siteUserService.getSiteUserByCookie(cookie.getValue());
                    if(siteUser != null){
                        model.addAttribute(siteUser);
                    }
                }
            }
        }

        return model;
    }

    public static SiteUser isUserConnected(HttpServletRequest request, SiteUserService siteUserService){
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if(cookie.getName().equalsIgnoreCase("cookie-id")){
                    return siteUserService.getSiteUserByCookie(cookie.getValue());
                }
            }
        }
        return null;
    }

}
