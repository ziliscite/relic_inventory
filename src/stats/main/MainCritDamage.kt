package stats.main
import MainStat

class MainCritDamage(
    rarityModifier: Double = 0.0,
    override val name: String = "CRIT DMG",
): MainStat(rarityModifier, name, 10.368)