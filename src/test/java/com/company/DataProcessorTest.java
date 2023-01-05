package com.company;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataProcessorTest {
    // Forms a preset list of objects that imitate the data from main program.
    private ArrayList<Film> generateTestingList() {
        ArrayList<Film> generatedList = new ArrayList<>();

        generatedList.add(new Film(1, 5, 1));
        generatedList.add(new Film(13, 5, 5));
        generatedList.add(new Film(5, 3, 1));
        generatedList.add(new Film(21, 3, 4));
        generatedList.add(new Film(9, 4, 2));
        generatedList.add(new Film(6, 2, 2));
        generatedList.add(new Film(17, 4, 4));
        generatedList.add(new Film(31, 2, 3));
        generatedList.add(new Series(8, 2, 1, 30));
        generatedList.add(new Series(2, 1, 4, 24));
        generatedList.add(new Series(11, 3, 2, 8));
        generatedList.add(new Series(19, 4, 3, 12));
        generatedList.add(new Series(25, 4, 2, 6));

        return generatedList;
    }

    @DisplayName("T1: Top 3 Rating formed correctly")
    @Test
    void top3RatingCorrect() {
        ArrayList<Film> testingList = generateTestingList();        // Generating a testing list.
        List<Film> result = DataProcessor.top3Rating(testingList);  // Getting a result by calling a method.

        // Forming a correct answer list.
        List<Film> correctList = new ArrayList<>();
        correctList.add(new Film(9, 4, 2));
        correctList.add(new Film(17, 4, 4));
        correctList.add(new Series(19, 4, 3, 12));

        // Checking if all the Top-3 ids are the same between the answer list and result list.
        assertEquals(correctList.get(0).id, result.get(0).id);
        assertEquals(correctList.get(1).id, result.get(1).id);
        assertEquals(correctList.get(2).id, result.get(2).id);
    }

    @DisplayName("T2: Top 3 Rating formed incorrectly")
    @Test
    void top3RatingIncorrect() {
        ArrayList<Film> testingList = generateTestingList();
        List<Film> result = DataProcessor.top3Rating(testingList);

        List<Film> incorrectList = new ArrayList<>();
        incorrectList.add(new Film(17, 4, 4));
        incorrectList.add(new Series(8, 2, 1, 30));
        incorrectList.add(new Series(2, 1, 4, 24));

        assertNotEquals(incorrectList.get(0).id, result.get(0).id);
        assertNotEquals(incorrectList.get(1).id, result.get(1).id);
        assertNotEquals(incorrectList.get(2).id, result.get(2).id);
    }

    @DisplayName("T3: Search by Rating formed correctly")
    @Test
    void searchRatingCorrect() throws NegativeInput {
        ArrayList<Film> testingList = generateTestingList();              // Generating a testing list.
        int rating = 4;
        int result = DataProcessor.searchRating(testingList, rating).id;  // Getting a result by calling a method.

        // Filling the correct answer id.
        int answer = 9;

        // Checking if ids are the same between the answer and result.
        assertEquals(answer, result);
    }

    @DisplayName("T4: Search by Rating formed incorrectly")
    @Test
    void searchRatingIncorrect() throws NegativeInput {
        ArrayList<Film> testingList = generateTestingList();
        int rating = 4;
        int result = DataProcessor.searchRating(testingList, rating).id;

        int answer = 13;

        assertNotEquals(answer, result);
    }
}