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

        val result = Score.score(listOf(Player.ONE))

        assertEquals(expected, result)
    }

    @Test
    fun playerOneScoreTwice() {
        val expected = Score.Points(Point.Thirty, Point.Love)

        val result = Score.score(List(2) { Player.ONE })

        assertEquals(expected, result)
    }

    @Test
    fun playerOneScoreThreeTimes() {
        val expected = Score.Forty(Player.ONE, Point.Love)

        val result = Score.score(List(3) { Player.ONE })

        assertEquals(expected, result)
    }

    @Test
    fun playerOneScoreFourTimes() {
        val expected = Score.Game(Player.ONE)

        val result = Score.score(List(4) { Player.ONE })

        assertEquals(expected, result)
    }

    @Test
    fun playerTwoScoreFourTimes() {
        val expected = Score.Game(Player.TWO)

        val result = Score.score(List(4) { Player.TWO })

        assertEquals(expected, result)
    }

    @Test
    fun playerOneScoreThreeTimesAndPlayerTwoComeBack() {
        val expected = Score.Forty(Player.ONE, Point.Fifteen)

        val result = Score.score(List(3) { Player.ONE }.plus(Player.TWO))

        assertEquals(expected, result)
    }

    @Test
    fun fortyThenDeuce() {
        val expected = Score.Deuce

        val result = Score.score(List(3) { Player.ONE }.plus(List(3) { Player.TWO }))

        assertEquals(expected, result)
    }

    @Test
    fun deuceThanAdvantage() {
        val expected = Score.Advantage(Player.ONE)

        val result = Score.score(List(3) { Player.ONE }.plus(List(3) { Player.TWO }).plus(Player.ONE))

        assertEquals(expected, result)
    }

    @Test
    fun advantageThenDeuce() {
        val expected = Score.Deuce

        val result = Score.score(List(3) { Player.ONE }.plus(List(3) { Player.TWO }).plus(Player.ONE).plus(Player.TWO))

        assertEquals(expected, result)
    }

    @Test
    fun advantageAndGame() {
        val expected = Score.Game(Player.ONE)

        val result = Score.score(List(3) { Player.ONE }.plus(List(3) { Player.TWO }).plus(Player.ONE).plus(Player.ONE))

        assertEquals(expected, result)
    }
}