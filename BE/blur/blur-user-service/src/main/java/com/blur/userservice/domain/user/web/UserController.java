package com.blur.userservice.domain.user.web;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.blur.userservice.domain.user.dto.UserDto;
import com.blur.userservice.domain.user.service.UserService;
import com.blur.userservice.global.dto.Result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/User")
    public ResponseEntity getUserByToken(@Valid @RequestHeader(value="user-id") String userId) {

        UserDto UserDto = userService.findUserByUserId(Integer.parseInt(userId));

        GetUserByTokenResponse getUserByTokenResponse = new GetUserByTokenResponse(UserDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(Result.createSuccessResult(getUserByTokenResponse));
    }

    @Data @NoArgsConstructor @AllArgsConstructor
    static class GetUserByTokenResponse {
    	private int userNo;
        private String userId;
        private String email;

        public GetUserByTokenResponse(UserDto UserDto) {
        	this.userNo = UserDto.getUserNo();
            this.userId = UserDto.getUserId();
            this.email = UserDto.getEmail();
        }
    }


//    @GetMapping("/User/{userId}")
//    public ResponseEntity getUser(@PathVariable("userId") Long userId) {
//
//        UserDto UserDto = userService.findUserByUserId(userId);
//
//        GetUserResponse getUserResponse = new GetUserResponse(UserDto);
//
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(Result.createSuccessResult(getUserResponse));
//    }
//
//    @GetMapping("/Users/{userIds}")
//    public ResponseEntity getUsers(@PathVariable List<Long> userIds) {
//
//        List<UserDto> Users = userService.findUserDtoByUserIds(userIds);
//
//        List<GetUserResponse> responses = Users
//                .stream()
//                .map(GetUserResponse::new)
//                .collect(Collectors.toList());
//
//        return ResponseEntity.ok(Result.createSuccessResult(responses));
//    }
//
//    @Data @NoArgsConstructor @AllArgsConstructor
//    static class GetUserResponse {
//        private Long userNo;
//        private String userName;
//        private String phoneNumber;
//
//        public GetUserResponse(UserDto UserDto) {
//            this.userId = UserDto.getUserId();
//            this.userName = UserDto.getName();
//            this.phoneNumber = UserDto.getPhoneNumber();
//        }
//    }

//    @GetMapping("/store-owner")
//    public ResponseEntity<Result> getStoreOwnerByToken(@RequestHeader(value="user-id") String userHeader) {
//        Long userId = Long.valueOf(userHeader);
//
//        StoreOwnerDto storeOwnerDto = userService.findOwnerById(userId);
//
//        return ResponseEntity.ok(Result.createSuccessResult(storeOwnerDto));
//    }
//
//    @Data
//    static class StoreOwnerByTokenResponse {
//        private Long id;
//        private String name;
//
//        public StoreOwnerByTokenResponse(StoreOwnerDto dto) {
//            this.id = dto.getId();
//            this.name = dto.getName();
//        }
//    }
}
