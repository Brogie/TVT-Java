package com.company;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Vote2TestingBoogaloo {
    private String[] votes;

    public Vote2TestingBoogaloo(String[] Votes) throws NullPointerException {
        if(Votes == null){throw new NullPointerException("Null votes are not accepted");}

        //If user voted for the same vendor multiple times in the same vote reject the lower vote
        List<String> Voted = new ArrayList<>();

        votes = new String[Votes.length];

        for (int i = 0; i < Votes.length; i++) {
            if(!Voted.contains(Votes[i])){
                votes[i] = Votes[i];
            }
        }
    }

    public String getFirst(){
        return votes[0];
    }

    public String[] getVotes() {
        return votes;
    }
}
