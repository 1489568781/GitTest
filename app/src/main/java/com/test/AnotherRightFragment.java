package com.test;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.gittest.R;

/**
 * AnotherRightFragment
 * Created by Administrator on 2017/11/20.
 */
public class AnotherRightFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.another_right_fragment, container, false);
        return view;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView tv_another = (TextView) getActivity().findViewById(R.id.tv_another);
        Main_Fragment_Activity activity = (Main_Fragment_Activity) getActivity();//获取Activity的context
        Button button = (Button) activity.findViewById(R.id.button);//根据获取的context获取Activity的控件
        tv_another.setText(button.getText().toString());

    }
}
