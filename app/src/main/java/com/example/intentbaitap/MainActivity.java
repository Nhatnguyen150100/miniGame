package com.example.intentbaitap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<String> arrayName;
    ImageView imgGoc, imgNhan;
    TextView txtdiem;
    int REQUEST_CODE_IMAGE = 123;
    String tenHinhGoc;
    int total = 30;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgGoc = (ImageView) findViewById(R.id.iamgeView1);
        imgNhan = (ImageView) findViewById(R.id.imageView2);
        txtdiem = (TextView) findViewById(R.id.diem);

        txtdiem.setText(""+ total);

        String[] name =getResources().getStringArray(R.array.list_name);
        arrayName = new ArrayList<>(Arrays.asList(name));

        // trộn mảng
        Collections.shuffle(arrayName);
        tenHinhGoc = arrayName.get(2);
        int idHinh = getResources().getIdentifier(arrayName.get(2),"drawable",getPackageName());

        imgGoc.setImageResource(idHinh);
        imgNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this,ImageActivity.class), REQUEST_CODE_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        String tenHinhNhan = null;
        if (requestCode == REQUEST_CODE_IMAGE && resultCode == RESULT_OK && data != null) {
            tenHinhNhan = data.getStringExtra("tenHinhChon");
            int idHinhNhan = getResources().getIdentifier(tenHinhNhan, "drawable", getPackageName());
            imgNhan.setImageResource(idHinhNhan);

            // so sánh
            if (tenHinhGoc.equals(tenHinhNhan)){
                Toast.makeText(this, "Chính Xác" + " Cộng 5 điểm", Toast.LENGTH_SHORT).show();
                total += 5;

                // CountDownTimer
                new CountDownTimer(3000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        // trộn mảng
                        Collections.shuffle(arrayName);
                        tenHinhGoc = arrayName.get(2);
                        int idHinh = getResources().getIdentifier(arrayName.get(2),"drawable",getPackageName());
                        imgGoc.setImageResource(idHinh);
                    }
                }.start();
            }else {
                Toast.makeText(this, "Sai rồi" + " Trừ 5 điểm", Toast.LENGTH_SHORT).show();
                total -= 5;
            }

        }else{
            Toast.makeText(this, "Bạn không được xem lại" + "Trừ 20 điểm", Toast.LENGTH_SHORT).show();
            total -= 20;
        }
        txtdiem.setText(""+ total);

            super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.reload,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menuReload){
            // trộn mảng
            Collections.shuffle(arrayName);
            tenHinhGoc = arrayName.get(2);
            int idHinh = getResources().getIdentifier(arrayName.get(2),"drawable",getPackageName());

            imgGoc.setImageResource(idHinh);
        }
        return super.onOptionsItemSelected(item);
    }
}