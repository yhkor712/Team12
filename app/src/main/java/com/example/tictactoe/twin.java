package com.example.tictactoe;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class twin extends AppCompatActivity implements View.OnClickListener {

    ImageButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    int A, B, C, D, E, F, G, H;//胜利
    int a1, a2, a3, a4, a5, a6, a7, a8, a9;
    TextView tv_result;
    int cnt = 0;
    int x=0;
    ImageView image1,image2,image3,image4,image5,image6,image7,image8,image9;
    Button btn_res;
    String end = "";
    private MyData myData;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.twin);
        UI();
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);

        btn_res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG",".\n"+"\n ply1 : "+ pvp_result.com1_rlt+"\n ply2 : "+ pvp_result.com2_rlt+"\n draw : "+ pvp_result.drw_rlt+"\n total : "+(pvp_result.drw_rlt+ pvp_result.com1_rlt+ pvp_result.com2_rlt));
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });



    }
    @Override
    protected void onStart() {
        super.onStart();
        myData=new MyData(this);
        db=myData.getWritableDatabase();
    }

    private void UI() {
        btn1 = findViewById(R.id.img1);
        btn2 = findViewById(R.id.img2);
        btn3 = findViewById(R.id.img3);
        btn4 = findViewById(R.id.img4);
        btn5 = findViewById(R.id.img5);
        btn6 = findViewById(R.id.img6);
        btn7 = findViewById(R.id.img7);
        btn8 = findViewById(R.id.img8);
        btn9 = findViewById(R.id.img9);
        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        image4 = findViewById(R.id.image4);
        image5 = findViewById(R.id.image5);
        image6 = findViewById(R.id.image6);
        image7 = findViewById(R.id.image7);
        image8 = findViewById(R.id.image8);
        image9 = findViewById(R.id.image9);
        btn_res = findViewById(R.id.btn_res);


        tv_result = findViewById(R.id.tv_result);
    }

    private int aa() {
        int xo;
        cnt++;
        if (cnt % 2 == 0) {
            x = 1;
        } else {
            x = -1;
        }
        xo = x;
        return xo;
    }

    private void judge() {
        A = a3 + a6 + a9;
        B = a2 + a5 + a8;
        C = a1 + a4 + a7;
        D = a1 + a5 + a9;
        E = a1 + a2 + a3;
        F = a4 + a5 + a6;
        G = a7 + a8 + a9;
        H = a7 + a5 + a3;
        if (A == 3 || B == 3 || C == 3 || D == 3 || E == 3 || F == 3 || G == 3 || H == 3) {
            tv_result.setText("玩家1 獲勝");
            db.execSQL("INSERT INTO histore VALUES('"+"玩家1VS玩家2" +"','"+"玩家2勝" +"');");
            pvp_result.com1_rlt++;
            prgstop();
        } else if (A == -3 || B == -3 || C == -3 || D == -3 || E == -3 || F == -3 || G == -3 || H == -3) {
            tv_result.setText("玩家2 獲勝");
            db.execSQL("INSERT INTO histore VALUES('"+"玩家1VS玩家2" +"','"+"玩家1勝" +"');");

            pvp_result.com2_rlt++;
            prgstop();
        } else if ((a1 != 0 && a2 != 0 && a3 != 0 && a4 != 0 && a5 != 0 && a6 != 0 && a7 != 0 && a8 != 0 && a9 != 0)) {
            tv_result.setText("平手");
            db.execSQL("INSERT INTO histore VALUES('"+"玩家1VS電腦2" +"','"+"平手" +"');");

            pvp_result.drw_rlt++;
            prgstop();
        }
    }
    private void prgstop() {
        end = "end";
        A = B = C = D = E = F = G = H = 0;
        btn1.setVisibility(View.INVISIBLE);
        btn2.setVisibility(View.INVISIBLE);
        btn3.setVisibility(View.INVISIBLE);
        btn4.setVisibility(View.INVISIBLE);
        btn5.setVisibility(View.INVISIBLE);
        btn6.setVisibility(View.INVISIBLE);
        btn7.setVisibility(View.INVISIBLE);
        btn8.setVisibility(View.INVISIBLE);
        btn9.setVisibility(View.INVISIBLE);


    }

    public void img(ImageView imageView){
        if(x==1){
            imageView.setImageResource(R.drawable.ic_x);
        }else
            imageView.setImageResource(R.drawable.ic_o);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img1: {
                btn1.setVisibility(View.INVISIBLE);

                a1=aa();
                judge();
                img(image1);
                break;
            }
            case R.id.img2: {
                btn2.setVisibility(View.INVISIBLE);
                a2=aa();
                judge();
                img(image2);
                break;
            }
            case R.id.img3: {
                btn3.setVisibility(View.INVISIBLE);
                a3=aa();
                judge();
                img(image3);
                break;
            }
            case R.id.img4: {
                btn4.setVisibility(View.INVISIBLE);
                a4=aa();
                judge();
                img(image4);
                break;
            }
            case R.id.img5: {
                btn5.setVisibility(View.INVISIBLE);
                a5=aa();
                judge();
                img(image5);
                break;
            }
            case R.id.img6: {
                btn6.setVisibility(View.INVISIBLE);
                a6=aa();
                judge();
                img(image6);
                break;
            }
            case R.id.img7: {
                btn7.setVisibility(View.INVISIBLE);
                a7=aa();
                judge();
                img(image7);
                break;
            }
            case R.id.img8: {
                btn8.setVisibility(View.INVISIBLE);
                a8=aa();
                judge();
                img(image8);
                break;
            }
            case R.id.img9: {
                btn9.setVisibility(View.INVISIBLE);
                a9=aa();
                judge();
                img(image9);
                break;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }
}