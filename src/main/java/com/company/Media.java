package com.company;

// Upper level abstract class.
public abstract class Media {
    int id;
    int rating;
    int genre;

    // Returns genre of a current object.
    public Integer getGenre() { return genre; }

    public Integer getRating() { return rating; }

    // Prints all the info about current object.
    public void printInfo() {}
}
