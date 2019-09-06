package com.example.frog.frog;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EmpInfo extends Activity {

    public String empID;
    TextView empTitle;
    TextView comName;
    TextView comLocation;
    TextView comPage;
    TextView industry;
    TextView experience;
    TextView educational;
    TextView salary;
    TextView quantity;
    TextView active;
    TextView expDate;

    ImageView bookmark;

    public boolean bmk;
    String companyID, companyName, title, deadLine = "";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employment_information_layout);

        TextView tv = (TextView) findViewById(R.id.comName);
        //db
        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "Information.db", null, 1);

        //인텐트 받아오기
        Intent it = getIntent();
        empID = it.getStringExtra("empID");

        //TextView 할당(?)
        empTitle = (TextView) findViewById(R.id.empTitle);
        comName = (TextView) findViewById(R.id.comName);
        comLocation = (TextView) findViewById(R.id.comLocation);
        comPage = (TextView) findViewById(R.id.comPage);
        industry = (TextView) findViewById(R.id.industry);
        experience = (TextView) findViewById(R.id.experience);
        educational = (TextView) findViewById(R.id.educationLevel);
        salary = (TextView) findViewById(R.id.salary);
        quantity = (TextView) findViewById(R.id.openQuantity);
        active = (TextView) findViewById(R.id.active);
        expDate = (TextView) findViewById(R.id.expDate);

        boolean check = false;
        boolean idcheck = false;
        boolean titleb = false;
        boolean nameb = false;
        boolean locb = false;
        boolean pageb = false;
        boolean indusb = false;
        boolean expeb = false;
        boolean educb = false;
        boolean salb = false;
        boolean quantb = false;
        boolean actb = false;
        boolean expb = false;

//TextView tv = (TextView)findViewById(R.id.checkdb);

        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        bookmark = (ImageView) findViewById(R.id.bookmark);
        dbHelper.select();
        int flag2 = 0;
        for (int k = 0; k < dbHelper.i; k++) {

            if (Integer.parseInt(dbHelper.bkInfo[k].companyID) == Integer.parseInt(empID))
                flag2 = 1;
        }
        if (flag2 == 0) {
            bmk = false;

            //북마크

        } else if (flag2 == 1) {
            bmk = true;

            //북마크
            bookmark.setImageResource(R.drawable.bookmark);
        }
        try {
            URL url = new URL("http://api.saramin.co.kr/job-search?ind_cd=301+302+304+305+306+307+308+313+314&count=100");

            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();

            parser.setInput(url.openStream(), null);

            int parserEvent = parser.getEventType();

            while (parserEvent != XmlPullParser.END_DOCUMENT) {
                switch (parserEvent) {
                    case XmlPullParser.START_TAG:
                        if (parser.getName().equals("id")) {
                            check = true;
                        }
                        if (idcheck) {
                            if (parser.getName().equals("title")) {
                                titleb = true;
                            }
                            if (parser.getName().equals("name")) {
                                nameb = true;
                            }
                            if (parser.getName().equals("location")) {
                                locb = true;
                            }
                            if (parser.getName().equals("url")) {
                                pageb = true;
                            }
                            if (parser.getName().equals("industry")) {
                                indusb = true;
                            }
                            if (parser.getName().equals("experience-level")) {
                                expeb = true;
                            }
                            if (parser.getName().equals("required-education-level")) {
                                educb = true;
                            }
                            if (parser.getName().equals("salary")) {
                                salb = true;
                            }
                            if (parser.getName().equals("open-quantity")) {
                                quantb = true;
                            }
                            if (parser.getName().equals("active")) {
                                actb = true;
                            }
                            if (parser.getName().equals("expiration-timestamp")) {
                                expb = true;
                            }
                        }


                        break;
                    case XmlPullParser.TEXT:
                        if (check) {
                            if (parser.getText().equals(empID)) {
                                companyID = empID;
                                idcheck = true;
                                check = false;
                            }

                        }
                        if (titleb) {
                            title = parser.getText();
                            empTitle.setText(parser.getText());
                            titleb = false;
                        }


                        if (nameb) {
                            companyName = parser.getText();
                            comName.setText(parser.getText());
                            nameb = false;
                        }

                        if (locb) {
                            String temp = parser.getText();
                            temp = temp.replaceAll("&gt;", ">");
                            comLocation.setText(temp);
                            locb = false;
                        }

                        if (pageb) {
                            comPage.setText(parser.getText());
                            pageb = false;
                        }


                        if (indusb) {
                            industry.setText(industry.getText() + parser.getText());
                            indusb = false;
                        }

                        if (expeb) {
                            experience.setText(experience.getText() + parser.getText());
                            expeb = false;
                        }

                        if (educb) {
                            educational.setText(educational.getText() + parser.getText());
                            educb = false;
                        }

                        if (salb) {
                            salary.setText(salary.getText() + parser.getText());
                            salb = false;
                        }

                        if (quantb) {
                            quantity.setText(quantity.getText() + parser.getText() + "명");
                            quantb = false;
                        }

                        if (actb) {
                            if (parser.getText().equals("1"))
                                active.setText(active.getText() + "진행 중");
                            else active.setText(active.getText() + "마감");
                            actb = false;
                        }

                        if (expb) {
                            long unixSeconds = Long.parseLong(parser.getText());
                            date = new Date(unixSeconds * 1000L);
                            deadLine = expDate.getText() + sdf.format(date);
                            expDate.setText(expDate.getText() + sdf.format(date));
                            expb = false;
                        }

                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("job"))
                            idcheck = false;
                        break;
                }
                parserEvent = parser.next();
            }
        } catch (Exception e) {
            // tv.setText("에러");
        }
        int flag = 0;
        dbHelper.select();
        for (int k = 0; k < dbHelper.i; k++) {

            if (Integer.parseInt(dbHelper.bkInfo[k].companyID) == Integer.parseInt(empID))
                flag = 1;

        }

        if (flag == 0) {
            bookmark.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    dbHelper.insertMark(companyID, companyName, title, deadLine);
                    if (bmk) {
                        bookmark.setImageResource(R.drawable.notmark);
                        bmk = false;
                    } else {
                        bookmark.setImageResource(R.drawable.bookmark);
                        bmk = true;
                    }
                    Toast.makeText(EmpInfo.this, "스크랩 되었습니다.", Toast.LENGTH_LONG).show();
                }

            });
        } else if (flag == 1) {
            bookmark.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //delete

                    dbHelper.delete(companyID);
                    if (bmk) {
                        bookmark.setImageResource(R.drawable.notmark);
                        bmk = false;
                    } else {
                        bookmark.setImageResource(R.drawable.bookmark);
                        bmk = true;
                    }
                    Toast.makeText(EmpInfo.this, "스크랩 해지되었습니다.", Toast.LENGTH_LONG).show();
                }

            });
        }

    }
}

