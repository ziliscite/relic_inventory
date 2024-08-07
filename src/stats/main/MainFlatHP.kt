package stats.main
import MainStat

class MainFlatHP(
    rarityModifier: Double = 0.0,
    override val name: String = "Flat HP",
): MainStat(rarityModifier, name, 112.896) {
    override fun getStat() {
        FlatStatFormatter.formatStat(name, baseStat)
    }
}