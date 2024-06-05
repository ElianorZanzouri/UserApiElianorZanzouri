package com.example.elianorzanzourihomework2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elianorzanzourihomework2.databinding.ActivityMainBinding;

import java.util.List;

public class UsersActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    UserAdapter userAdapter;
    UserDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.users_activity);
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        );
        db=UserDatabase.getInstance(this);

        //Print Users in database
        List<UserData> users=db.userDao().getAll();
        userAdapter=new UserAdapter(users);
        recyclerView.setAdapter(userAdapter);
    }

}
