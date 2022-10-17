package com.example.Feedback;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.Login.User;
import com.example.mars1.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class fragment_feedback extends Fragment {

    WebSettings webSettings;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feedback, null);
        TextView user_kind = view.findViewById(R.id.user_kind);
        TextView score = view.findViewById(R.id.score);
        TextView result = view.findViewById(R.id.result);
        WebView Video = view.findViewById(R.id.Video);
        ProgressBar progressBar = view.findViewById(R.id.progressBar);

        //웹뷰
        webSettings=Video.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);

        Video.setWebViewClient(new WebViewClient());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://184.72.49.233:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FeedbackAPI feedbackAPI = retrofit.create(FeedbackAPI.class);

        String fUser = ((User)getActivity().getApplication()).getUser();

        FeedbackResponse posts = new FeedbackResponse(fUser);
        Call<FeedbackResponse> call = feedbackAPI.PostFeedback(posts);

        call.enqueue(new Callback<FeedbackResponse>() {
            @Override
            public void onResponse(Call<FeedbackResponse> call, Response<FeedbackResponse> response) {
                Log.d("Feedback", "요청 성공");
                FeedbackResponse feedbackResponse = response.body();

                String url;
                String k = feedbackResponse.getKind();
                if(k.equals("Bartender")){
                    k = "조주기능사";
                    url = "https://youtu.be/G__j6zCVc9g";
                    Video.loadUrl(url);
                } else if (k.equals("Baker")||k.equals("baker")){
                    k = "제과제빵기능사";
                    url = "https://youtu.be/eWT6eleebYw";
                    Video.loadUrl(url);
                }

                user_kind.setText(fUser+"님의 "+k+" 시험 점수");
                score.setText(feedbackResponse.getScore()+"/100");
                if(feedbackResponse.getScore()>=60){
                    result.setText("시험결과 합격입니다.");
                }
                else
                    result.setText("시험결과 불합격입니다.");

            }

            @Override
            public void onFailure(Call<FeedbackResponse> call, Throwable t) {
                Log.d("Feedback", t.getLocalizedMessage());
            }
        });

        Video.setWebChromeClient(new WebChromeClient(){
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