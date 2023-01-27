package com.blur.auth.oauth;

import com.blur.auth.PrincipalDetails;
import com.blur.auth.oauth.provider.GoogleMemberInfo;
import com.blur.auth.oauth.provider.KakaoMemberInfo;
import com.blur.auth.oauth.provider.NaverMemberInfo;
import com.blur.auth.oauth.provider.OAuth2MemberInfo;
import com.blur.entity.Member;
import com.blur.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.Memberinfo.DefaultOAuth2MemberService;
import org.springframework.security.oauth2.client.Memberinfo.OAuth2MemberRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.Member.OAuth2Member;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PrincipalOauth2MemberService extends DefaultOAuth2MemberService {

    @Autowired
    private MemberRepository MemberRepository;

    //구글로부터 받은 MemberRequest데이터에 대해 후처리되는함수
    @Override
    public OAuth2Member loadMember(OAuth2MemberRequest MemberRequest) throws OAuth2AuthenticationException {

        OAuth2Member oAuth2Member = super.loadMember(MemberRequest);
        OAuth2MemberInfo oAuth2MemberInfo = null;
        if(MemberRequest.getClientRegistration().getRegistrationId().equals("google")) {
            oAuth2MemberInfo = new GoogleMemberInfo(oAuth2Member.getAttributes());
        }
        else if(MemberRequest.getClientRegistration().getRegistrationId().equals("naver")) {
            oAuth2MemberInfo = new NaverMemberInfo(oAuth2Member.getAttributes());
        }
        else if(MemberRequest.getClientRegistration().getRegistrationId().equals("kakao")) {
            oAuth2MemberInfo = new KakaoMemberInfo(oAuth2Member.getAttributes());
        }
        else {
            System.out.println("error");
        }

        String provider = oAuth2MemberInfo.getProvider();
        String providerId = oAuth2MemberInfo.getProviderId();
        String MemberId = provider+"_"+providerId;
        String email = oAuth2MemberInfo.getEmail();

//        Member MemberEntity = MemberRepository.findByMemberId(MemberId);
//
//        if(MemberEntity==null) {
//            MemberEntity = Member.builder()
//                    .MemberId(MemberId)
//                    .email(email)
//                    .build();
//            MemberRepository.save(MemberEntity);
//        }
//        return new PrincipalDetails(MemberEntity, oAuth2Member.getAttributes());
    }
}
