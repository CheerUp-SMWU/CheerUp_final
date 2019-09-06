package com.example.frog.frog;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton myInfo = (ImageButton)findViewById(R.id.myinfo);
        ImageButton employ = (ImageButton)findViewById(R.id.employ);
        ImageButton bookmark = (ImageButton)findViewById(R.id.bookmark);




        employ.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent Employment = new Intent(getApplicationContext(), Employment.class);
                //it.putExtra("hightemp", hightemp);
                //it.putExtra("lowtemp", lowtemp);
                startActivity(Employment);
            }

        });



        myInfo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent UserInformation = new Intent(getApplicationContext(), UserInformation.class);
                startActivity(UserInformation);
            }
        });

        bookmark.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent BookMark = new Intent(getApplicationContext(), BookMark.class);
                startActivity(BookMark);
            }
        });

    }
}