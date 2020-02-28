package calculation

/**
 * Takes an array and an initial value and applies the passed-in function.
 */
fun compute(array: IntArray, initValue: Int, op: (Int, Int) -> Int): Int {
    var answer = initValue

    for (item in array) {
        answer = op(answer, item)
    }

    return answer
}
