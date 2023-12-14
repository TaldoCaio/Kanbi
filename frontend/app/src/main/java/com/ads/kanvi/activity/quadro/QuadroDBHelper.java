package com.ads.kanvi.activity.quadro;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ads.kanvi.activity.quadro.response.QuadroDTO;

import java.util.ArrayList;
import java.util.List;

public class QuadroDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "kanvi.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_QUADRO = "quadro_table";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_DESCRICAO = "quadro_descricao";
    private static final String COLUMN_CRIADOR = "criador_id";
    private static final String COLUMN_MAX_LISTA = "max_lista";
    private static final String COLUMN_STATUS = "status";

    private static final String CREATE_TABLE_QUADRO =
            "CREATE TABLE " + TABLE_QUADRO + "(" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_DESCRICAO + " TEXT NOT NULL," +
                    COLUMN_CRIADOR + " INTEGER NOT NULL," +
                    COLUMN_MAX_LISTA + " INTEGER," +
                    COLUMN_STATUS + " TEXT" +
                    ");";

    public QuadroDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_QUADRO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Lógica para migrar dados, se necessário
    }

    public long salvarQuadro(QuadroDTO quadro) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DESCRICAO, quadro.getDescricao());
        values.put(COLUMN_CRIADOR, quadro.getCriador());
        values.put(COLUMN_MAX_LISTA, quadro.getMaxLista());
        values.put(COLUMN_STATUS, quadro.getStatus());

        long id = db.insert(TABLE_QUADRO, null, values);
        db.close();

        return id;
    }

    @SuppressLint("Range")
    public List<QuadroDTO> getQuadros(Integer userId) {
        List<QuadroDTO> quadros = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_QUADRO + " WHERE " + COLUMN_CRIADOR + " = " + userId;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                QuadroDTO quadro = new QuadroDTO();
                quadro.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                quadro.setDescricao(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRICAO)));
                quadro.setCriador(cursor.getInt(cursor.getColumnIndex(COLUMN_CRIADOR)));
                quadro.setMaxLista(cursor.getInt(cursor.getColumnIndex(COLUMN_MAX_LISTA)));
                quadro.setStatus(cursor.getString(cursor.getColumnIndex(COLUMN_STATUS)));

                quadros.add(quadro);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return quadros;
    }
}
