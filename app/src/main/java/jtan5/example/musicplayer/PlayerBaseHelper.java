package jtan5.example.musicplayer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PlayerBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "playerBase.db";
    public PlayerBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + PlayerDbSchema.PlayerTable.NAME + "(" +
                "_id integer primary key autoincrement, " +
                PlayerDbSchema.PlayerTable.Cols.UUID + ", " +
                PlayerDbSchema.PlayerTable.Cols.TITLE + ", " +
                PlayerDbSchema.PlayerTable.Cols.IS_PLAYING  +
            ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
