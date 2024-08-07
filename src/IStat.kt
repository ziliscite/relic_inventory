interface IStat {
    val name: String
    val baseStat: Double
    val statGrowth: Double
    fun getStat()
    fun levelUp()
}

// get stat and levelUp doesn't seem to be necessary