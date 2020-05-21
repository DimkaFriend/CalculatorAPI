package com.boltenkov.Calculator.util;

import com.boltenkov.Calculator.view.Request.SignUpRequest;
import com.boltenkov.Calculator.view.Response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    public static ActionStatus validationObjSignUpRequest(SignUpRequest signUpRequest){

        if(signUpRequest.getName().equals(null)|signUpRequest.getUsername().equals(null)|signUpRequest.getEmail().equals(null)|signUpRequest.getPassword().equals(null)){
            return new ActionStatus(false,"One or more query parameters are null");
        }

        if(signUpRequest.getName().equals("")|signUpRequest.getUsername().equals("")|signUpRequest.getEmail().equals("")|signUpRequest.getPassword().equals("")){
            return new ActionStatus(false,"One or more query parameters are empty");
        }

        String regex = "^[a-z0-9_-]{3,15}$";
        Pattern pattern = Pattern.compile(regex);
        if(!pattern.matcher(signUpRequest.getUsername()).matches()){
            return new ActionStatus(false,
                    "Username parameter is not valid because it does not satisfy one of the requirements:\n" +
                    "\t1)Start of the line\n" +
                    "\t2)Match characters and symbols in the list, a-z, 0-9, underscore, hyphen\n" +
                    "\t3)Length at least 3 characters and maximum length of 15\n" +
                    "\t4)End of the line");
        }

        regex = "^(.+)@(.+)$";
        pattern = Pattern.compile(regex);
        if(!pattern.matcher(signUpRequest.getEmail()).matches()){
            return new ActionStatus(false,"Email parameter does contain invalid characters");
        }

        regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*](?=\\S+$).{8,15}$";
        pattern = Pattern.compile(regex);
        if(!pattern.matcher(signUpRequest.getPassword()).matches()){
            return new ActionStatus(false,
                    "Password parameter is not valid because it does not satisfy one of the requirements:\n" +
                    "\t1) Password must contain at least 8 characters\n" +
                    "\t2) Password must contain at least 1 number\n" +
                    "\t3) Password must contain at least 1 upper case letter\n" +
                    "\t4) Password must contain at least 1 lower case letter\n" +
                    "\t5) Password must contain at least 1 special character\n" +
                    "\t6) Password must not contain any spaces");
        }

        return new ActionStatus(true,"User data meets validation criteria");
    }
}
