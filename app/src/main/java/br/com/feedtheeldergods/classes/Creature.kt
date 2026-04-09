package Classes

import kotlin.concurrent.thread

open class Creature(val name: String) {
    var hunger = 0
        protected set(value){
            field = value.coerceIn(0, 100)
        }
    var happiness = 100
        protected set(value){
            field = value.coerceIn(0, 100)
        }
    var tiredness = 0
        protected set(value){
            field = value.coerceIn(0, 100)
        }
    var bathroom = 0
        protected set(value) { field = value.coerceIn(0, 100) }

    var dirtiness = 0
        protected set(value) { field = value.coerceIn(0, 100) }

    var age = 1
        protected set

    open fun Feed() {
        hunger -= 15
        bathroom += 20
    }

    open fun ToPlay() {
        happiness += 15
        tiredness += 10
        dirtiness += 15
    }

    open fun Bathe() {
        dirtiness -= 30
        happiness += 5
        println("$name is... tolerating the bath. Barely.")
    }

    open fun Bathroom() {
        bathroom -= 40
        println("$name feels 40% lighter. Cosmically speaking.")
    }



    open fun Rest() {
        println("How many hours do you want to rest? (1-8)")
        val hours = readln().toInt().coerceIn(1, 8)

        tiredness -= if (hours >= 8) tiredness else (hours * 10)

        println("Resting...")
        Thread.sleep(hours * 1000L)

        tiredness -= (hours * 5)
        println("Rested for $hours hours!")



        happiness -= 5
    }

    fun ShowStatus() {
        println()
        println("|===== || =====|")
        println("Name: $name")
        println("Happiness: $happiness")
        println("Hunger: $hunger")
        println("Age: $age")
        println("Tiredness: $tiredness")
        println("Dirtiness: $dirtiness")
        println("Bathroom: $bathroom")
        println("|===== || =====|")
        println()
    }
    fun TimeCycle() {
        hunger += 3
        happiness -= 3
        tiredness += 10
        age++
    }
    fun checkDeath(): Boolean{
        if(hunger >= 100) {
            println("$name has consumed everything... including itself. You lose.")
            return true
        }
        if (happiness <= 0) {
            println("$name grows bored of this reality and leaves for another dimension. You lose.")
            return true
        }
        if (tiredness >= 100) {
            println("$name has fallen into an eternal slumber. Even gods need rest. You lose.")
            return true
        }
        if(bathroom >= 100) {
            println("$name couldn't hold it anymore. The universe paid the price. You lose.")
            return true
        }
        if(dirtiness >= 100){
            println("$name is now 90% eldritch slime. Even by $name standards, that's too much. You lose.")
            return true
        }

        if(age >= 50){
            println("$name has existed long enough. The stars are right no more. You win!!")
            return true
        }

        return false
    }
}
