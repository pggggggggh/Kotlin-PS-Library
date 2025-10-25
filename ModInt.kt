@JvmInline
value class ModInt(val v: Int) {
    constructor(v: Long) : this(v.toInt() % MOD)

    operator fun plus(other: ModInt) = ModInt((v + other.v) % MOD)
    operator fun plus(other: Int) = this + ModInt(other)
    operator fun plus(other: Long) = this + ModInt(other)

    operator fun minus(other: ModInt) = ModInt((v - other.v + MOD) % MOD)
    operator fun minus(other: Int) = this - ModInt(other)
    operator fun minus(other: Long) = this - ModInt(other)

    operator fun times(other: ModInt) = ModInt((v.toLong() * other.v) % MOD)
    operator fun times(other: Int) = this * ModInt(other)
    operator fun times(other: Long) = this * ModInt(other)

    operator fun div(other: ModInt) = this * other.inv()
    operator fun div(other: Int) = this / ModInt(other)
    operator fun div(other: Long) = this / ModInt(other)

    override fun toString() = v.toString()

    infix fun pow(exp: Long): ModInt {
        var res = ModInt(1)
        var a = ModInt(v)
        var b = exp
        while (b > 0) {
            if (b % 2L == 1L) res *= a
            a *= a
            b /= 2
        }
        return res
    }

    infix fun pow(exp: Int): ModInt = this pow exp.toLong()

    infix fun comb(other: ModInt): ModInt {
        val n = v
        val r = other.v
        check(r in 0..n)
        ensure(n)
        return fact[n] / fact[n - r] / fact[r]
    }

    infix fun comb(r: Int): ModInt = this comb ModInt(r)
    infix fun comb(r: Long): ModInt = this comb ModInt(r)

    fun inv() = this pow (MOD - 2)

    companion object {
        const val MOD = 1_000_000_007

        private val fact = mutableListOf(ModInt(1))

        fun ensure(n: Int) {
            val cur = fact.size - 1
            if (n <= cur) return
            for (i in (cur + 1)..n) fact += fact.last() * i
        }
    }
}