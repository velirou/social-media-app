package com.socialmedia.postservice.repository;

import com.socialmedia.postservice.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
