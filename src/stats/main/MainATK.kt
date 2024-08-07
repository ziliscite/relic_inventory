package stats.main
import MainStat

// In percentage
// TODO("Please refactor it so that we don't have to initialize a 0.0 value")
// maybe attach the "rarity" property inside of the relic instead of the stat
// also with type
class MainATK(
    rarityModifier: Double = 0.0,
    override val name: String = "ATK",
): MainStat(rarityModifier, name, 6.912)

// no.
// after looking at the codebase after leaving it for almost a month, I'd rather start over.
// It's already coupled so bad since the stat "base" and "modifier" is initialized using the rarity modifier
// it was actually quite a smart approach, haha, idk
