package com.ads.kanvi.activity.card;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ads.kanvi.R;
import com.ads.kanvi.activity.card.response.CardDTO;

import java.util.ArrayList;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {
    private List<CardDTO> cards;
    private List<CardDTO> cardList = new ArrayList<>();;

    public CardAdapter(List<CardDTO> cards) {
        this.cards = cards;
    }
    public void setCards(List<CardDTO> cards) {
        this.cardList = cards;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        CardDTO card = cardList.get(position);

        holder.txtTitle.setText(card.getTitulo());
        holder.txtDescription.setText(card.getDescricao());
        holder.txtStartDate.setText((CharSequence) card.getDtInicio());
        holder.txtEndDate.setText((CharSequence) card.getDtTermino());

        // Você pode adicionar listeners ou manipulações necessárias aqui
        // Por exemplo, para abrir um detalhe do card, use holder.itemView.setOnClickListener()
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle;
        TextView txtDescription;
        TextView txtStartDate;
        TextView txtEndDate;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtDescription = itemView.findViewById(R.id.txtDescription);
            txtStartDate = itemView.findViewById(R.id.txtStartDate);
            txtEndDate = itemView.findViewById(R.id.txtEndDate);
        }
    }
}

