package cdictv.diertao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cdictv.diertao.R;
import cdictv.diertao.util.Sputils;

public class GaosuETCActivity extends AppCompatActivity {

    private ImageView img;
    private TextView title;
    private LinearLayout cq;
    private LinearLayout ye;
    private LinearLayout cqjilu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaosu_etc);
        //三两车车默认余额50
        Sputils.putString("num1","50");
        Sputils.putString("num2","50");
        Sputils.putString("num3","50");
        initView();
        initener();

    }

    private void initener() {
        cq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GaosuETCActivity.this,EtccqActivity.class));
            }
        });
    }


    private void initView() {
        img = (ImageView) findViewById(R.id.img);
        title = (TextView) findViewById(R.id.title);
        cq = (LinearLayout) findViewById(R.id.cq);
        ye = (LinearLayout) findViewById(R.id.ye);
        cqjilu = (LinearLayout) findViewById(R.id.cqjilu);
    }
}
