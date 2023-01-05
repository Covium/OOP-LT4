package com.company;

import java.util.*;

public class Main {
    // #0 Useful methods.
    // Adds a russian ending to a word based on a word provided in the type variable.
    static String addEnding(int number, String type) {
        int[] singularExclusions = {2, 3, 4};       // Exceptions for the last number.
        int[] doubleExclusions = {11, 12, 13, 14};  // Exceptions for the last two numbers.

        if (Objects.equals(type, "игр")) {
            if (Arrays.stream(doubleExclusions).anyMatch(i -> i == (number % 100)))
                return number + " " + type;
            else if (number % 10 == 1)
                return number + " " + type + "у";
            else if (Arrays.stream(singularExclusions).anyMatch(i -> i == (number % 10)))
                return number + " " + type + "ы";
            else
                return number + " " + type;
        } else {
            if (Arrays.stream(doubleExclusions).anyMatch(i -> i == (number % 100)))
                return number + " " + type + "ов";
            else if (number % 10 == 1)
                return number + " " + type;
            else if (Arrays.stream(singularExclusions).anyMatch(i -> i == (number % 10)))
                return number + " " + type + "а";
            else
                return number + " " + type + "ов";
        }
    }
    // End of #0.


    // #2 Prints rating data of films, series, and games provided. Prints info about all the objects. With classes.
    static void printRatingsWithClasses(int[] filmIDs, int[] filmRatings, int[] filmGenres,
                                        int[] seriesIDs, int[] seriesRatings, int[] seriesGenres, int[] seriesEpisodes,
                                        int[] gameIDs, int[] gameRatings, int[] gameGenres) {
        ArrayList<Film> filmsAndSeries = new ArrayList<>();  // Array for Film and Series objects.
        ArrayList<Game> games = new ArrayList<>();   // Array for Game objects.
        ArrayList<Media> media = new ArrayList<>();  // Array for Media (includes Film, Series, and Game) objects.

        float sumRating   = 0;  // The sum of all Film and Series ratings.
        int bestRatingID  = 0;  // ID of the best rated Film or Series.
        int worstRatingID = 0;  // ID of the worst rated Film or Series.

        int filmsAmount  = filmIDs.length;    // Amount of films given.
        int seriesAmount = seriesIDs.length;  // Amount of series given.
        int gamesAmount  = gameIDs.length;    // Amount of games given.

        // Adding media into an ArrayList from their respective input arrays.
        for (int i = 0; i < filmsAmount; i++) {
            filmsAndSeries.add(new Film(filmIDs[i], filmRatings[i], filmGenres[i]));
            sumRating += filmsAndSeries.get(i).rating;
            if (filmsAndSeries.get(i).rating >= filmsAndSeries.get(bestRatingID).rating) bestRatingID = i;
            if (filmsAndSeries.get(i).rating <= filmsAndSeries.get(worstRatingID).rating) worstRatingID = i;
        }

        for (int i = 0; i < seriesAmount; i++) {
            int current = i + filmsAmount;
            filmsAndSeries.add(new Series(seriesIDs[i], seriesRatings[i], seriesGenres[i], seriesEpisodes[i]));
            sumRating += filmsAndSeries.get(current).rating;
            if (filmsAndSeries.get(current).rating >= filmsAndSeries.get(bestRatingID).rating) bestRatingID = current;
            if (filmsAndSeries.get(current).rating <= filmsAndSeries.get(worstRatingID).rating) worstRatingID = current;
        }

        for (int i = 0; i < gamesAmount; i++) {
            games.add(new Game(gameIDs[i], gameRatings[i], gameGenres[i]));
        }

        // Printing out average rating, best, and worst Film and Series by rating.
        System.out.println("## Основной рейтинг:");
        System.out.printf("Средний рейтинг: %.1f%n", sumRating/filmsAndSeries.size());
        System.out.println("Лучшая оценка (" + filmsAndSeries.get(bestRatingID).rating  + ") " +
                ((filmsAndSeries.get(bestRatingID).getClass().getSimpleName().equals("Film"))? "у фильма: ":"у сериала: ") +
                filmsAndSeries.get(bestRatingID).id);
        System.out.println("Худшая оценка (" + filmsAndSeries.get(worstRatingID).rating  + ") " +
                ((filmsAndSeries.get(worstRatingID).getClass().getSimpleName().equals("Film"))? "у фильма: ":"у сериала: ") +
                filmsAndSeries.get(worstRatingID).id);
        System.out.println();

        // Adding ALL the media (Film, Series, and Game) we have into the media array.
        for (int i = 0; i < filmsAmount + seriesAmount; i++) {
            media.add(filmsAndSeries.get(i));
        }

        for (int i = 0; i < gamesAmount; i++) {
            media.add(games.get(i));
        }

        media.sort(Comparator.comparing(Media::getGenre));  // Sorting all media by genre.

        float sumRatingPG  = 0;  // The sum of media ratings per genre.
        int filmsAmountPG  = 0;  // The amount of films per genre.
        int seriesAmountPG = 0;  // The amount of series per genre.
        int gamesAmountPG = 0;   // The amount of games per genre.

        // Counting the amount of media and the sum of ratings per genre.
        System.out.println("## Перечисление жанров:");
        for (int i = 0; i < media.size()-1; i++) {
            sumRatingPG += media.get(i).rating;  // Increasing sum of media rating per genre regardless of media type.

            // Incrementing an amount of media per genre regarding the media type.
            if ((media.get(i).getClass().getSimpleName().equals("Film"))) { filmsAmountPG++; }
            else if ((media.get(i).getClass().getSimpleName().equals("Series"))) { seriesAmountPG++; }
            else { gamesAmountPG++; }

            // Print out genre corresponding data if next media has different genre.
            if (media.get(i).genre != media.get(i+1).genre) {

                System.out.printf("Жанр " + media.get(i).genre + ": " + "имеет " +
                        addEnding(filmsAmountPG, "фильм") + ", " +
                        addEnding(seriesAmountPG, "сериал") + " и " +
                        addEnding(gamesAmountPG, "игр") +
                        " со средней оценкой %.1f%n", (sumRatingPG/(filmsAmountPG+seriesAmountPG+gamesAmountPG)));
                // Nullifying all the data per genre so the last element could use empty values.
                sumRatingPG = 0;
                filmsAmountPG = 0;
                seriesAmountPG = 0;
                gamesAmountPG = 0;
            }

            // Print out genre corresponding data including the next media in list if current item is last in cycle.
            if (i == media.size()-2) {
                sumRatingPG += media.get(i+1).rating;
                if ((media.get(i+1).getClass().getSimpleName().equals("Film"))) { filmsAmountPG++; }
                else if ((media.get(i+1).getClass().getSimpleName().equals("Series"))) { seriesAmountPG++; }
                else { gamesAmountPG++; }

                System.out.printf("Жанр " + media.get(i+1).genre + ": " + "имеет " +
                        addEnding(filmsAmountPG, "фильм") + ", " +
                        addEnding(seriesAmountPG, "сериал") + " и " +
                        addEnding(gamesAmountPG, "игр") +
                        " со средней оценкой %.1f%n", (sumRatingPG/(filmsAmountPG+seriesAmountPG+gamesAmountPG)));
            }
        }
        System.out.println();

        // Printing data of all the objects included in the media.
        System.out.println("## Перечисление всех объектов:");
        for (int i = 0; i < filmsAmount + seriesAmount + gamesAmount; i++) media.get(i).printInfo();
        System.out.println();

        // Printing a Top-3 among films and series.
        List<Film> rating = DataProcessor.top3Rating(filmsAndSeries);
        System.out.println("## Вывод Топ-3 лучших фильмов/сериалов:");
        for (Film film : rating) film.printInfo();
        System.out.println();

        // Searching for the requested rating among films and series and taking the first one found.
        System.out.println("## Поиск первого встреченного объекта по рейтингу: ");
        System.out.print("Введите рейтинг: ");
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                DataProcessor.searchRating(filmsAndSeries, scanner.nextInt()).printInfo();
                break;
            } catch (InputMismatchException e) {
                System.out.print("Вы ввели строку, а не число.\nПожалуйста, введите число: ");
            } catch (NegativeInput e) {
                System.out.print("Вы ввели отрицательное число.\nПожалуйста, введите число: ");
            } catch (IndexOutOfBoundsException e) {
                System.out.print("Объект с таким рейтингом не найден.");
                break;
            }
        }
    }
    // End of #2.


    // Driver.
    public static void main(String[] args) {
        int[] filmIDs        = {1, 13, 5, 21, 9, 6, 17, 31};
        int[] filmRatings    = {5, 5,  3, 3,  4, 2, 4,  2};
        int[] filmGenres     = {1, 5,  1, 4,  2, 2, 4,  3};

        int[] seriesIDs      = {8,  2,  11, 19, 25};
        int[] seriesRatings  = {2,  1,  3,  4,  4};
        int[] seriesGenres   = {1,  4,  2,  3,  2};
        int[] seriesEpisodes = {30, 24, 8,  12, 6};

        int[] gameIDs        = {12, 3};
        int[] gameRatings    = {2,  5};
        int[] gameGenres     = {3,  5};

        System.out.println("\n### С ИСПОЛЬЗОВАНИЕМ КЛАССОВ");
        printRatingsWithClasses(filmIDs, filmRatings, filmGenres,
                                seriesIDs, seriesRatings, seriesGenres, seriesEpisodes,
                                gameIDs, gameRatings, gameGenres);
    }
    // End of Driver.
}
