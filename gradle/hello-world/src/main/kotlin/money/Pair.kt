package money

class Pair(var from: String, var to: String) {
    override fun equals(other: Any?): Boolean {
        val pair = other as Pair
        return from.equals(pair.from) && to.equals(pair.to)
    }

    override fun hashCode(): Int {
        return 0
    }
}