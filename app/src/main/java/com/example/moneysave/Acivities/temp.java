package com.example.moneysave.Acivities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.TextView;

import com.example.moneysave.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class temp extends AppCompatActivity {
    PieChart pieChart;
    ArrayList<Integer>colors;
    TextView TvYear, TvMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temp);
        HashMap<String,Integer> data=new HashMap<>();
        InitViews();
        //test
        data.put("option1", 20);
        data.put("option2",60);
        data.put("option3",20);
        data.put("option4",20);
        data.put("option5",30);
        data.put("option6",20);
        data.put("option7",20);
        data.put("option8",10);
        data.put("option9",5);
        data.put("option10",2);
        String year = "2000";
        String month = "December";
        setData(data);
        setYearMonth(year, month);
    }

    private void setYearMonth(String year, String month) {
        TvYear.setText(year);
        TvYear.setVisibility(View.VISIBLE);
        TvMonth.setText(month);
        TvMonth.setVisibility(View.VISIBLE);
    }

    private void InitViews() {
        pieChart = findViewById(R.id.piechart);
        colors = new ArrayList<>();
        TvYear = findViewById(R.id.year);
        TvMonth = findViewById(R.id.month);
    }

    private void setData(HashMap<String,Integer> data) {

        if (data.size()!=0) {
            List<String> keys = new ArrayList<>(data.keySet());
            List <PieEntry> value = new ArrayList<>();


            for (int i = 0; i < keys.size(); i++) {

//                pieChart.setTransparentCircleRadius(25f);

                value.add(new PieEntry(data.get(keys.get(i)),keys.get(i)));
                PieDataSet pieDataSet = new PieDataSet(value, "categories");
                PieData pieData = new PieData(pieDataSet);
                pieDataSet.setValueTextSize(12);
                pieDataSet.setColors(colors);
//                pieData.setValueTextSize(15f);
                pieChart.setData(pieData);


//                pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
            }
            DefinePieChartProperties();
            pieChart.animateXY(1400,1400);
        }
    }

    private void DefinePieChartProperties() {
        Description desc = new Description();
        desc.setTextSize(20f);
        desc.setPosition(1,2);
        pieChart.setDescription(desc);

        pieChart.setRotationEnabled(true);
        pieChart.setHoleRadius(25f);
        pieChart.setTransparentCircleAlpha(0);

        colors.add(Color.parseColor("#fb7268"));
        colors.add(Color.parseColor("#E3E0E0"));
        colors.add(Color.parseColor("#FFA726"));
        colors.add(Color.parseColor("#66BB6A"));
        colors.add(Color.parseColor("#EF5350"));
        colors.add(Color.parseColor("#29B6F6"));
        colors.add(Color.parseColor("#FFBB86FC"));
        colors.add(Color.parseColor("#FF000000"));
        colors.add(Color.parseColor("#FF018786"));
        colors.add(Color.parseColor("#FF5722"));

        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);

//        pieChart.setDrawEntryLabels(true);
    }
}