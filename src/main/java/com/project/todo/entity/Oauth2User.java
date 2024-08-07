package com.project.todo.entity;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import java.util.Collection;
import java.util.Map;

@Getter
public class Oauth2User extends DefaultOAuth2User {

    private String email;
    private String provider;
    private String providerId;

    /**
     * Constructs a {@code DefaultOAuth2User} using the provided parameters.
     *
     * @param authorities      the authorities granted to the user
     * @param attributes       the attributes about the user
     * @param nameAttributeKey the key used to access the user's &quot;name&quot; from
     *                         {@link #getAttributes()}
     */
    public Oauth2User(Collection<? extends GrantedAuthority> authorities, Map<String, Object> attributes, String nameAttributeKey,
                      String email, String provider, String providerId) {
        super(authorities, attributes, nameAttributeKey);
        this.email = email;
        this.provider = provider;
        this.providerId = providerId;
    }
}
