package com.company;

public class Film extends Media {
    // Constructor.
    public Film(int id, int rating, int genre) {
        this.id = id;
        this.rating = rating;
        this.genre = genre;
    }

    // Prints all the info about current object.
    public void printInfo() {
        System.out.println("Film   | ID: " + id +
                ", Rating: " + rating +
                ", Genre: " + genre);
    }
}
