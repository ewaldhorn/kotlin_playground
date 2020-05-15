package mastermind

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun evaluateGuess(secret: String, guess: String): Evaluation {
    var rightPosition = 0
    var wrongPosition = 0

    if (secret == guess) {
        rightPosition = 4
    } else {
        getMatchingLetterCount(secret, guess)
    }

    return Evaluation(rightPosition, wrongPosition)
}

private fun getMatchingLetterCount(secret: String, guess: String): Int {
    val r = secret.forEachIndexed {idx, char -> if (guess[idx] == char) 1 else 0}
    print("R is $r")
    return 0
}