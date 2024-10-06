package com.socialmedia.postservice.service;

import com.socialmedia.postservice.client.UserServiceClient;
import com.socialmedia.postservice.model.Post;
import com.socialmedia.postservice.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserServiceClient userServiceClient;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    public List<Post> getPostsByUsername(Long userId) {
            return postRepository.findAllByUserId(userId);
    }

    public ResponseEntity<String> createPost(Post post) {
        Long userId = post.getUserId();
        Boolean userExists = userServiceClient.checkUserExists(userId);

        if (!userExists) {
            return ResponseEntity.status(404).body("User not found, cannot create post");
        } else {
            postRepository.save(post);
            return ResponseEntity.ok("Post created successfully");
        }
    }

    public Post updatePost(Long id, Post postDetails) {
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        post.setContent(postDetails.getContent());
        return postRepository.save(post);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
