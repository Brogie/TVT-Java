package com.company;

public class Vendor implements Comparable<Vendor> {
    private String _links;
    private String _name;
    private int _points;
    private String _quote;
    private String _origin;
    private String _range;
    private int _change;

    public Vendor(String Name, String Links, String Quote, String Origin, String Range){
        _name = Name.strip();
        _links = Links.strip();
        _quote = Quote.strip();
        _origin = Origin.strip();
        _range = Range.strip();
    }

    public void AddPoints(int Points){
        _points += Points;
    }

    @Override
    public int compareTo(final Vendor a){
        return Integer.compare( this._points, a.GetPoints());
    }

    protected int GetPoints(){
        return _points;
    }

    public String ToString(){

        return "Not Implemented";
    }
}
