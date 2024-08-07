package stats.sub
import SubStat
import java.util.*
import kotlin.math.ceil

class SubSPD(
    rarityModifier: Double = 0.0,
    override val name: String = "SPD",
): SubStat(rarityModifier, name, 2.6) {
    override fun getStat() {
        print("$name ")
        println(String.format(Locale.US, "%.2f", ceil(baseStat * 100) / 100).toDouble().toInt())
    }
}