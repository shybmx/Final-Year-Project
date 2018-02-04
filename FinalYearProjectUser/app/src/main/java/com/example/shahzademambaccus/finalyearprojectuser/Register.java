package com.example.shahzademambaccus.finalyearprojectuser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    private TextView title;
    private ImageView backButton;
    private TextView userName;
    private TextView password1;
    private TextView password2;
    private ImageDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setupGUI();
        setToolBar();
        database = new ImageDatabase();
    }

    public void setupGUI() {
        title = (TextView) findViewById(R.id.Tool_Bar_Text);
        backButton = (ImageView) findViewById(R.id.Tool_Bar_Back);
        userName = (TextView) findViewById(R.id.Register_Username);
        password1 = (TextView) findViewById(R.id.Register_Password);
        password2 = (TextView) findViewById(R.id.Register_Password_Re);

    }

    public void setToolBar() {
        title.setText("Register");
        backButton.setImageResource(R.drawable.back);
    }

    public void registerButtonPressed(View v){
        String user = userName.getText().toString();
        String p1 = password1.getText().toString();
        if(checkFields()){
           database.register(user, p1, this);
        }
    }

    public boolean checkFields(){
        String user = userName.getText().toString();
        String p1 = password1.getText().toString();
        String p2 = password2.getText().toString();
        if(user.isEmpty() || p1.isEmpty() || p2.isEmpty()){
            Toast.makeText(this, "Fill in boxes", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            if(!p1.equals(p2)){
                Toast.makeText(this, "Both passwords must be the same", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }
}
