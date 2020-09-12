package com.example.latihan3halamanintent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button nextTo2;
    Button nextTo3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nextTo2 = findViewById(R.id.next_to_2);
        nextTo3 = findViewById(R.id.next_to_3);
        nextTo2.setOnClickListener(this);
        nextTo3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.next_to_2:
                Intent nextPageTo2 = new Intent(this,Page2Activity.class);
                startActivity(nextPageTo2);
                break;
            case R.id.next_to_3:
                Intent nextPageTo3 = new Intent(this,Page3Activity.class);
                startActivity(nextPageTo3);
                break;
        }
    }
}