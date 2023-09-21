/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viamatica.prueba.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Anderson Macias
 */
public class EmailGenerator {

    private final String domain = "@mail.com";

    public String generate(String fullName) {

        String result = generateName(fullName) + domain;

        return result;

    }

    public String generate(String name, String lastName) {

        return name.charAt(0) + lastName.split(lastName)[0] + domain;

    }
    
    public String generateFromEmail(String email){
        
        String[] parts = email.split("@");
        int number = 1;
        Pattern pattern = Pattern.compile("[1-9]$");

        Matcher matcher = pattern.matcher((CharSequence) parts[0] );

        
        if(matcher.find() != true) {
            return parts[0] + number+ "@" +parts[1];
        }
       
        int start = matcher.start();

        String str_number = parts[0].substring(start);

        number = (Integer.parseInt(str_number))+1;

        return parts[0].substring(0, start)+number+"@"+parts[1];
    }


    public String generateName(String fullName){
    String[] parts = fullName.toLowerCase().split(" ");

        String result;

        if (parts.length >= 3) {
            result = parts[0].charAt(0) + parts[2];
        } else if (parts.length == 2) {

            result = parts[0].charAt(0) + parts[1];

        } else {

            result = parts[0];

        }

        return result;
    }

}

