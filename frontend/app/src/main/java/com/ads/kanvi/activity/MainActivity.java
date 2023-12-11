package com.ads.kanvi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ads.kanvi.R;

public class MainActivity extends AppCompatActivity {

    private EditText edtUsername;
    private EditText edtPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicialização dos elementos da interface
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);

        // Definir valores padrão nos campos de usuário e senha
        String usuarioPadrao = "admin";
        String senhaPadrao = "admin";

        edtUsername.setText(usuarioPadrao);
        edtPassword.setText(senhaPadrao);

        // Lógica para o botão de login
        btnLogin.setOnClickListener(v -> realizarLogin());
    }


    private void realizarLogin() {
        String usuario = edtUsername.getText().toString();
        String senha = edtPassword.getText().toString();

        if (usuario.equals("admin") && senha.equals("admin")) {
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Usuário ou senha incorretos", Toast.LENGTH_SHORT).show();
        }
    }
}
