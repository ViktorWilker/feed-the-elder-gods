package br.com.feedtheeldergods.classes

import android.content.Context
import br.com.feedtheeldergods.classes.Fear
import br.com.feedtheeldergods.classes.Persona
import br.com.feedtheeldergods.classes.Profile
import org.json.JSONObject
class PersonaRepository(context: Context) {

    private val personas: List<Persona>
    private var _allSymbols: List<String> = emptyList()
    val allSymbols: List<String> get() = _allSymbols

    init {
        val json = context.assets
            .open("personas.json")
            .bufferedReader()
            .use { it.readText() }

        val root = JSONObject(json)

        val fearsMap = buildMap {
            val arr = root.getJSONArray("fears")
            repeat(arr.length()) { i ->
                val obj = arr.getJSONObject(i)
                val elements = buildList {
                    val elArr = obj.getJSONArray("elements")
                    repeat(elArr.length()) { j -> add(elArr.getString(j)) }
                }
                put(obj.getString("id"), Fear(obj.getString("id"), elements))
            }
        }

        _allSymbols = buildList {
            val arr = root.getJSONArray("symbols")
            repeat(arr.length()) { i -> add(arr.getString(i)) }
        }

        val profilesArr = root.getJSONArray("profiles")
        personas = buildList {
            repeat(profilesArr.length()) { i ->
                val obj = profilesArr.getJSONObject(i)
                val profile = Profile(
                    name       = obj.getString("name"),
                    age        = obj.getInt("age"),
                    occupation = obj.getString("occupation"),
                    bio        = obj.getString("bio"),
                    fearId     = obj.getString("fearId")
                )
                val fear = fearsMap[profile.fearId] ?: return@repeat
                add(Persona(profile, fear))
            }
        }
    }

    fun random(): Persona = personas.random()

    fun buildGrid(fear: Fear): List<String> {
        val wrong = _allSymbols
            .filter { it !in fear.elements }
            .shuffled()
            .take(4)
        return (fear.elements + wrong).shuffled()
    }
}
