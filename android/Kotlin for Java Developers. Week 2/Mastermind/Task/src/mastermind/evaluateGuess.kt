package mastermind

import kotlin.math.min

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun evaluateGuess(secret: String, guess: String): Evaluation {
    val validLetters = getValidLetterCount(secret, guess)
    var rightPosition = 0
    var wrongPosition = 0

    if (secret == guess) {
        rightPosition = 4
    } else {
        rightPosition = getMatchingLetterCount(secret, guess)
        wrongPosition = getValidLetterCount(secret, guess) - rightPosition
    }
    return Evaluation(rightPosition, wrongPosition)
}

// Find out which, if any, letters are an exact match
private fun getMatchingLetterCount(secret: String, guess: String) =
    secret.mapIndexed { idx, ch -> if (guess[idx] == ch) 1 else 0 }.sum()

// Check all our letters and find the count of shared letters between secret and guess
// Need to convert them to charArray to be able to create a distinct list to avoid duplicates
private fun getValidLetterCount(secret: String, guess: String) =
    guess.toCharArray().plus(secret.toCharArray()).distinct().sumBy { ch ->
        min(guess.count { it == ch }, secret.count { it == ch })
    }