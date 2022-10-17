package com.example.mars_vr_1;

import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.Login.User;
import com.example.mars1.R;

import org.w3c.dom.Text;

public class fragment_main extends Fragment {

    WebView webView;
    WebSettings webSettings;
    ProgressBar progressBar;

    public static fragment_main newInstance(){
        return new fragment_main();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, null);
        ImageButton button1 = (ImageButton)view.findViewById(R.id.imageButton);
        ImageButton button2 = (ImageButton)view.findViewById(R.id.imageButton2);
        ImageButton button3 = (ImageButton)view.findViewById(R.id.imageButton3);
        TextView mUser = (TextView)view.findViewById(R.id.mUser);
        TextView webType = (TextView)view.findViewById(R.id.webType);
        WebView webView = (WebView)view.findViewById(R.id.webView);
        progressBar = (ProgressBar)view.findViewById(R.id.progressBar);

        String user = ((User)getActivity().getApplication()).getUser();
        mUser.setText(user+"님 환영합니다.");

        webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);

        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.q-net.or.kr/rcv001.do?id=rcv00103&gSite=Q&gId=");

        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                webType.setText("조주기능사 시험 일정 확인");

                webSettings.setUseWideViewPort(false);
                webSettings.setLoadWithOverviewMode(false);
                webView.setWebViewClient(new WebViewClient());
                webView.loadUrl("http://ci2022kingsman.dongyangmirae.kr/Mars_Cal/build/index.html");
            }
        });

        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                webType.setText("제과제빵사 시험 일정 확인");

                webView.setWebViewClient(new WebViewClient());
                webView.loadUrl("http://ci2022kingsman.dongyangmirae.kr/Mars_Cal/build2/index.html");
            }
        });

        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                webType.setText("시험 접수 페이지");

                webSettings.setUseWideViewPort(true);
                webSettings.setLoadWithOverviewMode(true);
                webView.setWebViewClient(new WebViewClient());
                webView.loadUrl("https://www.q-net.or.kr/rcv001.do?id=rcv00103&gSite=Q&gId=");
            }
        });

        webView.setWebChromeClient(new WebChromeClient(){
            public void onProgressChanged(WebView view, int progress){
                if(progress<100){
                    progressBar.setVisibility(ProgressBar.VISIBLE);
                }
                else if(progress==100){
                    progressBar.setVisibility(progressBar.GONE);
                }
                progressBar.setProgress(progress);
            }
        });

        return view;
    }

}