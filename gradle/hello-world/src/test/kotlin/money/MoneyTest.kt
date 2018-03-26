package money

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class MoneyTest {
    @Test
    fun testMultiplication() {
        val five = Money.doller(5) as Money
        assertEquals(Money.doller(10), five.times(2))
        assertEquals(Money.doller(15), five.times(3))
    }

    @Test
    fun testEquality() {
        assertTrue(Money.doller(5).equals(Money.doller(5)))
        assertFalse(Money.doller(5).equals(Money.doller(6)))
        assertFalse(Money.doller(5).equals(Money.franc(5)))
    }

    @Test
    fun testCurrency() {
        assertEquals("USD", Money.doller(1).currency());
        assertEquals("CHF", Money.franc(1).currency());
    }

    @Test
    fun testSimpleAdd() {
        val five = Money.doller(5)
        val sum = five.plus(five)
        val bank = Bank()
        val reduced = bank.reduce(sum, "USD")
        assertEquals(Money.doller(10), reduced)
    }

    @Test
    fun testPlusReturnsSum() {
        val five = Money.doller(5)
        var sum = five.plus(five)
        sum = sum as Sum
        assertEquals(five, sum.augend)
    }

    @Test
    fun testReduceSum(){
        val sum = Sum(Money.doller(3), Money.doller(4))
        val bank = Bank();
        val result = bank.reduce(sum, "USD")
        assertEquals(Money.doller(7), result)
    }

    @Test
    fun testReduceMoney(){
        val bank = Bank();
        val result = bank.reduce(Money.doller(1), "USD")
        assertEquals(Money.doller(1), result)
    }

    @Test
    fun testReduceMoneyDifferentCurrency(){
        val bank = Bank()
        bank.addRate("CHF","USD",2)
        val result = bank.reduce(Money.franc(2), "USD")
        assertEquals(Money.doller(1), result)
    }

    @Test
    fun testIdentifyRate(){
        assertEquals(1, Bank().rate("USD", "USD"))
    }

    @Test
    fun testMixedAddition(){
        val fiveBucks = Money.doller(5) as Expression
        val tenFrancs = Money.franc(10) as Expression
        val bank = Bank()
        bank.addRate("CHF", "USD", 2)
        val result = bank.reduce(fiveBucks.plus(tenFrancs), "USD")
        assertEquals(Money.doller(10), result)
    }

    @Test
    fun testSumPlusMoney(){
        val fiveBucks = Money.doller(5) as Expression
        val tenFrancs = Money.franc(10) as Expression
        val bank = Bank()
        bank.addRate("CHF", "USD", 2)
        val sum = Sum(fiveBucks, tenFrancs).plus(fiveBucks)
        val result = bank.reduce(sum, "USD")
        assertEquals(Money.doller(15),result)
    }

    @Test
    fun testSumTimes(){
        val fiveBucks = Money.doller(5) as Expression
        val tenFrancs = Money.franc(10) as Expression
        val bank = Bank()
        bank.addRate("CHF", "USD", 2)
        val sum = Sum(fiveBucks, tenFrancs).times(2) as Expression
        val result = bank.reduce(sum, "USD")
        assertEquals(Money.doller(20), result)
    }

}
