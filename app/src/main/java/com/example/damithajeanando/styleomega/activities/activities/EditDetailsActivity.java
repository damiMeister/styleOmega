package com.example.damithajeanando.styleomega.activities.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.damithajeanando.styleomega.R;
import com.example.damithajeanando.styleomega.activities.database.dbHelper;
import com.example.damithajeanando.styleomega.activities.validateHelper.InputValidate;

public class EditDetailsActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editNameTxt;
    EditText currentPassTxt;
    EditText newPassTxt;
    EditText confirmPassTxt;

    Button submitName;
    Button submitPassword;

    InputValidate validate;
    dbHelper db;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_details);

        editNameTxt = (EditText) findViewById(R.id.editname);
        currentPassTxt = (EditText) findViewById(R.id.currentpassword);
        newPassTxt = (EditText) findViewById(R.id.newpassword);
        confirmPassTxt = (EditText) findViewById(R.id.confirmpassword);
        submitName = (Button) findViewById(R.id.submitname);
        submitPassword = (Button) findViewById(R.id.submitpassword);

        validate = new InputValidate(this);
        db = new dbHelper(this);
        Intent in = getIntent();
        email = in.getStringExtra("EMAIL");
        submitName.setOnClickListener(this);
        submitPassword.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

    }
}
