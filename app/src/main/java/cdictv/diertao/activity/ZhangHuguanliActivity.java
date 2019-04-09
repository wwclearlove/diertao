package cdictv.diertao.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cdictv.diertao.R;
import cdictv.diertao.adatper.ChognzhiAdapter;
import cdictv.diertao.bean.CarChongzhijiluBean;
import cdictv.diertao.bean.ChongzhiguanliBean;
import cdictv.diertao.http.Mycall;
import cdictv.diertao.http.ShowOkhttpApi;
import cdictv.diertao.sql.DataBaseHelp;

public class ZhangHuguanliActivity extends AppCompatActivity {

    private TextView chongzhi_piliang;
    private TextView chongzhi_jilu;
    private ListView zhanghu_listview;

    List<ChongzhiguanliBean.DataBean> list = new ArrayList<>();
    Gson gson = new Gson();

    ChognzhiAdapter adapter;
    Dao dao;


    private EditText chogzhiChogzhinum;
    private Button chognzhiDagliogok;
    private Button chognzhiQuxiao;
    private TextView chognzhidagliogchongzho;
    private ImageButton zhangdantuichu;
    ProgressDialog dialog;
//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhang_huguanli);
        initView();
        initRequest();

        try {
            dao = DataBaseHelp.getDataBase(this).getDao(CarChongzhijiluBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        chongzhi_jilu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ZhangHuguanliActivity.this,ZhangdangguanliActivity.class));
            }
        });

        zhangdantuichu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        chongzhi_piliang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(list.size() != 0){
                    list.clear();
                }
                list.addAll(adapter.getActivtelist());
                if(list.size() == 0){
                    Toast.makeText(ZhangHuguanliActivity.this,"请选择要充值的车辆！",Toast.LENGTH_LONG).show();
                    return;
                }
                View view = View.inflate(ZhangHuguanliActivity.this,R.layout.dailog_chongzhi,null);
                chogzhiChogzhinum = (EditText)view.findViewById(R.id.chogzhi_chogzhinum);
                chognzhiDagliogok = (Button) view.findViewById(R.id.chognzhi_dagliogok);
                chognzhiQuxiao = (Button) view.findViewById(R.id.chognzhi_quxiao);
                chognzhidagliogchongzho =  view.findViewById(R.id.chognzhi_dagliogchongzho);
                final AlertDialog dialog = new AlertDialog.Builder(ZhangHuguanliActivity.this).setView(view).show();
                StringBuilder sb = new StringBuilder();
                for(ChongzhiguanliBean.DataBean bean:list){
                    sb.append(bean.chepai+",");
                }
                chognzhidagliogchongzho.setText(sb.toString());
                chognzhiDagliogok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final String leirong = chogzhiChogzhinum.getText().toString();
                        //https://www.easy-mock.com/mock/5c8f3515c42b1c0235654282/jiaotong/recharge
                        try{
                            for(final ChongzhiguanliBean.DataBean bean:list){
                                ShowOkhttpApi.setCar("https://www.easy-mock.com/mock/5c8f3515c42b1c0235654282/jiaotong/recharge",bean.id+"",leirong, new Mycall() {
                                    @Override
                                    public void success(String json) {

                                        Toast.makeText(ZhangHuguanliActivity.this,json,Toast.LENGTH_SHORT).show();
                                        int money= bean.money+Integer.parseInt(leirong);
                                        //chongzhiMoney.setText(money+"");
                                        CarChongzhijiluBean bean1 = new CarChongzhijiluBean();
                                        bean1.carnum = bean.id+"";
                                        bean1.carmoney = leirong;
                                        bean1.carper = "admin";
                                        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm");
                                        bean1.cartime =  format.format(new Date());
                                        try {
                                            dao.create(bean1);
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }
                                        adapter.notifyDataSetChanged();
                                        dialog.cancel();
                                    }

                                    @Override
                                    public void filed(String msg) {

                                    }
                                });
                            }
                        }catch (Exception e){

                        }

}

                });
                chognzhiQuxiao.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

            }

        });
    }

    private void initRequest() {

        dialog = ProgressDialog.show(ZhangHuguanliActivity.this,"正在请求数据","请稍候...");
        ShowOkhttpApi.show("https://www.easy-mock.com/mock/5c8f3515c42b1c0235654282/jiaotong/carlist", new Mycall() {
            @Override
            public void success(String json) {
                Log.i("--------------", "success: "+json);
                ChongzhiguanliBean bean = null;
                try {
                    bean = gson.fromJson(json,ChongzhiguanliBean.class);
                    adapter = new ChognzhiAdapter(ZhangHuguanliActivity.this,bean.data);
                    zhanghu_listview.setAdapter(adapter);
                    dialog.dismiss();
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }

            }
            @Override
            public void filed(String msg) {
                Toast.makeText(ZhangHuguanliActivity.this,msg,Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });
    }

    private void initView() {
        chongzhi_piliang = (TextView) findViewById(R.id.chongzhi_piliang);
        chongzhi_jilu = (TextView) findViewById(R.id.chongzhi_jilu);
        zhanghu_listview = (ListView) findViewById(R.id.zhanghu_listview);
        zhangdantuichu =  findViewById(R.id.zhangdan_tuichu);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
