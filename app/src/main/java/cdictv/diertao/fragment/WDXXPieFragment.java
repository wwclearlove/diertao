package cdictv.diertao.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import java.util.List;

import cdictv.diertao.R;
import cdictv.diertao.bean.WDXXPieBean;
import cdictv.diertao.http.Mycall;
import cdictv.diertao.http.ShowOkhttpApi;

public class WDXXPieFragment extends Fragment {

    private Context context;
    private List<WDXXPieBean.DataBean.ShujuBean> shuju;
    private CategorySeries dataset = new CategorySeries("title");
    private TextView pm;
    private TextView gz;
    private TextView wd;
    private TextView sd;
    private TextView co;


    public WDXXPieFragment() {
    }

    @SuppressLint("ValidFragment")
    public WDXXPieFragment(Context context) {
        this.context = context;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.pie_layout, container, false);
        LinearLayout linear = inflate.findViewById(R.id.linear);
        init(inflate);


        dataset.add("tyh", 12f);
        dataset.add("tyh", 12f);
        dataset.add("tyh", 12f);
        dataset.add("tyh", 12f);
        dataset.add("tyh", 12f);

        int[] colors = new int[]{
                Color.parseColor("#BFDD7B"),
                Color.parseColor("#E3DCA1"),
                Color.parseColor("#78EA5A"),
                Color.parseColor("#EE80B6"),
                Color.parseColor("#FF23B4")
        };
        DefaultRenderer renderer = new DefaultRenderer();
        for (int c : colors) {
            SimpleSeriesRenderer simpleSeriesRenderer = new SimpleSeriesRenderer();
            simpleSeriesRenderer.setColor(c);
            renderer.addSeriesRenderer(simpleSeriesRenderer);
        }
        renderer.setChartTitle("平台上有违章车辆和没违章车辆的占比统计");
        renderer.setShowLegend(false);
        renderer.setLabelsColor(Color.BLACK);
        renderer.setPanEnabled(false);
        renderer.setLabelsTextSize(20f);
        renderer.setChartTitleTextSize(30f);
        renderer.setShowLabels(false);

        GraphicalView pieChartView = ChartFactory.getPieChartView(context, dataset, renderer);
        linear.removeAllViews();
        linear.addView(pieChartView);
        getData();
        return inflate;
    }

    private void init(View inflate) {
        pm = inflate.findViewById(R.id.pm);
        gz = inflate.findViewById(R.id.gz);
        wd = inflate.findViewById(R.id.wd);
        sd = inflate.findViewById(R.id.sd);
        co = inflate.findViewById(R.id.co);
    }

    private void getData() {
        dataset.clear();
        ShowOkhttpApi.show("https://www.easy-mock.com/mock/5c8f3515c42b1c0235654282/jiaotong/baojing", new Mycall() {
            @Override
            public void success(String json) {
                try {
                    WDXXPieBean wdxxPieBean = new Gson().fromJson(json, WDXXPieBean.class);
                    WDXXPieBean.DataBean data = wdxxPieBean.getData();
                    shuju = data.getShuju();

                    for (WDXXPieBean.DataBean.ShujuBean s : shuju) {
                        dataset.add(s.getName(), s.getCishu());
                    }
                    pm.setText("PM2.5："+shuju.get(0).getCishu()+"");
                    gz.setText("光照："+shuju.get(1).getCishu()+"");
                    wd.setText("温度："+shuju.get(2).getCishu()+"");
                    sd.setText("湿度："+shuju.get(3).getCishu()+"");
                    co.setText("CO2："+shuju.get(4).getCishu()+"");

                } catch (Exception e) {

                }


            }

            @Override
            public void filed(String msg) {

            }
        });
    }
}
