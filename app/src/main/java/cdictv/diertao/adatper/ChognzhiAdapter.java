package cdictv.diertao.adatper;




import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cdictv.diertao.R;
import cdictv.diertao.bean.CarChongzhijiluBean;
import cdictv.diertao.bean.ChongzhiguanliBean;
import cdictv.diertao.http.Mycall;
import cdictv.diertao.http.ShowOkhttpApi;
import cdictv.diertao.sql.DataBaseHelp;

public class ChognzhiAdapter extends BaseAdapter {

    Context context;
    List<ChongzhiguanliBean.DataBean> list;
    List<ChongzhiguanliBean.DataBean> activtelist = new ArrayList<>();

    private TextView chongzhiId;
    private TextView chongzhiChepai;
    private TextView chongzhiChezhu;
    private TextView chongzhiMoney;
    private CheckBox chognzhiCheckbox;
    private Button chognzhiOk;

    private EditText chogzhiChogzhinum;
    private Button chognzhiDagliogok;
    private Button chognzhiQuxiao;
    private TextView chognzhidagliogchongzho;

    private Dao dao;

    String leirong;
    ProgressDialog progressDialog;


    public ChognzhiAdapter(Context context, List<ChongzhiguanliBean.DataBean> list) {
        this.context = context;
        this.list = list;
        try {
            dao = DataBaseHelp.getDataBase(context).getDao(CarChongzhijiluBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.chognzhi_item,parent,false);
        }

        chongzhiId = (TextView) convertView.findViewById(R.id.chongzhi_id);
        chongzhiChepai = (TextView) convertView.findViewById(R.id.chongzhi_chepai);
        chongzhiChezhu = (TextView) convertView.findViewById(R.id.chongzhi_chezhu);
        chongzhiMoney = (TextView) convertView.findViewById(R.id.chongzhi_money);
        chognzhiCheckbox = (CheckBox)convertView. findViewById(R.id.chognzhi_checkbox);
        chognzhiOk = (Button) convertView.findViewById(R.id.chognzhi_ok);
        ImageView chongzhichepaiimg =  convertView.findViewById(R.id.  chongzhi_chepaiimg);

        chognzhiCheckbox.setTag(position);
        chognzhiOk.setTag(position);
        chognzhiCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               int i  = (int) v.getTag();
                list.get(i).flag = !list.get(i).flag;
                chognzhiCheckbox.setChecked( list.get(i).flag);
                if(list.get(i).flag){
                    activtelist.add(list.get(i));
                }else {
                    activtelist.remove(list.get(i));
                }

            }
        });

        chongzhiId.setText(list.get(position).id+"");
        chongzhiChepai.setText(list.get(position).chepai+"");
        chongzhiChezhu.setText(list.get(position).chezhu+"");
        chongzhiMoney.setText(list.get(position).money+"");


        switch (position){
            case 0:
                chongzhichepaiimg.setImageResource(R.mipmap.bmw);
                break;
            case 1:
                chongzhichepaiimg.setImageResource(R.mipmap.zhonghua);
                break;
            case 2:
                chongzhichepaiimg.setImageResource(R.mipmap.benz);
                break;
            case 3:
                chongzhichepaiimg.setImageResource(R.mipmap.mazda);
                break;

        }


        chognzhiOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              View view = View.inflate(context,R.layout.dailog_chongzhi,null);
              chogzhiChogzhinum = (EditText)view.findViewById(R.id.chogzhi_chogzhinum);
              chognzhiDagliogok = (Button) view.findViewById(R.id.chognzhi_dagliogok);
              chognzhiQuxiao = (Button) view.findViewById(R.id.chognzhi_quxiao);
              chognzhidagliogchongzho =  view.findViewById(R.id.chognzhi_dagliogchongzho);
              final AlertDialog dialog = new AlertDialog.Builder(context).setView(view).show();



                chognzhiDagliogok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final int i = (int) v.getTag();
                        chognzhidagliogchongzho.setText(list.get(i).chepai+"");
                        leirong = chogzhiChogzhinum.getText().toString();
                        //https://www.easy-mock.com/mock/5c8f3515c42b1c0235654282/jiaotong/recharge

                        ShowOkhttpApi.setCar("https://www.easy-mock.com/mock/5c8f3515c42b1c0235654282/jiaotong/recharge",list.get(position).id+"",leirong, new Mycall() {
                            @Override
                            public void success(String json) {
                                Toast.makeText(context,json,Toast.LENGTH_SHORT).show();
                                 int money= list.get(i).money+Integer.parseInt(leirong);
                                chongzhiMoney.setText(money+"");
                                CarChongzhijiluBean bean1 = new CarChongzhijiluBean();
                                bean1.carnum = list.get(i).id+"";
                                bean1.carmoney = leirong;
                                bean1.carper = "admin";
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm");
                                bean1.cartime =  format.format(new Date());
                                try {
                                    dao.create(bean1);
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                notifyDataSetChanged();
                                dialog.cancel();
                            }

                            @Override
                            public void filed(String msg) {

                            }
                        });

                        progressDialog.dismiss();
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
        return convertView;
    }

    public List getActivtelist(){
        return activtelist;
    }
}
