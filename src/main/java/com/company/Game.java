package com.company;

public class Game extends Media {
    // Constructor.
    public Game(int id, int rating, int genre) {
        this.id = id;
        this.rating = rating;
        this.genre = genre;
    }

    // Prints all the info about current object.
    public void printInfo() {
        System.out.println("Game   | ID: " + id +
                ", Rating: " + rating +
                ", Genre: " + genre);
    }
}
