package cdictv.diertao.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cdictv.diertao.R;
import cdictv.diertao.adatper.MyAdapter;
import cdictv.diertao.fragment.BarFragment;
import cdictv.diertao.fragment.PieFragment;
import cdictv.diertao.fragment.WDXXPieFragment;
import cdictv.diertao.fragment.XXCXFragment;

public class WDXXActivity extends AppCompatActivity {
    private ViewPager viewpager;
    private TextView t1;
    private TextView t2;
    private List<Fragment> lsit = new ArrayList<>();
    private MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wdxx);
        init();
        setData();
        setAdapter();

    }

    private void init() {
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        t1 = (TextView) findViewById(R.id.t1);
        t2 = (TextView) findViewById(R.id.t2);
    }
    private void setData() {
        lsit.add(new XXCXFragment(this));
        lsit.add(new WDXXPieFragment(this));

    }

    private void setAdapter() {
        myAdapter = new MyAdapter(getSupportFragmentManager(), lsit);
        viewpager.setAdapter(myAdapter);
    }
}
