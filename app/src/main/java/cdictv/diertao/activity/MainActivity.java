package cdictv.diertao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cdictv.diertao.R;
import cdictv.diertao.util.Sputils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView left_menu;
    private TextView title;
    private TextView zhuxiao;
    private RelativeLayout tool_bar;
    private ImageView top_bac;
    private LinearLayout zhanghu;
    private LinearLayout czls;
    private LinearLayout gerenzhongxin;
    private LinearLayout wdxiaoxi;
    private LinearLayout shujufenxi;
    private LinearLayout gaosuETC;
    private LinearLayout erweima;
    private LinearLayout cheliangshoufei;
    private LinearLayout yule;
    private LinearLayout navigation_view;
    private DrawerLayout drawer_yout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Sputils.putString("fz","50");
        initView();
    }

    private void initView() {
        left_menu = (ImageView) findViewById(R.id.left_menu);
        title = (TextView) findViewById(R.id.title);
        zhuxiao = (TextView) findViewById(R.id.zhuxiao);
        tool_bar = (RelativeLayout) findViewById(R.id.tool_bar);
        top_bac = (ImageView) findViewById(R.id.top_bac);
        zhanghu = (LinearLayout) findViewById(R.id.zhanghu);
        czls = (LinearLayout) findViewById(R.id.czls);
        gerenzhongxin = (LinearLayout) findViewById(R.id.gerenzhongxin);
        wdxiaoxi = (LinearLayout) findViewById(R.id.wdxiaoxi);
        shujufenxi = (LinearLayout) findViewById(R.id.shujufenxi);
        gaosuETC = (LinearLayout) findViewById(R.id.gaosuETC);
        erweima = (LinearLayout) findViewById(R.id.erweima);
        cheliangshoufei = (LinearLayout) findViewById(R.id.cheliangshoufei);
        yule = (LinearLayout) findViewById(R.id.yule);
        navigation_view = (LinearLayout) findViewById(R.id.navigation_view);
        drawer_yout = (DrawerLayout) findViewById(R.id.drawer_yout);

        left_menu.setOnClickListener(this);
        zhanghu.setOnClickListener(this);
        czls.setOnClickListener(this);
        gerenzhongxin.setOnClickListener(this);
        wdxiaoxi.setOnClickListener(this);
        shujufenxi.setOnClickListener(this);
        gaosuETC.setOnClickListener(this);
        erweima.setOnClickListener(this);
        cheliangshoufei.setOnClickListener(this);
        yule.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.left_menu:
                //侧滑
                if (drawer_yout.isDrawerOpen(Gravity.LEFT)) {
                    drawer_yout.closeDrawer(Gravity.LEFT);
                } else {
                    drawer_yout.openDrawer(Gravity.LEFT);
                }
                break;
            case R.id.zhanghu:
                startActivity(new Intent(MainActivity.this,
                       ZhangHuguanliActivity.class));
                break;
            case R.id.czls:
                startActivity(new Intent(MainActivity.this,ZhangdangguanliActivity.class));
                break;
            case R.id.gerenzhongxin:
                startActivity(new Intent(MainActivity.this,
                        PersonActivity.class));
                break;
            case R.id.wdxiaoxi:

                break;
            case R.id.shujufenxi:

                break;
            case R.id.gaosuETC:
                startActivity(new Intent(MainActivity.this, GaosuETCActivity.class));
                break;
            case R.id.erweima:
                startActivity(new Intent(MainActivity.this, ErweimaActivity.class));
                break;
            case R.id.cheliangshoufei:

                break;
            case R.id.yule:
                startActivity(new Intent(MainActivity.this, NewsActivity.class));
                break;
        }
    }
}
