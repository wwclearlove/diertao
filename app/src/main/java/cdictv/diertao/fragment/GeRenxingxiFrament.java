package cdictv.diertao.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import cdictv.diertao.R;
import cdictv.diertao.bean.PersonBean;
import cdictv.diertao.http.Mycall;
import cdictv.diertao.http.ShowOkhttpApi;


public class GeRenxingxiFrament extends Fragment {
    private TextView geName;
    private TextView geSex;
    private TextView gePhone;
    private TextView getShengfengzheng;
    private TextView getZhucheshijian;
    private TextView che1;
    private TextView che2;
    private TextView che3;
    private TextView che4;
    Context mContext;
    private View mView;
    private ProgressDialog mProgressDialog;
    public List<PersonBean.DataBean.CarBean> carlist;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.frament_getrenxingxi,container,false);
        mContext=container.getContext();
        carlist=new ArrayList<>();
        initew();
        okhttpgeta();
        return mView;
    }

    private void okhttpgeta() {
        mProgressDialog = ProgressDialog.show(mContext,"请稍后","正在请求");
        ShowOkhttpApi.show("https://www.easy-mock.com/mock/5c8f3515c42b1c0235654282/jiaotong/geren"
                , new Mycall() {
                    @Override
                    public void success(String json) {
                        Gson gson=new Gson();
                        try{
                            PersonBean personBean=gson.fromJson(json,PersonBean.class);
                            PersonBean.DataBean data=personBean.data;

                           List<PersonBean.DataBean.CarBean> car=data.car;
                           carlist.clear();
                           carlist.addAll(car);
                            xuanyan(data);
                        }catch (Exception e){

                        }finally {
                            mProgressDialog.dismiss();
                        }
                    }

                    @Override
                    public void filed(String msg) {
                        mProgressDialog.dismiss();
                    }
                });
    }

    private void xuanyan(PersonBean.DataBean data) {
        geName.setText("姓名："+data.name+"");
        geSex.setText("性别："+data.sex+"");
        gePhone.setText("手机号："+data.phone);
        getShengfengzheng.setText("身份证："+data.idcard);
        getZhucheshijian.setText("注册时间："+data.createtime);
        che1.setText(carlist.get(0).chepai);
        che2.setText(carlist.get(1).chepai);
        che3.setText(carlist.get(2).chepai);
        che4.setText(carlist.get(3).chepai);
    }

    private void initew() {
        geName = mView. findViewById(R.id.ge_name);
        geSex =  mView. findViewById(R.id.ge_sex);
        gePhone =  mView. findViewById(R.id.ge_phone);
        getShengfengzheng =  mView.findViewById(R.id.get_shengfengzheng);
        getZhucheshijian =  mView.findViewById(R.id.get_zhucheshijian);
        che1 = mView. findViewById(R.id.che1);
        che2 =  mView. findViewById(R.id.che2);
        che3 =  mView. findViewById(R.id.che3);
        che4 =  mView.findViewById(R.id.che4);
    }


}
