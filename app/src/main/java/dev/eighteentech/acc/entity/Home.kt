package dev.eighteentech.acc.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Home(val page: Cards)

@JsonClass(generateAdapter = true)
data class Cards(val cards: List<CardHolder>){
    fun toSimplifiedCardList() = cards.map { it.convertToSimplifiedCard() }
}

@JsonClass(generateAdapter = true)
data class CardHolder(val card_type: String, val card: Card) {

    fun convertToSimplifiedCard() = when (card_type) {
        "image_title_description",
        "title_description" -> convertFromTitleCard()
        else -> convertFromSimple()
    }

    private fun convertFromSimple(): SimplifiedCard {
        return SimplifiedCard(
            card_type,
            card.value!!,
            card.attributes!!.font.size,
            card.attributes.text_color!!
        )
    }

    private fun convertFromTitleCard(): SimplifiedCard {
        return SimplifiedCard(
            card_type,
            card.title!!.value,
            card.title.attributes.font.size,
            card.title.attributes.text_color,
            card.description!!.value,
            card.description.attributes.font.size,
            card.description.attributes.text_color,
            card.image?.url,
            card.image?.size?.width,
            card.image?.size?.height,
        )
    }
}

@JsonClass(generateAdapter = true)
data class Card(
    val value: String?,
    val attributes: Attributes?,
    val title: Title?,
    val description: Description?,
    val image: Image?
)

@JsonClass(generateAdapter = true)
data class Attributes(
    val text_color: String,
    val font: Font
)

@JsonClass(generateAdapter = true)
data class Font(
    val size: Int
)

@JsonClass(generateAdapter = true)
data class Title(
    val value: String,
    val attributes: Attributes
)

@JsonClass(generateAdapter = true)
data class Description(
    val value: String,
    val attributes: Attributes
)

@JsonClass(generateAdapter = true)
data class Image(
    val url: String,
    val size: Size
)

@JsonClass(generateAdapter = true)
data class Size(
    val width: Int,
    val height: Int,
)