package com.example.mars_vr_1;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.mars1.R;

public class fragment_lisence extends Fragment {

    public static fragment_lisence newInstance(){
        return new fragment_lisence();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lisence, null);
        Button wbutton = (Button)view.findViewById(R.id.button_write);
        Button sbutton = (Button)view.findViewById(R.id.button_skill);
        ImageButton button1 = (ImageButton)view.findViewById(R.id.Button1);
        ImageButton button2 = (ImageButton)view.findViewById(R.id.Button2);
        ImageButton similar_button = (ImageButton)view.findViewById(R.id.similar_button);

        wbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                ((MainActivity)getActivity()).replaceFragment(fragment_schedule.newInstance());
            }
        });

        sbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Toast.makeText(getActivity(), "준비중입니다.", Toast.LENGTH_SHORT).show();
            }
        });

        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ((MainActivity)getActivity()).replaceFragment(fragment_lisence_f.newInstance());
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Toast.makeText(getActivity(), "준비중입니다.", Toast.LENGTH_SHORT).show();
            }
        });

        similar_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Toast.makeText(getActivity(), "준비중입니다.", Toast.LENGTH_SHORT).show();
            }
        });

        return view;

    }
}