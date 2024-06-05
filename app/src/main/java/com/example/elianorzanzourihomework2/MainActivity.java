package com.example.elianorzanzourihomework2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.example.elianorzanzourihomework2.databinding.ActivityMainBinding;
import com.example.elianorzanzourihomework2.user.Result;
import com.example.elianorzanzourihomework2.user.User;
import com.example.elianorzanzourihomework2.userService.UserAPIClient;
import com.example.elianorzanzourihomework2.userService.UserService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    User user;
    Context context;
    UserDatabase db;
    String value = "";
    String URL = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        db = UserDatabase.getInstance(this);
        setContentView(binding.getRoot());
        context = this;

        binding.buttonNext.setOnClickListener(v -> {
            setButtonsEnabled(false); //buttons
            fetchData();
        });

        binding.buttonAdd.setOnClickListener(v -> {
            if (!isErrorState()) {
                UserData userData = new UserData();
                userData.firstName = binding.textViewFirstName.getText().toString();
                userData.lastName = binding.textViewLastName.getText().toString();
                userData.country = binding.textViewCountry.getText().toString();
                userData.city = binding.textViewCity.getText().toString();
                userData.value = value;
                userData.picture = URL;

                if (db.userDao().getUserById(value) == null) {
                    db.userDao().insertUser(userData);
                }
            }
        });

        binding.buttonView.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), UsersActivity.class);
            startActivity(intent);
        });
    }


    private boolean isErrorState() {
        return binding.textViewFirstName.getText().toString().equals("ERROR") ||
                binding.textViewLastName.getText().toString().equals("ERROR") ||
                binding.textViewCountry.getText().toString().equals("ERROR") ||
                binding.textViewCity.getText().toString().equals("ERROR");
    }
    private void fetchData(){
        //Json http
        Retrofit retrofit = UserAPIClient.getClient();
        UserService service = retrofit.create(UserService.class);
        Call<User> callAsync = service.getUsers(null, null, null);

        callAsync.enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                setButtonsEnabled(true);//buttons

                user = response.body();
                if (user != null && !user.results.isEmpty()) {//user not null and array result not empty
                    Result res = user.results.get(0);//first value

                    //save value=id and url picture
                    value = res.id.value;
                    URL = res.picture.large;
                    //copy result in activity_main
                    Glide.with(MainActivity.this).load(res.picture.large).into(binding.imageView);
                    binding.textViewFirstName.setText(res.name.first);
                    binding.textViewLastName.setText(res.name.last);
                    binding.textViewAge.setText(String.valueOf(res.dob.age));
                    binding.textViewEmail.setText(res.email);
                    binding.textViewCity.setText(res.location.city);
                    binding.textViewCountry.setText(res.location.country);
                }
            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable throwable) {
                setButtonsEnabled(true);//button
                //Error in activity_main
                binding.textViewFirstName.setText("ERROR");
                binding.textViewLastName.setText("ERROR");
                binding.textViewAge.setText("ERROR");
                binding.textViewEmail.setText("ERROR");
                binding.textViewCity.setText("ERROR");
                binding.textViewCountry.setText("ERROR");
            }
        });
    }

    private void setButtonsEnabled(boolean enabled) {
        binding.buttonNext.setEnabled(enabled);
        binding.buttonAdd.setEnabled(enabled);
        binding.buttonView.setEnabled(enabled);
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchData();
    }
}
