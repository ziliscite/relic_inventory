package stats.main
import MainStat

class MainCritRate(
    rarityModifier: Double = 0.0,
    override val name: String = "CRIT Rate",
): MainStat(rarityModifier, name, 5.184)