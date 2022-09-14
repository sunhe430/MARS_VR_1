package com.example.mars_vr_1;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.mars1.R;

public class fragment_schedule extends Fragment {

    public static fragment_schedule newInstance(){
        return new fragment_schedule();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_schedule, null);
        ImageButton back_button = (ImageButton)view.findViewById(R.id.back_button);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                ((MainActivity)getActivity()).replaceFragment(fragment_lisence.newInstance());
            }
        });

        return view;
    }
}