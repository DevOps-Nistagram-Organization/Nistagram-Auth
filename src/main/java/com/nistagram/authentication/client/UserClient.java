package com.nistagram.authentication.client;

import com.nistagram.authentication.model.dto.UserInfoDTO;
import com.nistagram.authentication.model.dto.UserInfoRegistrationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(url = "${api.userService}", name = "UserService")
public interface UserClient {

    @PostMapping(value = "saveUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserInfoDTO> saveUser(@RequestBody() UserInfoRegistrationDTO dto, @RequestHeader("Authorization") String bearerToken);
}
