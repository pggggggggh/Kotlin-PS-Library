class SegmentTree<T>(
    private val n: Int,
    private val identity: T,
    private val combine: (T, T) -> T,
) {
    private val tree = MutableList(2 * n) { identity }

    constructor(ar: List<T>, identity: T, combine: (T, T) -> T) : this(ar.size, identity, combine) {
        ar.forEachIndexed { i, v -> tree[i + n] = v }
        for (i in n - 1 downTo 1) tree[i] = combine(tree[i * 2], tree[i * 2 + 1])
    }

    fun query(l: Int, r: Int): T {
        var resL = identity
        var resR = identity
        var i = l + n
        var j = r + n
        while (i <= j) {
            if (i and 1 == 1) resL = combine(resL, tree[i++])
            if (j and 1 == 0) resR = combine(tree[j--], resR)
            i /= 2
            j /= 2
        }
        return combine(resL, resR)
    }

    fun update(idx: Int, v: T) {
        var i = idx + n
        tree[i] = v
        while (i > 1) {
            i /= 2
            tree[i] = combine(tree[i * 2], tree[i * 2 + 1])
        }
    }

    operator fun get(idx: Int) = query(idx, idx)
    operator fun get(range: IntRange): T = query(range.first, range.last)
    operator fun set(idx: Int, v: T) = update(idx, v)
}