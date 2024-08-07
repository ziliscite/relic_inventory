package stats.sub
import SubStat

class SubATK(
    rarityModifier: Double = 0.0,
    override val name: String = "ATK",
): SubStat(rarityModifier, name, 4.32)