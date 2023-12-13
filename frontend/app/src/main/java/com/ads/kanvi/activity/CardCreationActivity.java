package com.ads.kanvi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ads.kanvi.R;
import com.ads.kanvi.activity.card.CardDBHelper;
import com.ads.kanvi.activity.card.response.CardDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CardCreationActivity extends AppCompatActivity {

    private EditText editTextCardTitle;
    private EditText editTextCardDescription;
    private EditText editTextCardStartDate;
    private EditText editTextCardEndDate;
    private CardDBHelper cardDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_creation_activity);

        editTextCardTitle = findViewById(R.id.editTextCardTitle);
        editTextCardDescription = findViewById(R.id.editTextCardDescription);
        editTextCardStartDate = findViewById(R.id.editTextCardStartDate);
        editTextCardEndDate = findViewById(R.id.editTextCardEndDate);

        cardDBHelper = new CardDBHelper(this);

        Button buttonSaveCard = findViewById(R.id.buttonSaveCard);
        buttonSaveCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveCard();
            }
        });
    }

    private void saveCard() {
        String cardTitle = editTextCardTitle.getText().toString();
        String cardDescription = editTextCardDescription.getText().toString();
        String cardStartDateStr = editTextCardStartDate.getText().toString();
        String cardEndDateStr = editTextCardEndDate.getText().toString();

        if (!cardTitle.isEmpty() && !cardDescription.isEmpty() && !cardStartDateStr.isEmpty() && !cardEndDateStr.isEmpty()) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date cardStartDate = dateFormat.parse(cardStartDateStr);
                Date cardEndDate = dateFormat.parse(cardEndDateStr);

                CardDTO newCard = new CardDTO();
                newCard.setDescricao(cardDescription);
                newCard.setDtInicio(cardStartDate);
                newCard.setDtTermino(cardEndDate);
                newCard.setLista(getIntent().getIntExtra("quadro_id", 0));

                cardDBHelper.salvarCard(newCard); // Salva o novo card

                Toast.makeText(this, "Card salvo localmente", Toast.LENGTH_SHORT).show();

                // Retorna para a tela de listagem de cards (ListaCardsActivity)
                Intent intent = new Intent(CardCreationActivity.this, ListaCardsActivity.class);
                startActivity(intent);
                finish(); // Encerra a atividade atual para evitar voltar para esta tela ao pressionar "Back"
            } catch (ParseException e) {
                e.printStackTrace();
                Toast.makeText(this, "Erro ao converter datas", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
        }
    }
}
