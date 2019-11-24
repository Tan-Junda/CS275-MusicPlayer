package jtan5.example.musicplayer;

import android.database.Cursor;
import android.database.CursorWrapper;
import android.media.MediaPlayer;

import java.util.UUID;

public class PlayerCursorWrapper extends CursorWrapper {
    public PlayerCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Player getPlayer() {
        String uuidString = getString(getColumnIndex(PlayerDbSchema.PlayerTable.Cols.UUID));
        String title = getString(getColumnIndex(PlayerDbSchema.PlayerTable.Cols.TITLE));
        int isPlaying = getInt(getColumnIndex(PlayerDbSchema.PlayerTable.Cols.IS_PLAYING));
        Player player = new Player(UUID.fromString(uuidString));
        player.setTitle(title);
        player.setPlaying(isPlaying == 1);
        return player;
    }
}
