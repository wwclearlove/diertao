package cdictv.diertao.adatper;




import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cdictv.diertao.R;
import cdictv.diertao.bean.ChongzhiguanliBean;

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

    public ChognzhiAdapter(Context context, List<ChongzhiguanliBean.DataBean> list) {
        this.context = context;
        this.list = list;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.chognzhi_item,parent,false);
        }

        chongzhiId = (TextView) convertView.findViewById(R.id.chongzhi_id);
        chongzhiChepai = (TextView) convertView.findViewById(R.id.chongzhi_chepai);
        chongzhiChezhu = (TextView) convertView.findViewById(R.id.chongzhi_chezhu);
        chongzhiMoney = (TextView) convertView.findViewById(R.id.chongzhi_money);
        chognzhiCheckbox = (CheckBox)convertView. findViewById(R.id.chognzhi_checkbox);
        chognzhiOk = (Button) convertView.findViewById(R.id.chognzhi_ok);

        chognzhiCheckbox.setTag(position);
        chognzhiDagliogok.setTag(position);
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
                        int i = (int) v.getTag();
                        chognzhidagliogchongzho.setText(list.get(i).chepai+"");

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
