package stats.main
import MainStat
import java.util.*
import kotlin.math.ceil

class MainSPD(
    rarityModifier: Double = 0.0,
    override val name: String = "SPD",
): MainStat(rarityModifier, name, 4.032) {
    override fun getStat() {
        print("$name ")
        println(String.format(Locale.US, "%.2f", ceil(baseStat * 100) / 100).toDouble().toInt())
    }
}