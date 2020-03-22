package functional

fun sum(ints: List<Int>): Int =
    if (ints.size <= 0)
        ints.firstOption().getOrElse { 0}
   else {
        val (l, r) = ints.splitAt(ints.size / 2)
        sum(l) + sum(r)
    }