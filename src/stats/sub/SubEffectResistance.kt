package stats.sub
import SubStat

class SubEffectResistance(
    rarityModifier: Double = 0.0,
    override val name: String = "Effect RES",
): SubStat(rarityModifier, name, 4.32)