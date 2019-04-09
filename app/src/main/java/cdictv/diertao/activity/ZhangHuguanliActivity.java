package cdictv.diertao.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import cdictv.diertao.R;

public class ZhangHuguanliActivity extends AppCompatActivity {

    private TextView chongzhi_piliang;
    private TextView chongzhi_jilu;
    private ListView zhanghu_listview;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhang_huguanli);
        initView();
    }

    private void initView() {
        chongzhi_piliang = (TextView) findViewById(R.id.chongzhi_piliang);
        chongzhi_jilu = (TextView) findViewById(R.id.chongzhi_jilu);
        zhanghu_listview = (ListView) findViewById(R.id.zhanghu_listview);
    }
}
