package com.example.damithajeanando.styleomega.activities.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.damithajeanando.styleomega.activities.database.dbHelper;
import com.example.damithajeanando.styleomega.R;
import com.idescout.sql.SqlScoutServer;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    dbHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        SqlScoutServer.create(this, getPackageName());

        final TextView registerLink = (TextView) findViewById(R.id.registerlink);
        final Button signIn = (Button) findViewById(R.id.signinBtn);
        db = new dbHelper(this);
        registerLink.setOnClickListener(this);
        signIn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){

        if(v.getId() == R.id.signinBtn) {
            final EditText eMail = (EditText) findViewById(R.id.username);
            final EditText passWord = (EditText) findViewById(R.id.password);

            String usernamestr = eMail.getText().toString();
            String passwordstr = passWord.getText().toString();


            if (!usernamestr.equals("") && !passwordstr.equals("")) {
                if (db.userCheck(usernamestr, passwordstr)) {
                    Intent intent = new Intent(LoginActivity.this, MainMenuActivity.class);
                    intent.putExtra("EMAIL", eMail.getText().toString().trim());
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Email or password is incorrect!", Toast.LENGTH_SHORT).show();
                    passWord.setText(null);
                }
            } else {
                Toast.makeText(getApplicationContext(), "Required field(s) empty!", Toast.LENGTH_SHORT).show();
            }
        }
                else if (v.getId() == R.id.registerlink){
                    Intent intentRegister = new Intent(LoginActivity.this, RegisterActivity.class);
                    LoginActivity.this.startActivity(intentRegister);
                }

            }
        }



