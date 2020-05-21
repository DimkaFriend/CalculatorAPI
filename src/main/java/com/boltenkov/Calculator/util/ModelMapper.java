package com.boltenkov.Calculator.util;

import com.boltenkov.Calculator.model.CalculatorModel;
import com.boltenkov.Calculator.model.User;
import com.boltenkov.Calculator.view.Response.CalculatorResponse;
import com.boltenkov.Calculator.view.Response.ExpressionMetricsResponse;
import com.boltenkov.Calculator.view.Response.UserSummaryResponse;
import com.boltenkov.Calculator.view.UserProfile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ModelMapper {
    public static CalculatorResponse map(CalculatorModel calculatorModel)
    {
        CalculatorResponse calculatorResponse= new CalculatorResponse();
        calculatorResponse.setId(calculatorModel.getId());
        calculatorResponse.setReadableExpression(calculatorModel.getReadableExpression());
        ExpressionMetricsResponse expressionMetricsResponse = new ExpressionMetricsResponse(calculatorModel.getExpressionMetrics());

        calculatorResponse.setExpressionMetrics(expressionMetricsResponse);
        return calculatorResponse;
    }

    public static List<CalculatorResponse> mapToListCalculatorResponses(List<CalculatorModel> list){
        List<CalculatorResponse> calculatorResponses = new ArrayList<>();
        list.stream().forEach(x -> {
            calculatorResponses.add(new CalculatorResponse(x.getId(),x.getReadableExpression(), new ExpressionMetricsResponse(x.getExpressionMetrics())));
            System.out.println(x.getExpressionMetrics());
        });

        return calculatorResponses;
    }

    public static List<UserProfile> mapToListProfileResponses(List<User> list){
        List<UserProfile> userProfiles = new ArrayList<>();
        list.stream().forEach(x -> userProfiles.add(new UserProfile(x.getId(),x.getUsername(),x.getName(),x.getCreatedAt())));
        return userProfiles;
    }

    public static UserProfile convertToUserProfile(User user){
        return new UserProfile(user.getId(), user.getUsername(), user.getName(), user.getCreatedAt());
    }

    public static User conversionToUser(UserProfile userProfile, User user){
        if(!user.getId().equals(userProfile.getId())){

        }
        user.setUsername(userProfile.getUsername());
        user.setName(userProfile.getName());
        return user;
    }
}
