package com.example.frog.frog;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class UserInformation extends Activity {
    public Integer flag = 0;
    int a, b, c, d, e;
    String graduations, exlevels, categorys, salarys, locations;
    public int graduationi, exleveli, categoryi, salaryi, locationi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_information);

        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "Information.db", null, 1);

        final EditText edName = (EditText) findViewById(R.id.editText);
        Spinner spGrad = (Spinner) findViewById(R.id.spinner);
        Spinner spExlevel = (Spinner) findViewById(R.id.spinner2);
        Spinner spCate = (Spinner) findViewById(R.id.spinner3);
        Spinner spSalary = (Spinner) findViewById(R.id.spinner4);
        Spinner spLocation = (Spinner) findViewById(R.id.spinner5);
        ImageButton saveBt = (ImageButton) findViewById(R.id.button2);


        //final TextView tv1 = (TextView)findViewById(R.id.tv1);


        //DB에서 정보 받아오기
        dbHelper.getResult();
        int salary = dbHelper.salary;
        String location = dbHelper.location;
        int exLevel = dbHelper.exLevel;
        int graduation = dbHelper.graduation;
        int category = dbHelper.category;

        flag = dbHelper.numb;
        String name = dbHelper.userName;

        if (flag == 1) {
            edName.setText(name);
            switch (graduation) {
                case 0:
                    spGrad.setSelection(0);
                    break;
                case 6:
                    spGrad.setSelection(1);
                    break;
                case 8:
                    spGrad.setSelection(2);
                    break;
                case 9:
                    spGrad.setSelection(3);
                    break;
            }

            switch (salary) {

                case 7:
                    spSalary.setSelection(0);
                    break;
                case 17:
                    spSalary.setSelection(1);
                    break;
                case 19:
                    spSalary.setSelection(2);
                    break;
                case 21:
                    spSalary.setSelection(3);
                    break;
                case 23:
                    spSalary.setSelection(4);
                    break;
                case 0:
                    spSalary.setSelection(5);
                    break;
            }

            switch (exLevel) {
                case 0:
                    spExlevel.setSelection(0);
                    break;
                case 1:
                    spExlevel.setSelection(1);
                    break;
            }

            switch (category) {

                case 301:
                    spCate.setSelection(0);
                    break;
                case 302:
                    spCate.setSelection(1);
                    break;
                case 304:
                    spCate.setSelection(2);
                    break;
                case 305:
                    spCate.setSelection(3);
                    break;
                case 306:
                    spCate.setSelection(4);
                    break;
                case 307:
                    spCate.setSelection(5);
                    break;
                case 308:
                    spCate.setSelection(6);
                    break;
                case 313:
                    spCate.setSelection(7);
                    break;
                case 314:
                    spCate.setSelection(8);
                    break;
                case 0:
                    spCate.setSelection(9);
                    break;
            }

            switch (location) {
                case "서울":
                    spLocation.setSelection(0);
                    break;
                case "경기":
                    spLocation.setSelection(1);
                    break;
                case "광주":
                    spLocation.setSelection(2);
                    break;
                case "대구":
                    spLocation.setSelection(3);
                    break;
                case "대전":
                    spLocation.setSelection(4);
                    break;
                case "부산":
                    spLocation.setSelection(5);
                    break;
                case "울산":
                    spLocation.setSelection(6);
                    break;
                case "인천":
                    spLocation.setSelection(7);
                    break;
                case "강원":
                    spLocation.setSelection(8);
                    break;
                case "경남":
                    spLocation.setSelection(9);
                    break;
                case "경북":
                    spLocation.setSelection(10);
                    break;
                case "전남":
                    spLocation.setSelection(11);
                    break;
                case "전북":
                    spLocation.setSelection(12);
                    break;
                case "충남":
                    spLocation.setSelection(13);
                    break;
                case "충북":
                    spLocation.setSelection(14);
                    break;
                case "제주":
                    spLocation.setSelection(15);
                    break;
                case "전국":
                    spLocation.setSelection(16);
                    break;


            }

        }

        spGrad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                graduations = adapterView.getItemAtPosition(i).toString();
                switch (graduations) {
                    case "전체":
                        graduationi = 0;
                        break;
                    case "고등학교 졸업 이상":
                        graduationi = 6;
                        break;
                    case "대학졸업(4년) 이상":
                        graduationi = 8;
                        break;
                    case "석사졸업 이상":
                        graduationi = 9;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });

        spSalary.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                salarys = adapterView.getItemAtPosition(i).toString();
                switch (salarys) {
                    case "무관":
                        salaryi = 0;
                        break;
                    case "2000천 이상":
                        salaryi = 7;
                        break;
                    case "4000천 이상":
                        salaryi = 17;
                        break;
                    case "6000천 이상":
                        salaryi = 19;
                        break;
                    case "8000천 이상":
                        salaryi = 21;
                        break;
                    case "1억 이상":
                        salaryi = 23;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spExlevel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                exlevels = adapterView.getItemAtPosition(i).toString();
                switch (exlevels) {
                    case "신입":
                        exleveli = 0;
                        break;
                    case "경력":
                        exleveli = 1;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                locations = adapterView.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spCate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                categorys = adapterView.getItemAtPosition(i).toString();
                switch (categorys) {
                    case "전체":
                        categoryi = 0;
                        break;
                    case "솔루션,SI,ERP,CRM":
                        categoryi = 301;
                        break;
                    case "웹에이젼시":
                        categoryi = 302;
                        break;
                    case "쇼핑몰,오픈마켓":
                        categoryi = 304;
                        break;
                    case "포털,인터넷,컨텐츠":
                        categoryi = 305;
                        break;
                    case "네트워크,통신,모바일":
                        categoryi = 306;
                        break;
                    case "하드웨어,장비":
                        categoryi = 307;
                        break;
                    case "정보보안,백신":
                        categoryi = 308;
                        break;
                    case "IT컨설팅":
                        categoryi = 313;
                        break;
                    case "게임":
                        categoryi = 314;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        //tv1.setText(a+" "+b+" "+c+" "+d+" "+e);

        saveBt.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                if (flag == 1) {
                    //tv1.setText(graduationi+" "+locationi+" "+categoryi+" "+salaryi);
                    dbHelper.insertInfo(1, edName.getText().toString(), graduationi, exleveli, locations, categoryi, salaryi, 1);
                    //dbHelper.update(edName.getText().toString(),graduationi, exleveli,locationi,categoryi,salaryi);
                    //tv1.setText(dbHelper.getResult());
                    Toast.makeText(UserInformation.this, "업데이트되었습니다.", Toast.LENGTH_LONG).show();
                }else{
                    dbHelper.insertInfo(1, edName.getText().toString(), graduationi, exleveli, locations, categoryi, salaryi, 0);
                    //dbHelper.update(edName.getText().toString(),graduationi, exleveli,locationi,categoryi,salaryi);
                    //tv1.setText(dbHelper.getResult());
                    Toast.makeText(UserInformation.this, "새로만들었습니다.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}