import java.util.*
import kotlin.math.floor

//internal object StatModifier {
//    fun modifyStat(baseStat: Double, rarityModifier: Double): Double {
//        return baseStat.times(1 - rarityModifier)
//    }
//}

abstract class AbstractGenericStat(
    initialBaseStat: Double, rarityModifier: Double = 0.0
): IStat {
    private companion object BaseStatModifier {
        fun apply(baseStat: Double, rarityModifier: Double): Double {
            return baseStat.times(1 - rarityModifier)
        }
    }

    final override var baseStat: Double = apply(initialBaseStat, rarityModifier)
        protected set

    override fun getStat(){
        print("$name ")
        print(String.format(Locale.US, "%.1f", floor(baseStat * 100) / 100).toDouble())
        println("%")
    }
}