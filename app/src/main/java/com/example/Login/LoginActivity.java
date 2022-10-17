package com.example.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.Mypage.UpdateResponse;
import com.example.mars1.databinding.ActivityLoginBinding;
import com.example.mars_vr_1.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://184.72.49.233:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LoginApi LoginApi = retrofit.create(LoginApi.class);

        binding.loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                String email = binding.loginEmail.getText().toString();
                String pw = binding.loginPw.getText().toString();

                LoginRequest posts = new LoginRequest(email, pw);

                Call<UpdateResponse> call = LoginApi.LoginPost(posts);

                call.enqueue(new Callback<UpdateResponse>() {
                    @Override
                    public void onResponse(Call<UpdateResponse> call, Response<UpdateResponse> response) {
                        Log.d("Login", "요청 성공");

                        UpdateResponse loginResponse = response.body();
                        String name = loginResponse.getName();
                        ((User)getApplication()).setUser(name);

                        if(name!=null) {
                            Toast.makeText(LoginActivity.this, name + "님 환영합니다.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(LoginActivity.this, "이메일 혹은 비밀번호가 잘못되었습니다.", Toast.LENGTH_SHORT).show();
                            String status = loginResponse.getStatus();
                            String statusMessage = loginResponse.getStatusMessage();
                            Log.d("Login", "오류입니다.\n"+status+"\n"+statusMessage);
                        }

                    }

                    @Override
                    public void onFailure(Call<UpdateResponse> call, Throwable t) {
                        Log.e("Login", t.getLocalizedMessage());
                    }
                });

            }

        });

        binding.goRegist.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}