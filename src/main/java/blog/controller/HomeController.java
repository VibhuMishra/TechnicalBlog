package blog.controller;

import blog.model.Post;
import blog.services.PostService;
import com.fasterxml.jackson.annotation.JacksonInject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
//@Controller indicates that the class is a Controller class
//The Dispatcher Servlet scans the classes annotated with @Controller annotation to find the mapped methods.

public class HomeController {
    //HomeController class needs the object of PostService class & hence is dependent on PostService class.
    //So we want to inject the PostService object inside this class.So we need some service to inject the dependency,This service is known as dependency injection & is responsible for creating an object of PostService class & injecting the object in this class.
    //Autowired annotation says this class needs an object of PostService class, so link it here.
    @Autowired
    private PostService postService;
    //This object contains all the posts



    //As already mentioned that the Dispatcher Servlet scans the HomeController class & when a user enters a URL 'localhost:8080', the Dispatcher Servlet looks for a method mapped with request mapping "/"
    //This annotation maps the url 'localhost:8080' with this index() method
    @RequestMapping("/")

    //This method receives Model type object as an argument from Spring configuration
    public String index(Model model)
    {
        List<Post> list = postService.firstThreePosts();
        model.addAttribute("posts" , list);
        return "index";
    }

    //In the method we make a list of only first 3 posts as we want to display only first 3 posts on Home Page.The list is added to Model type object using addAttribute() method.
    //Then this model is sent to index.html file to add a view.
    //The combination of model & view is then displayed on Home Page
}