package relics

import IStat
import stats.sub.*

object SubStatInitializer {
    fun initialize(relicRarity: RelicRarity, existingStats: List<IStat>): IStat {
        while (true) {
            val subStat: IStat = randomize(relicRarity.rarityModifier)
            // Check if a stat with the same name didn't already exist in existingStats.
            // existingStats would also contain mainStat in addition to the already established subStats.
            if (!existingStats.any { it.name == subStat.name }) return subStat
        }
    }

    private fun randomize(relicRarityModifier: Double): IStat {
        val randomResult = (0..99).random() // Generate a random number between 0 and 9
        return when (randomResult) {
            in 0..9 -> SubHP(relicRarityModifier) // 10%
            in 10..19 -> SubATK(relicRarityModifier) // 10%
            in 20..29 -> SubDEF(relicRarityModifier) // 10%
            in 30..39 -> SubFlatHP(relicRarityModifier) // 10%
            in 40..49 -> SubFlatATK(relicRarityModifier) // 10%
            in 50..59 -> SubFlatDEF(relicRarityModifier) // 10%
            in 60..67 -> SubEffectHitRate(relicRarityModifier) // 8%
            in 68..75 -> SubEffectResistance(relicRarityModifier) // 8%
            in 76..83 -> SubBreakEffect(relicRarityModifier) // 8%
            in 84..89 -> SubCritRate(relicRarityModifier) // 6%
            in 90..95 -> SubCritDamage(relicRarityModifier) // 6%
            else -> SubSPD(relicRarityModifier) // 4%
        }
    }
}

object SubStatGenerator {
    fun generate(relicRarity: RelicRarity, mainStat: IStat): MutableList<IStat> {
        return when (relicRarity) {
            RelicRarity.FiveStar -> FiveStarSubStat.initialize(mainStat)
            RelicRarity.FourStar -> FourStarSubStat.initialize(mainStat)
            RelicRarity.ThreeStar -> ThreeStarSubStat.initialize(mainStat)
            RelicRarity.TwoStar -> TODO()
        }
    }
}

interface ISubStatGenerator {
    fun initialize(mainStat: IStat): MutableList<IStat>
}

// maybe make it a class or something, object is shit ass
// look at that `val subStats` and shits. might not be a problem rn
// but a state is ultimately a state and this will lead to problem
object FiveStarSubStat: ISubStatGenerator {
    override fun initialize(mainStat: IStat): MutableList<IStat> {
        val subStats = mutableListOf<IStat>()
        val randomAmount = (1..10).random()
        when (randomAmount) {
            in 1..6 -> repeat(3) {
                subStats.add(SubStatInitializer.initialize(RelicRarity.FiveStar, subStats + mainStat))
            }
            in 7..10 -> repeat(4) {
                subStats.add(SubStatInitializer.initialize(RelicRarity.FiveStar, subStats + mainStat))
            }
        }
        return subStats
    }
}

object FourStarSubStat: ISubStatGenerator {
    override fun initialize(mainStat: IStat): MutableList<IStat> {
        val subStats = mutableListOf<IStat>()
        val randomAmount = (2..3).random() // 50-50, so, that is it
        repeat(randomAmount) {
            subStats.add(SubStatInitializer.initialize(RelicRarity.FourStar, subStats + mainStat))
        }
        return subStats
    }
}

object ThreeStarSubStat: ISubStatGenerator {
    override fun initialize(mainStat: IStat): MutableList<IStat> {
        val subStats = mutableListOf<IStat>()
        val randomAmount = (1..10).random()
        when (randomAmount) {
            in 1..4 -> repeat( 1) {
                subStats.add(SubStatInitializer.initialize(RelicRarity.ThreeStar, subStats + mainStat))
            }
            in 5..10 -> repeat(2) {
                subStats.add(SubStatInitializer.initialize(RelicRarity.ThreeStar, subStats + mainStat))
            }
        }
        return subStats
    }
}

// Bikin yg kyk di main stat, buat inisialisasi jumlah substat awal
// bintang 5 = 3-4 substat, 70%-30%
// bintang 4 = 2-3 substat, 50%-50%
// bintang 3 = 1-2 substat, 40%-60%
// bintang 2 = 1 substat
// bentar, emang ada yg bintang 2?

fun testSome() {
//    val subStats = mutableListOf<IStat>()
//
//    val subStatOne = SubStatInitializer.initialize(RelicRarity.FiveStar)
//    subStats.addLast(subStatOne)
//
//    val subStatTwo = SubStatInitializer.initialize(RelicRarity.FiveStar, subStats)
//    subStats.addLast(subStatTwo)
//
//    val subStatThree = SubStatInitializer.initialize(RelicRarity.FiveStar, subStats)
//    subStats.addLast(subStatThree)
//
//    val subStatFour = SubStatInitializer.initialize(RelicRarity.FiveStar, subStats)
//
//    subStatOne.getStat()
//    subStatTwo.getStat()
//    subStatThree.getStat()
//    subStatFour.getStat()
//    println()
//
//    SubStatEnhanceRandomizer.enhance(subStats)
//
//    subStatOne.getStat()
//    subStatTwo.getStat()
//    subStatThree.getStat()
//    subStatFour.getStat()
}


fun main() {
//    repeat(1) {
//        val newSubStats = SubStatInitializer.initialize(RelicRarity.FiveStar,
//            listOf(
//                SubATK(), MainFlatHP(), MainHP(), SubFlatDEF()
//            )
//        )
//        newSubStats.levelUp()
//        newSubStats.levelUp()
//        newSubStats.getStat()
//    }

//    val subStats = FiveStarSubStat.initialize()
//    subStats.forEach {
//        it.getStat()
//    }
//    SubStatEnhanceRandomizer.enhance(subStats)
//    println()
//    subStats.forEach {
//        it.getStat()
//    }
}