package com.crio.jukebox.commands;

import java.util.List;

import com.crio.jukebox.services.IPlaylistService;

public class DeletePlaylistCommand implements ICommand{
    
    private IPlaylistService playlistService;

    public DeletePlaylistCommand(IPlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Override
    public void execute(List<String> tokens) {
        if(tokens.get(0).equals("DELETE-PLAYLIST")) {
            String userId = tokens.get(1);
            String playlistId = tokens.get(2);
            playlistService.deletePlaylist(userId, playlistId);
        }
    }
}
