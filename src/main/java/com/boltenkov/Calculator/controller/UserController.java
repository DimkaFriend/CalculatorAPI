package com.boltenkov.Calculator.controller;

import com.boltenkov.Calculator.exception.ResourceNotFoundException;
import com.boltenkov.Calculator.model.User;
import com.boltenkov.Calculator.repository.UserRepository;
import com.boltenkov.Calculator.security.CurrentUser;
import com.boltenkov.Calculator.security.UserPrincipal;
import com.boltenkov.Calculator.service.UserService;
import com.boltenkov.Calculator.view.Response.ApiResponse;
import com.boltenkov.Calculator.view.Response.PagedResponse;
import com.boltenkov.Calculator.view.Response.UserSummaryResponse;
import com.boltenkov.Calculator.view.UserIdentityAvailability;
import com.boltenkov.Calculator.view.UserProfile;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public UserSummaryResponse getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        UserSummaryResponse userSummary = new UserSummaryResponse(currentUser.getId(), currentUser.getUsername(), currentUser.getName(),currentUser.getEmail());
        return userSummary;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/user/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
        Boolean isAvailable = !userRepository.existsByUsername(username);
        return new UserIdentityAvailability(isAvailable);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/user/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        Boolean isAvailable = !userRepository.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/users/{username}")
    @PreAuthorize("hasRole('USER')")
    public UserProfile getUserProfile(@PathVariable(value = "username") String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));


        UserProfile userProfile = new UserProfile(user.getId(), user.getUsername(), user.getName(), user.getCreatedAt());

        return userProfile;
    }

    @GetMapping("/users/all")
    public PagedResponse<UserProfile> getAllUserProfile(){

        return userService.getUserProfileAll();
    }

    /**
      The code of this method does not claim any evaluation, since it is written for informational purposes.
      There are a sufficient number of examples on the Internet of processing the HTTP PUT method when getting the described representation of an object.
      There are very few examples on the Internet of how to do this if the object was sent not under a contract, but only part of it or with excess parameters.
      In this method, this is achieved through the Java  Reflection API.
      */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateUserProfile(@PathVariable(value = "id") long id, @RequestBody String s) throws IOException {
        List<String> erroneousParameters = new ArrayList<>();
        UserProfile userProfile = userService.getUserProfileById(id);

        if(userProfile.getId()==null)
            return new ResponseEntity<>(new ApiResponse(false,"The user with this ID was not found"),
                    HttpStatus.BAD_REQUEST);

        List<Field> fieldList = Arrays.asList(UserProfile.class.getDeclaredFields());

        List<String> filds=  fieldList.stream()
                                        .map(x -> x.toString()
                                                .split(userProfile
                                                        .getClass()
                                                        .getName()+".")[1])
                                        .collect(Collectors.toList());

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jNode = objectMapper.readTree(s);
        Iterator<Map.Entry<String, JsonNode>> it = jNode.fields();

        it.forEachRemaining(x ->{
            if (filds.contains(x.getKey())){

                try {

                    Field field = userProfile
                                        .getClass()
                                        .getDeclaredField(x.getKey());

                    field.setAccessible(true);

                    field.set(userProfile,x.getValue()
                                            .toString()
                                                .split("\"")[1]);
                    field.setAccessible(false);

                } catch (NoSuchFieldException | IllegalAccessException  e) {
                    e.printStackTrace();
                }
            }else {
                erroneousParameters.add(x.getKey());
            }
        });

        userService.updateUser(userProfile);

        if(!erroneousParameters.isEmpty()){
            return new ResponseEntity<>(new ApiResponse(true,"User data was changed.\n The server did not find part of the passed parameters in the request for the object\n"
                    + erroneousParameters.toString()),
                    HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<>(new ApiResponse(true,"User data was changed"),
                HttpStatus.ACCEPTED);
    }
}