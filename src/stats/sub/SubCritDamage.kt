package stats.sub
import SubStat

class SubCritDamage(
    rarityModifier: Double = 0.0,
    override val name: String = "CRIT DMG",
): SubStat(rarityModifier, name, 6.48)