package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.BoringLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private EditText edtTxtName, edtTxtEmail, edtTxtPassword, edtTxtPassRepeat;
    private Button btnPickImagine, btnRegister;
    private TextView txtWarningName, txtWarningEmail, txtWarningPass, txtWarningPassRepeat;
    private Spinner countriesSpinner;
    private RadioGroup rgGender;
    private CheckBox agreementCheck;
    private ConstraintLayout parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        btnPickImagine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Toast.makeText(MainActivity.this,"To talk about",Toast.LENGTH_SHORT).show();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                initRegister();
            }
        });
    }
    private void initRegister (){
        Log.d(TAG, "initRegister: Started");
        if (validateDate()){
            if (agreementCheck.isChecked())
            {
                showSnackBar();
            }else{
                Toast.makeText(this,"You need to agree with the license",Toast.LENGTH_SHORT).show();
            }
        }
    }
    @SuppressLint("NonConstantResourceId")
    private void showSnackBar(){
        Log.d(TAG,"showSnackBar: Started");
        txtWarningName.setVisibility(View.GONE);
        txtWarningEmail.setVisibility(View.GONE);
        txtWarningPass.setVisibility(View.GONE);
        txtWarningPassRepeat.setVisibility(View.GONE);

        String name = edtTxtName.getText().toString();
        String email = edtTxtEmail.getText().toString();
        String country = countriesSpinner.getSelectedItem().toString();
        String gender = "";
        switch (rgGender.getCheckedRadioButtonId()){
            case R.id.rbMale:
                gender = "Male";
                break;
            case R.id.rbFemale:
                gender = "Female";
                break;
            case R.id.rbOther:
                gender = "Other";
                break;
            default:
                gender = "Unknow";
                break;
        }
        String snackText = "Name " + name + "\n" +
                "Email: " + email  + "\n" +
                "Gender: " + gender + "\n" +
                "Country: " + country;

        Snackbar.make(parent,snackText, Snackbar.LENGTH_INDEFINITE)
                .setAction("Dismiss", new View.OnClickListener(){
                    public void onClick(View v){
                edtTxtName.setText("");
                edtTxtEmail.setText("");
                edtTxtPassword.setText("");
                edtTxtPassRepeat.setText("");
            }
        }).show();
    }
    private boolean validateDate(){
        Log.d(TAG,"ValidataDate: started");
        if (edtTxtName.getText().toString().equals(""))
        {
            txtWarningName.setVisibility(View.VISIBLE);
            txtWarningName.setText("Enter your name");
            return false;
        }
        if (edtTxtEmail.getText().toString().equals(""))
        {
            txtWarningEmail.setVisibility(View.VISIBLE);
            txtWarningEmail.setText("Enter your Email");
            return false;
        }
        if (edtTxtPassword.getText().toString().equals(""))
        {
            txtWarningPass.setVisibility(View.VISIBLE);
            txtWarningPass.setText("Enter your Password");
            return false;
        }
        if (edtTxtPassRepeat.getText().toString().equals(""))
        {
            txtWarningPassRepeat.setVisibility(View.VISIBLE);
            txtWarningPassRepeat.setText("Re-enter your Password");
            return false;
        }
        if (!edtTxtPassword.getText().toString().equals(edtTxtPassRepeat.getText().toString()))
        {
            txtWarningPassRepeat.setVisibility(View.VISIBLE);
            txtWarningPassRepeat.setText("Password doesn't match");
            return false;
        }
        return true;
    }
    private void initViews(){
        Log.d(TAG,"initViews: Started");
        edtTxtName = findViewById(R.id.edtTxtName);
        edtTxtEmail = findViewById(R.id.edtTxtEmail);
        edtTxtPassword = findViewById(R.id.edtTxtPassword);
        edtTxtPassRepeat = findViewById(R.id.edtTxtPassRepeat);

        btnPickImagine = findViewById(R.id.btnPickImagine);
        btnRegister = findViewById(R.id.btnRegister);

        txtWarningName = findViewById(R.id.txtWarningName);
        txtWarningEmail = findViewById(R.id.txtWarningEmail);
        txtWarningPass = findViewById(R.id.txtWarningPass);
        txtWarningPassRepeat = findViewById(R.id.txtWarningPassRepeat);

        countriesSpinner = findViewById(R.id.spinnerCountry);
        rgGender = findViewById(R.id.rgGender);
        agreementCheck = findViewById(R.id.agreementCheck);
        parent = findViewById(R.id.parent);

    }
}