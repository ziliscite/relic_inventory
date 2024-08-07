package stats.main
import MainStat

class MainBreakEffect(
    rarityModifier: Double = 0.0,
    override val name: String = "Break Effect",
): MainStat(rarityModifier, name, 10.368)