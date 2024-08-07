package stats.main
import MainStat

// In percentage
class MainHP(
    rarityModifier: Double = 0.0,
    override val name: String = "HP",
): MainStat(rarityModifier, name, 6.912)