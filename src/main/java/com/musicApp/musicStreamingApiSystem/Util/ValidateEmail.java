package com.musicApp.musicStreamingApiSystem.Util;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateEmail {

    public static final String email_Regex="^.+@admin\\.com$";
    public static boolean isValid(String email){
        Pattern pattern=  Pattern.compile(email_Regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
