package cdictv.diertao.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cdictv.diertao.R;
import cdictv.diertao.util.Sputils;

public class FzFragment extends Fragment {
    private Context context;
    private View mInflate;
    private TextView defaltValue;
    private EditText value;
    private Button set;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mInflate = inflater.inflate(R.layout.yz_layout, container, false);
        context=container.getContext();
        initew();
        initlinster();
        return mInflate;
    }

    private void initlinster() {

      set.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
               String str=value.getText().toString().trim();
              if(!TextUtils.isEmpty(str)&&Integer.parseInt(str)!=0){
                    Sputils.putString("fz",str);
                  defaltValue.setText("当前1-4号小车账户余额告警阈值为"+str+"元");
                  Toast.makeText(context,"设置成功",Toast.LENGTH_SHORT).show();
                  value.setText("");
              }else {
                   Toast.makeText(context,"请输入正确阀值",Toast.LENGTH_SHORT).show();
              }
          }
      });
    }

    private void initew() {
        defaltValue = mInflate.findViewById(R.id.defalt_value);
        value = mInflate. findViewById(R.id.value);
        set = mInflate.findViewById(R.id.set);
        defaltValue.setText("当前1-4号小车账户余额告警阈值为"+Sputils.getString("fz")+"元");
    }
}
