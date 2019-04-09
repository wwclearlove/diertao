package cdictv.diertao.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import cdictv.diertao.R;
import cdictv.diertao.adatper.PersonAdatper;
import cdictv.diertao.fragment.ChongzhijiluFragment;
import cdictv.diertao.fragment.FzFragment;
import cdictv.diertao.fragment.GeRenxingxiFrament;

public class PersonActivity extends AppCompatActivity {

    private TabLayout tablayout;
    private ViewPager viewpager;
    List<Fragment>mFragments;
    List<String>mTitles;
    private PersonAdatper mPersonAdatper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        mFragments=new ArrayList<>();
        mTitles=new ArrayList<>();
        initView();
        getData();
        setAdatper();
    }

    private void setAdatper() {
        mPersonAdatper = new PersonAdatper(getSupportFragmentManager(),mFragments,mTitles);
        viewpager.setAdapter(mPersonAdatper);
        tablayout.setupWithViewPager(viewpager);
        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));
    }

    private void getData() {
        mTitles.add("个人信息");
        mTitles.add("充值记录");
        mTitles.add("阀值设置");
        mFragments.add(new GeRenxingxiFrament());
        mFragments.add(new ChongzhijiluFragment());
        mFragments.add(new FzFragment());
    }
    private void initView() {
        tablayout = (TabLayout) findViewById(R.id.tablayout);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
    }
}
