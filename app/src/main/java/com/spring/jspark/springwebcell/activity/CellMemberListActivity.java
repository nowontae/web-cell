package com.spring.jspark.springwebcell.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.spring.jspark.springwebcell.R;
import com.spring.jspark.springwebcell.adapter.CellMemberListViewAdapter;
import com.spring.jspark.springwebcell.common.Common;
import com.spring.jspark.springwebcell.httpparser.CellMemberInfo;
import com.spring.jspark.springwebcell.httpparser.HttpManager;

import java.util.ArrayList;

public class CellMemberListActivity extends AppCompatActivity {

    int weekOfYear;
    int year;

    ArrayList<CellMemberInfo> mCellMemberList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cell_member_list);

        weekOfYear = Common.getTodaysWeekOfYear();
        year = Common.getTodaysYear();

        mCellMemberList = HttpManager.getInstance().getCellMemberInfo();

        ListView listView = (ListView) findViewById(R.id.cell_member_list);
        CellMemberListViewAdapter adapter = new CellMemberListViewAdapter(mCellMemberList);
        listView.setAdapter(adapter);

        ((Button)findViewById(R.id.submit)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpManager.getInstance().submitCellAttandance(year, weekOfYear);
            }
        });
    }
}