package stats.sub
import SubStat

class SubCritRate(
    rarityModifier: Double = 0.0,
    override val name: String = "CRIT Rate",
): SubStat(rarityModifier, name, 3.24)
