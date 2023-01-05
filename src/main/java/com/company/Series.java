package com.company;

public class Series extends Film {
    public final int episodes;

    // Constructor.
    public Series(int id, int rating, int genre, int episodes) {
        super(id, rating, genre);
        this.episodes = episodes;
    }

    // Prints all the info about current object.
    public void printInfo() {
        System.out.println("Series | ID: " + id +
                ", Rating: " + rating +
                ", Genre: " + genre +
                ", Episodes " + episodes);
    }

    // Returns episodes of a current object.
    // public Integer getEpisodes() { return episodes; }
}
