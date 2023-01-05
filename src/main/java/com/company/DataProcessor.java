package com.company;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.*;

// Has methods according to the task given.
public class DataProcessor {
    // Generates a Top-3 list of the best films and series in range of [3;5).
    public static List<Film> top3Rating(ArrayList<Film> list) {
        list.sort(Comparator.comparing(Media::getRating).reversed());

        return list.stream()
                .filter(element -> element.rating < 5)
                .filter(element -> element.rating >= 3)
                .limit(3)
                .collect(Collectors.toList());
    }

    // Searches for the first object in list with rating provided.
    public static Film searchRating(ArrayList<Film> list, int rating) throws NegativeInput {
        if (rating < 0) { throw new NegativeInput("positive number expected"); }

        List<Film> exactRating = list.stream()
                .filter(element -> element.rating == rating).toList();

        return exactRating.get(0);
    }
}
