package com.blur.api.controller;

import com.blur.api.dto.MemberProfileDto;
import com.blur.service.PasswordService;
import com.blur.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profile/{id}")
public class ProfileController {

    @Autowired
    PasswordService passwordService;

    @Autowired
    ProfileService profileService;

    @GetMapping
    public MemberProfileDto getProfile(@PathVariable("id") String memberId) {
    	MemberProfileDto memberProfileDto = profileService.getProfile(memberId);
        return memberProfileDto;
    }

    @PutMapping("/updateProfile") //비밀번호 변경
    public void updateProfile(@RequestBody MemberProfileDto memberProfileDto) throws Exception {
        profileService.updateProfile(memberProfileDto);
    }

    @PutMapping("/updatePassword") //비밀번호 변경
    public void updatePassword(@PathVariable("id") String userId, @RequestParam("newPassword") String newPassword) throws Exception {
        passwordService.updatePassword(userId, newPassword);
    }


}
