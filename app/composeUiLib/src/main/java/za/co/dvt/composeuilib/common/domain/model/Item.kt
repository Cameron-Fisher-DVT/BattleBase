package za.co.dvt.composeuilib.common.domain.model

class Item private constructor(
    val id: String,
    val title: String,
    val subTitle: String,
    val imageUrl: String
) {
    private constructor(builder: Builder) : this(builder.id, builder.title, builder.subTitle, builder.imageUrl)

    class Builder {
        var id = ""
        var title = ""
        var subTitle = ""
        var imageUrl = ""

        fun id(id: String) = apply { this.id = id }
        fun title(title: String) = apply { this.title = title }
        fun subTitle(subTitle: String) = apply { this.subTitle = subTitle }
        fun imageUrl(imageUrl: String) = apply { this.imageUrl = imageUrl }
        fun build(): Item = Item(this)
    }
}