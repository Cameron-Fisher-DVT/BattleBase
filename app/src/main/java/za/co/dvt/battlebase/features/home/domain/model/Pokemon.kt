package za.co.dvt.battlebase.features.home.domain.model

data class Pokemon(
    val id: String = "",
    val name: String = "",
    val imageUrl: String = "",
    val abilityList: List<Ability> = listOf(),
    val statsList: List<Stat> = listOf(),
    val isFavourite: Boolean = false
)

fun Pokemon.description(): String {
    val stringBuilder = StringBuilder()
    this.statsList.forEach {
        stringBuilder.append("${it.name}: ${it.score}")
        stringBuilder.appendLine()
    }
    return stringBuilder.toString()
}