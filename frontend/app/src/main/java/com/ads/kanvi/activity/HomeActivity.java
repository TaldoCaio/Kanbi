package com.ads.kanvi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ads.kanvi.R;
import com.ads.kanvi.activity.quadro.QuadroAdapter;
import com.ads.kanvi.activity.quadro.QuadroDBHelper;
import com.ads.kanvi.activity.quadro.response.QuadroDTO;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private QuadroAdapter quadroAdapter;
    private QuadroDBHelper quadroDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.recyclerViewQuadros);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        quadroDBHelper = new QuadroDBHelper(this);

        setupAddButton();

        loadQuadrosFromLocal();
    }

    private void loadQuadrosFromLocal() {
        try {
            List<QuadroDTO> quadrosSalvos = quadroDBHelper.getQuadros();
            if (quadrosSalvos != null && !quadrosSalvos.isEmpty()) {
                setupQuadroAdapter(quadrosSalvos);
            } else {
                setupQuadroAdapter(null);
            }
        } catch (Exception e) {
            Log.e("Local Data Load", "Erro ao carregar quadros do banco local: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void setupQuadroAdapter(List<QuadroDTO> quadros) {
        quadroAdapter = new QuadroAdapter(quadros);
        recyclerView.setAdapter(quadroAdapter);
    }

    private void setupAddButton() {
        ImageView imageViewAdd = findViewById(R.id.imageViewAdd);
        imageViewAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToQuadroCreationActivity();
            }
        });
    }

    private void navigateToQuadroCreationActivity() {
        Intent intent = new Intent(HomeActivity.this, QuadroCreationActivity.class);
        startActivity(intent);
    }


    private void saveQuadrosLocally(List<QuadroDTO> quadros) {
        for (QuadroDTO quadro : quadros) {
            quadroDBHelper.salvarQuadro(quadro);
        }
    }
}
