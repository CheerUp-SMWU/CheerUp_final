package com.example.frog.frog;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomAdapterBK extends ArrayAdapter<EmpList> {

    private ArrayList<EmpList> dataSet;

    Context mContext;

    private static class ViewHolder {
        TextView empTitle;
        TextView comName;
        TextView expDate;
        TextView id;
    }

    public CustomAdapterBK(ArrayList<EmpList> data, Context context) {
        super(context, R.layout.bookmark_layout, data);
        this.dataSet = data;
        this.mContext = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //아이템을 받아옵니다
        EmpList dataModel = getItem(position);
        //viewholder는 해당 view에 대한 객체를 가지고 cache를 저장한 후에 재사용을 하게 됩니다
        ViewHolder viewHolder;

        final View result;

        //첫 아이템을 생성해주거나 할때는 converview가 비어있게 됩니다 만약 비어있을 경우에만
        //view들을 새롭게 생성해주고 그렇지 않으면 tag에 저장된 값을 재사용합니다
        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.bookmark_list, parent, false);
            viewHolder.empTitle = (TextView) convertView.findViewById(R.id.empTitle);
            viewHolder.comName = (TextView) convertView.findViewById(R.id.comName);
            viewHolder.expDate = (TextView) convertView.findViewById(R.id.expDate);
            viewHolder.id = (TextView)convertView.findViewById(R.id.id);

            result = convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        viewHolder.empTitle.setText((dataModel.getEmpTitle()));
        viewHolder.comName.setText((dataModel.getComName()));
        viewHolder.expDate.setText(dataModel.getExpDate());
        viewHolder.id.setText((dataModel.getID()));

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent EmpInfo = new Intent(mContext, EmpInfo.class);


                mContext.startActivity(EmpInfo);
                Toast.makeText(mContext, position + "번째 클릭", Toast.LENGTH_LONG).show();
            }
        });
        return convertView;
    }
}