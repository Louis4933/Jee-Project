package fr.cytech.jeeProject.jeeProject.controllers;

import fr.cytech.jeeProject.jeeProject.beans.SiteUser;
import fr.cytech.jeeProject.jeeProject.dao.SiteUserDao;
import fr.cytech.jeeProject.jeeProject.enums.UserRole;
import fr.cytech.jeeProject.jeeProject.services.interfaces.SiteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {

    @Autowired
    private SiteUserService siteUserService;
    @Autowired
    private SiteUserDao siteUserDao;

    @RequestMapping("/register")
    public String getRegisterPage(Model model){

        return "users/register";
    }

    @RequestMapping(value = "/registeruser", method = { RequestMethod.POST }, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView registerUser(@RequestBody MultiValueMap<String, String> formData, HttpServletResponse response){

        SiteUser siteUser = new SiteUser();
        siteUser.setName(formData.get("firstName").get(0));
        siteUser.setSurname(formData.get("lastName").get(0));
        siteUser.setUserRole(UserRole.DEFAULT);
        siteUser.setAddress(formData.get("address").get(0));
        siteUser.setPassword(formData.get("password").get(0));
        siteUser.setEmail(formData.get("email").get(0));
        siteUser.setCookieCode(String.valueOf(java.util.UUID.randomUUID()));

        siteUserDao.save(siteUser);

        Cookie cookie = new Cookie("cookie-id", siteUser.getCookieCode());
        //cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(7 * 24 * 60 * 60); // expires in 7 days

        response.addCookie(cookie);

        formData.clear();

        return new ModelAndView("redirect:/");
    }

    @RequestMapping("/login")
    public String getLoginPage(Model model){

        return "users/login";
    }

    @RequestMapping(value = "/loginuser", method = { RequestMethod.POST }, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView loginUser(@RequestBody MultiValueMap<String, String> formData, HttpServletResponse response){


        SiteUser siteUser = siteUserDao.validateUser(formData.get("email").get(0), formData.get("password").get(0));
        if(siteUser != null){
            Cookie cookie = new Cookie("cookie-id", siteUser.getCookieCode());
            //cookie.setSecure(true);
            cookie.setPath("/");
            cookie.setMaxAge(7 * 24 * 60 * 60); // expires in 7 days

            response.addCookie(cookie);
        }

        formData.clear();

        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/logout")
    public ModelAndView logoutUser(HttpServletResponse response){

        Cookie cookie = new Cookie("cookie-id", null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return new ModelAndView("redirect:/");
    }

    @RequestMapping("/profile")
    public String getProfilePage(Model model){

        return "users/profile";
    }

    @RequestMapping("/editprofile")
    public String getEditProfilePage(Model model){

        return "users/editprofile";
    }

}
