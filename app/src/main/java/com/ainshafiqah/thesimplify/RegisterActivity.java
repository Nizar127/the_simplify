package com.ainshafiqah.thesimplify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static androidx.constraintlayout.motion.widget.MotionScene.TAG;

public class RegisterActivity extends AppCompatActivity {

    String TAG;
    String userId, editPhone, editEmail, editPassword, editCompanyName, editFullName, productRandomKey, saveCurrentDate,saveCurrentTime;
    EditText companyName, fullName, email, phoneNum, password;
    Button btnReg;
    TextView signin;
    FirebaseAuth fAuth;
    DatabaseReference mBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        companyName = findViewById(R.id.editCompanyName);
        fullName    = findViewById(R.id.editFullName);
        email       = findViewById(R.id.editEmail);
        phoneNum    = findViewById(R.id.editPhone);
        password    = findViewById(R.id.editPassword);
        btnReg      = findViewById(R.id.buttonReg);
        signin      = findViewById(R.id.signin);

        fAuth       = FirebaseAuth.getInstance();
        mBase       = FirebaseDatabase.getInstance().getReference("Users");
        userId      = fAuth.getCurrentUser().getUid();
        Log.d(TAG, "UserID:" + userId);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignUp();
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void SignUp() {
        editCompanyName = companyName.getText().toString();
        editFullName    = fullName.getText().toString();
        editPhone       = phoneNum.getText().toString();
        editEmail       = email.getText().toString().trim();
        editPassword    = password.getText().toString().trim();


        if (TextUtils.isEmpty(editCompanyName)) {
            Toast.makeText(this, "Company Name is required", Toast.LENGTH_SHORT).show();
        }

        if (TextUtils.isEmpty(editFullName)) {
            Toast.makeText(this, "Username is required", Toast.LENGTH_SHORT).show();

        }

        if (TextUtils.isEmpty(editEmail)) {
            Toast.makeText(this, "Email is required", Toast.LENGTH_SHORT).show();

        }

        if (TextUtils.isEmpty(editPhone)) {
            Toast.makeText(this, "Phone is required", Toast.LENGTH_SHORT).show();

        }

        if (TextUtils.isEmpty(editPassword)) {
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();

        }

        if (editPassword.length() < 7) {
            Toast.makeText(this,"Password need to be longer", Toast.LENGTH_SHORT).show();

        }else{
            storeData();
        }
    }

    private void storeData() {
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        //To create a unique product random key, so that it doesn't overwrite other product
        productRandomKey = saveCurrentDate + saveCurrentTime;

        HashMap<String, Object> userMap = new HashMap<>();
        userMap.put("name",editFullName);
        userMap.put("companyName", editCompanyName);
        userMap.put("phoneNum", editPhone);
        userMap.put("email", editEmail);

        fAuth.createUserWithEmailAndPassword(editEmail,editPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    mBase.child(userId).updateChildren(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                            Toast.makeText(RegisterActivity.this,"User is added", Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{
                    String message = task.getException().toString();
                    Toast.makeText(RegisterActivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}