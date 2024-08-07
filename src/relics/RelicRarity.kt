package relics

enum class RelicRarity(val rarityName: String, val rarityModifier: Double, val relicRarityMaxLevel: Int) {
    FiveStar("Five Stars", 0.0, 15),
    FourStar("Four Stars", 0.2, 12),
    ThreeStar("Three Stars", 0.4, 9),
    TwoStar("Two Stars", 0.6, 6)
}