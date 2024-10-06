package com.socialmedia.postservice.client;

import com.socialmedia.postservice.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "http://localhost:8081", configuration = FeignConfig.class)
public interface UserServiceClient {

    @GetMapping("/api/users/{userId}/exists")
    Boolean checkUserExists(@PathVariable Long userId);
}
