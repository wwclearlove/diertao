package cdictv.diertao.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import cdictv.diertao.R;
import cdictv.diertao.adatper.MyAdapter;
import cdictv.diertao.fragment.BarFragment;
import cdictv.diertao.fragment.PieFragment;

public class SJFXActivity extends AppCompatActivity {
    private ViewPager viewpager;
    private RadioButton b1;
    private RadioButton b2;


    private List<Fragment> lsit = new ArrayList<>();
    private MyAdapter myAdapter;
    private RadioGroup group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sjfx);
        init();
        setData();
        setAdapter();
        group.check(R.id.b1);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.b1:
                        viewpager.setCurrentItem(0);
                        break;
                    case R.id.b2:
                        viewpager.setCurrentItem(1);
                        break;
                }
            }
        });


        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if(i==0){
                    group.check(R.id.b1);
                }
                if(i==1){
                    group.check(R.id.b2);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


    }

    private void setData() {
        lsit.add(new PieFragment(this));
        lsit.add(new BarFragment(this));
    }

    private void setAdapter() {
        myAdapter = new MyAdapter(getSupportFragmentManager(), lsit);
        viewpager.setAdapter(myAdapter);
    }

    private void init() {
        b1 = (RadioButton) findViewById(R.id.b1);
        b2 = (RadioButton) findViewById(R.id.b2);
        viewpager = findViewById(R.id.viewpager);
        group = (RadioGroup) findViewById(R.id.group);
    }
}
