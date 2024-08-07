package relics
//
import IStat

import com.google.gson.Gson
import com.google.gson.GsonBuilder

import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

interface IRelic {
//    val relicSet: IRelicSet
    val relicName: String
    val relicRarity: RelicRarity
    val relicType: RelicType
    val stat: IRelicStat
    val level: IRelicLevel
}

interface IRelicStat {
    val mainStat: IStat
    val subStats: MutableList<IStat>
}

class RelicStatDelegate(relicRarity: RelicRarity, relicType: RelicType): IRelicStat {
    override val mainStat: IStat = MainStatInitializer.initialize(relicRarity, relicType)
    override val subStats: MutableList<IStat> = SubStatGenerator.generate(relicRarity, mainStat)
}

interface IRelicLevel {
    var level: Int
    var exp: Int
    val expRequired: Int
    fun levelUp(mainStat: IStat, subStats: MutableList<IStat>)
}

class RelicLevelDelegate(private val relicRarity: RelicRarity): IRelicLevel {
    override var level = 0
    override var exp: Int = 0
    override val expRequired: Int = 0

    private fun addSubStat(relicRarity: RelicRarity, mainStat: IStat, subStats: MutableList<IStat>) {
        val newSubStat = SubStatInitializer.initialize(relicRarity, subStats + mainStat)
        subStats.add(newSubStat)
    }

    override fun levelUp(mainStat: IStat, subStats: MutableList<IStat>) {
        mainStat.levelUp()
        if (level != 0 && level % 3 == 0) {
            if (subStats.size < 4) addSubStat(relicRarity, mainStat, subStats)
            else subStats.random().levelUp()
        }
        level += 1
    }
}

open class AbstractGenericRelic (
    final override val relicName: String,
    final override val relicType: RelicType,
    final override val relicRarity: RelicRarity
): IRelic {
    final override val stat: IRelicStat = RelicStatDelegate(relicRarity, relicType)
    final override val level: IRelicLevel = RelicLevelDelegate(relicRarity)

    // Classic printer
    fun getRelic() {
        println()
        println(relicName)
        print("Main stat: ").also { stat.mainStat.getStat() }
        println()
        println("Sub stats: ")
        stat.subStats.forEach { it.getStat() }
        println()
    }

    fun getRelicJson(): String {
        // Create a map for the JSON structure
        val relicJson = mutableMapOf<String, Any>()
        relicJson["relicName"] = relicName
        relicJson["relicType"] = relicType.name
        relicJson["mainStat"] = mapOf("name" to stat.mainStat.name, "value" to String.format("%.2f", stat.mainStat.baseStat))
        relicJson["subStats"] = stat.subStats.map { mapOf("name" to it.name, "value" to String.format("%.2f", it.baseStat)) }

        // Convert the map to JSON
        val gson: Gson = GsonBuilder().setPrettyPrinting().create()
        return gson.toJson(relicJson)
    }

    fun levelUp() {
        if (level.level == relicRarity.relicRarityMaxLevel) {
            println("Relic has reached max level")
        } else {
            level.levelUp(stat.mainStat, stat.subStats)
        }
    }
}

class JSONWriter{
    fun run(path: String, iteration: Int){
        write(path, iteration)
    }

    private fun write(path: String, iteration: Int) {
        val outputPath = Paths.get(path)

        // Create or clear the output file before writing
        Files.write(outputPath, "[\n".toByteArray()) // Start of JSON array

        repeat(iteration) {
            val rarity = RelicRarity.FiveStar
            val randRelic = AbstractGenericRelic("Ball Sacks Relic", RelicType.Body, rarity)

            repeat(rarity.relicRarityMaxLevel) {
                randRelic.levelUp()
            }

            // Get the JSON representation of the relic
            val relicJson = randRelic.getRelicJson()

            // Write to the file, adding a comma if it's not the last element
            val shouldAppendComma = it < iteration-1 // Check if this is the last relic
            val jsonLine = if (shouldAppendComma) "  $relicJson,\n" else "  $relicJson\n"
            Files.write(outputPath, jsonLine.toByteArray(), StandardOpenOption.APPEND)
        }

        // Close the JSON array
        Files.write(outputPath, "]\n".toByteArray(), StandardOpenOption.APPEND)
    }
}

fun main() {
    val path = "C:/Users/manzi/VSCoding/relic_something/relics.json"
    val writer = JSONWriter()
    writer.run(path, 1000)
}

//interface IRelicMainStat {
//    val mainStat: IStat
//}
//
//interface IRelicSubStats {
//    val subStats: List<IStat>
//}
//
//interface IRelicType {
//    val type: RelicType
//}
//
//interface IRelicRarity {
//    val rarity: RelicRarity
//    val rarityModifier: Double
//}
//
//interface IRelicLevel {
//    val maxLevel: Int
//    var level: Int
//}
//
//abstract class AbstractRelicStarLevel: IRelicRarity, IRelicLevel {
//    abstract override val rarity: RelicRarity
//    abstract override val rarityModifier: Double
//    abstract override val maxLevel: Int
//    final override var level: Int = 0
//}
//
//open class GenericFiveStarRelic: AbstractRelicStarLevel() {
//    override val rarity: RelicRarity = RelicRarity.FiveStar
//    override val rarityModifier: Double = 0.0
//    override val maxLevel: Int = 15
//}
//
//open class GenericFourStarRelic: AbstractRelicStarLevel() {
//    override val rarity: RelicRarity = RelicRarity.FourStar
//    override val rarityModifier: Double = 0.2
//    override val maxLevel: Int = 12
//}
//
//open class GenericThreeStarRelic: AbstractRelicStarLevel() {
//    override val rarity: RelicRarity = RelicRarity.ThreeStar
//    override val rarityModifier: Double = 0.4
//    override val maxLevel: Int = 9
//}
//
//open class GenericTwoStarRelic: AbstractRelicStarLevel() {
//    override val rarity: RelicRarity = RelicRarity.TwoStar
//    override val rarityModifier: Double = 0.6
//    override val maxLevel: Int = 6
//}
//
//abstract class AbstractRelicType: IRelicType, IRelicMainStat {
//    abstract override val type: RelicType
//    abstract override val mainStat: IStat
//}
//
//open class GenericHeadRelic: AbstractRelicType() {
//    override val type: RelicType = RelicType.Head
//    override val mainStat: IStat = MainFlatHP()
//}
//
//open class GenericHandsRelic: AbstractRelicType() {
//    override val type: RelicType = RelicType.Hands
//    override val mainStat: IStat = MainFlatATK()
//}
//
//
//interface IMainStatRandomizer {
//    fun randomizeMainStat(): IStat
//}
//
//open class GenericBodyRelic: AbstractRelicType(), IMainStatRandomizer {
//    override val type: RelicType = RelicType.Body
//    override val mainStat: IStat = randomizeMainStat()
//
//    final override fun randomizeMainStat(): IStat {
//        val randomResult = (0..6).random()
//        return when (randomResult) {
//            0 -> MainHP()
//            1 -> MainATK()
//            2 -> MainDEF()
//            3 -> MainEffectHitRate()
//            4 -> MainOutgoingHealingBoost()
//            5 -> MainCritRate()
//            6 -> MainCritDamage()
//            else -> throw InstantiationError("Invalid Main Stat Initialization")
//        }
//    }
//}
//
//open class GenericFeetRelic(): AbstractRelicType(), IMainStatRandomizer {
//    override val type: RelicType = RelicType.Feet
//    override val mainStat: IStat = randomizeMainStat()
//
//    final override fun randomizeMainStat(): IStat {
//        val randomResult = (0..3).random()
//        return when (randomResult) {
//            0 -> MainHP()
//            1 -> MainATK()
//            2 -> MainDEF()
//            3 -> MainSPD()
//            else -> throw InstantiationError("Invalid Main Stat Initialization")
//        }
//    }
//}
//
//abstract class AbstractRelic(
//    open val name: String,
//    private val relicRarityLevel: AbstractRelicStarLevel,
//    private val relicType: AbstractRelicType
//): IRelicRarity by relicRarityLevel, IRelicLevel by relicRarityLevel, IRelicType by relicType, IRelicMainStat by relicType {
//
//}
//
//// we need to add some kind of function that automatically
//// lowers the base of the main stat and its growth rate
//// depending on the rarity
//
//class FourStarFeetRelic(
//    override val name: String
//): AbstractRelic(name, GenericFourStarRelic(), GenericFeetRelic())
//
//class FiveStarHeadRelic(
//    val name: String,
//    private val relicRarityLevel: GenericFiveStarRelic = GenericFiveStarRelic(),
//    private val relicType: GenericHeadRelic = GenericHeadRelic()
//): IRelicRarity by relicRarityLevel, IRelicLevel by relicRarityLevel,
//    IRelicType by relicType, IRelicMainStat by relicType {
//
//}
//
//interface IRelicLevelManager {
//
//}
//
//interface IStatInitializer {
//    fun initiateStat(rarityModifier: Double): IStat
//}
//
//fun main() {
//    val randomFiveStarHeadRelic = FiveStarHeadRelic("This Penis")
//    println(randomFiveStarHeadRelic.name)
//    println(randomFiveStarHeadRelic.rarity.rarityName)
//    println(randomFiveStarHeadRelic.type)
//    println(randomFiveStarHeadRelic.mainStat.getStat())
//    println(randomFiveStarHeadRelic.mainStat)
//
//    val randomFourStarFeetRelicOne = FourStarFeetRelic("Lala1")
//    println(randomFourStarFeetRelicOne.mainStat)
//    val randomFourStarFeetRelicTwo = FourStarFeetRelic("Lala2")
//    println(randomFourStarFeetRelicTwo.mainStat)
//    val randomFourStarFeetRelicThree = FourStarFeetRelic("Lala3")
//    println(randomFourStarFeetRelicThree.mainStat)
//}