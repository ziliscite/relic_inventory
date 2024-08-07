package stats.main
import MainStat

// In percentage
class MainDEF (
    rarityModifier: Double = 0.0,
    override val name: String = "DEF",
): MainStat(rarityModifier, name, 8.64)