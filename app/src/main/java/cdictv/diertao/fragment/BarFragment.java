package cdictv.diertao.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.formatter.YAxisValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cdictv.diertao.R;

public class BarFragment extends Fragment {
    private Context context;
    private List<String> xVals = new ArrayList<>();
    private BarDataSet dataSet;
    private List<BarEntry> yVals = new ArrayList<>();
    private float[] sj = new float[]{26.46f, 22.10f, 12.87f, 9.97f, 7.43f, 4.82f, 4.80f, 4.31f, 3.87f, 3.37f};


    public BarFragment() {
    }

    @SuppressLint("ValidFragment")
    public BarFragment(Context context) {
        this.context = context;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.bar_layout, container, false);
        HorizontalBarChart barChart = inflate.findViewById(R.id.barChart);
        barChart.setGridBackgroundColor(Color.WHITE);
        barChart.setDescription("");

        setData();
        int[] ints = {
                Color.parseColor("#9AB7D7"),
                Color.parseColor("#B3A8CC"),
                Color.parseColor("#FEB278"),
                Color.parseColor("#B9D18D"),
                Color.parseColor("#FEC90A"),
                Color.parseColor("#644A7F"),
                Color.parseColor("#E8700F"),
                Color.parseColor("#7E953D"),
                Color.parseColor("#4F82C7"),
                Color.parseColor("#C30405")
        };
        dataSet = new BarDataSet(yVals, "label");
        dataSet.setColors(ints);
        dataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float v, Entry entry, int i, ViewPortHandler viewPortHandler) {
                return String.format("%.2f",entry.getVal())+"%";
            }
        });

        Legend legend = barChart.getLegend();
        legend.setEnabled(false);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setYOffset(20f);
        xAxis.setDrawGridLines(false);


        YAxis axisLeft = barChart.getAxisLeft();
        axisLeft.setDrawGridLines(false);
        axisLeft.setGridColor(Color.TRANSPARENT);
        axisLeft.setAxisLineColor(Color.TRANSPARENT);
        axisLeft.setTextColor(Color.TRANSPARENT);


        YAxis axisRight = barChart.getAxisRight();
        axisRight.setStartAtZero(true);
        axisRight.setAxisMaxValue(30f);
        axisRight.setAxisMinValue(0f);
        axisRight.setValueFormatter(new YAxisValueFormatter() {
            @Override
            public String getFormattedValue(float v, YAxis yAxis) {
                return String.format("%.2f",v)+"%";
            }
        });
        axisRight.setLabelCount(7,true);




        BarData barData = new BarData(xVals, dataSet);

        barChart.setData(barData);

        return inflate;
    }

    private void setData() {
        xVals.add("超速行驶");
        xVals.add("违规驶入导向车道");
        xVals.add("违规停车拒绝驶离");
        xVals.add("违反禁止标线指示");
        xVals.add("违反信号灯规定");
        xVals.add("机动车不走机动车道");
        xVals.add("不按规定系安全带");
        xVals.add("违反禁令标志指示");
        xVals.add("违规使用专用车道");
        xVals.add("机动车逆向行驶");

        for (int i = 0; i < sj.length; i++) {
            yVals.add(new BarEntry(sj[sj.length - i - 1], i));
        }
        Collections.reverse(xVals);

    }

}
