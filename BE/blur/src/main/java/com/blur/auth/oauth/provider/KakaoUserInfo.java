package com.blur.auth.oauth.provider;

import java.util.Map;

public class KakaoMemberInfo implements OAuth2MemberInfo{

    private Map<String, Object> attributes;
    private Map<String, Object> attributesAccount;

    public KakaoMemberInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
        this.attributesAccount = (Map<String, Object>) attributes.get("kakao_account");
    }

    @Override
    public String getProviderId() {
        return attributes.get("id").toString();
    }

    @Override
    public String getEmail() {
        return (String) attributesAccount.get("email");
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

}

