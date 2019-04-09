package cdictv.diertao.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cdictv.diertao.R;
import cdictv.diertao.adatper.ZhangdangguanliAdapter;
import cdictv.diertao.bean.CarChongzhijiluBean;
import cdictv.diertao.sql.DataBaseHelp;

public class ZhangdangguanliActivity extends AppCompatActivity {

    Dao dao;
    private Spinner zhangdanSpinner;
    private Button zhangdanQurey;
    int position = 0;
    List<CarChongzhijiluBean> beans;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhangdangguanli);
        initView();
        ListView listview = findViewById(R.id.zhangdan_listview);
        String[] strings = new String[]{"时间降序","时间升序"};
        zhangdanSpinner.setAdapter(new ArrayAdapter<String>(ZhangdangguanliActivity.this,R.layout.txte_spinner,R.id.zhangban_text,strings));
        zhangdanSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ZhangdangguanliActivity.this.position=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        try {
            dao = DataBaseHelp.getDataBase(this).getDao(CarChongzhijiluBean.class);

            beans = dao.queryForAll();
            selectPaixu(1);
            if(beans.size() ==0){
                Toast.makeText(ZhangdangguanliActivity.this,"暂无历史记录",Toast.LENGTH_LONG).show();
            }
            ZhangdangguanliAdapter adapter = new ZhangdangguanliAdapter(this,beans);
            listview.setAdapter(adapter);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        zhangdanQurey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPaixu(position);
            }
        });

    }

    private void initView() {
        zhangdanSpinner = (Spinner) findViewById(R.id.zhangdan_spinner);
        zhangdanQurey = (Button) findViewById(R.id.zhangdan_qurey);
    }

    void selectPaixu(int position){
        switch (position){
            case 0:
                Collections.sort(beans, new Comparator<CarChongzhijiluBean>() {
                    @Override
                    public int compare(CarChongzhijiluBean o1, CarChongzhijiluBean o2) {
                        return o1.cartime.compareTo(o2.cartime);
                    }
                });
                break;
            case 1:
                Collections.sort(beans, new Comparator<CarChongzhijiluBean>() {
                    @Override
                    public int compare(CarChongzhijiluBean o1, CarChongzhijiluBean o2) {
                        return o2.cartime.compareTo(o1.cartime);
                    }
                });
                break;
        }
    }
}
