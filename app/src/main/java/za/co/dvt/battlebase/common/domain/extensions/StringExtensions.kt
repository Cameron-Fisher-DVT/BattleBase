package za.co.dvt.battlebase.common.domain.extensions

fun String.toTitleCase(): String {
    if (this.isNotBlank()) {
        return this[0].uppercase() + this.substring(1)
    }
    return this
}

fun String.extractIdFromUrl(): String {
    val tokens = this.split("/")
    return if (tokens.size >= 2) {
        tokens[tokens.size - 2]
    } else {
        ""
    }
}