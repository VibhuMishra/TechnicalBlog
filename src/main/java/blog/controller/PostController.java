package blog.controller;

import blog.model.Post;
import blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
//@Controller indicates that the class is a Controller class
//The Dispatcher Servlet scans the classes annotated with @Controller annotation to find the mapped methods.

public class PostController {

    //PostController class needs the object of PostService class & hence is dependent on PostService class.
    //So we want to inject the PostService object inside this class.So we need some service to inject the dependency,This service is known as dependency injection & is responsible for creating an object of PostService class & injecting the object in this class.
    //Autowired annotation says this class needs an object of PostService class, and link it here.
    @Autowired
    private PostService postService;



    //When Dispatcher Servlet scans the PostController class & when a user enters a URL '/posts', the Dispatcher Servlet looks for a method mapped with request mapping "/posts"
    //This annotation maps the url '/posts' with this getAllPosts() method
    @RequestMapping("posts")
    public String getAllPosts(Model model)
    {
        List<Post> list = postService.findAll();
        model.addAttribute("posts" , list);
        return "posts";
    }
    //This method receives a Model type object & adds a list of all the posts into Model type object.This method then returns the output given by posts.html.
    //When request for URL '/posts/create' is made,the Dispatcher Servlet maps that request to this method.
    //This method redirects to posts/create.html file.
    @RequestMapping("/posts/create")
    public String createPost(){
        return "posts/create";
    }

    //Pay attention
    //The createPost() method redirects to posts/create.html file containing a form.
    //When user fills the form & clicks on Submit button,the Post type object is instantiated & the details are stored in the object attributes.
    //After that this method will be called as indicated by @RequestMapping(value= "/create" ,method= RequestMethod.POST)
    //Note that this method is listening for a HTTP POST request as indicated by method= RequestMethod.POST
    @RequestMapping(value= "/create" ,method= RequestMethod.POST)
    public String createPostPage(Post post){
        post.setId(System.currentTimeMillis()%1000);
        postService.create(post);
        return "redirect:/posts";
    }
    //This method receives Post type object, adds an id to a post & then calls create() method for PostService type object to add this post to a file.
    //Then again redirects to '/posts'
}