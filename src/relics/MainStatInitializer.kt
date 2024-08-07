package relics

import IStat
import stats.main.*
import kotlin.random.Random

interface IMainStatInitializer {
    fun initialize(relicRarity: RelicRarity, relicType: RelicType): IStat
}

object MainStatInitializer: IMainStatInitializer {
    override fun initialize(relicRarity: RelicRarity, relicType: RelicType): IStat {
        return when (relicType) {
            RelicType.Head -> HeadMainStatInitializer.initialize(relicRarity.rarityModifier)
            RelicType.Hands -> HandsMainStatInitializer.initialize(relicRarity.rarityModifier)
            RelicType.Body -> BodyMainStatInitializer.initialize(relicRarity.rarityModifier)
            RelicType.Feet -> FeetMainStatInitializer.initialize(relicRarity.rarityModifier)
            RelicType.PlanarSphere -> PlanarSphereMainStatInitializer.initialize(relicRarity.rarityModifier)
            RelicType.LinkRope -> LinkRopeMainStatInitializer.initialize(relicRarity.rarityModifier)
        }
    }
}

interface IGenericMainStatInitializer {
    fun initialize(relicRarityModifier: Double): IStat
}

object HeadMainStatInitializer: IGenericMainStatInitializer {
    override fun initialize(relicRarityModifier: Double): IStat {
        return MainFlatHP(relicRarityModifier)
    }
}

object HandsMainStatInitializer: IGenericMainStatInitializer {
    override fun initialize(relicRarityModifier: Double): IStat {
        return MainFlatATK(relicRarityModifier)
    }
}

object BodyMainStatInitializer: IGenericMainStatInitializer {
    override fun initialize(relicRarityModifier: Double): IStat {
        val randomResult = (0..9).random() // Generate a random number between 0 and 9
        return when (randomResult) {
            in 0..1 -> MainHP(relicRarityModifier)   // 0-1 (20% chance for HP)
            in 2..3 -> MainATK(relicRarityModifier)  // 2-3 (20% chance for ATK)
            in 4..5 -> MainDEF(relicRarityModifier)  // 4-5 (20% chance for DEF)
            6 -> MainEffectHitRate(relicRarityModifier)  // 6 (10% chance for Effect Hit Rate)
            7 -> MainOutgoingHealingBoost(relicRarityModifier)  // 7 (10% chance for Outgoing Healing Boost)
            8 -> MainCritRate(relicRarityModifier)  // 8 (10% chance for Crit Rate)
            else -> MainCritDamage(relicRarityModifier)  // 9 (10% chance for Crit Damage)
        }
    }
}

object FeetMainStatInitializer: IGenericMainStatInitializer {
    override fun initialize(relicRarityModifier: Double): IStat {
        val randomResult = (0..9).random() // Generate a random number between 0 and 9
        return when (randomResult) {
            in 0..2 -> MainHP(relicRarityModifier)   // 0-2 (30% chance for each HP, ATK, DEF)
            in 3..5 -> MainATK(relicRarityModifier)  // 3-5 (30% chance for each HP, ATK, DEF)
            in 6..8 -> MainDEF(relicRarityModifier)  // 6-8 (30% chance for each HP, ATK, DEF)
            else -> MainSPD(relicRarityModifier)     // 9 (10% chance for SPD)
        }
    }
}

object PlanarSphereMainStatInitializer: IGenericMainStatInitializer {
    override fun initialize(relicRarityModifier: Double): IStat {
        val randomResult = Random.nextDouble(0.0, 100.0) // Generate a random number between 0.0 and 100.0
        return when (randomResult) {
            in 0.0 .. 11.99 -> MainHP(relicRarityModifier)     // 0.0-11.99 (12% chance for HP)
            in 12.0 .. 23.99 -> MainATK(relicRarityModifier)    // 12.0-23.99 (12% chance for ATK)
            in 24.00 .. 35.99 -> MainDEF(relicRarityModifier)    // 24.0-35.99 (12% chance for DEF)
            else -> {
                // Elemental damage boosts (64% collective chance)
                val elementType = (0..6).random() // Randomly choose an element type
                when (elementType) {
                    0 -> MainPhysicalDamageBoost(relicRarityModifier)
                    1 -> MainFireDamageBoost(relicRarityModifier)
                    2 -> MainIceDamageBoost(relicRarityModifier)
                    3 -> MainWindDamageBoost(relicRarityModifier)
                    4 -> MainLightningDamageBoost(relicRarityModifier)
                    5 -> MainQuantumDamageBoost(relicRarityModifier)
                    else -> MainImaginaryDamageBoost(relicRarityModifier)
                }
            }
        }
    }
}

object LinkRopeMainStatInitializer: IGenericMainStatInitializer {
    override fun initialize(relicRarityModifier: Double): IStat {
        val randomResult = (0..99).random() // Generate a random number between 0 and 99
        return when (randomResult) {
            in 0 .. 25 -> MainHP(relicRarityModifier)   // 0-25 (26% chance)
            in 26 .. 51 -> MainATK(relicRarityModifier)  // 26-51 (26% chance)
            in 52 .. 77 -> MainDEF(relicRarityModifier)  // 52-77 (26% chance)
            in 78 .. 93 -> MainBreakEffect(relicRarityModifier) // 78-93 (16% chance)
            else -> MainEnergyRegenerationRate(relicRarityModifier) // 94-99 (6% chance)
        }
    }
}

fun main() {
    repeat(2) {
        val mainStat = MainStatInitializer.initialize(RelicRarity.FiveStar, RelicType.LinkRope)
        mainStat.getStat()

        mainStat.levelUp()
        mainStat.levelUp()
        mainStat.levelUp()
        mainStat.levelUp()
        mainStat.levelUp()
        mainStat.levelUp()
        mainStat.levelUp()
        mainStat.levelUp()
        mainStat.levelUp()
        mainStat.levelUp()
        mainStat.levelUp()
        mainStat.levelUp()
        mainStat.levelUp()
        mainStat.levelUp()
        mainStat.levelUp()

        mainStat.getStat()
        println()
    }
}