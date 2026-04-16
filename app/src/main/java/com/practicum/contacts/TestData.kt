package com.practicum.contacts

object TestData {
    val noPhotoGuy = Contact(
        name = "Инкогнито",
        surname = null,
        familyName = "БезФото",
        imageRes = null,
        isFavorite = true,
        phone = "+8 911 815 92 38",
        address = "Где-то рядом",
        email = "incognto@hz.ru"
    )

    val strangeNeighbor = Contact(
        name = "Сосед",
        surname = "Странный",
        familyName = "Очень",
        imageRes = R.drawable.user_photo,
        isFavorite = false,
        phone = "+123",
        address = "Квартира снизу (гремит ночью)",
        email = null
    )
}