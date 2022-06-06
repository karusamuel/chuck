package com.example.chuck;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.chuck.RetrofitClient.ChuckClient;
import com.example.chuck.interfaces.ChuckApi;
import com.example.chuck.models.RandomResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
     Button button;
     ProgressBar progressBar;
     TextView mainText;
     ChuckApi chuckApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        progressBar = findViewById(R.id.progressBar);
        mainText = findViewById(R.id.textView);

        chuckApi = ChuckClient.getClient();

        getRandomJoke();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getRandomJoke();
            }
        });

    }

    void getRandomJoke(){
        loader(true);
        Call<RandomResponse> call = chuckApi.getRandomJoke();
        call.enqueue(new Callback<RandomResponse>() {
            @Override
            public void onResponse(Call<RandomResponse> call, Response<RandomResponse> response) {
                RandomResponse randomResponse = response.body();
              mainText.setText(randomResponse.getValue());
              loader(false);
            }

            @Override
            public void onFailure(Call<RandomResponse> call, Throwable t) {
                loader(false);
            }
        });

    }






     void loader(Boolean loading){

        if(loading){
             progressBar.setVisibility(View.VISIBLE);
             button.setVisibility(View.GONE);
             mainText.setText("Loading . . .");
             button.setText("next");

        }else{
            progressBar.setVisibility(View.GONE);
            button.setVisibility(View.VISIBLE);
        }

    }
}