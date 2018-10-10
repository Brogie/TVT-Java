# TVT-Java
## What the program does

This program takes a google form (exported to CSV) of a vote on tea vendors and generates a markdown table that can be pasted into a reddit wiki page, each vote has a reddit users favourite five vendors.

The Program can do two differnt tallying systems:
* Un-weighted: All votes are treated equally (one vote one point)
* Weighted: Votes are ranked so that vendors get more points if they got higher up in a users vote.
  * 1st = 5 
  * 2nd = 4 
  * 3rd = 3 
  * Runner-Up 1 = 1 
  * Runner-Up 2 = 1

### Example

https://www.reddit.com/r/tea/wiki/vendors/page_01

## Why the program was chosen

This was chosen as I understood the scope of the program and was only interesed in learning java and I knew this project would have some interesting challenges

## How to run the program

For data to run please request them directly from me

Simple commandline program with basic commands

* -v for the vendor CSV (A list of all vendors that have been voted on, in markdown so you can copy and paste the old markdown tables)
* -p for the votes CSV (The google form results downloaded as .CSV with no editing)
* -w true/false for weighted votes

For example
>TeaTally -v Vendors.csv -p Votes.CSV -w true

or put this in the program arguments

>-v Vendors.csv -p Votes.CSV -w true
