package com.example.damithajeanando.styleomega.activities.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.damithajeanando.styleomega.R;
import com.example.damithajeanando.styleomega.activities.model.User;
import com.example.damithajeanando.styleomega.activities.database.dbHelper;

/**
 * Created by Damitha Jeanando on 9/18/2017.
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText name;
    EditText email;
    EditText password;
    EditText confirmPassword;
    Button register;
    TextView loginLink;

    String namestr = "";
    String emailstr = "";
    String passwordstr = "";
    String confirmPasswordstr = "";

    dbHelper dbhelper;
    InputValidate validate;
    User u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        name = (EditText) findViewById(R.id.nametxt);
        email = (EditText) findViewById(R.id.emailtxt);
        password = (EditText) findViewById(R.id.passwordtxt);
        confirmPassword = (EditText) findViewById(R.id.confirmPasswordtxt);
        register = (Button) findViewById(R.id.registerbtn);
        loginLink = (TextView) findViewById(R.id.linkLogin);

        dbhelper = new dbHelper(this);
        u = new User();

       // validate = new InputValidate(this);

        register.setOnClickListener(this);
        loginLink.setOnClickListener(this);
    }

    public void clearFilledFields() {
        name.setText(null);
        email.setText(null);
        password.setText(null);
        confirmPassword.setText(null);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.registerbtn) {
            namestr = name.getText().toString().trim();
            emailstr = email.getText().toString().trim();
            passwordstr = password.getText().toString().trim();
            confirmPasswordstr = confirmPassword.getText().toString().trim();

            u.setName(namestr);
            u.setEmail(emailstr);
            u.setPassword(passwordstr);
            dbhelper.userReg(u);

            Toast.makeText(getApplicationContext(), "Successfully Registered!", Toast.LENGTH_LONG).show();

            Intent intentRegister = new Intent(this, LoginActivity.class);
            startActivity(intentRegister);

           /* if (!validate.nameFieldEmpty(namestr)) {
                if (!validate.emailFieldEmpty(emailstr) && !dbhelper.userCheck(emailstr)) {
                    if (!valid.passwordFieldEmpty(passwordstr)) {
                        if (passwordstr.equals(confirmPasswordstr)) {
                            u.setName(namestr);
                            u.setEmail(emailstr);
                            u.setPassword(passwordstr);

                            dbhelper.userReg(u);

                            Toast.makeText(getApplicationContext(), "Successfully Registered!", Toast.LENGTH_LONG).show();

                            Intent intentRegister = new Intent(this, LoginActivity.class);
                            startActivity(intentRegister);
                        } else {
                            Toast.makeText(RegisterActivity.this, "Password doesn't match", Toast.LENGTH_SHORT).show();
                            password.setText("");
                            confirmPassword.setText("");
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Password field is empty!", Toast.LENGTH_LONG).show();
                        password.setText("");
                        confirmPassword.setText("");
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Email field is empty or registered with this email already!", Toast.LENGTH_LONG).show();
                    password.setText("");
                    confirmPassword.setText("");
                    email.setText("");
                }
            } else {
                Toast.makeText(getApplicationContext(), "Name field is empty!", Toast.LENGTH_LONG).show();
                password.setText("");
                confirmPassword.setText("");
            } */
        } else if (v.getId() == R.id.linkLogin) {
            Intent intentLogin = new Intent(RegisterActivity.this, LoginActivity.class);
            RegisterActivity.this.startActivity(intentLogin);
        }
    }
}