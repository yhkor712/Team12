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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class single extends AppCompatActivity implements View.OnClickListener {

    ImageButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    ImageView image1, image2, image3, image4, image5, image6, image7, image8, image9;
    int a1, a2, a3, a4, a5, a6, a7, a8, a9 = 0;
    TextView tv_result;
    int A, B, C, D, E, F, G, H;//胜利
    int i;
    int lck1, lck2, lck3, lck4, lck5, lck6, lck7, lck8, lck9 = 0;
    List<String> list = new ArrayList<String>();
    public final String tag = "TAG";
    String end = "";
    private MyData myData;
    private SQLiteDatabase db;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single);
        UI();
        btn = findViewById(R.id.button);
        listshuffle();
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(tag,".\n"+"\n ply : "+ pvc_result.ply_rlt+"\n com : "+ pvc_result.com_rlt+"\n draw : "+ pvc_result.drw_rlt+"\n total : "+(pvc_result.drw_rlt+ pvc_result.com_rlt+ pvc_result.ply_rlt));
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

    private void play(ImageButton btn, ImageView image, int lck) {
        if (lck == 1) {
            btn.setVisibility(View.INVISIBLE);
            image.setImageResource(R.drawable.ic_x);

        }
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


        tv_result = findViewById(R.id.tv_result);
    }

    private void com() {
        if (end != "end") {
            i = 0;
            while (i < 9) {
                if (!list.get(i).equals("0")) {
                    int com = Integer.parseInt(list.get(i));
                    num(com);
                    list.set(i, "0");
                    break;
                }
                i++;
            }
            judge();
        }
    }

    private void num(int i) {
        if (i == 1) {
            btn1.setVisibility(View.INVISIBLE);
            image1.setImageResource(R.drawable.ic_o);
            a1 = -1;
        } else if (i == 2) {
            btn2.setVisibility(View.INVISIBLE);
            image2.setImageResource(R.drawable.ic_o);
            a2 = -1;
        } else if (i == 3) {
            btn3.setVisibility(View.INVISIBLE);
            image3.setImageResource(R.drawable.ic_o);
            a3 = -1;
        } else if (i == 4) {
            btn4.setVisibility(View.INVISIBLE);
            image4.setImageResource(R.drawable.ic_o);
            a4 = -1;
        } else if (i == 5) {
            btn5.setVisibility(View.INVISIBLE);
            image5.setImageResource(R.drawable.ic_o);
            a5 = -1;
        } else if (i == 6) {
            btn6.setVisibility(View.INVISIBLE);
            image6.setImageResource(R.drawable.ic_o);
            a6 = -1;
        } else if (i == 7) {
            btn7.setVisibility(View.INVISIBLE);
            image7.setImageResource(R.drawable.ic_o);
            a7 = -1;
        } else if (i == 8) {
            btn8.setVisibility(View.INVISIBLE);
            image8.setImageResource(R.drawable.ic_o);
            a8 = -1;
        } else if (i == 9) {
            btn9.setVisibility(View.INVISIBLE);
            image9.setImageResource(R.drawable.ic_o);
            a9 = -1;
        }
    }

    private void listshuffle() {
        for (i = 1; i < 10; i++) {
            list.add(String.valueOf(i));
        }
        Collections.shuffle(list);
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
            tv_result.setText("玩家 獲勝");
            db.execSQL("INSERT INTO histore VALUES('"+"玩家VS電腦" +"','"+"玩家勝" +"');");
            pvc_result.ply_rlt++;
            prgstop();
        } else if (A == -3 || B == -3 || C == -3 || D == -3 || E == -3 || F == -3 || G == -3 || H == -3) {
            tv_result.setText("電腦 獲勝");
            db.execSQL("INSERT INTO histore VALUES('"+"玩家VS電腦" +"','"+"電腦勝" +"');");
            pvc_result.com_rlt++;
            prgstop();
        } else if ((a1 != 0 && a2 != 0 && a3 != 0 && a4 != 0 && a5 != 0 && a6 != 0 && a7 != 0 && a8 != 0 && a9 != 0)) {
            tv_result.setText("DRAW");
            db.execSQL("INSERT INTO histore VALUES('"+"玩家VS電腦" +"','"+"平手" +"');");
            pvc_result.drw_rlt++;
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img1: {
                lck1++;
                a1 = lck1;
                play(btn1, image1, lck1);
                findi(1);
                judge();
                com();
                break;
            }
            case R.id.img2: {
                lck2++;
                a2 = lck2;
                play(btn2, image2, lck2);
                findi(2);
                judge();
                com();
                break;
            }
            case R.id.img3: {
                lck3++;
                a3 = lck3;
                play(btn3, image3, lck3);
                findi(3);
                judge();
                com();
                break;
            }
            case R.id.img4: {
                lck4++;
                a4 = lck4;
                play(btn4, image4, lck4);
                findi(4);
                judge();
                com();
                break;
            }
            case R.id.img5: {
                lck5++;
                a5 = lck5;
                play(btn5, image5, lck5);
                findi(5);
                judge();
                com();
                break;
            }
            case R.id.img6: {
                lck6++;
                a6 = lck6;
                play(btn6, image6, lck6);
                findi(6);
                judge();
                com();
                break;
            }
            case R.id.img7: {
                lck7++;
                a7 = lck7;
                play(btn7, image7, lck7);
                findi(7);
                judge();
                com();
                break;
            }
            case R.id.img8: {
                lck8++;
                a8 = lck8;
                play(btn8, image8, lck8);
                findi(8);
                judge();
                com();
                break;
            }
            case R.id.img9: {
                lck9++;
                a9 = lck9;
                play(btn9, image9, lck9);
                findi(9);
                judge();
                com();
                break;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }

    private void findi(int i) {
        String ii = String.valueOf(i);
        int j = 0;
        while (true) {
            if (list.get(j) == ii) {
                list.set(j, "0");
                break;
            }
            j++;
        }
    }
}