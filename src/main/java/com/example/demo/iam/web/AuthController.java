package com.example.demo.iam.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class AuthController {

    //    private final JwtEncoder encoder;
//    private final AuthenticationManager authenticationManager;

    @GetMapping("/v1/auth/profile")
    Authentication profile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("auth name : {}", authentication.getName());
        log.info("auth authorities : {}", authentication.getAuthorities());
        log.info("auth credentials : {}", authentication.getCredentials());
        log.info("auth details : {}", authentication.getDetails());
        log.info("auth principal : {}", authentication.getPrincipal());
//        Jwt principal = (Jwt) authentication.getCredentials();
//        String sessionId = principal.getClaim("sid");
//        log.info("external user id : {}", principal.getSubject());
//        log.info("session id : {}", sessionId);
        return authentication;
    }

    //    @PostMapping("/token")
//    public Object token(@RequestBody LoginDto loginDto) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
//        );
//        Instant now = Instant.now();
//        long expiry = 36000L;
//        // @formatter:off
//        String roles = authentication.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.joining(" "));
//        JwtClaimsSet claims = JwtClaimsSet.builder()
////                .issuer("self")
//                .issuedAt(now)
//                .id("example id")
//                .expiresAt(now.plusSeconds(expiry))
//                .subject(authentication.getName())
//                .claim("scope", roles)
//                .build();
//        // @formatter:on
//        String accessToken = encoder.encode(JwtEncoderParameters.from(
////                JwsHeader.with(MacAlgorithm.HS512).build(),
//                claims
//        )).getTokenValue();
//        return Map.of("accessToken", accessToken);
//    }

}
