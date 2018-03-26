package money

class Bank {
    var rates : MutableMap<Pair, Int> = HashMap()

    fun reduce(source: Expression, to: String): Expression{
        return source.reduce(this, to)
    }

    fun addRate(from: String, to: String, rate: Int){
        rates.put(Pair(from,to), rate)
    }

    fun rate(from: String, to: String): Int?{
        if(from.equals(to)){
            return 1
        }
        return rates.get(Pair(from, to))
    }
}