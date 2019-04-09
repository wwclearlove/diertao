package cdictv.diertao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import cdictv.diertao.R;

public class ErweimaActivity extends AppCompatActivity {

    private Spinner spinenr;
    private EditText je;
    private EditText zq;
    private Button sc;
    private String mCheha="1";
    private String mZqstr="";
    private String mJestr="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_erweima);
        initView();
        inteliner();
    }

    private void inteliner() {
        spinenr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mCheha= (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
                Intent intent=new Intent(ErweimaActivity.this,shengchengActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("bh",mCheha);
                bundle.putString("je",mJestr);
                bundle.putString("zq",mZqstr);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void getData() {
        mJestr = je.getText().toString().trim();
        mZqstr = zq.getText().toString().trim();
        if(TextUtils.isEmpty(mJestr)||TextUtils.isEmpty(mZqstr)){
             Toast.makeText(getApplicationContext(),"数据不能为空",Toast.LENGTH_LONG).show();
            return ;
        }
        if(Integer.parseInt(mJestr)==0||Integer.parseInt(mZqstr)==0){
            Toast.makeText(getApplicationContext(),"数据不能为0",Toast.LENGTH_LONG).show();
            return ;
        }
    }

    private void initView() {
        spinenr = (Spinner) findViewById(R.id.spinenr);
        je = (EditText) findViewById(R.id.je);
        zq = (EditText) findViewById(R.id.zq);
       sc = findViewById(R.id.shengcheng);

    }


}
