package com.ads.kanvi.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ads.kanvi.R;
import com.ads.kanvi.activity.QuadroCreationActivity;
import com.ads.kanvi.activity.quadro.QuadroAdapter;
import com.ads.kanvi.activity.quadro.QuadroDBHelper;
import com.ads.kanvi.activity.quadro.response.QuadroDTO;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private QuadroAdapter quadroAdapter;
    private QuadroDBHelper quadroDBHelper;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        userId = intent.getIntExtra("userId", -1);

        recyclerView = findViewById(R.id.recyclerViewQuadros);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        quadroDBHelper = new QuadroDBHelper(this);

        setupAddButton();

        new GetQuadrosTask().execute(userId);
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
        intent.putExtra("userId", userId);
        startActivity(intent);
    }

    private class GetQuadrosTask extends AsyncTask<Integer, Void, List<QuadroDTO>> {
        @Override
        protected List<QuadroDTO> doInBackground(Integer... params) {
            int userId = params[0];
            List<QuadroDTO> quadros = new ArrayList<>();

            try {
                URL url = new URL("http://10.21.6.175:8080/quadro/criador/" + userId);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                try (BufferedReader br = new BufferedReader(
                        new InputStreamReader(urlConnection.getInputStream(), "utf-8"))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }

                    JSONArray jsonArray = new JSONArray(response.toString());

                    for (int i = 0; i < jsonArray.length(); i++) {
                        QuadroDTO quadro = new QuadroDTO();
                        quadro.setId(jsonArray.getJSONObject(i).getInt("id"));
                        quadro.setDescricao(jsonArray.getJSONObject(i).getString("descricao"));

                        quadros.add(quadro);
                    }
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            return quadros;
        }

        @Override
        protected void onPostExecute(List<QuadroDTO> quadros) {
            if (quadros != null && !quadros.isEmpty()) {
                setupQuadroAdapter(quadros);
                // Salvar quadros localmente (opcional)
                quadroDBHelper.salvarQuadro((QuadroDTO) quadros);
            } else {
                Log.e("GetQuadrosTask", "Falha ao obter quadros do servidor");
            }
        }
    }
}
