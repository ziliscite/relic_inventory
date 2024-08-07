package relics

enum class RelicType(val typeName: String, val probability: Double) {
    Head("Head", 23.1), Hands("Hands", 23.6),
    Body("Body", 27.5), Feet("Feet", 25.8),
    PlanarSphere("Planar Sphere", 48.92),
    LinkRope("Link Rope", 51.08)
}

interface IRelicGenerator {
    fun generate()
}

// Ini baru generate type relic, belum random generate rarity-nya
// ntah nanti gimana
object DungeonRelicGenerator: IRelicGenerator {
    override fun generate() {
        val randomRelic = (0..3).random()
        when (randomRelic) {
            0 -> RelicType.Head
            1 -> RelicType.Hands
            2 -> RelicType.Body
            3 -> RelicType.Feet
        }
    }
}

object SimulatedUniverseRelicGenerator: IRelicGenerator {
    override fun generate() {
        val randomRelic = (0..1).random()
        when (randomRelic) {
            0 -> RelicType.LinkRope
            1 -> RelicType.PlanarSphere
        }
    }
}
