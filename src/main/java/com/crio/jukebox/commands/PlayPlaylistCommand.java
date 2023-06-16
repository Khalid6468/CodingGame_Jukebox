package com.crio.jukebox.commands;

import java.util.List;

import com.crio.jukebox.services.IPlaylistService;

public class PlayPlaylistCommand implements ICommand {
    
    private IPlaylistService playlistService;

    public PlayPlaylistCommand(IPlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Override
    public void execute(List<String> tokens) {
        if(tokens.get(0).equals("PLAY-PLAYLIST")) {
            String userId = tokens.get(1);
            String playlistId = tokens.get(2);
            playlistService.playPlaylist(userId, playlistId);
        }
    }
}
