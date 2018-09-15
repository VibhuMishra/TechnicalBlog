package blog.controller;
import blog.form.RegisterNewUser;
import blog.services.PostService;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import blog.services.UserServiceImp;
import blog.model.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;

import java.nio.charset.StandardCharsets;

public class UserController {


    //UserController class needs the object of UserServiceImp class & hence is dependent on UserServiceImp class.
    //So we want to inject the UserServiceImp object inside this class.So we need some service to inject the dependency. This service is known as dependency injection & is responsible for creating an object of UserServiceImp class & injecting the object in the UserController class.
    //Autowired annotation says “this class needs an object of UserServiceImp class, so please create the UserServiceImp object for us, manage it, and link it here.”
    @Autowired
    private UserServiceImp userServiceImp;


    //UserController class needs the object of PostService class & hence is dependent on PostService class.
    //So we want to inject the PostService object inside this class.So we need some service to inject the dependency,This service is known as dependency injection & is responsible for creating an object of PostService class & injecting the object in this class.
    //Autowired annotation says “this class needs an object of PostService class, so please create the UserServiceImp object for us, manage it, and link it here.”
    @Autowired
    private PostService postService;


    @RequestMapping("/users/login")  //Dispatcher Servlet maps the URL '/users/login' with this method
    private String loginPage(User user)
    {
        return "users/login";
    }
    //This method returns the output given by users/login.html file.


    //Pay attention
    //The loginPage method redirects to users/login.html file containing a form.
    //When user fills the form & clicks on Submit button, the User type object is instantiated & the details are stored in the object attributes.
    //After that this method will be called as indicated by  @RequestMapping(value="/users/login" , method= RequestMethod.POST).
    //Note that this method is listening for a HTTP POST request as indicated by method= RequestMethod.POST  @RequestMapping(value="/users/login" , method= RequestMethod.POST)

    private String login(User user, Model model)
    {
        if(userServiceImp.authenticate(user.getUsername(),user.getPasswordHash()))
            return "redirect:/posts";
        return "redirect:/";

    }
    //This method uses UserServiceImp type object to authenticate the user using authenticate() method.It sends the username & password entered by user & if it matches the existing user, he/she will be redirected to /posts else he/she will be redirected to Home Page
    //The register method redirects to users/register.html file containing a form.
    //When user fills the form & clicks on Submit button,the RegisterNewUser type object is instantiated & the details are stored in the object attributes.
    //After that this method will be called as indicated by  @RequestMapping(value="/users/register" , method= RequestMethod.POST).
    //Note that this method is listening for a HTTP POST request as indicated by method= RequestMethod.POST

    @RequestMapping(value = "/users/register",method = RequestMethod.POST)
    public String registerUser(RegisterNewUser registerNewUser){
        User user = new User(registerNewUser.getUserName(),
                registerNewUser.getFullName());
        String sha256hex;
        sha256hex = Hashing.sha256()
                .hashString(registerNewUser.getPasswordHash(), StandardCharsets.UTF_8)
                .toString();
        user.setPasswordHash(sha256hex);
        userServiceImp.registerNewUser(user);
        return "redirect:/";
    }
}