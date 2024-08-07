package stats.sub
import SubStat

class SubEffectHitRate(
    rarityModifier: Double = 0.0,
    override val name: String = "Effect Hit Rate",
): SubStat(rarityModifier, name, 4.32)