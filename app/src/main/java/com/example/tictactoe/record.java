package com.example.tictactoe;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.stetho.Stetho;

import java.util.ArrayList;
import java.util.HashMap;

public class record extends AppCompatActivity{
    private final String DB_NAME = "MyList.db";
    private String TABLE_NAME = "MyTable";
    private final int DB_VERSION = 1;
    private MyData myData;
    private SQLiteDatabase db;
    private Myrecy_adpater myrecy_adpater;
    private  ArrayList<HashMap<String, String>> arrayList ;//取得所有資料
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record);

        Stetho.initializeWithDefaults(this);
        recyclerViewSetting();//設置RecyclerView
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void recyclerViewSetting() {//設置RecyclerView
        arrayList=new ArrayList<>();
        myData=new MyData(this);
        db=myData.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT mode,winorlose FROM histore",null);
        cursor.moveToFirst();
        if(cursor.getCount()!=0) {
            HashMap<String, String> raw = new HashMap<>();
            raw.put("mode", cursor.getString(0));
            raw.put("winorlose", cursor.getString(1));
            arrayList.add(raw);
            for (int a = 1; a < cursor.getCount(); a++) {
                cursor.moveToNext();
                HashMap<String, String> row = new HashMap<>();
                row.put("mode", cursor.getString(0));
                row.put("winorlose", cursor.getString(1));
                arrayList.add(row);
            }
        }
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        myrecy_adpater=new Myrecy_adpater();
        recyclerView.setAdapter(myrecy_adpater);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
    }
    private class Myrecy_adpater extends RecyclerView.Adapter<Myrecy_adpater.Myholder>{
        class Myholder extends RecyclerView.ViewHolder{
            public View itemView;
            public TextView textView;
            public Myholder(@NonNull View v) {
                super(v);
                itemView=v;
                textView=itemView.findViewById(R.id.textView2);
            }
        }

        @NonNull
        @Override
        public Myrecy_adpater.Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.histore_adpater,null);
            Myholder ah=new Myholder(view);
            return ah;
        }

        @Override
        public void onBindViewHolder(@NonNull Myrecy_adpater.Myholder holder, int position) {
            holder.textView.setText(arrayList.get(position).get("mode")+"    "+arrayList.get(position).get("winorlose"));
            }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }
    }
}
