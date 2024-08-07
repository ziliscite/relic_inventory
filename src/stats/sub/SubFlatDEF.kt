package stats.sub
import SubStat

class SubFlatDEF(
    rarityModifier: Double = 0.0,
    override val name: String = "Flat DEF",
): SubStat(rarityModifier, name, 21.168754) {
    override fun getStat() {
        FlatStatFormatter.formatStat(name, baseStat)
    }
}