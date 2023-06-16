package com.crio.codingame.commands;

import java.util.List;
import java.util.Arrays;

import com.crio.codingame.entities.Contest;
import com.crio.codingame.entities.Level;
import com.crio.codingame.exceptions.QuestionNotFoundException;
import com.crio.codingame.exceptions.UserNotFoundException;
import com.crio.codingame.services.IContestService;

public class CreateContestCommand implements ICommand{

    private final IContestService contestService;

    public CreateContestCommand(IContestService contestService) {
        this.contestService = contestService;
    }

    // TODO: CRIO_TASK_MODULE_CONTROLLER
    // Execute create method of IContestService and print the result.
    // Also Handle Exceptions and print the error messsages if any.
    // Look for the unit tests to see the expected output.
    // Sample Input Token List:- ["CREATE_CONTEST","CRIODO2_CONTEST","LOW Monica","40"]
    // or
    // ["CREATE_CONTEST","CRIODO1_CONTEST","HIGH","Ross"]
    // Hint - Use Parameterized Exceptions in the Service class to match with the Unit Tests Output.

    @Override
    public void execute(List<String> tokens) {
        if(tokens.get(0).equals("CREATE-CONTEST")) {
            String contestName = tokens.get(1);
            String contestCreator = tokens.get(3);
            Integer numQuestions = null;
            if(tokens.size() > 4) {
                numQuestions = Integer.parseInt(tokens.get(4));
            }
            Level level;
            String lvl = tokens.get(2);
            if(lvl.equals("HIGH")) {
                level = Level.HIGH;
            } else if (lvl.equals("LOW")) {
                level = Level.LOW;
            } else {
                level = Level.MEDIUM;
            }
            try {
                Contest newContest = contestService.create(contestName, level, contestCreator, numQuestions);
                System.out.println(newContest);
            } catch(UserNotFoundException | QuestionNotFoundException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
}
