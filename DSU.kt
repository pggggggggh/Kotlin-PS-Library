class DSU(n: Int) {
    val par = IntArray(n) { it }
    val size = IntArray(n) { 1 }

    fun find(x: Int): Int = if (par[x] == x) x else find(par[x]).also { par[x] = it }
    fun merge(x: Int, y: Int) {
        var i = find(x)
        var j = find(y)
        if (i == j) return
        if (size[i] < size[j]) i = j.also { j = i }
        par[j] = i
        size[i] += size[j]
    }
}