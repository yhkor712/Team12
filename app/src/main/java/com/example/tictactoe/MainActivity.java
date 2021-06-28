package com.example.tictactoe;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    ImageView imageView1;
    Button btn,btn2,btn301,btn302,btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        imageView1=findViewById(R.id.imageview1);
        imageView1.setAlpha(0.5f);
        btn=findViewById(R.id.btn);
        btn.setOnClickListener(b1);
        btn2=findViewById(R.id.btn2);
        btn2.setOnClickListener(b2);
        btn301=findViewById(R.id.btn301);
        btn301.setOnClickListener(b301);
        btn302=findViewById(R.id.btn302);
        btn302.setOnClickListener(b302);
        btn4=findViewById(R.id.btn4);
        btn4.setOnClickListener(b4);
    }
    private View.OnClickListener b1=new View.OnClickListener(){
        public void onClick(View v){
            Intent intent1=new Intent();
            intent1.setClass(MainActivity.this,single.class);
            startActivity(intent1);
        }
    };
    private View.OnClickListener b2=new View.OnClickListener(){
        public void onClick(View v){
            Intent intent2=new Intent();
            intent2.setClass(MainActivity.this,twin.class);
            startActivity(intent2);
        }
    };
    private View.OnClickListener b301=new View.OnClickListener(){
        public void onClick(View v){
            Intent intent301=new Intent();
            intent301.setClass(MainActivity.this,Severpvp.class);
            startActivity(intent301);
        }
    };
    private View.OnClickListener b302=new View.OnClickListener(){
        public void onClick(View v){
            Intent intent302=new Intent();
            intent302.setClass(MainActivity.this,Clientpvp.class);
            startActivity(intent302);
        }
    };
    private View.OnClickListener b4=new View.OnClickListener(){
        public void onClick(View v){
            Intent intent4=new Intent();
            intent4.setClass(MainActivity.this,record.class);
            startActivity(intent4);
        }
    };
}