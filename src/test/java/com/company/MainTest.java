package com.company;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @DisplayName("T1: Word Endings formed correctly")
    @Test
    void addEndingCorrect() {
        assertEquals("15 фильмов", Main.addEnding(15, "фильм"));
        assertEquals("102 сериала", Main.addEnding(102, "сериал"));
        assertEquals("3 игры", Main.addEnding(3, "игр"));
    }

    @DisplayName("T2: Word Endings formed incorrectly")
    @Test
    void addEndingIncorrect() {
        assertNotEquals("-3 игры", Main.addEnding(-3, "игр"));
    }
}