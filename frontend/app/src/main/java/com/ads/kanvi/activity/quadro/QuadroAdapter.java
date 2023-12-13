package com.ads.kanvi.activity.quadro;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.ads.kanvi.R;
import com.ads.kanvi.activity.ListaCardsActivity;
import com.ads.kanvi.activity.quadro.response.QuadroDTO;
import java.util.List;

public class QuadroAdapter extends RecyclerView.Adapter<QuadroAdapter.QuadroViewHolder> {

    private List<QuadroDTO> quadros;

    public QuadroAdapter(List<QuadroDTO> quadros) {
        this.quadros = quadros;
    }

    public void atualizarQuadros(List<QuadroDTO> novaListaQuadros) {
        this.quadros = novaListaQuadros;
        notifyDataSetChanged(); // Notifica o RecyclerView sobre as mudanças nos dados
    }

    @NonNull
    @Override
    public QuadroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quadro_item, parent, false);
        return new QuadroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuadroViewHolder holder, int position) {
        // Associe os dados do quadro atual ao ViewHolder
        QuadroDTO quadro = quadros.get(position);
        holder.bind(quadro);

        // Adicione um listener de clique ao item da lista
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ao clicar em um quadro, inicie a ListaCardsActivity
                Context context = holder.itemView.getContext();
                Intent intent = new Intent(context, ListaCardsActivity.class);
                intent.putExtra("quadro_id", quadro.getId()); // Passando o ID do quadro clicado
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return quadros != null ? quadros.size() : 0;
    }

    public static class QuadroViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewQuadroTitle;

        public QuadroViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewQuadroTitle = itemView.findViewById(R.id.textViewQuadroTitle);
            // Você pode encontrar e atribuir outros elementos do layout aqui
        }

        public void bind(QuadroDTO quadro) {
            textViewQuadroTitle.setText(quadro.getDescricao());
            // Aqui você pode definir os valores para outros elementos do layout
        }
    }
}
