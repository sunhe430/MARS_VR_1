package com.example.mars1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.Login.LoginApi;
import com.example.Login.LoginRequest;
import com.example.Login.LoginResponse;
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
                .baseUrl("http://10.0.2.2:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LoginApi LoginApi = retrofit.create(LoginApi.class);

        binding.loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                String email = binding.loginEmail.getText().toString();
                String pw = binding.loginPw.getText().toString();

                LoginRequest posts = new LoginRequest(email, pw);

                Call<LoginResponse> call = LoginApi.LoginPost(posts);

                Toast.makeText(LoginActivity.this, "clicked button", Toast.LENGTH_SHORT).show();

                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        Log.d("Login", "요청 성공");

                        LoginResponse loginResponse = response.body();
                        String name = loginResponse.getName();

                        if(name!=null) {
                            Log.d("Login", name + "님 환영합니다.");
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        }
                        else{
                            String status = loginResponse.getStatus();
                            String statusMessage = loginResponse.getStatusMessage();
                            Log.d("Login", "오류입니다.\n"+status+"\n"+statusMessage);
                        }

                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
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