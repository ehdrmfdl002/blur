package com.blur.auth.oauth.provider;

public interface OAuth2MemberInfo {
    String getProviderId();
    String getProvider();
    String getEmail();
}
