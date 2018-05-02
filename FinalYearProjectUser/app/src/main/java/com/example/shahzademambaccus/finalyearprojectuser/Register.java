package com.example.shahzademambaccus.finalyearprojectuser;

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
    private DatabaseConnection database;
    private ImageView logout;
    private Register register;
    private static final String TITLE_LABEL = "Register";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //database connection
        database = new DatabaseConnection();
        //class as a variable
        register = this;
        setupGUI();
        setToolBar();
    }

    public void setupGUI() {
        title = (TextView) findViewById(R.id.Tool_Bar_Text);
        backButton = (ImageView) findViewById(R.id.Tool_Bar_Back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register.finish();
            }
        });
        userName = (TextView) findViewById(R.id.Register_Username);
        password1 = (TextView) findViewById(R.id.Register_Password);
        password2 = (TextView) findViewById(R.id.Register_Password_Re);
        logout = (ImageView) findViewById(R.id.Tool_Bar_Logout);
    }

    public void setToolBar() {
        title.setText(TITLE_LABEL);
        backButton.setImageResource(R.drawable.back);
        logout.setImageResource(0);
    }
    //get values from text bar to send to database
    public void registerButtonPressed(View v){
        String user = userName.getText().toString();
        String p1 = password1.getText().toString();
        //encrypt password
        Encryption encryption = new Encryption();
        String encryptedPassword = encryption.cipher(p1);
        if(checkFields()){
           database.register(user, encryptedPassword, this);
        }
    }

    public boolean checkFields(){
        String user = userName.getText().toString();
        String p1 = password1.getText().toString();
        String p2 = password2.getText().toString();
        //check if fields are empty
        if(user.isEmpty() || p1.isEmpty() || p2.isEmpty()){
            Toast.makeText(this, "Fill in boxes", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            //checks if password is the same
            if(!p1.equals(p2)){
                Toast.makeText(this, "Both passwords must be the same", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }
    //ends this activity
    public void finishActivity(){
        this.finish();
    }
}
