
package money


open class Money(open var amount: Int, open var currency: String): Expression {

    companion object {
        fun doller(amount: Int): Money {
            return Money(amount, "USD")
        }
        fun franc(amount: Int): Money {
            return Money(amount, "CHF")
        }
    }

    override fun times(multiplier: Int): Expression {
        return Money(amount * multiplier, currency)
    }

    fun currency(): String {
        return currency
    }

    override fun plus(addend: Expression): Expression{
        return Sum(this, addend)
    }

    override fun reduce(bank: Bank, to: String): Money{
        val rate = if (currency.equals("CHF") && to.equals("USD"))  2 else 1
        return Money(amount / rate, to)
    }

    override fun equals(other: Any?): Boolean {
        val money =  other as Money
        return this.amount.equals(money.amount) &&  this.currency.equals(money.currency)
    }

    override fun toString(): String {
        return amount.toString() + " " + currency
    }

}
