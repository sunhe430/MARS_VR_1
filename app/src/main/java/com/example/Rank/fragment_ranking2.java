package com.example.Rank;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.Login.User;
import com.example.mars1.R;
import com.example.mars_vr_1.MainActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class fragment_ranking2 extends Fragment {

    public static fragment_ranking2 newInstance(){
        return new fragment_ranking2();
    }

    private RecyclerView recyclerView;
    private RankingAdapter rankingAdapter;
    private ArrayList<Rank> rankItems;

    private Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //레이아웃 선언
        View view = inflater.inflate(R.layout.fragment_ranking, container, false);
        TextView mRank = (TextView)view.findViewById(R.id.my_rank);
        TextView mName = (TextView)view.findViewById(R.id.my_name);
        TextView mDate = (TextView)view.findViewById(R.id.my_date);
        TextView mScore = (TextView)view.findViewById(R.id.my_score);
        TextView rankingType = (TextView)view.findViewById(R.id.ranking_type);
        Button button = (Button)view.findViewById(R.id.change_btn);

        button.setText("조주기능사 랭킹 보기");
        rankingType.setText("제과제빵기능사 랭킹");

        //리사이클러뷰
        recyclerView = view.findViewById(R.id.recycler_view);

        rankingAdapter = new RankingAdapter();

        recyclerView.setAdapter(rankingAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false));

        //레트로핏
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://184.72.49.233:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RankingApi RankingApi = retrofit.create(RankingApi.class);

        String rUser = ((User)getActivity().getApplication()).getUser();
        Call<List<RankingResponse>>  call = RankingApi.GetRanking(rUser);

        //kind=bartender
        call.enqueue(new Callback<List<RankingResponse>>() {
            @Override
            public void onResponse(Call<List<RankingResponse>> call, Response<List<RankingResponse>> response) {
                Log.d("Ranking", "요청 성공");
                List<RankingResponse> rankingResponse = response.body();

                int i=1;
                String r, s;
                int k=0;
                rankItems = new ArrayList<>();
                for (RankingResponse rankingResponses : rankingResponse) {
                    r = String.valueOf(rankingResponses.getRanking());
                    s = String.valueOf(rankingResponses.getScore());
                    String kind = rankingResponses.getKind();
                    String date = rankingResponses.getPlay_at();
                    if(kind.equals("baker")||kind.equals("Baker")) {
                        rankItems.add(new Rank(String.valueOf(i), rankingResponses.getName(), s, date.substring(0,10)+", "+ date.substring(11,19)));
                        if (rankingResponses.getName().equals(rUser) && k==0) {
                            mRank.setText(String.valueOf(i));
                            mName.setText(rankingResponses.getName());
                            mScore.setText(s);
                            mDate.setText(date.substring(0,10)+", "+ date.substring(11,19));
                            k++;
                        }
                        i++;
                    }
                }
                rankingAdapter.setRankList(rankItems);
            }

            @Override
            public void onFailure(Call<List<RankingResponse>> call, Throwable t) {
                Log.d("Ranking", t.getLocalizedMessage());
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(new fragment_ranking().newInstance());
            }
        });

        return view;
    }


}