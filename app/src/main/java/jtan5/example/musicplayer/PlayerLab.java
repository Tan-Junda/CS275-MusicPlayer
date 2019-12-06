package jtan5.example.musicplayer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerLab {

    private static PlayerLab sPlayerLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;


    private PlayerLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new PlayerBaseHelper(mContext).getWritableDatabase();
    }

    public static PlayerLab get(Context c) {
        if (sPlayerLab == null) {
            sPlayerLab = new PlayerLab(c);
        }
        return sPlayerLab;
    }

    public List<Player> getPlayers() {
        List<Player> players = new ArrayList<>();
        PlayerCursorWrapper cursor = queryPlayers(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                players.add(cursor.getPlayer());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return players;
    }

    public Player getPlayer(UUID id) {
        PlayerCursorWrapper cursor = queryPlayers (PlayerDbSchema.PlayerTable.Cols.UUID + "= ?",
            new String[] { id.toString()}
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getPlayer();
        } finally {
            cursor.close();
        }
    }

    private static ContentValues getContentValues(Player player) {
        ContentValues values = new ContentValues();
        values.put(PlayerDbSchema.PlayerTable.Cols.UUID, player.getId().toString());
        values.put(PlayerDbSchema.PlayerTable.Cols.TITLE, player.getTitle());
        values.put(PlayerDbSchema.PlayerTable.Cols.IS_PLAYING, player.isPlaying() ? 1 : 0);
        return values;
    }

    public void addPlayer(Player player) {
        ContentValues values = getContentValues(player);
        mDatabase.insert(PlayerDbSchema.PlayerTable.NAME, null, values);
    }
    public void deletePlayer(Player player) {
        mDatabase.delete(
            PlayerDbSchema.PlayerTable.NAME,
            PlayerDbSchema.PlayerTable.Cols.UUID + " = ?",
            new String[] {player.getId().toString()}
        );
        player.stop();
    }

    public void stopPlayer(Player player){
        player.stop();
    }

    public void updatePlayer(Player player) {
        String uuidString = player.getId().toString();
        ContentValues values = getContentValues(player);

        mDatabase.update(PlayerDbSchema.PlayerTable.NAME, values, PlayerDbSchema.PlayerTable.Cols.UUID + " = ?",
                new String[] { uuidString });
    }

    private PlayerCursorWrapper queryPlayers(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                PlayerDbSchema.PlayerTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null,
                null

        );
        return new PlayerCursorWrapper(cursor);
    }
}
