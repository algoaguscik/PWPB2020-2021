package com.example.notes;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class HomeActivity  extends AppCompatActivity {
    private static final String TAG = com.example.notes.HomeActivity.class.getSimpleName();

    private DBHelper mDatabase;
    private ArrayList<Post> allPost =new ArrayList<>();
    private com.example.notes.PostAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        FrameLayout fLayout = (FrameLayout) findViewById(R.id.activity_to_do);

        RecyclerView postView = (RecyclerView)findViewById(R.id.post_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        postView.setLayoutManager(linearLayoutManager);
        postView.setHasFixedSize(true);
        mDatabase = new DBHelper(this);
        allPost = mDatabase.listPost();

        if(allPost.size() > 0){
            postView.setVisibility(View.VISIBLE);
            mAdapter = new com.example.notes.PostAdapter(this, allPost);
            postView.setAdapter(mAdapter);

        }else {
            postView.setVisibility(View.GONE);
            Toast.makeText(this, "Belum ada catatan.", Toast.LENGTH_LONG).show();
        }
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTaskDialog();
            }
        });
    }

    private void addTaskDialog(){
        LayoutInflater inflater = LayoutInflater.from(this);
        View subView = inflater.inflate(R.layout.activity_form, null);

        final EditText nameField = (EditText)subView.findViewById(R.id.enter_name);
        final EditText descField = (EditText)subView.findViewById(R.id.enter_desc);



        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Buat Catatan Baru");
        builder.setView(subView);
        builder.create();

        builder.setPositiveButton("Buat", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String name = nameField.getText().toString();
                final String desc = descField.getText().toString();


                if(TextUtils.isEmpty(name)){
                    Toast.makeText(com.example.notes.HomeActivity.this, "Terjadi Kesalahan. Mohon Periksa Kembali Data", Toast.LENGTH_LONG).show();
                }
                else{
                    SimpleDateFormat dateFormat = new SimpleDateFormat(
                            "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                    Date date = new Date();
                    final String tanggal = dateFormat.format(date);
                    com.example.notes.Post newPost = new com.example.notes.Post(name, desc,tanggal);
                    mDatabase.addPost(newPost);

                    finish();
                    startActivity(getIntent());
                }
            }
        });

        builder.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mDatabase != null){
            mDatabase.close();
        }
    }

}
