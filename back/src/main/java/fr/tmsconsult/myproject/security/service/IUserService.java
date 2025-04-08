package fr.tmsconsult.myproject.security.service;

import fr.tmsconsult.myproject.security.model.User;
import fr.tmsconsult.myproject.topic.payload.request.LoginRequest;
import fr.tmsconsult.myproject.topic.payload.request.SignupRequest;
import fr.tmsconsult.myproject.topic.payload.request.UserCredentialUpdateRequest;
import fr.tmsconsult.myproject.topic.payload.response.JwtResponse;

public interface IUserService  {
    public void register(SignupRequest signupRequest);
    public JwtResponse authenticate(LoginRequest loginRequest);
    public JwtResponse getConnectedUserJwtResponse();
    public User findUserByIdentifier(String identifier);
    public User getConnectedUser();
    public void updateUser(UserCredentialUpdateRequest userCredentialUpdateRequest);


}
