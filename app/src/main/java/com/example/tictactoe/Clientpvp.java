package com.example.tictactoe;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.Timer;
import java.util.TimerTask;

public class Clientpvp extends AppCompatActivity implements View.OnClickListener {
    private TextView textView, tv_connect;
    ImageButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    int A, B, C, D, E, F, G, H;//胜利
    int a1, a2, a3, a4, a5, a6, a7, a8, a9;
    TextView tv_result;
    int cnt = 0;
    int x = 0;
    private boolean activity_status = false;
    private int port, status = 0;
    private EditText editText;
    private WifiManager wifiManager;
    private WifiInfo wifiInfo;
    private Socket socket;
    private BufferedReader bufferedInputStream;
    private PrintWriter printWriter;
    private AlertDialog.Builder alert;
    private Dialog dialog;
    ImageView image1, image2, image3, image4, image5, image6, image7, image8, image9;
    String end = "";
    boolean lock = false;
    private int receive;
    private String string;
    private Thread thread, getThread;
    private Timer timer;
    private TimerTask timerTask;
    private MyData myData;
    private SQLiteDatabase db;
    private View view;
    private Button btn_connect_sure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.severpvp);
        setTitle("CLIENT");
        activity_status = true;
        UI();
        textView.setText("對方回合");
        status = 0;
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
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


        textView = findViewById(R.id.textView);
        tv_result = findViewById(R.id.tv_result);
    }

    @Override
    public void onClick(View v) {
        while (lock) {
            switch (v.getId()) {
                case R.id.img1:
                    textView.setText("對方回合");
                    play(btn1, image1);
                    me_Toother(1);
                    a1 = x;
                    break;
                case R.id.img2:
                    Log.d("有點及按鈕反映", "onClick: ");
                    textView.setText("對方回合");
                    play(btn2, image2);
                    me_Toother(2);
                    a2 = x;
                    break;
                case R.id.img3:
                    textView.setText("對方回合");
                    play(btn3, image3);
                    me_Toother(3);
                    a3 = x;
                    break;
                case R.id.img4:
                    textView.setText("對方回合");
                    play(btn4, image4);
                    me_Toother(4);
                    a4 = x;
                    break;
                case R.id.img5:
                    textView.setText("對方回合");
                    play(btn5, image5);
                    me_Toother(5);
                    a5 = x;
                    break;
                case R.id.img6:
                    textView.setText("對方回合");
                    play(btn6, image6);
                    me_Toother(6);
                    a6 = x;
                    break;
                case R.id.img7:
                    textView.setText("對方回合");
                    play(btn7, image7);
                    me_Toother(7);
                    a7 = x;
                    break;
                case R.id.img8:
                    textView.setText("對方回合");
                    play(btn8, image8);
                    me_Toother(8);
                    a8 = x;
                    break;
                case R.id.img9:
                    textView.setText("對方回合");
                    play(btn9, image9);
                    me_Toother(9);
                    a9 = x;
                    break;
            }
            lock = false;
            judge();
            Log.d("taat", "vdz");
        }
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
            tv_result.setText("房主 獲勝");
            db.execSQL("INSERT INTO histore VALUES('" + "遠端連線（挑戰）" + "','" + "房主贏" + "');");
            pvp_result.com1_rlt++;
            prgstop();
        } else if (A == -3 || B == -3 || C == -3 || D == -3 || E == -3 || F == -3 || G == -3 || H == -3) {
            tv_result.setText("挑戰者 獲勝");
            db.execSQL("INSERT INTO histore VALUES('" + "遠端連線（挑戰）" + "','" + "挑戰者勝" + "');");

            pvp_result.com2_rlt++;
            prgstop();
        } else if ((a1 != 0 && a2 != 0 && a3 != 0 && a4 != 0 && a5 != 0 && a6 != 0 && a7 != 0 && a8 != 0 && a9 != 0)) {
            tv_result.setText("平手");
            db.execSQL("INSERT INTO histore VALUES('" + "遠端連線（挑戰）" + "','" + "平手" + "');");
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

    public void img(ImageView imageView) {
        if (x == 1) {
            imageView.setImageResource(R.drawable.ic_x);
        } else
            imageView.setImageResource(R.drawable.ic_o);
    }

    private void play(ImageButton btn, ImageView image) {
        btn.setVisibility(View.INVISIBLE);
        if (cnt % 2 == 0) {
            x = 1;
        } else {
            x = -1;
        }
        cnt++;
        Log.d("taat", "234" + String.valueOf(cnt));
        img(image);
        //send()

        Log.d("tatt","a1:"+a1);
        Log.d("tatt","a2:"+a2);
        Log.d("tatt","a3:"+a3);
        Log.d("tatt","a4:"+a4);
        Log.d("tatt","a5:"+a5);
        Log.d("tatt","a6:"+a6);
        Log.d("tatt","a7:"+a7);
        Log.d("tatt","a8:"+a8);
        Log.d("tatt","a9:"+a9);




    }

    private void receive() {
        while (!lock) {
            switch (receive) {
                case 1:
                    play(btn1, image1);
                    a1=x;
                    break;
                case 2:
                    play(btn2, image2);
                    a2=x;
                    break;
                case 3:
                    play(btn3, image3);
                    a3=x;
                    break;
                case 4:
                    play(btn4, image4);
                    a4=x;
                    break;
                case 5:
                    play(btn5, image5);
                    a5=x;
                    break;
                case 6:
                    play(btn6, image6);
                    a6=x;
                    break;
                case 7:
                    play(btn7, image7);
                    a7=x;
                    break;
                case 8:
                    play(btn8, image8);
                    a8=x;
                    break;
                case 9:
                    play(btn9, image9);
                    a9=x;
                    break;
            }
            lock = true;
            judge();
        }
    }

    public void connect_alert() {
        view = LayoutInflater.from(this).inflate(R.layout.connect_layout, null);
        btn_connect_sure = view.findViewById(R.id.connect);
        btn_connect_sure.setOnClickListener(Inport);
        editText = view.findViewById(R.id.edt_port);
        tv_connect = view.findViewById(R.id.messaggg);
        alert = new AlertDialog.Builder(this);
        alert.setMessage("連線中");
        alert.setCancelable(false);
        alert.setView(view);
        alert.setPositiveButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if ((socket != null)) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                dialog.dismiss();
                finish();
            }
        });
        alert.create();
        dialog = alert.show();
    }

    public void me_Toother(int posit) {
        getThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    printWriter = new PrintWriter(socket.getOutputStream());
                    printWriter.print(posit + "\n");
                    printWriter.flush();
                    Log.d("我傳了", "run: ");
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
        });
        getThread.start();
    }

    public void other_Tome() {
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    bufferedInputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    string = bufferedInputStream.readLine();
                    Log.d("ttt",string);
                    Log.d(string, "run: ");
                    if (string != null && !string.trim().equals("") && !string.trim().equals("A")) {

                        receive = Integer.parseInt(string);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                textView.setText("我的回合");
                                receive();
                            }
                        });
                    }
                    Thread.sleep(300);
                    if (activity_status == true) {
                        thread.run();
                    }
                } catch (IOException | InterruptedException e) {
                    Log.d("ttt", "out: " + e.toString());
                    finish();
                }
            }
        });
        thread.start();
    }

    View.OnClickListener Inport = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            port = Integer.parseInt(editText.getText().toString());
            Log.d("" + port, "onClick: ");
            tv_connect.setText("連線中 密碼"+editText.getText());
            editText.setEnabled(false);
            if (socket != null) {
                try {
                    status = 0;
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            connect_socket();
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        myData = new MyData(this);
        db = myData.getWritableDatabase();
        connect_alert();
    }

    private void connect_socket() {
        int inIp;
        wifiManager = (WifiManager) this.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiInfo = wifiManager.getConnectionInfo();
        inIp = wifiInfo.getIpAddress();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (status < 256) {
                    try {
                        socket = new Socket();
                        Log.d(socket.isConnected() + "", "run: ");
                        SocketAddress socketAddress = new InetSocketAddress((inIp & 0xFF) + "." + (0xFF & inIp >> 8) + "." + (0xFF & inIp >> 16) + "." + status, port);
//                        SocketAddress socketAddress = new InetSocketAddress("192.168.1.125",port);
                        socket.connect(socketAddress, 200);

                    } catch (IOException e) {
                        Log.d(e.getMessage(), "run: ");
                    }
                    Log.d("目前:" + status, "run: " + (inIp & 0xFF) + "." + (0xFF & inIp >> 8) + "." + (0xFF & inIp >> 16) + "." + status);
                    status++;
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (socket != null && socket.isConnected()) {
                        status = 257;
                    }
                }
                if (socket != null && socket.isConnected()) {
                    Log.d("來過", "run: ");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            dialog.dismiss();
                        }
                    });
                    timer = new Timer();
                    timerTask = new TimerTask() {
                        @Override
                        public void run() {
                            try {
                                printWriter = new PrintWriter(socket.getOutputStream());
                                printWriter.write("A\n");
                                printWriter.flush();
                            } catch (IOException e) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "連線斷開請重開", Toast.LENGTH_LONG).show();
                                        try {
                                            socket.close();
                                            finish();
                                        } catch (IOException ioException) {
                                            finish();
                                        }
                                    }
                                });
                            }
                        }
                    };
                    try {
                        socket.setSoTimeout(5000);
                        Log.d("ttt","3456");
                    } catch (SocketException socketException) {
                        Log.d("setSoTimeout", "run: ");
                        finish();
                    }
                    other_Tome();
                    timer.schedule(timerTask, 0, 1000);
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (socket.isConnected() == false && status > 256) {
                            Log.d("我來關過了", "run: " + status);
                            try {
                                socket.close();
                            } catch (IOException e) {
                                Toast.makeText(getApplicationContext(), "遊戲沒救了請重開", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                            dialog.dismiss();
                            finish();
                        }
                    }
                });
            }
        }).start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        activity_status = false;
        if (timerTask != null) {
            timerTask.cancel();
            timer.cancel();
        }
        if (socket != null) {

            try {
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}