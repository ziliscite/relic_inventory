package stats.sub
import SubStat

class SubFlatATK(
    rarityModifier: Double = 0.0,
    override val name: String = "Flat ATK",
): SubStat(rarityModifier, name, 21.168754) {
    override fun getStat() {
        FlatStatFormatter.formatStat(name, baseStat)
    }
}