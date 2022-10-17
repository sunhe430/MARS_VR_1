package com.example.Mypage;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Login.User;
import com.example.Rank.RankingApi;
import com.example.Rank.RankingResponse;
import com.example.mars1.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class fragment_mypage extends Fragment {

    fragment_edit fragmentedit;
    public static fragment_mypage newInstance(){
        return new fragment_mypage();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //사용 레이아웃
        View view = inflater.inflate(R.layout.fragment_mypage, null);
        ImageButton btn = view.findViewById(R.id.edit_btn);
        TextView record = view.findViewById(R.id.record);
        TextView license_list = view.findViewById(R.id.license_list);
        TextView username = view.findViewById(R.id.username);
        ImageView license = view.findViewById(R.id.license);
        ImageView license2 = view.findViewById(R.id.license2);
        Drawable bartender = getResources().getDrawable(R.drawable.bartender_license);
        Drawable baker = getResources().getDrawable(R.drawable.baker_license);
        Drawable empty = getResources().getDrawable(R.drawable.empty);

        //User네임
        String fUser = ((User)getActivity().getApplication()).getUser();
        username.setText(fUser+"님의 마이페이지");

        //레트로핏 선언
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://184.72.49.233:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //기록 관련
        RankingApi rankingApi = retrofit.create(RankingApi.class);
        Call<List<RankingResponse>> callR = rankingApi.GetRanking("test1");

        //자격증 이미지 관련
        MypageAPI mypageAPI = retrofit.create(MypageAPI.class);
        MyRequest posts = new MyRequest(fUser);
        Call<MyResponse> callM = mypageAPI.PostMy(posts);

        //기록 요청
        callR.enqueue(new Callback<List<RankingResponse>>() {
            @Override
            public void onResponse(Call<List<RankingResponse>> call, Response<List<RankingResponse>> response) {
                Log.d("Record", "요청 성공");
                List<RankingResponse> rankingResponse = response.body();

                String k,p,t;
                int s;
                String result = "";
                for(RankingResponse rankingResponse1:rankingResponse){
                    if(rankingResponse1.getName().equals(fUser)) {
                        k = rankingResponse1.getKind();
                        if(k.equals("Bartender")||k.equals("bartender")){
                            k = "조주기능사";
                        } else if (k.equals("Baker")||k.equals("baker")){
                            k = "제과제빵기능사";
                        }
                        s = rankingResponse1.getScore();
                        p = rankingResponse1.getPlay_at().substring(0,10);
                        t = rankingResponse1.getPlaying_time();
                        result += p + "> " + "종류 : " + k + ", 점수 : " + s + ", 플레이 시간 : "+ t + "\n";
                    }
                }
                record.setText(result);
            }

            @Override
            public void onFailure(Call<List<RankingResponse>> call, Throwable t) {
                Log.d("Record", t.getLocalizedMessage());
            }
        });

 //       자격증 이미지 요청
        callM.enqueue(new Callback<MyResponse>() {
            @Override
            public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                Log.d("Mypage", "요청성공");
                MyResponse myResponse = response.body();
                if(myResponse.isBartender() && !myResponse.isBaker()){
                    license.setImageDrawable(bartender);
                    license2.setImageDrawable(empty);
                    license2.setVisibility(view.VISIBLE);
                    license_list.setText("- 보유자격증 : 조주기능사");
                }
                else if(!myResponse.isBartender() && myResponse.isBaker()){
                    license.setImageDrawable(baker);
                    license2.setImageDrawable(empty);
                    license2.setVisibility(view.VISIBLE);
                    license_list.setText("- 보유자격증 : 제과제빵기능사");
                }
                else if(myResponse.isBartender() && myResponse.isBaker()){
                    license.setImageDrawable(bartender);
                    int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 600, getResources().getDisplayMetrics());
                    license2.getLayoutParams().width = width;
                    license2.requestLayout();
                    license2.setVisibility(view.VISIBLE);
                    license2.setImageDrawable(baker);
                    license_list.setText("- 보유자격증 : 조주기능사, 제과제빵기능사");
                }
            }

            @Override
            public void onFailure(Call<MyResponse> call, Throwable t) {
                Log.d("Mypage", t.getLocalizedMessage());
            }
        });

        //버튼누르면 정보수정 페이지 뜸
        fragmentedit = new fragment_edit();

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_up, R.anim.slide_out_up)
                        .replace(R.id.frame, fragmentedit)
                        .commit();
            }
        });

        return view;
    }
}