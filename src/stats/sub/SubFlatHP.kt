package stats.sub
import SubStat

class SubFlatHP(
    rarityModifier: Double = 0.0,
    override val name: String = "Flat HP",
): SubStat(rarityModifier, name, 42.33751) {
    override fun getStat() {
        FlatStatFormatter.formatStat(name, baseStat)
    }
}