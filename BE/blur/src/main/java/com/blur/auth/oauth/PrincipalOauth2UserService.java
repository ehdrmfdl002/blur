package com.blur.auth.oauth;

import com.blur.auth.PrincipalDetails;
import com.blur.auth.oauth.provider.GoogleUserInfo;
import com.blur.auth.oauth.provider.KakaoUserInfo;
import com.blur.auth.oauth.provider.NaverUserInfo;
import com.blur.auth.oauth.provider.OAuth2UserInfo;
import com.blur.entity.User;
import com.blur.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.Userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.Userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.User.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository UserRepository;

    //구글로부터 받은 UserRequest데이터에 대해 후처리되는함수
    @Override
    public OAuth2User loadUser(OAuth2UserRequest UserRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(UserRequest);
        OAuth2UserInfo oAuth2UserInfo = null;
        if(UserRequest.getClientRegistration().getRegistrationId().equals("google")) {
            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        }
        else if(UserRequest.getClientRegistration().getRegistrationId().equals("naver")) {
            oAuth2UserInfo = new NaverUserInfo(oAuth2User.getAttributes());
        }
        else if(UserRequest.getClientRegistration().getRegistrationId().equals("kakao")) {
            oAuth2UserInfo = new KakaoUserInfo(oAuth2User.getAttributes());
        }
        else {
            System.out.println("error");
        }

        String provider = oAuth2UserInfo.getProvider();
        String providerId = oAuth2UserInfo.getProviderId();
        String UserId = provider+"_"+providerId;
        String email = oAuth2UserInfo.getEmail();

//        User UserEntity = UserRepository.findByUserId(UserId);
//
//        if(UserEntity==null) {
//            UserEntity = User.builder()
//                    .UserId(UserId)
//                    .email(email)
//                    .build();
//            UserRepository.save(UserEntity);
//        }
//        return new PrincipalDetails(UserEntity, oAuth2User.getAttributes());
    }
}
