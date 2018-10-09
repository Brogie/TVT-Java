package com.company;

import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws Exception {
        //FileLocations
        String VendorFileLocation = "";
        String VoteFileLocation = "";
        //Data
        Map<String,Vendor> Vendors = new HashMap<>();
        List<Vote> Votes = new ArrayList<>();


        //Read Arguments
        char CurrentOption = ' ';
        for (int i = 0; i < args.length; i++) {
            if (args[i].charAt(0) == '-') {
                if (args[i].length() < 2) {
                    throw new IllegalArgumentException("Not a valid argument: " + args[i]);
                } else {
                    CurrentOption = args[i].charAt(1);
                }
            } else {
                switch (CurrentOption) {
                    case 'v':
                        VendorFileLocation = args[i];
                        break;
                    case 'p':
                        VoteFileLocation = args[i];
                        break;

                }
            }
        }

        //Verify Files
        if (VendorFileLocation.equals("")) {
            throw new Exception("No vendor file selected");
        }
        if (VoteFileLocation.equals("")) {
            throw new Exception("No vendor file selected");
        }

        //Load in the vendors
        LoadVendors(Vendors, VendorFileLocation);

        //Load Votes
        LoadVotes(VoteFileLocation, Votes);

        //Verify Votes
        VerifyVotes(Votes);

        //Tally Votes
        TallyVotes(Votes, Vendors, false);

        //Sort votes

        //Write Table
    }

    private static void TallyVotes(List<Vote> Votes, Map<String, Vendor> Vendors, Boolean Weighted){
        //Add points
        for (Vote v :
                Votes) {
            if(Vendors.containsKey(v.GetFirst())){
                Vendors.get(v.GetFirst()).AddPoints(Weighted?5:1);
            }
            if(Vendors.containsKey(v.GetSecond())){
                Vendors.get(v.GetSecond()).AddPoints(Weighted?4:1);
            }
            if(Vendors.containsKey(v.GetThird())){
                Vendors.get(v.GetThird()).AddPoints(Weighted?3:1);
            }
            if(Vendors.containsKey(v.GetRunner1())){
                Vendors.get(v.GetRunner1()).AddPoints(Weighted?1:1);
            }
            if(Vendors.containsKey(v.GetRunner2())){
                Vendors.get(v.GetRunner2()).AddPoints(Weighted?1:1);
            }
        }

    }

    private static void VerifyVotes(List<Vote> Votes) {
        //Find repetitive votes which could indicate vote stuffing
        Map<String, Integer> RepetitivenessCount = new HashMap<>();

        for (Vote v :
                Votes) {
            //increment similar votes
            int count = RepetitivenessCount.containsKey(v.Key()) ? RepetitivenessCount.get(v.Key()) : 0;
            RepetitivenessCount.put(v.Key(), count + 1);
        }

        /*
        The following sorting system has been taken from https://howtodoinjava.com/sort/java-sort-map-by-values/
        */
        //LinkedHashMap preserve the ordering of elements in which they are inserted
        LinkedHashMap<String, Integer> reverseSortedMap = new LinkedHashMap<>();

        //Use Comparator.reverseOrder() for reverse ordering
        RepetitivenessCount.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));

        //Remove votes where one user has voted multiple times
        List<String> UsernameCount = new ArrayList<>();
        List<String> MultipleCountFound = new ArrayList<>();

        for (Vote v :
                Votes) {
            //increment similar votes
            if(UsernameCount.contains(v.GetUsername())){
                if(!MultipleCountFound.contains(v.GetUsername())){
                    MultipleCountFound.add(v.GetUsername());
                }
            } else {
                UsernameCount.add(v.GetUsername());
            }
        }

        //Finally invalidate multiple voter votes
        for (Vote v:
             Votes) {
            if(MultipleCountFound.contains(v.GetUsername())){
                v.Invalidate("Voter voted multiple times with the same username");
            }
        }
    }

    private static void LoadVotes(String voteFileLocation, List<Vote> votes) {
        System.out.print("Loading Votes: ");

        //Simple CSV reader
        BufferedReader br;
        String line;

        try {
            br = new BufferedReader(new FileReader(voteFileLocation));

            //Read past header
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] Data = line.split(",");

                votes.add(new Vote(Data[0], Data[1], Arrays.copyOfRange(Data, 2, 7)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("[" + votes.size() + "]");
    }

    private static void LoadVendors(Map<String, Vendor> Vendors, String FileLocation) {
        System.out.print("Loading Vendors: ");

        //Load Vendors
        BufferedReader br;
        String line;

        try {
            br = new BufferedReader(new FileReader(FileLocation));

            while ((line = br.readLine()) != null) {
                String[] Data = line.split("\\|");

                String name = Data[0].replaceAll("\\(.*?\\) ?", "");
                name = name.replaceAll("\\[", "");
                name = name.replaceAll("]", "");

                Vendors.put(name, new Vendor(name, Data[0], Data[1], Data[2], Data[3]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("[" + Vendors.size() + "]");
    }
}

