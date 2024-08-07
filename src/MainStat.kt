//internal object StatGrowthInitializer {
//    fun initialize(baseStat: Double): Double {
//        return baseStat.times(0.35)
//    }
//}

open class MainStat(
    rarityModifier: Double = 0.0,
    override val name: String, initialBaseStat: Double
): AbstractGenericStat(initialBaseStat, rarityModifier) {
    private companion object GrowthInitializer {
        fun initializeGrowth(baseStat: Double): Double {
            return baseStat.times(0.35)
        }
    }

    override var statGrowth: Double = initializeGrowth(baseStat)

    override fun levelUp() {
        baseStat = baseStat.plus(statGrowth)
    }
}