package com.example.sw221103;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth firebaseAuth;
    private Button buttonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
/*
        Button study_button = (Button) findViewById(R.id.Pen_button);
        study_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), study_activity.class);
                startActivity(intent);
            }
        });

        Button delivery_button = (Button) findViewById(R.id.Deli_button);
        delivery_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), delivery_activity.class);
                startActivity(intent);
            }
        });

        Button exercise_button = (Button) findViewById(R.id.Exer_button);
        exercise_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), exercise_activity.class);
                startActivity(intent);
            }
        });

        Button together_button = (Button) findViewById(R.id.Tog_button);
        together_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), together_activity.class);
                startActivity(intent);
            }
        });

        Button shopping_button = (Button) findViewById(R.id.Shop_button);
        shopping_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), shopping_activity.class);
                startActivity(intent);
            }
        });

        Button eat_button = (Button) findViewById(R.id.eat_button);
        eat_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), eat_activity.class);
                startActivity(intent);
            }
        });

        Button next_button = (Button) findViewById(R.id.next_button);
        next_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TodoActivity.class);
                startActivity(intent);
            }
        });

 */
        Button TODO = (Button) findViewById(R.id.todo);
        TODO.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TodoActivity.class);
                startActivity(intent);
            }
        });

        ImageView main = (ImageView) findViewById(R.id.imageView3);
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });


        Button butstudy = (Button) findViewById(R.id.butstudy);
        butstudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), study_activity2.class);
                startActivity(intent);
            }
        });

        Button buttonTest = (Button) findViewById(R.id.test);
        buttonTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, Test.class);
                startActivity(intent);
            }
        });

        Button board = (Button) findViewById(R.id.board);
        board.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, Test.class);
                startActivity(intent);
            }
        });


        Button buttonMy = (Button) findViewById(R.id.buttonMy);
        buttonMy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, MyActivity.class);
                startActivity(intent);
            }
        });


        buttonLogout = (Button) findViewById(R.id.buttonLogout);

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();

        buttonLogout.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        if (view == buttonLogout) {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}

