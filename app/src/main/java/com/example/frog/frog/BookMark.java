package com.example.frog.frog;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BookMark extends Activity {
    //공고제목, 기업명, 마감일
    TextView empTitle;
    TextView comName;
    TextView expDate;
    ImageView comImg;


    CustomAdapter cAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employment_layout);

        //DB 추가
        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "Information.db", null,1);




        //final TextView experienceText = (TextView)findViewById(R.id.experience_level_textView);
        //Spinner spinner = (Spinner)findViewById(R.id.experience_level_spinner);


        ListView empList = (ListView)findViewById(R.id.empListView);

        final ArrayList<EmpList> list = new ArrayList<EmpList>();
        //list.add(new EmpList("삼성공고", "삼성", "2018-08-30"));

        cAdapter = new CustomAdapter(list, this);
        empList.setAdapter(cAdapter);


        //


        String title = null;
        String cname = null;
        String end_date = null;
        String id = null;
        String url = null;


        //데이터 불러오기
        dbHelper.select();

        for(int k=0; k<dbHelper.i; k++){
            id = dbHelper.bkInfo[k].companyID;
            title = dbHelper.bkInfo[k].title;
            cname = dbHelper.bkInfo[k].companyName;
            end_date = dbHelper.bkInfo[k].deadLine;



            list.add(new EmpList(title, cname, end_date, id));
        }



    }
}