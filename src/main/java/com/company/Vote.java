package com.company;

import java.text.ParseException;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

class Vote {
    private Date _voteTime;
    private String _user;
    private String[] _votes;
    private Boolean _valid;
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

        //If user voted for the same vendor multiple times in the same vote reject the lower vote
        List<String> Voted = new ArrayList<>();

        _votes = new String[Votes.length];

        for (int i = 0; i < Votes.length; i++) {
            if(!Voted.contains(Votes[i])){
                _votes[i] = Votes[i];
            }
        }

        _valid = true;
        InvalidationReason = new ArrayList<>();
    }

    public void Invalidate(String Reason){
        _valid = false;
        InvalidationReason.add(Reason);
    }

    public  String GetReason(){
        String output = "";

        for (String s :
                InvalidationReason) {
            output += s + ", ";
        }

        return output.equals("")? "Vote is valid":output;
    }

    public String Key(){
        return Arrays.toString(_votes);
    }

    public Boolean IsValid(){
        return _valid;
    }

    public String GetUsername(){
        return _user;
    }

    public String GetFirst(){
        return _votes[0];
    }
    public String GetSecond(){
        return _votes[1];
    }
    public String GetThird(){
        return _votes[2];
    }
    public String GetRunner1(){
        return _votes[3];
    }
    public String GetRunner2(){
        return _votes[4];
    }


}
