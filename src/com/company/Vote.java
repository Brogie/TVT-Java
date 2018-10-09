package com.company;

import java.text.ParseException;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

class Vote {
    private Date _voteTime;
    private String _user;
    private String[] _votes;
    private Boolean Valid;
    private List<String> InvalidationReason;

    public Vote(String VoteTime, String User, String[] Votes){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        try {
            _voteTime = dateFormat.parse(VoteTime);
        } catch (ParseException e) {
            e.printStackTrace();
            _voteTime = null;
        }

        //If users submitted malformed username (starting with /u/ or u/) clean them up
        if(User.startsWith("/u/")){
            User = User.substring(3);
        }

        if(User.startsWith("u/")){
            User = User.substring(2);
        }

        _user = User;

        _votes = Votes;
        Valid = true;
        InvalidationReason = new ArrayList<>();
    }

    public void Invalidate(String Reason){
        Valid = false;
        InvalidationReason.add(Reason);
    }

    public String Key(){
        return Arrays.toString(_votes);
    }


}
