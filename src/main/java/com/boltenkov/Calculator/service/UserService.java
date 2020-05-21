package com.boltenkov.Calculator.service;

import com.boltenkov.Calculator.exception.AppException;
import com.boltenkov.Calculator.model.User;
import com.boltenkov.Calculator.repository.UserRepository;
import com.boltenkov.Calculator.util.ModelMapper;
import com.boltenkov.Calculator.view.Response.PagedResponse;
import com.boltenkov.Calculator.view.Response.UserSummaryResponse;
import com.boltenkov.Calculator.view.UserProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public PagedResponse<UserProfile> getUserProfileAll(){

        return new PagedResponse<>(ModelMapper.mapToListProfileResponses(userRepository.findAll()));
    }

    public UserProfile getUserProfileById(Long id){

        return ModelMapper.convertToUserProfile(userRepository.findById(id).orElse(new User()));
    }
    public void updateUser(UserProfile userProfile){
        User user  =userRepository.findById(userProfile.getId()).orElseThrow(() -> new AppException("User not find"));


        userRepository.save(ModelMapper.conversionToUser(userProfile,user));
    }


}
