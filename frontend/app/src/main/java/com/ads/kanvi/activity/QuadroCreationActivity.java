package com.ads.kanvi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ads.kanvi.R;
import com.ads.kanvi.activity.quadro.QuadroDBHelper;
import com.ads.kanvi.activity.quadro.response.QuadroDTO;

public class QuadroCreationActivity extends AppCompatActivity {

    private EditText editTextTitle;
    private QuadroDBHelper quadroDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalhes_activity);

        editTextTitle = findViewById(R.id.editTextTitle);
        quadroDBHelper = new QuadroDBHelper(this);

        Button buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveQuadro();
            }
        });
    }

    private void saveQuadro() {
        String quadroTitle = editTextTitle.getText().toString();
        if (!quadroTitle.isEmpty()) {
            QuadroDTO novoQuadro = new QuadroDTO();
            novoQuadro.setDescricao(quadroTitle);

            long quadroId = quadroDBHelper.salvarQuadro(novoQuadro); // Salva o novo quadro e obtém o ID

            Toast.makeText(this, "Quadro salvo localmente, ID: " + quadroId, Toast.LENGTH_SHORT).show();

            // Retorna para a HomeActivity
            Intent intent = new Intent(QuadroCreationActivity.this, HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Limpa a pilha de atividades
            startActivity(intent);
        } else {
            Toast.makeText(this, "Insira o título do quadro", Toast.LENGTH_SHORT).show();
        }
    }
}
