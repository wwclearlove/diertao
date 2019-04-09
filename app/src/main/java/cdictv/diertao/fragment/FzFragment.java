package cdictv.diertao.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cdictv.diertao.R;

public class FzFragment extends Fragment {
    private Context context;
    public FzFragment() {
    }


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.yz_layout, container, false);
        context=container.getContext();
        return inflate;
    }
}
