package com.ads.kanvi.activity.card;



import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ads.kanvi.activity.card.response.CardDTO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CardDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "card_database";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_CARDS = "cards";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_START_DATE = "start_date";
    private static final String COLUMN_END_DATE = "end_date";
    private static final String COLUMN_LIST_ID = "list_id";

    public CardDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_CARDS + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_DESCRIPTION + " TEXT, "
                + COLUMN_START_DATE + " TEXT, "
                + COLUMN_END_DATE + " TEXT, "
                + COLUMN_LIST_ID + " INTEGER)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CARDS);
        onCreate(db);
    }

    public void salvarCard(CardDTO card) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_DESCRIPTION, card.getDescricao());
        values.put(COLUMN_START_DATE, formatDate(card.getDtInicio()));
        values.put(COLUMN_END_DATE, formatDate(card.getDtTermino()));
        values.put(COLUMN_LIST_ID, card.getLista());

        db.insert(TABLE_CARDS, null, values);
        db.close();
    }

    @SuppressLint("Range")
    public List<CardDTO> getAllCards() {
        List<CardDTO> cardList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CARDS, null);
        if (cursor.moveToFirst()) {
            do {
                CardDTO card = new CardDTO();
                card.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                card.setDescricao(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)));
                card.setDtInicio(parseDate(cursor.getString(cursor.getColumnIndex(COLUMN_START_DATE))));
                card.setDtTermino(parseDate(cursor.getString(cursor.getColumnIndex(COLUMN_END_DATE))));
                card.setLista(cursor.getInt(cursor.getColumnIndex(COLUMN_LIST_ID)));

                cardList.add(card);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return cardList;
    }

    @SuppressLint("Range")
    public List<CardDTO> getCardsByLista(int listaId) {
        List<CardDTO> cardList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_CARDS +
                " WHERE " + COLUMN_LIST_ID + " = " + listaId;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                CardDTO card = new CardDTO();
                card.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                card.setDescricao(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)));
                card.setDtInicio(new Date(cursor.getColumnIndex(COLUMN_START_DATE)));

                cardList.add(card);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return cardList;
    }


    // Outros métodos conforme necessário para manipulação de cartões no banco de dados
    // Certifique-se de implementar métodos para atualizar, excluir, etc.

    private String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    private Date parseDate(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.parse(dateString);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
