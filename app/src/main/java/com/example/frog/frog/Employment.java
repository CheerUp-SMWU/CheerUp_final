package com.example.frog.frog;

import android.app.Activity;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

public class Employment extends Activity {
    //공고제목, 기업명, 마감일
    TextView empTitle;
    TextView comName;
    TextView expDate;
    ImageView comImg;

    public static String experience_level, education_level, salary_level, category_level, location_level;

    CustomAdapter cAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employment_layout);


        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "Information.db", null, 1);  //db 객체 생성
        //DB에서 정보 받아오기
        dbHelper.getResult();
        int salary = dbHelper.salary;
        String location = dbHelper.location;
        int exLevel = dbHelper.exLevel;
        int graduation = dbHelper.graduation;
        int category = dbHelper.category;

        /*
        TextView tv = (TextView) findViewById(R.id.dbcheck);
        tv.setText("salary:" + salary + " location:" + location + " exLevel:" + exLevel + " graduation:" + graduation + " category:" + category);
        //final TextView experienceText = (TextView)findViewById(R.id.experience_level_textView);
        Spinner spinner = (Spinner) findViewById(R.id.experience_level_spinner);*/
        //String text = spinner.getSelectedItem().toString();

        ListView empList = (ListView) findViewById(R.id.empListView);

        final ArrayList<EmpList> list = new ArrayList<EmpList>();
        //list.add(new EmpList("삼성공고", "삼성", "2018-08-30"));

        cAdapter = new CustomAdapter(list, this);
        empList.setAdapter(cAdapter);

        StrictMode.enableDefaults();

        boolean intitle = false;
        boolean incname = false;
        boolean inend_date = false;
        boolean inlocation = false;
        boolean inid = false;
        String title = null;
        String cname = null;
        String end_date = null;
        String location_data = null;
        String id = null;
        Date date = null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            URL url = new URL("http://api.saramin.co.kr/job-search?ind_cd=301+302+304+305+306+307+308+313+314&count=100");

            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();

            parser.setInput(url.openStream(), null);

            int parserEvent = parser.getEventType();

            while (parserEvent != XmlPullParser.END_DOCUMENT) {
                switch (parserEvent) {
                    case XmlPullParser.START_TAG:
                        if (parser.getName().equals("title")) {                  //공고제목
                            intitle = true;
                        }
                        if (parser.getName().equals("name")) {                   //기업명
                            incname = true;
                        }
                        if (parser.getName().equals("expiration-timestamp")) { //마감일
                            inend_date = true;
                        }
                        if (parser.getName().equals("experience-level")) {     //신입/경력
                            experience_level = parser.getAttributeValue(null, "code");

                        }
                        if (parser.getName().equals("id")) {
                            inid = true;
                        }
                        if (parser.getName().equals("required-education-level")) {     //학력
                            education_level = parser.getAttributeValue(null, "code");
                        }
                        if (parser.getName().equals("salary")) {
                            salary_level = parser.getAttributeValue(null, "code");
                        }
                        if (parser.getName().equals("industry")) {
                            category_level = parser.getAttributeValue(null, "code");
                        }
                        if (parser.getName().equals("location")) {
                            inlocation = true;
                        }
                        break;
                    case XmlPullParser.TEXT:
                        if (intitle) {
                            title = parser.getText();
                            intitle = false;
                        }
                        if (incname) {
                            cname = parser.getText();
                            incname = false;
                        }
                        if (inend_date) {
                            long unixSeconds = Long.parseLong(parser.getText());
                            date = new Date(unixSeconds * 1000L);
                            end_date = sdf.format(date);
                            inend_date = false;
                        }
                        if (inlocation) {
                            location_data = parser.getText();
                            location_data = location_data.replaceAll("&gt;", " ");
                            inlocation = false;
                        }
                        if (inid) {
                            id = parser.getText();
                            inid = false;
                        }

                        break;

                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("job")) {

                            int el = Integer.parseInt(experience_level);
                            int rel = Integer.parseInt(education_level);
                            int sl = Integer.parseInt(salary_level);
                            int cl = Integer.parseInt(category_level);
                            if (exLevel == 0 && (el == 1 || el == 3 || el == 0)) {
                                if (rel == graduation) {
                                    if (sl >= salary) {
                                        if (category == 0) {
                                            if (location != "전국") {
                                                if (location_data.contains(location))
                                                    list.add(new EmpList(title, cname, end_date, id));
                                            } else
                                                list.add(new EmpList(title, cname, end_date, id));
                                        } else if (cl == category)
                                            if (location != "전국") {
                                                if (location_data.contains(location))
                                                    list.add(new EmpList(title, cname, end_date, id));
                                            } else
                                                list.add(new EmpList(title, cname, end_date, id));
                                    }

                                }
                            } else if (exLevel == 1 && (el == 2 || el == 3 || el == 0)) {
                                if (rel == graduation) {
                                    if (sl >= salary) {
                                        if (category == 0) {
                                            if (location != "전국") {
                                                if (location_data.contains(location))
                                                    list.add(new EmpList(title, cname, end_date, id));
                                            } else
                                                list.add(new EmpList(title, cname, end_date, id));
                                        } else if (cl == category)
                                            if (location != "전국") {
                                                if (location_data.contains(location))
                                                    list.add(new EmpList(title, cname, end_date, id));
                                            } else
                                                list.add(new EmpList(title, cname, end_date, id));
                                    }
                                }
                            }
                            break;
                        }
                }
                parserEvent = parser.next();
            }
        } catch (Exception e) {
            // tv.setText("에러");
        }
    }
}