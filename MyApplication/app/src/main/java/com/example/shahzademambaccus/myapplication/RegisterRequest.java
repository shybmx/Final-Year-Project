package com.example.shahzademambaccus.myapplication;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

public class RegisterRequest extends StringRequest {

    private static final String REGISTER_REQUEST_URL = "https://lissome-amperage.000webhostapp.com/Register.php";
    private Map<String, String> databaseParam;

    public RegisterRequest(String eMail, String firstName, String lastName, String dateOfBirth,
                           int phoneNumber, Response.Listener<String> listener){
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        databaseParam.put("EMail", eMail);
        databaseParam.put("FirstName", firstName);
        databaseParam.put("LastName", lastName);
        databaseParam.put("DateOfBirth", dateOfBirth);
        databaseParam.put("PhoneNumber", phoneNumber + "");
    }

    public Map<String, String> getDatabaseParam() {
        return databaseParam;
    }
}
