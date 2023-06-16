package com.crio.jukebox.commands;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import com.crio.jukebox.repositories.SongRepository;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.Album;
import com.crio.jukebox.entities.Artist;

public class LoadDataCommand implements ICommand{
    @Override
    public void execute(List<String> tokens) {
        String fileName = tokens.get(1);
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            reader.readLine();
            String line = reader.readLine();
            HashMap<String, Song> songs = new HashMap<String, Song>();
            while(line != null) {
                List<String> songDetails = parseCSVLine(line);
                if(songDetails.size() < 6){
                    break;
                }
                String songId = songDetails.get(0);
                String songName = songDetails.get(1);
                String albumName = songDetails.get(3);
                String artistName = songDetails.get(4);
                List<String> featuredArtist = splitLastString(songDetails.get(5));
                List<Artist> fA = new ArrayList<Artist> ();
                for(String artist: featuredArtist) {
                    Artist a = new Artist(artist);
                    fA.add(a);
                }
                Song song = new Song(songId, songName, fA, new Album(albumName, artistName));
                songs.put(songId, song);
                line = reader.readLine();
            }
            SongRepository.getInstance(songs);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static List<String> parseCSVLine(String line) {
        List<String> values = new ArrayList<>();
        String[] tokens = line.split(",");
        for (String token : tokens) {
            values.add(token.trim());
        }
        return values;
    }

    private static List<String> splitLastString(String lastString) {
        List<String> values = new ArrayList<>();
        String[] tokens = lastString.split("#");
        for (String token : tokens) {
            values.add(token.trim());
        }
        return values;
    }
}
