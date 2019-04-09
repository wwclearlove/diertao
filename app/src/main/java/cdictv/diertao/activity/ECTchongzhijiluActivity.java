package cdictv.diertao.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import cdictv.diertao.R;
import cdictv.diertao.adatper.EtcAdatper;
import cdictv.diertao.bean.EtcBean;
import cdictv.diertao.sql.DataBaseHelp;

public class ECTchongzhijiluActivity extends AppCompatActivity {

    private TextView heji;
    private ListView list;
    private List<EtcBean>mEtcBeans;
    Dao mDao;
    int qian=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ectchongzhijilu);
        try {
            mDao= DataBaseHelp.getDataBase(this).getDao(EtcBean.class);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        initView();
        try {
            mEtcBeans= mDao.queryForAll();
            for (EtcBean etcBean:
                 mEtcBeans) {
                 qian+=Integer.parseInt(etcBean.ectmoney);
            }
            heji.setText("充值合计："+qian+"元");
            EtcAdatper etcAdatper=new EtcAdatper(this,mEtcBeans);
            list.setAdapter(etcAdatper);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        heji = (TextView) findViewById(R.id.heji);
        list = (ListView) findViewById(R.id.list);
    }
}
