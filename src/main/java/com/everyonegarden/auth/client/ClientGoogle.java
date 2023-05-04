package com.everyonegarden.auth.client;

import com.everyonegarden.auth.dto.GoogleUserResponse;
import com.everyonegarden.auth.exception.TokenValidFailedException;
import com.everyonegarden.user.entity.User;
import com.everyonegarden.user.enunerate.UserProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ClientGoogle implements ClientProxy{

    private final WebClient webClient;

    @Override
    public User getUserData(String accessToken) {

        GoogleUserResponse googleUserResponse = webClient.get()
                .uri("https://www.googleapis.com/oauth2/v2/userinfo")
                .header("AUTHORIZATION","Bearer"+accessToken)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> Mono.error(new TokenValidFailedException("Social Access Token is unauthorized")))
                .onStatus(HttpStatus::is5xxServerError, response -> Mono.error(new TokenValidFailedException("Internal Server Error")))
                .bodyToMono(GoogleUserResponse.class)
                .block();


        System.out.println("googleUserResponse:"+googleUserResponse);


        return User.builder()
                .socialId(googleUserResponse.getSub())
                .name(googleUserResponse.getFullname())
                .email(googleUserResponse.getEmail())
                .userProvider(UserProvider.GOOGLE)
                .roleType("ROLE_USER")
                .build();
    }
}
