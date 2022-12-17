package fr.cytech.jeeProject.jeeProject.controllers;

import fr.cytech.jeeProject.jeeProject.beans.Book;
import fr.cytech.jeeProject.jeeProject.beans.SiteUser;
import fr.cytech.jeeProject.jeeProject.controllers.Utils.ModelDefaultAttributes;
import fr.cytech.jeeProject.jeeProject.dao.SiteUserDao;
import fr.cytech.jeeProject.jeeProject.enums.UserRole;
import fr.cytech.jeeProject.jeeProject.services.interfaces.BookService;
import fr.cytech.jeeProject.jeeProject.services.interfaces.SiteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SiteUserController {

    @Autowired
    private SiteUserService siteUserService;
    @Autowired
    private SiteUserDao siteUserDao;
    @Autowired
    private BookService bookService;

    @RequestMapping("/register")
    public String getRegisterPage(HttpServletRequest request, Model model){

        ModelDefaultAttributes.getDefaultAttributes(model, request, siteUserService);

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
    public String getLoginPage(HttpServletRequest request, Model model){

        ModelDefaultAttributes.getDefaultAttributes(model, request, siteUserService);

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
    public String getProfilePage(HttpServletRequest request, Model model){

        ModelDefaultAttributes.getDefaultAttributes(model, request, siteUserService);
        SiteUser siteUser = ModelDefaultAttributes.isUserConnected(request, siteUserService);
        if(siteUser == null){ return "index"; }

        model.addAttribute("holdingBooks", bookService.getBookByHolder(siteUser));
        model.addAttribute("siteUser", siteUser);

        return "users/profile";
    }

    @RequestMapping("/editprofile")
    public String getEditProfilePage(HttpServletRequest request, Model model){

        ModelDefaultAttributes.getDefaultAttributes(model, request, siteUserService);

        return "users/editprofile";
    }

    @RequestMapping("/cart")
    public String getUserBookCart(HttpServletRequest request, Model model){

        ModelDefaultAttributes.getDefaultAttributes(model, request, siteUserService);
        SiteUser siteUser = ModelDefaultAttributes.isUserConnected(request, siteUserService);
        if(siteUser == null){ return "index"; }
        model.addAttribute("siteUser", siteUser);

        return "users/cart";
    }

    @RequestMapping("/cart/validate")
    public ModelAndView validateUserCart(HttpServletRequest request, Model model){

        SiteUser siteUser = ModelDefaultAttributes.isUserConnected(request, siteUserService);
        if(siteUser == null){ return new ModelAndView("redirect:/"); }

        for (Book book : siteUser.bookCart) {
            book.setCurrentHolder(siteUser);
            bookService.saveBook(book);
        }

        siteUser.bookCart.clear();
        siteUserService.saveSiteUser(siteUser);

        return new ModelAndView("redirect:/profile");
    }

    @RequestMapping("/cart/add/{id}")
    public ModelAndView addBookToUserCart(@PathVariable(value = "id", required = false) Long bookId, HttpServletRequest request, Model model){

        SiteUser siteUser = ModelDefaultAttributes.isUserConnected(request, siteUserService);
        if(siteUser == null){ return new ModelAndView("redirect:/"); }

        Book book = bookService.getBookById(bookId);
        if(book == null){ return new ModelAndView("redirect:/"); }

        siteUser.bookCart.add(book);
        siteUserDao.save(siteUser);

        return new ModelAndView("redirect:/cart");
    }

    @RequestMapping("/cart/remove/{id}")
    public ModelAndView removeBookToUserCart(@PathVariable(value = "id", required = false) Long bookId, HttpServletRequest request, Model model){

        SiteUser siteUser = ModelDefaultAttributes.isUserConnected(request, siteUserService);
        if(siteUser == null){ return new ModelAndView("redirect:/"); }

        Book book = bookService.getBookById(bookId);
        if(book == null){ return new ModelAndView("redirect:/"); }

        siteUser.bookCart.remove(book);
        siteUserDao.save(siteUser);

        return new ModelAndView("redirect:/cart");
    }

    @RequestMapping("/wishlist/add/{id}")
    public ModelAndView addBookToUserWishlist(@PathVariable(value = "id", required = false) Long bookId, HttpServletRequest request, Model model){

        SiteUser siteUser = ModelDefaultAttributes.isUserConnected(request, siteUserService);
        if(siteUser == null){ return new ModelAndView("redirect:/"); }

        Book book = bookService.getBookById(bookId);
        if(book == null){ return new ModelAndView("redirect:/"); }

        siteUser.favorites.add(book);
        siteUserDao.save(siteUser);

        return new ModelAndView("redirect:/profile");
    }

    @RequestMapping("/wishlist/remove/{id}")
    public ModelAndView removeBookToUserWishlist(@PathVariable(value = "id", required = false) Long bookId, HttpServletRequest request, Model model){

        SiteUser siteUser = ModelDefaultAttributes.isUserConnected(request, siteUserService);
        if(siteUser == null){ return new ModelAndView("redirect:/"); }

        Book book = bookService.getBookById(bookId);
        if(book == null){ return new ModelAndView("redirect:/"); }

        siteUser.favorites.remove(book);
        siteUserDao.save(siteUser);

        return new ModelAndView("redirect:/profile");
    }

}
