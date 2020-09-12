package com.example.latihan3halamanintent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Page3Activity extends AppCompatActivity implements View.OnClickListener {
    Button backTo2;
    Button backTo1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.halaman_3);
        backTo1 = findViewById(R.id.back_to_1);
        backTo2 = findViewById(R.id.back_to_2);

        backTo1.setOnClickListener(this);
        backTo2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_to_1:
                Intent backPageTo1 = new Intent(this,MainActivity.class);
                startActivity(backPageTo1);
                break;
            case R.id.back_to_2:
                Intent backPageTo2 = new Intent(this,Page2Activity.class);
                startActivity(backPageTo2);
                break;
        }
    }
}
