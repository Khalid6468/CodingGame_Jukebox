package com.crio.jukebox.commands;

import java.util.List;
import java.util.ArrayList;

import com.crio.jukebox.services.IPlaylistService;

public class ModifyPlaylistCommand implements ICommand{
    
    private IPlaylistService playlistService;

    public ModifyPlaylistCommand(IPlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Override
    public void execute(List<String> tokens) {
        if(tokens.get(0).equals("MODIFY-PLAYLIST")) {
            String addOrdelete = tokens.get(1);
            String userId = tokens.get(2);
            String playlistId = tokens.get(3);
            List<String> songIds = new ArrayList<String> ();
            for(int i=4;i<tokens.size();i++) {
                songIds.add(tokens.get(i));
            }
            if(addOrdelete.equals("ADD-SONG"))
                playlistService.addSongsToPlaylist(userId, playlistId, songIds);
            else
                playlistService.deleteSongsFromPlaylist(userId, playlistId, songIds);
        }
    }
}
