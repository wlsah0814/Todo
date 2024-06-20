package com.project.todo.service.oauth;

import com.project.todo.config.security.PrincipalDetails;
import com.project.todo.dto.OAuth2UserInfo;
import com.project.todo.entity.User;
import com.project.todo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class Oauth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        Map<String, Object> attribute = super.loadUser(userRequest).getAttributes();

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();

        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfo.of(registrationId, attribute);

        User user = checkUserOrSave(oAuth2UserInfo);

        return new PrincipalDetails(user, attribute, userNameAttributeName);
    }

    private User checkUserOrSave(OAuth2UserInfo oAuth2UserInfo) {
        User user = userRepository.findByEmail(oAuth2UserInfo.email());
        if(user == null) {
            return userRepository.save(
                    User.builder()
                            .username(oAuth2UserInfo.name())
                            .password(passwordEncoder.encode("1234"))
                            .email(oAuth2UserInfo.email())
                            .role("ROLE_USER")
                            .provider(oAuth2UserInfo.provider())
                            .providerId(oAuth2UserInfo.providerId())
                            .build()
            );
        }
        return user;
    }
}
