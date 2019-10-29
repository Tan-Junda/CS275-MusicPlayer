package jtan5.example.musicplayer;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

public class PlayerLab {

    private ArrayList<Player> mPlayers;
    private static PlayerLab sPlayerLab;
    private Context mAppContext;

    private PlayerLab(Context appContext) {
        mAppContext = appContext;
        mPlayers = new ArrayList<Player>();
        for (int i = 0; i < 5; i++) {
            Player p = new Player();
            p.setTitle("Music #" + i);
            mPlayers.add(p);
        }
    }

    public static PlayerLab get(Context c) {
        if (sPlayerLab == null) {
            sPlayerLab = new PlayerLab(c.getApplicationContext());
        }
        return sPlayerLab;
    }

    public ArrayList<Player> getPlayers() {
        return mPlayers;
    }

    public Player getPlayer(UUID id) {
        for (Player p : mPlayers) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    public void addPlayer(Player player) {
        mPlayers.add(player);
    }
    public void deletePlayer(Player player) {
        mPlayers.remove(player);
        player.getMusicPlayer().stop();
    }


}
