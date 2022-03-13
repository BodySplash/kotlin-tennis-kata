package demo.kotlin

import kotlin.test.Test
import kotlin.test.assertEquals

class TennisTest {
    @Test
    fun startsAtLove() {
        val expected = Score.Points(Point.Love, Point.Love)

        assertEquals(Score.score(listOf()), expected)
    }

    @Test
    fun playerOneScore() {
        val expected = Score.Points(Point.Fifteen, Point.Love)

        val result = Score.score(listOf(Player.One))

        assertEquals(expected, result)
    }

    @Test
    fun playerOneScoreTwice() {
        val expected = Score.Points(Point.Thirty, Point.Love)

        val result = Score.score(List(2) { Player.One })

        assertEquals(expected, result)
    }

    @Test
    fun playerOneScoreThreeTimes() {
        val expected = Score.Forty(Player.One, Point.Love)

        val result = Score.score(List(3) { Player.One })

        assertEquals(expected, result)
    }

    @Test
    fun playerOneScoreFourTimes() {
        val expected = Score.Game(Player.One)

        val result = Score.score(List(4) { Player.One })

        assertEquals(expected, result)
    }

    @Test
    fun playerTwoScoreFourTimes() {
        val expected = Score.Game(Player.Two)

        val result = Score.score(List(4) { Player.Two })

        assertEquals(expected, result)
    }

    @Test
    fun playerOneScoreThreeTimesAndPlayerTwoComeBack() {
        val expected = Score.Forty(Player.One, Point.Fifteen)

        val result = Score.score(List(3) { Player.One }.plus(Player.Two))

        assertEquals(expected, result)
    }

    @Test
    fun fortyThenDeuce() {
        val expected = Score.Deuce

        val result = Score.score(List(3) { Player.One }.plus(List(3) { Player.Two }))

        assertEquals(expected, result)
    }

    @Test
    fun deuceThanAdvantage() {
        val expected = Score.Advantage(Player.One)

        val result = Score.score(List(3) { Player.One }.plus(List(3) { Player.Two }).plus(Player.One))

        assertEquals(expected, result)
    }

    @Test
    fun advantageThenDeuce() {
        val expected = Score.Deuce

        val result = Score.score(List(3) { Player.One }.plus(List(3) { Player.Two }).plus(Player.One).plus(Player.Two))

        assertEquals(expected, result)
    }

    @Test
    fun advantageAndGame() {
        val expected = Score.Game(Player.One)

        val result = Score.score(List(3) { Player.One }.plus(List(3) { Player.Two }).plus(Player.One).plus(Player.One))

        assertEquals(expected, result)
    }
}