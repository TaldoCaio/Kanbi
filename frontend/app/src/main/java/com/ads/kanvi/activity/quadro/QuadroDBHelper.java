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
    private static final String COLUMN_DESCRICAO = "descricao";

    private static final String CREATE_TABLE_QUADRO =
            "CREATE TABLE " + TABLE_QUADRO + "(" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_DESCRICAO + " TEXT NOT NULL" +
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

        long id = db.insert(TABLE_QUADRO, null, values);
        db.close();

        return id;
    }

    @SuppressLint("Range")
    public List<QuadroDTO> getQuadros() {
        List<QuadroDTO> quadros = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_QUADRO;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                QuadroDTO quadro = new QuadroDTO();
                quadro.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                quadro.setDescricao(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRICAO)));


                quadros.add(quadro);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return quadros;
    }


    // Adicione outros métodos conforme necessário para consultar, atualizar e excluir quadros
}
