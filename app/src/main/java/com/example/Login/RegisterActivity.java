package com.example.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.mars1.databinding.ActivityRegisterBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://184.72.49.233:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SignUpApi SignUpApi = retrofit.create(SignUpApi.class);

        binding.registBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                String name = binding.signupName.getText().toString();
                String email = binding.signupEmail.getText().toString();
                String pw = binding.signupPw.getText().toString();

                SignUpRequest posts = new SignUpRequest(name, email, pw);

                Call<SignUpResponse> call = SignUpApi.SignUpPost(posts);

                call.enqueue(new Callback<SignUpResponse>() {
                    @Override
                    public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {

                        SignUpResponse loginResponse = response.body();
                        String status = loginResponse.getStatus();
                        String statusMessage = loginResponse.getStatusMessage();

                        if(status.equals("REGISTER_STATUS_TRUE")){
                            Toast.makeText(RegisterActivity.this, "환영합니다. 로그인해주세요", Toast.LENGTH_LONG).show();
                            finish();
                        }
                        else{
                            Toast.makeText(RegisterActivity.this, statusMessage, Toast.LENGTH_LONG).show();
                        }

                        Log.d("Signup", status + statusMessage);
                    }

                    @Override
                    public void onFailure(Call<SignUpResponse> call, Throwable t) {
                        Log.e("Signup", t.getLocalizedMessage());
                    }
                });

            }
        });

        binding.backBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });

    }
}