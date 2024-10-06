package com.socialmedia.postservice.controller;

import com.socialmedia.postservice.client.UserServiceClient;
import com.socialmedia.postservice.model.Post;
import com.socialmedia.postservice.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserServiceClient userServiceClient;

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping(path = "/{id}")
    public Optional<Post> getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Post>> getPostsByUserId(@PathVariable Long userId) {
        List<Post> posts = postService.getPostsByUsername(userId);
        return ResponseEntity.ok(posts);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }

    @PutMapping(path = "/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody Post postDetails) {
        return postService.updatePost(id, postDetails);
    }

    @DeleteMapping(path = "/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }
}
