package com.example.shahzademambaccus.finalyearprojectuser;

public class Encryption {

    public static final int SHIFT_NUMBER = 3;

    public Encryption(){

    }

    public String cipher(String password){
        String encryptedPassword = "";
        int stringLength = password.length();
        for(int i = 0; i < stringLength; i++){
            char c = (char) (password.charAt(i) + SHIFT_NUMBER);
            if(c > 'z'){
                encryptedPassword += (char) (password.charAt(i) - (26 - SHIFT_NUMBER));
            }else{
                encryptedPassword += (char) (password.charAt(i) + SHIFT_NUMBER);
            }
        }
        return encryptedPassword;
    }

}
