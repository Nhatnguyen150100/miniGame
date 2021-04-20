package com.example.intentbaitap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.Collections;

import static com.example.intentbaitap.MainActivity.arrayName;

public class ImageActivity extends AppCompatActivity {

    TableLayout myTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        myTable = (TableLayout) findViewById(R.id.tableLayoutImage);

        int soDong = 4;
        int soCot = 3;

        // trộn mảng
        Collections.shuffle(arrayName);

        for(int i = 1 ; i <= soDong ; i++){
            TableRow tableRow = new TableRow(this);
            for(int j = 1 ; j <= soCot ; j++){
                ImageView imageView = new ImageView(this);
                TableRow.LayoutParams  layoutParams = new TableRow.LayoutParams(400,400);
                imageView.setLayoutParams(layoutParams);
                int viTri = soCot * (i-1) + (j-1);
                int idHinh = getResources().getIdentifier(arrayName.get(viTri),"drawable",getPackageName());
                imageView.setImageResource(idHinh);
                // add vào tableRow
                tableRow.addView(imageView);

                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.putExtra("tenHinhChon", arrayName.get(viTri));
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });
            }
            myTable.addView(tableRow);
        }

    }
}