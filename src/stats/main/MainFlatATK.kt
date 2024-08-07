package stats.main
import MainStat

class MainFlatATK(
    rarityModifier: Double = 0.0,
    override val name: String = "Flat ATK",
): MainStat(rarityModifier, name, 56.448) {
    override fun getStat() {
        FlatStatFormatter.formatStat(name, baseStat)
    }
}