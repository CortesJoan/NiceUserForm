package com.example.p18niceuserform;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterScreen extends AppCompatActivity {
    public static boolean hasError = false;
    Button registerButton;
    TextInputLayout layoutUsername;
    TextInputLayout layoutBirthDate;
    TextInputLayout layoutPassword;
    TextInputLayout layoutRepeatPassword;
    TextInputLayout layoutEmail;
    TextInputLayout layoutName;
    TextInputLayout layoutSurname;
    AutoCompleteTextView genderPronouns;
    MaterialCheckBox checkboxTerms;

    public static void checkIfError(TextInputLayout layout, boolean condition, String errorMessage) {
        if (checkIfLayoutTextIsEmpty(layout)) {
            layout.setError("required field");
            hasError = true;
        } else if (condition) {
            layout.setError(errorMessage);

        } else {
            hasError = false;

        }
    }

    public static boolean checkIfLayoutTextIsEmpty(TextInputLayout layout) {

        return layout.getEditText().getText().toString().isEmpty();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);
        layoutUsername = findViewById(R.id.usernameLayout);
        layoutPassword = findViewById(R.id.passwordLayout);
        layoutPassword = findViewById(R.id.passwordLayout);
        layoutBirthDate = findViewById(R.id.birthDateLayout);
        layoutEmail = findViewById(R.id.emailLayout);
        layoutRepeatPassword = findViewById(R.id.repeatPasswordLayout);
        layoutName = findViewById(R.id.nameLayout);
        genderPronouns = findViewById(R.id.autoComplete);
        layoutSurname = findViewById(R.id.surnameLayout);
        checkboxTerms = findViewById(R.id.checkboxConditions);
        registerButton = findViewById(R.id.registerButton);
        MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
        final MaterialDatePicker<Long> picker = builder.build();
        String[] genderPronouns = new String[]{"He , Him", "She , her", "They , them", "Combat helicopter"};
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(RegisterScreen.this, R.layout.item,
                        genderPronouns);
        this.genderPronouns.setAdapter(adapter);
        this.genderPronouns.setText(genderPronouns[0], false);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkIfError(layoutUsername, false, "");
                checkIfError(layoutName, false, "");
                checkIfError(layoutSurname, false, "");
                //checkIfError(genderPronouns,false,"");
                checkIfError(layoutPassword, layoutPassword.getEditText().getText().toString().length() < 8, "\"Password must have at least 8 characters\"");
                checkIfError(layoutEmail, layoutEmail.getEditText().getText().toString().contains("@"), "This is not a valid mail");
                checkIfError(layoutRepeatPassword, layoutPassword.getEditText().getText() != layoutRepeatPassword.getEditText().getText(), "Repeat password must  match with the password field");
                if (RegisterScreen.this.genderPronouns.getAdapter().isEmpty()) {
                    RegisterScreen.this.genderPronouns.setError("required field");
                    hasError = true;
                } else {
                    hasError = false;

                }
                if (!checkboxTerms.isChecked()) {
                    checkboxTerms.setError("You must agree the terms and conditions");
                    hasError = true;
                }

                if (!hasError) {
                    Intent intent = new Intent(RegisterScreen.this, WelcomeScreen.class);
                    startActivity(intent);
                }

            }
        });
        picker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
            @Override
            public void onPositiveButtonClick(Long selection) {
                layoutBirthDate.getEditText().setText(picker.getHeaderText());
            }
        });
        layoutBirthDate.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                picker.show(getSupportFragmentManager(), picker.toString());

            }

        });
    }


}