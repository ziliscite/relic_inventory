import java.util.*
import kotlin.math.round

object FlatStatFormatter {
    fun formatStat(name: String, baseStat: Double) {
        val statName = name.removePrefix("Flat ")
        val formattedStat = String.format(Locale.US, "%.1f", round(baseStat * 100) / 100).toDouble().toInt()
        println("$statName $formattedStat")
    }
}