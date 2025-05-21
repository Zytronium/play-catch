import kotlin.random.Random
import java.lang.Thread.sleep

// Custom exception representing the Ball
class Ball(target: String) : Exception("$target missed the ball! Game over.")

fun main() {
    val players = listOf("Bill", "Steve", "Jim")
    var prevPlayer = players.random()
    while (true) {
        val nextPlayer = players.filterNot { it == prevPlayer }.random()

        println("$prevPlayer threw the ball to $nextPlayer.\n")
        sleep(500)
        if (Random.nextInt(5) != 0) {
            try {
                throw Ball(nextPlayer)
            } catch (_: Ball) {
                println("$nextPlayer caught the ball.")
                prevPlayer = nextPlayer
                sleep(750)
            }
        } else {
            sleep(250)
            throw Ball(nextPlayer)
        }
    }
}
