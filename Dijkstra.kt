import java.util.PriorityQueue

fun main() {
    val (n, m) = readInts()
    val s = readInt()
    val adj = Array(n + 1) { mutableListOf<Pair<Int, Int>>() }

    repeat(m) {
        val (u, v, w) = readInts()
        adj[u].add(v to w)
    }

    val dist = LongArray(n + 1) { Long.MAX_VALUE }
    val pq = PriorityQueue<Pair<Long, Int>>(compareBy { it.first })
    dist[s] = 0
    pq += 0L to s
    while (pq.isNotEmpty()) {
        val (d, u) = pq.poll()
        if (d != dist[u]) continue
        for ((v, cost) in adj[u]) {
            if (d + cost < dist[v]) {
                dist[v] = d + cost
                pq += dist[v] to v
            }
        }
    }
}
