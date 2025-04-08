package fr.tmsconsult.myproject.security.controller;

import fr.tmsconsult.myproject.topic.payload.request.LoginRequest;
import fr.tmsconsult.myproject.topic.payload.request.SignupRequest;
import fr.tmsconsult.myproject.topic.payload.request.UserCredentialUpdateRequest;
import fr.tmsconsult.myproject.topic.payload.response.JwtResponse;
import fr.tmsconsult.myproject.security.service.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final IUserService userService;
    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        JwtResponse jwtResponse;
        jwtResponse = userService.authenticate(loginRequest);
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
            userService.register(signUpRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("me")
    public ResponseEntity<?> getConnectedUserInformation() {
        JwtResponse jwtResponse = userService.getConnectedUserJwtResponse();
        logger.debug("jwtResponse:"+jwtResponse);
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserCredentialUpdateRequest userCredentialUpdateRequest) {
        userService.updateUser(userCredentialUpdateRequest);
        return ResponseEntity.ok().build();
    }
}