package stats.sub
import SubStat

class SubDEF(
    rarityModifier: Double = 0.0,
    override val name: String = "DEF",
): SubStat(rarityModifier, name, 5.4)
