package com.ads.kanvi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ads.kanvi.R;
import com.ads.kanvi.activity.card.CardAdapter;
import com.ads.kanvi.activity.card.CardDBHelper;
import com.ads.kanvi.activity.card.response.CardDTO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ListaCardsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CardAdapter cardAdapter;
    private CardDBHelper cardDBHelper;
    private int quadroId; // Id do quadro atual

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_cards);

        recyclerView = findViewById(R.id.recyclerViewCards);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fabAddCard = findViewById(R.id.fabAddCard);
        fabAddCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Inicia a Activity de criação de cards
                Intent intent = new Intent(ListaCardsActivity.this, CardCreationActivity.class);
                intent.putExtra("Quadro_id", quadroId); // Passa o ID do quadro para a nova Activity
                startActivity(intent);
            }
        });

        // Obtém o id do quadro a partir do Intent e carrega os cards do quadro atual
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("Quadro_id")) {
            quadroId = intent.getIntExtra("Quadro_id", 0);
            loadCardsFromLocal();
        }
    }

    private void loadCardsFromLocal() {
        try {
            List<CardDTO> cards = cardDBHelper.getAllCards();
            if (cards != null && !cards.isEmpty()) {
                cardAdapter.setCards(cards);
            } else {
                cardAdapter.setCards(new ArrayList<>());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
