package com.example.mars_vr_1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mars1.R;

public class fragment_webview extends Fragment {

    public static fragment_webview newInstance(){
        return new fragment_webview();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_webview, container, false);
    }
}