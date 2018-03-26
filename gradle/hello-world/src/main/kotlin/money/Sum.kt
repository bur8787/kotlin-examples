package money

class Sum(var augend: Expression, var addend: Expression): Expression {
    override fun reduce(bank: Bank, to: String): Money{
        val amount = this.augend.reduce(bank,to).amount + this.addend.reduce(bank, to).amount
        return Money(amount, to)
    }

    override fun plus(addend: Expression): Expression {
        return Sum(this, addend)
    }

    override fun times(multiplier: Int): Expression{
        return Sum(augend.times(multiplier), addend.times(multiplier))
    }
}