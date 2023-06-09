package com.example.sw221103;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.util.Log;

public class JoinActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabaseReference;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextName;
    private Button buttonJoin;
    //private Button buttonJoine;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join);

        firebaseAuth = FirebaseAuth.getInstance();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("FirebaseEmailAccount");

        editTextEmail = (EditText) findViewById(R.id.edittext_email);
        editTextPassword = (EditText) findViewById(R.id.edittext_password);
        editTextName = (EditText) findViewById(R.id.edittext_name);

        buttonJoin = (Button) findViewById(R.id.btn_join);
        buttonJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editTextEmail.getText().toString().equals("") && !editTextPassword.getText().toString().equals("")) {
                    // 이메일과 비밀번호가 공백이 아닌 경우
                    createUser(editTextEmail.getText().toString(), editTextPassword.getText().toString(), editTextName.getText().toString());
                } else {
                    // 이메일과 비밀번호가 공백인 경우
                    Toast.makeText(JoinActivity.this, "계정과 비밀번호를 입력하세요.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void createUser(String email, String password, String name) {
        String strEmail = editTextEmail.getText().toString();
        String strPwd = editTextPassword.getText().toString();
        String strName = editTextName.getText().toString();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // 회원가입 성공시
                            Toast.makeText(JoinActivity.this, "회원가입 성공", Toast.LENGTH_SHORT).show();
                            finish();

                            FirebaseUser user = firebaseAuth.getCurrentUser();//해당기기의 언어 설정
                            User account = new User();
                            account.setEmail(strEmail);
                            account.setPassword(strPwd);
                            account.setName(strName);
                            account.setIdToken(user.getUid());

                            //database에 저장
                            mDatabaseReference.child("User").child(user.getUid()).setValue(account);

                            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {                         //해당 이메일에 확인메일을 보냄
                                        Log.d(TAG, "Email sent.");
                                        Toast.makeText(JoinActivity.this,
                                                "Verification email sent to " + user.getEmail(),
                                                Toast.LENGTH_SHORT).show();
                                    } else {                                             //메일 보내기 실패
                                        Log.e(TAG, "sendEmailVerification", task.getException());
                                        Toast.makeText(JoinActivity.this,
                                                "Failed to send verification email.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        } else {
                            // 계정이 중복된 경우
                            Toast.makeText(JoinActivity.this, "이미 존재하는 계정입니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}