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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.List;

import cdictv.diertao.R;
import cdictv.diertao.adatper.ChognzhiAdapter;
import cdictv.diertao.bean.ChongzhiguanliBean;
import cdictv.diertao.http.Mycall;
import cdictv.diertao.http.ShowOkhttpApi;

public class ZhangHuguanliActivity extends AppCompatActivity {

    private TextView chongzhi_piliang;
    private TextView chongzhi_jilu;
    private ListView zhanghu_listview;

    List<ChongzhiguanliBean.DataBean> list = new ArrayList<>();
    Gson gson = new Gson();

    ChognzhiAdapter adapter;


    private EditText chogzhiChogzhinum;
    private Button chognzhiDagliogok;
    private Button chognzhiQuxiao;
    private TextView chognzhidagliogchongzho;
    ProgressDialog dialog;
//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhang_huguanli);
        initView();
        initRequest();



        chongzhi_jilu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ZhangHuguanliActivity.this,ZhangdangguanliActivity.class));
            }
        });


        chongzhi_piliang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                chognzhiDagliogok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        StringBuilder sb = new StringBuilder();
                        for(ChongzhiguanliBean.DataBean bean:list){
                            sb.append(bean.chepai+",");
                        }
                        chognzhidagliogchongzho.setText(sb.toString());

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
    }


}
