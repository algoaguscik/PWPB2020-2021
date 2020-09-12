package com.example.latihan3halamanintent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Page2Activity extends AppCompatActivity implements View.OnClickListener {
    Button backTo1;
    Button nextTo3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.halaman_2);
        backTo1 = findViewById(R.id.back_to_1);
        nextTo3 = findViewById(R.id.next_to_3);
        backTo1.setOnClickListener(this);
        nextTo3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_to_1:
                Intent backPageTo1 = new Intent(this,MainActivity.class);
                startActivity(backPageTo1);
                break;
            case R.id.next_to_3:
                Intent nextPageTo3 = new Intent(this,Page3Activity.class);
                startActivity(nextPageTo3);
                break;
        }
    }
}
