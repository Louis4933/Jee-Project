package fr.cytech.jeeProject.jeeProject.controllers;

import fr.cytech.jeeProject.jeeProject.beans.SiteUser;
import fr.cytech.jeeProject.jeeProject.dao.SiteUserDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.stream.Collectors;

@Controller
public class DefaultController {
    private final SiteUserDao siteUserDao;

    public DefaultController(SiteUserDao siteUserDao) {
        this.siteUserDao = siteUserDao;
    }

    @RequestMapping("/")
    public String getLibraries(HttpServletRequest request, Model model){

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if(cookie.getName().equalsIgnoreCase("cookie-id")){
                    SiteUser siteUser = siteUserDao.findSiteUserByCookie(cookie.getValue());
                    if(siteUser != null){
                        model.addAttribute(siteUser);
                    }
                }
            }
        }

        return "index";
    }

}
