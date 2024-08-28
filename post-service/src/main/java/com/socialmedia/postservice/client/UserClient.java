package com.socialmedia.postservice.client;

import com.socialmedia.postservice.client.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "userservice", url = "http://localhost:8080/api/users")
public interface UserClient {

    @GetMapping("/{id}")
    UserResponse getUserById(@PathVariable("id") Long id);
}
