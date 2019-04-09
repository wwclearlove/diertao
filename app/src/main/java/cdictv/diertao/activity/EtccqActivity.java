package cdictv.diertao.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import cdictv.diertao.R;
import cdictv.diertao.http.Mycall;
import cdictv.diertao.http.ShowOkhttpApi;
import cdictv.diertao.util.Sputils;

public class EtccqActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView img;
    private TextView title;
    private Spinner spinner;
    private Button button1;
    private Button button2;
    private Button button3;
    private EditText ed_menony;
    private Button cz;
    private String mMenony;
    int num=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etccq);
        initView();
    }

    private void initView() {
        img = (ImageView) findViewById(R.id.img);
        title = (TextView) findViewById(R.id.title);
        spinner = (Spinner) findViewById(R.id.spinner);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        ed_menony = (EditText) findViewById(R.id.ed_menony);
        cz = (Button) findViewById(R.id.cz);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        num=1;
                        break;
                    case 1:
                        num=2;
                        break;
                    case 2:
                        num=3;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        cz.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                ed_menony.setText("10");
                break;
            case R.id.button2:
                ed_menony.setText("50");
                break;
            case R.id.button3:
                ed_menony.setText("100");
                break;
            case R.id.cz:
                submit();
                ShowOkhttpApi.setCar("https://www.easy-mock.com/mock/5c8f3515c42b1c0235654282/jiaotong/recharge"
                        , num + "", mMenony, new Mycall() {
                            @Override
                            public void success(String json) {
                               Toast.makeText(EtccqActivity.this,json,Toast.LENGTH_SHORT).show();
                                if(num==1){
                                    Sputils.putString("num1",Integer.parseInt(Sputils.getString("num1"))+Integer.parseInt(mMenony)+"");


                                }else if(num==2){
                                    Sputils.putString("num2",Integer.parseInt(Sputils.getString("num2"))+Integer.parseInt(mMenony)+"");
                                }else if(num==3) {
                                    Sputils.putString("num3",Integer.parseInt(Sputils.getString("num3"))+Integer.parseInt(mMenony)+"");
                                }
                            }

                            @Override
                            public void filed(String msg) {

                            }
                        });
                break;
        }
    }

    private void submit() {
        mMenony = ed_menony.getText().toString().trim();
        if (TextUtils.isEmpty(mMenony)) {
            Toast.makeText(this, "请输入1-999", Toast.LENGTH_SHORT).show();
            return;
        }
        if (Integer.parseInt(mMenony)==0) {
            Toast.makeText(this, "请输入1-999", Toast.LENGTH_SHORT).show();
            return;
        }



    }
}
