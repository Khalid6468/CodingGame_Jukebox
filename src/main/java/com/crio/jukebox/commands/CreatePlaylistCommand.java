package com.crio.jukebox.commands;

import java.util.List;
import java.util.ArrayList;

import com.crio.jukebox.services.IPlaylistService;

public class CreatePlaylistCommand implements ICommand{
    
    private IPlaylistService playlistService;

    public CreatePlaylistCommand(IPlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Override
    public void execute(List<String> tokens) {
        if(tokens.get(0).equals("CREATE-PLAYLIST")) {
            String userId = tokens.get(1);
            String playlistName = tokens.get(2);
            List<String> songIds = new ArrayList<String> ();
            for(int i=3;i<tokens.size();i++) {
                songIds.add(tokens.get(i));
            }
            playlistService.createPlaylist(userId, playlistName, songIds);
        }
    }
}
