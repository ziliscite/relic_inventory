package stats.main
import MainStat

class MainEnergyRegenerationRate(
    rarityModifier: Double = 0.0,
    override val name: String = "Energy Regeneration Rate",
): MainStat(rarityModifier, name, 3.1104)