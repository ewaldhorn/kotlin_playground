package thewitcher

interface Player {
    fun playGame()
}

class RpgGamePlayer(val enemy: String): Player {
    override fun playGame() {
        println("Killing $enemy")
    }
}

class WitcherPlayer1(enemy: String) : Player by RpgGamePlayer(enemy)
class WitcherPlayer2() : Player by RpgGamePlayer("monsters")
class WitcherPlayer3(enemy: String) : Player by RpgGamePlayer(enemy)
class WitcherPlayer4(player: Player) : Player by player
class WitcherPlayer5(val player: Player = RpgGamePlayer("monsters")) : Player by player

val player = RpgGamePlayer("monsters")
class WitcherPlayer6(): Player by player