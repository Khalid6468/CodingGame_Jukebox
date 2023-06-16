package com.crio.codingame.commands;

import java.util.List;

import com.crio.codingame.entities.Contest;
import com.crio.codingame.entities.Level;
import com.crio.codingame.services.IContestService;

public class ListContestCommand implements ICommand{

    private final IContestService contestService;
    
    public ListContestCommand(IContestService contestService) {
        this.contestService = contestService;
    }

    // TODO: CRIO_TASK_MODULE_CONTROLLER
    // Execute getAllContestLevelWise method of IContestService and print the result.
    // Look for the unit tests to see the expected output.
    // Sample Input Token List:- ["LIST_CONTEST","HIGH"]
    // or
    // ["LIST_CONTEST"]

    @Override
    public void execute(List<String> tokens) {
        if(tokens.get(0).equals("LIST-CONTEST")) {
            Level lvl = null;
            if(tokens.size()>1) {
                lvl = tokens.get(1).equals("HIGH") ? Level.HIGH : (tokens.get(1).equals("LOW") ? Level.LOW : Level.MEDIUM);
            }
            List<Contest> contestList = contestService.getAllContestLevelWise(lvl);
            System.out.println(contestList);
        }
    }
    
}
