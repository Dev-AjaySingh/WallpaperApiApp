package com.example.wallpaperapiapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private ArrayList<ImageModel>modelClasslist;
    private RecyclerView recyclerView;
    Adapter adapter;
    CardView mnature,mbus,mcar,mtrain,mtrending;
    EditText editText;
    ImageButton search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        recyclerView=findViewById(R.id.recyclerview);
        mnature=findViewById(R.id.nature);
        mcar=findViewById(R.id.car);
        mbus=findViewById(R.id.Bus);
        mtrain=findViewById(R.id.train);
        mtrending=findViewById(R.id.trending);
        editText=findViewById(R.id.edittext);
        search=findViewById(R.id.search);


        modelClasslist=new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setHasFixedSize(true);
        adapter=new Adapter(getApplicationContext(),modelClasslist);
        recyclerView.setAdapter(adapter);
        findphotos();

        mnature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query="nature";
                getsearchimage(query);
            }
        });

        mcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query="car";
                getsearchimage(query);
            }
        });
        mtrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query="train";
                getsearchimage(query);
            }
        });
        mtrending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              findphotos();
            }
        });
        mbus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query="bus";
                getsearchimage(query);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String query=editText.getText().toString();
                if(query.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Please enter something", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    getsearchimage(query);
                }
            }
        });


    }

    private void getsearchimage(String query) {

        ApiUtilities.getApiInterface().getSearchImage(query,1,80).enqueue(new Callback<SearchModel>() {
            @Override
            public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {
                modelClasslist.clear();
                if(response.isSuccessful())
                {
                    modelClasslist.addAll(response.body().getPhotos());
                    adapter.notifyDataSetChanged();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchModel> call, Throwable t) {

            }
        });
    }

    private void findphotos() {
        modelClasslist.clear();
        ApiUtilities.getApiInterface().getImage(1,80).enqueue(new Callback<SearchModel>() {
            @Override
            public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {
                if(response.isSuccessful())
                {
                    modelClasslist.addAll(response.body().getPhotos());
                    adapter.notifyDataSetChanged();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<SearchModel> call, Throwable t) {

            }
        });
    }
}