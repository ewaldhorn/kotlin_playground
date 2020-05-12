fun isValidIdentifier(s: String): Boolean {
    if (s.isNotEmpty()) {
        return when (s[0]) {
            in 'a'..'z', in 'A'..'Z', '_' -> {
                for (c in s) {
                    if (c !in 'a'..'z' && c !in 'A'..'Z' && c !in '0'..'9' && c != '_') {
                        return false
                    }
                }
                true
            }
            else -> false
        }
    }

    return false
}

fun main(args: Array<String>) {
    println(isValidIdentifier("name"))   // true
    println(isValidIdentifier("_name"))  // true
    println(isValidIdentifier("_12"))    // true
    println(isValidIdentifier(""))       // false
    println(isValidIdentifier("012"))    // false
    println(isValidIdentifier("no$"))    // false
}