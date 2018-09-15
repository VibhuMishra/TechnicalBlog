package blog.services;

import blog.common.Constants;
import blog.common.FileOperations;
import blog.common.PostsManager;
import blog.model.Post;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImp implements PostService{
    private PostsManager postsManager;

    public PostServiceImp() {
        postsManager= new PostsManager();
    }

    @Override
    //This method writes the post to a file
    public Post create(Post post) {
        postsManager.writeToFile(post);
        return post;
    }

    @Override
    //This method returns all the posts
    public List<Post> findAll() {
        return postsManager.readAllPosts();
    }

    @Override
    //This method returns only first three posts
    public List<Post> firstThreePosts() {
        return postsManager.getThreePosts();
    }

    @Override
    public Post findById(Long id) {
        return null;
    }

    @Override
    public Post editPost(Post post) {
        return null;
    }

    @Override
    //This method deletes a post
    public void deleteById(Post post) {
        postsManager.deletePost(post.getTitle());
    }
}