package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.services.IUserService;

public class CreateUserCommand implements ICommand{

    private final IUserService userService;

    public CreateUserCommand(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(List<String> tokens) {
        if(tokens.get(0).equals("CREATE-USER")) {
            String userName = tokens.get(1);
            userService.createUser(userName);
        }
    }
}
