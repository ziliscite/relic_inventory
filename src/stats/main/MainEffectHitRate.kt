package stats.main
import MainStat

class MainEffectHitRate(
    rarityModifier: Double = 0.0,
    override val name: String = "Effect Hit Rate",
): MainStat(rarityModifier, name, 6.912)