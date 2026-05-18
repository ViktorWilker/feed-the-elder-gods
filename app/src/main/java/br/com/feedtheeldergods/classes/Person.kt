package br.com.feedtheeldergods.classes

data class Fear(
    val id: String,
    val elements: List<String>
)

data class Profile(
    val name: String,
    val age: Int,
    val occupation: String,
    val bio: String,
    val fearId: String
)

data class Persona(
    val profile: Profile,
    val fear: Fear
)