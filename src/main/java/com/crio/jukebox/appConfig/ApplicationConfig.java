package com.crio.jukebox.appConfig;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.Arrays;
import com.crio.jukebox.commands.CommandInvoker;
import com.crio.jukebox.commands.CreatePlaylistCommand;
import com.crio.jukebox.commands.CreateUserCommand;
import com.crio.jukebox.commands.DeletePlaylistCommand;
import com.crio.jukebox.commands.LoadDataCommand;
import com.crio.jukebox.commands.ModifyPlaylistCommand;
import com.crio.jukebox.commands.PlayPlaylistCommand;
import com.crio.jukebox.commands.PlaySongCommand;

// import com.crio.jukebox.repositories.IUserRepository;
// import com.crio.jukebox.repositories.ISongRepository;
// import com.crio.jukebox.repositories.IPlaylistRepository;
import com.crio.jukebox.repositories.UserRepository;
import com.crio.jukebox.repositories.SongRepository;
// import com.crio.jukebox.repositories.PlaylistRepository;

import com.crio.jukebox.services.IUserService;
import com.crio.jukebox.services.ISongService;
import com.crio.jukebox.services.IPlaylistService;
import com.crio.jukebox.services.UserService;
import com.crio.jukebox.services.SongService;
import com.crio.jukebox.services.PlaylistService;


public class ApplicationConfig {
    private final UserRepository userRepository = new UserRepository();
    // private final IPlaylistRepository playlistRepository = new PlaylistRepository();
    private SongRepository songRepository;

    private final IUserService userService = new UserService(userRepository);
    private final ISongService songService = new SongService(userRepository);
    private IPlaylistService playlistService;

    private final CreateUserCommand createUserCommand = new CreateUserCommand(userService);
    private final LoadDataCommand loadDataCommand = new LoadDataCommand(); 
    private final PlaySongCommand playSongCommand = new PlaySongCommand(songService);
    private ModifyPlaylistCommand modifyPlaylistCommand;
    private CreatePlaylistCommand createPlaylistCommand;
    private DeletePlaylistCommand deletePlaylistCommand;
    private PlayPlaylistCommand playPlaylistCommand;

    private final CommandInvoker commandInvoker = new CommandInvoker();

    // public ApplicationConfig() {
    //     songRepository = new SongRepository();

    // }

    public ApplicationConfig(List<String> toks) {
        BufferedReader reader;
        String inputFile = toks.get(0).split("=")[1];
        toks.remove(0);
        try {
            reader = new BufferedReader(new FileReader(inputFile));
            String loadCommand = reader.readLine();
            String[] firstCommand = loadCommand.split(" ");
            List<String> mCommand = Arrays.asList(firstCommand);
            loadDataCommand.execute(mCommand);
            songRepository = SongRepository.getInstance();
            playlistService = new PlaylistService(songRepository, userRepository);
            modifyPlaylistCommand = new ModifyPlaylistCommand(playlistService);
            createPlaylistCommand = new CreatePlaylistCommand(playlistService);
            deletePlaylistCommand = new DeletePlaylistCommand(playlistService);
            playPlaylistCommand = new PlayPlaylistCommand(playlistService);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    public CommandInvoker getCommandInvoker() {
        commandInvoker.register("CREATE-USER", createUserCommand);
        commandInvoker.register("CREATE-PLAYLIST", createPlaylistCommand);
        commandInvoker.register("DELETE-PLAYLIST", deletePlaylistCommand);
        commandInvoker.register("MODIFY-PLAYLIST", modifyPlaylistCommand);
        commandInvoker.register("PLAY-PLAYLIST", playPlaylistCommand);
        commandInvoker.register("PLAY-SONG", playSongCommand);
        return commandInvoker;
    }

}
