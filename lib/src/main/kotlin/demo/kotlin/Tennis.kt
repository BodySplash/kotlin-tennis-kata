package demo.kotlin


enum class Point {
    Love, Fifteen, Thirty
}


fun nextPoint(v: Point) = when (v) {
    Point.Love -> Point.Fifteen
    Point.Fifteen -> Point.Thirty
    else -> null
}


enum class Player {
    ONE, TWO
}


sealed class Score {

    data class Points(val playerOne: Point, val playerTwo: Point) : Score()

    data class Forty(val player: Player, val other: Point) : Score()

    object Deuce : Score()

    data class Advantage(val player: Player) : Score()

    data class Game(val player: Player) : Score()

    companion object {
        private val start = Points(Point.Love, Point.Love)

        private fun pointTo(winner: Player, next: Point, playerOne: Point, playerTwo: Point) = when (winner) {
            Player.ONE -> Points(next, playerTwo)
            Player.TWO -> Points(playerOne, next)
        }

        private fun currentPointOf(player: Player, playerOne: Point, playerTwo: Point) =
            when (player) {
                Player.ONE -> playerOne
                Player.TWO -> playerTwo
            }

        private fun otherPlayer(player: Player) = when (player) {
            Player.ONE -> Player.TWO
            Player.TWO -> Player.ONE
        }

        private fun scoreWithPoints(winner: Player, score: Points): Score {
            val (playerOne, playerTwo) = score
            return nextPoint(currentPointOf(winner, playerOne, playerTwo)).let { next ->
                if (next != null) pointTo(winner, next, playerOne, playerTwo)
                else Forty(winner, currentPointOf(otherPlayer(winner), playerOne, playerTwo))
            }

        }

        private fun scoreForty(score: Forty, player: Player): Score {
            val (p, other) = score
            return if (p == player) Game(player)
            else
                with(nextPoint(other)) {
                    if (this == null) Deuce
                    else
                        Forty(p, this)


                }
        }

        private fun scoreDeuce(player: Player) = Advantage(player)

        private fun scoreAdvantage(score: Advantage, player: Player) =
            if (score.player == player) Game(player) else Deuce


        private fun next(score: Score, player: Player) = when (score) {
            is Advantage -> scoreAdvantage(score, player)
            Deuce -> scoreDeuce(player)
            is Forty -> scoreForty(score, player)
            is Game -> score
            is Points -> scoreWithPoints(player, score)
        }

        fun score(points: List<Player>) = points.fold(start, ::next)
    }

}
