package com.example.Mypage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Login.User;
import com.example.Rank.fragment_ranking;
import com.example.Rank.fragment_ranking2;
import com.example.mars1.R;
import com.example.mars_vr_1.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class fragment_edit extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //레이아웃 선언
        View view = inflater.inflate(R.layout.fragment_edit, null);
        EditText editName = view.findViewById(R.id.editName);
        EditText editPw = view.findViewById(R.id.editPw);
        Button editbtn = view.findViewById(R.id.editOkbtn);
        Button btn = view.findViewById(R.id.close_btn);

        //프래그먼트 매니저
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        //User네임
        String fUser = ((User)getActivity().getApplication()).getUser();

        //레트로핏 선언
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://184.72.49.233:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //처음 회원 정보 보여지기
        UpdateApi updateApi = retrofit.create(UpdateApi.class);
        UpdateRequest posts = new UpdateRequest(fUser);
        Call<UpdateResponse> call = updateApi.PostUpdate(posts);

        call.enqueue(new Callback<UpdateResponse>() {
            @Override
            public void onResponse(Call<UpdateResponse> call, Response<UpdateResponse> response) {
                Log.d("Update", "요청 성공");
                UpdateResponse updateResponse = response.body();
                editName.setText(updateResponse.getName());
                editPw.setText(updateResponse.getPw());
            }

            @Override
            public void onFailure(Call<UpdateResponse> call, Throwable t) {
                Log.d("Update", t.getLocalizedMessage());
            }
        });

        //정보 수정 확인 버튼
        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //name, pw
                String new_name = editName.getText().toString();
                String new_pw = editPw.getText().toString();

                //정보 수정 확인 API
                UpdateOkApi updateOkApi = retrofit.create(UpdateOkApi.class);
                UpdateRequest postOk = new UpdateRequest(fUser, new_name, new_pw);
                Call<UpdateResponse> callOk = updateOkApi.PostUpdateOk(postOk);

                callOk.enqueue(new Callback<UpdateResponse>() {
                    @Override
                    public void onResponse(Call<UpdateResponse> call, Response<UpdateResponse> response) {
                        Log.d("UpdateOk", "요청 성공");
                        UpdateResponse updateResponse = response.body();
                        Log.d("UpdateOk", updateResponse.getStatusMessage());
                    }

                    @Override
                    public void onFailure(Call<UpdateResponse> call, Throwable t) {
                        Log.d("UpdateOk", t.getLocalizedMessage());
                    }
                });

                Toast.makeText(getActivity().getApplicationContext(), "정보 수정이 완료되었습니다.", Toast.LENGTH_SHORT).show();

                ((User)getActivity().getApplication()).setUser(new_name);

                fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_down, R.anim.slide_out_down)
                        .remove(fragment_edit.this)
                        .commit();
            }
        });

        //X버튼
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity().getApplicationContext(), "정보 수정이 취소되었습니다.", Toast.LENGTH_SHORT).show();
                fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_down, R.anim.slide_out_down)
                        .remove(fragment_edit.this)
                        .commit();
            }
        });

        return view;
    }
}