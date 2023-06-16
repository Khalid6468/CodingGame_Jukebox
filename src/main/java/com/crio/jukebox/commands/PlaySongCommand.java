package com.crio.jukebox.commands;

import java.util.List;

import com.crio.jukebox.services.ISongService;

public class PlaySongCommand implements ICommand{
    
    private ISongService songService;

    public PlaySongCommand(ISongService songService) {
        this.songService = songService;
    }

    @Override
    public void execute(List<String> tokens) {
        if(tokens.get(0).equals("PLAY-SONG")) {
            String userId = tokens.get(1);
            String nextOrBackOrSong = tokens.get(2);
            if(nextOrBackOrSong.equals("NEXT")) {
                songService.playNextSong(userId);
            } else if(nextOrBackOrSong.equals("BACK")) {
                songService.playPrevSong(userId);
            } else {
                songService.playSong(userId, nextOrBackOrSong);
            }
        }
    }
}
