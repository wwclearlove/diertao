package cdictv.diertao.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import cdictv.diertao.R;
import cdictv.diertao.util.Sputils;

public class ECTyueActivity extends AppCompatActivity {

    private Spinner chogzhi_chogzhinum;
    private TextView etc_yue;
    private String mNum="1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ectyue);
        initView();
        etc_yue.setText(Sputils.getString("num1"));
    }

    private void initView() {
        chogzhi_chogzhinum = (Spinner) findViewById(R.id.chogzhi_chogzhinum);
        etc_yue = (TextView) findViewById(R.id.etc_yue);
        chogzhi_chogzhinum.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mNum = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void onClick(View view) {
        switch (mNum){
            case "1":
                etc_yue.setText(Sputils.getString("num1"));
                break;
            case "2":
                etc_yue.setText(Sputils.getString("num2"));
                break;
            case "3":
                etc_yue.setText(Sputils.getString("num3"));
                break;
        }
    }
}
