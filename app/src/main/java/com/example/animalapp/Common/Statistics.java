package com.example.animalapp.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.example.animalapp.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class Statistics extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        PieChart pieChart = findViewById(R.id.pieChart);

        ArrayList<PieEntry> animals = new ArrayList<PieEntry>();
        animals.add(new PieEntry(15, "Cat"));
        animals.add(new PieEntry(6, "Dog"));
        animals.add(new PieEntry(7, "Cow"));
        animals.add(new PieEntry(6, "Horse"));
        animals.add(new PieEntry(10, "Unidentified"));

        PieDataSet pieDataSet = new PieDataSet(animals, "");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("Identifications");
        pieChart.animate();
    }
}