package cdictv.diertao.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cdictv.diertao.R;
import cdictv.diertao.adatper.ZhangdangguanliAdapter;
import cdictv.diertao.bean.CarChongzhijiluBean;
import cdictv.diertao.sql.DataBaseHelp;
import cdictv.diertao.util.Sputils;


public class ChongzhijiluFragment extends Fragment {
    View mView;
    Context mContext;
    private TextView zhichu;
    private TextView name;
    private TextView gone;
    private ListView list;
    Dao dao;
    int zcj=0;
    int position = 0;
    List<CarChongzhijiluBean> beans;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext=container.getContext();
        mView = inflater.inflate(R.layout.frament_chognzhijilu,container,false);
        initview();
        xuanyan();
        try {
            dao = DataBaseHelp.getDataBase(mContext).getDao(CarChongzhijiluBean.class);

            beans = dao.queryForAll();

            selectPaixu(1);
            if(beans.size() ==0){
                gone.setVisibility(View.VISIBLE);

            }else {
                gone.setVisibility(View.GONE);
                for (CarChongzhijiluBean bean:
                     beans) {
                    zcj+=Integer.parseInt(bean.carmoney);
                }

            }
            zhichu.setText(zcj+"元");
            ZhangdangguanliAdapter adapter = new ZhangdangguanliAdapter(mContext,beans);
            list.setAdapter(adapter);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return mView;
    }

    private void xuanyan() {
        name.setText(Sputils.getString("name"));
    }

    private void initview() {
        zhichu = mView. findViewById(R.id.zhichu);
        name= mView. findViewById(R.id.name);
        gone= mView. findViewById(R.id.gone);
        list =mView. findViewById(R.id.list);

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
