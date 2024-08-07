//internal object SubStatEnhancer {
//    enum class ValidModifier(val multiplier: Double) {
//        Low(0.2), Medium(0.1), High(0.0)
//    }
//
//    fun enhance(statGrowth: Double): Double {
//        val randomResult = (0..9).random()
//        return when (randomResult) {
//            in 0..2 -> statGrowth.times(1 - ValidModifier.Low.multiplier) // 30%
//            in 3..7 -> statGrowth.times(1 - ValidModifier.Medium.multiplier) // 50%
//            else -> statGrowth.times(1 - ValidModifier.High.multiplier) // 20%
//        }
//    }
//}

open class SubStat(
    rarityModifier: Double = 0.0, override val name: String, initialBaseStat: Double,
): AbstractGenericStat(rarityModifier, initialBaseStat) {
    override var statGrowth: Double = baseStat

    private companion object Enhancer {
        enum class ValidModifier(val multiplier: Double) {
            Low(0.2), Medium(0.1), High(0.0)
        }

        fun enhance(statGrowth: Double): Double {
            val randomResult = (0..9).random()
            return when (randomResult) {
                in 0..2 -> statGrowth.times(1 - ValidModifier.Low.multiplier) // 30%
                in 3..7 -> statGrowth.times(1 - ValidModifier.Medium.multiplier) // 50%
                else -> statGrowth.times(1 - ValidModifier.High.multiplier) // 20%
            }
        }
    }

    override fun levelUp() {
        baseStat += enhance(statGrowth)
    }
}