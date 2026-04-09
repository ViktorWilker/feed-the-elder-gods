// Creature.kt
package Classes

open class Creature(val name: String) {
    var hunger = 0
        protected set(value){ field = value.coerceIn(0, 100) }
    var happiness = 100
        protected set(value){ field = value.coerceIn(0, 100) }
    var tiredness = 0
        protected set(value){ field = value.coerceIn(0, 100) }
    var bathroom = 0
        protected set(value) { field = value.coerceIn(0, 100) }
    var dirtiness = 0
        protected set(value) { field = value.coerceIn(0, 100) }
    var age = 1
        protected set

    open fun Feed(opc: Int): String {
        hunger -= 15
        bathroom += 20
        return ""
    }

    open fun ToPlay(opc: Int): String {
        happiness += 15
        tiredness += 10
        dirtiness += 15
        return ""
    }

    open fun Rest(hours: Int): String {
        tiredness -= if (hours >= 8) tiredness else (hours * 10)
        happiness -= 5
        return "Rested for $hours hours!"
    }

    open fun Bathe(): String {
        dirtiness -= 30
        happiness += 5
        return "$name is... tolerating the bath. Barely."
    }

    open fun Bathroom(): String {
        bathroom -= 40
        return "$name feels 40% lighter. Cosmically speaking."
    }

    fun TimeCycle() {
        hunger += 3
        happiness -= 3
        tiredness += 10
        age++
    }

    fun checkDeath(): String? {
        return when {
            hunger >= 100 -> "$name has consumed everything... including itself. You lose."
            happiness <= 0 -> "$name grows bored of this reality and leaves for another dimension. You lose."
            tiredness >= 100 -> "$name has fallen into an eternal slumber. Even gods need rest. You lose."
            bathroom >= 100 -> "$name couldn't hold it anymore. The universe paid the price. You lose."
            dirtiness >= 100 -> "$name is now 90% eldritch slime. Even by $name standards, that's too much. You lose."
            age >= 50 -> "$name has existed long enough. The stars are right no more. You win!!"
            else -> null  // null = ainda vivo
        }
    }
}