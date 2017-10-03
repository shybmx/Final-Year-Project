package com.example.shahzademambaccus.finalyearprojectuser;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest{
    private static final String REGISTER_REQUEST_URL = "https://lissome-amperage.000webhostapp.com/Register.php";
    private Map<String, String> params;

    public RegisterRequest(String eMail, String firstName, String lastName, String dateOfBirth, int phoneNumber, String passWord, Response.Listener<String> listener){
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("email", eMail);
        params.put("firstname", firstName);
        params.put("lastname", lastName);
        params.put("dateofbirth", dateOfBirth);
        params.put("phonenumber", phoneNumber+"");
        params.put("pass", passWord);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}