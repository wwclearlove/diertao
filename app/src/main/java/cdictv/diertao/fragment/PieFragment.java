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
import android.widget.LinearLayout;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import cdictv.diertao.R;

public class PieFragment extends Fragment {

    private Context context;

    public PieFragment() {
    }

    @SuppressLint("ValidFragment")
    public PieFragment(Context context) {
        this.context = context;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.pie_layout, container, false);
        LinearLayout linear = inflate.findViewById(R.id.linear);

        CategorySeries dataset = new CategorySeries("title");
        dataset.add("有违章",28.6f);
        dataset.add("无违章",71.3f);


        int[] colors = new int[]{Color.parseColor("#4572A7"),Color.parseColor("#AA4643")};
        DefaultRenderer renderer = new DefaultRenderer();
        for(int c:colors){
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

        SimpleSeriesRenderer seriesRendererAt = renderer.getSeriesRendererAt(0);
        seriesRendererAt.setHighlighted(true);

        GraphicalView pieChartView = ChartFactory.getPieChartView(context, dataset, renderer);
        linear.removeAllViews();
        linear.addView(pieChartView);
        return inflate;
    }
}
