package stats.sub
import SubStat

class SubBreakEffect(
    rarityModifier: Double = 0.0,
    override val name: String = "Break Effect",
): SubStat(rarityModifier, name, 6.48)