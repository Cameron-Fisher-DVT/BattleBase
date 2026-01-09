package za.co.dvt.battlebase.features.home.domain.model

data class PokemonInformation(
    val frontDefaultSprite: String,
    val pokemonAbilityList: List<Ability> = emptyList(),
    val stats: List<Stat> = emptyList()
)