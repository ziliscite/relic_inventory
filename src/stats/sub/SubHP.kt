package stats.sub
import SubStat

class SubHP(
    rarityModifier: Double = 0.0,
    override val name: String = "HP",
): SubStat(rarityModifier, name, 4.32)