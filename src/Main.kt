import kotlin.random.Random
import java.lang.Thread.sleep
import kotlin.system.exitProcess

// Custom exception representing the Ball
class Ball(target: String) : Exception("$target missed the ball! Game over.")

fun main() {
    var players = listOf("Jerry", "Steve", "Alex", "Kate")
    var prevPlayer = players.random()

    fun hitPlayer(hitPlayer: String) {
        val playerHandoff = players.filterNot { it == hitPlayer }.random()

        println("$prevPlayer hit $hitPlayer with the ball! Ouch!")
        sleep(2000)
        when (Random.nextInt(10)) {
            // 0-4: They're fine.
            in 0..4 -> {
                println("$hitPlayer is a little shaken, but fine.")
                prevPlayer = hitPlayer
            }

            // 5-6: They're fine, but annoyed and angry. They throw the ball back and hit the other player.
            in 5..6 -> {
                println("$hitPlayer is a little shaken, and annoyed, but fine.")
                sleep(500)
                println("$hitPlayer threw the ball back at $prevPlayer in anger!\n")
                sleep(250)
                val previousPlayer = prevPlayer
                prevPlayer = hitPlayer
                hitPlayer(previousPlayer)
            }

            // 7-8: They're hurt and leave the game to get an ice pack.
            in 7..8 -> {

                println("$hitPlayer is hurt and needs to go grab an ice pack.")
                sleep(500)

                if (players.size == 2) {
                    println("That was a rough game. Only one player remains unharmed. Let's call it a day.")
                    exitProcess(0)
                }
                // (else)
                println("$hitPlayer hands the ball to $playerHandoff and leaves the game.")

                players = players.filterNot { it == hitPlayer }
                prevPlayer = playerHandoff
            }

            // 9: OH MY GOD ARE THEY DEAD?
            9 -> {
                print("...")
                sleep(1000)
                print("$hitPlayer? ... ")
                sleep(500)
                println("Are you okay?")
                sleep(2000)
                println("${hitPlayer.uppercase()}!?")
                sleep(1000)
                println("OH MY GOD, I THINK ${hitPlayer.uppercase()} IS DEAD!")
                sleep(250)
                println("SOMEBODY CALL 911!")
                sleep(1000)
                print("$hitPlayer was hit in the head and is being transported to the hospital with life-threatening " +
                        "injuries.\nGame over. ")
                sleep(500)
                println("Hope you had fun, because $hitPlayer didn't.")
                sleep(250)
                exitProcess(0)
            }
        }
    }

    while (true) {
        val nextPlayer = players.filterNot { it == prevPlayer }.random()

        println("$prevPlayer threw the ball to $nextPlayer.\n")
        sleep(500)

        if (Random.nextInt(5) != 0) {
            try {
                throw Ball(nextPlayer)
            } catch (_: Ball) {
                if (Random.nextInt(15) == 0) {
                    hitPlayer(nextPlayer)
                } else {
                    println("$nextPlayer caught the ball.")
                    prevPlayer = nextPlayer
                }
                sleep(750)
            }
        } else {
            sleep(250)
            throw Ball(nextPlayer)
        }
    }
}
