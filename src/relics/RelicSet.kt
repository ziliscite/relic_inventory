package relics

interface IRelicSet {
    val relicSetName: String
    val relicSetEffect: RelicSetEffect
}

sealed interface IRelicSetEffect
interface IDungeonRelicSetEffect: IRelicSetEffect {
    val twoPieceEffect: String
    val fourPieceEffect: String
}
interface ISimulatedUniverseSetEffect: IRelicSetEffect {
    val twoPieceEffect: String
}

sealed class RelicSetEffect: IRelicSetEffect {
    data class DungeonRelicSetEffect(
        override val twoPieceEffect: String, override val fourPieceEffect: String
    ): RelicSetEffect(), IDungeonRelicSetEffect

    data class SimulatedUniverseSetEffect(
        override val twoPieceEffect: String
    ): RelicSetEffect(), ISimulatedUniverseSetEffect
}

sealed interface IRelicSetPiecesName
interface IRelicDungeonSetPiecesName: IRelicSetPiecesName {
    val headPiece: String
    val handsPiece: String
    val bodyPiece: String
    val feetPiece: String
}

interface IRelicSimulatedUniverseSetPiecesName: IRelicSetPiecesName {
    val linkRopePiece: String
    val planarSphere: String
}

class RelicDungeonSetPiecesName(
    override val headPiece: String,
    override val handsPiece: String,
    override val bodyPiece: String,
    override val feetPiece: String
): IRelicDungeonSetPiecesName

abstract class GenericRelicSet: IRelicSet, IRelicSetPiecesName {
    abstract override val relicSetName: String
    abstract override val relicSetEffect: RelicSetEffect
}

class InazumaRelicSet(
    override val relicSetName: String = "Inazuma",
    private val piecesName: RelicDungeonSetPiecesName = RelicDungeonSetPiecesName(
        "Inazuma Mask",
        "Inazuma Hands",
        "Inazuma Armor",
        "Inazuma Feet"
    )
): GenericRelicSet(), IRelicDungeonSetPiecesName by piecesName {
    override val relicSetEffect: RelicSetEffect = RelicSetEffect.DungeonRelicSetEffect(
        "Will do something about enemy defense", "No idea"
    )
}