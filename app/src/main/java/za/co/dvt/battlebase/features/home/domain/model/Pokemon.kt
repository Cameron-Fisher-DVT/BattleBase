package za.co.dvt.battlebase.features.home.domain.model

data class Pokemon(
    val pokemonId: String,
    val name: String,
    val imageUrl: String,
    val abilityList: List<Ability>,
    val statsList: List<Stat>,
    val isFavourite: Boolean
)

fun Pokemon.description(): String {
    val stringBuilder = StringBuilder()
    this.statsList.forEach {
        stringBuilder.append("${it.name}: ${it.score}")
        stringBuilder.appendLine()
    }
    return stringBuilder.toString()
}