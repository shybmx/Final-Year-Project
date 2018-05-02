package com.example.shahzademambaccus.finalyearprojectuser;

public class Encryption {

    public static final int SHIFT_NUMBER = 3;

    public Encryption(){

    }

    public String cipher(String password){
        String encryptedPassword = "";
        //get password length
        int stringLength = password.length();
        //loop for each value within pasword
        for(int i = 0; i < stringLength; i++){
            //get each character and shift by the shift number
            char c = (char) (password.charAt(i) + SHIFT_NUMBER);
            //if at the end go back to the start
            if(c > 'z'){
                encryptedPassword += (char) (password.charAt(i) - (26 - SHIFT_NUMBER));
            }else{
                encryptedPassword += (char) (password.charAt(i) + SHIFT_NUMBER);
            }
        }
        //get new password
        return encryptedPassword;
    }

}
