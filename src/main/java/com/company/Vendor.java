package com.company;

public class Vendor implements Comparable<Vendor> {
    private String links;
    private String name;
    private int points;
    private String quote;
    private String origin;
    private String range;
    private int change;

    public Vendor(String Name, String Links, String Quote, String Origin, String Range){
        name = Name.strip();
        links = Links.strip();
        quote = Quote.strip();
        origin = Origin.strip();
        range = Range.strip();
    }

    public void AddPoints(int Points){
        points += Points;
    }

    @Override
    public int compareTo(final Vendor a){
        return Integer.compare( this.points, a.GetPoints());
    }

    protected int GetPoints(){
        return points;
    }

    //in format "Vendor/Website Link | Reddit User / Vendor Comments | Shipping Origin | Shipping Range"
    @Override
    public String toString(){

        return links + " | " + quote + " | " + origin + " | " + range;
    }
}
