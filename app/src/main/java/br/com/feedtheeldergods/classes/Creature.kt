
package Classes

open class Creature(val name: String) {
    var hunger = 80
        protected set(value){ field = value.coerceIn(0, 100) }
    var happiness = 50
        protected set(value){ field = value.coerceIn(0, 100) }
    var tiredness = 20
        protected set(value){ field = value.coerceIn(0, 100) }
    var bathroom = 15
        protected set(value) { field = value.coerceIn(0, 100) }
    var dirtiness = 10
        protected set(value) { field = value.coerceIn(0, 100) }
    var age = 49
        protected set

    open fun Feed(reduction: Int): String {
        hunger -= reduction
        bathroom += 20
        return ""
    }

    open fun FeedSouls(soulsCollected: Int): String {
        val reduction = (soulsCollected * 3).coerceAtMost(60)
        Feed(reduction)
        return when {
            soulsCollected >= 15 -> "Sim... MAIS. A fome cessa... por enquanto."
            soulsCollected >= 8  -> "Aceitável. A fome diminui."
            else                 -> "É só isso? Patético. A fome permanece."
        }
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
        return "Descansou por $hours horas!"
    }

    open fun Bathe(): String {
        dirtiness -= 30
        happiness += 5
        return "$name está... tolerando o banho. Por pouco."
    }

    open fun Bathroom(): String {
        bathroom -= 40
        return "$name se sente 40% mais leve. Cosmicamente falando."
    }

    fun TimeCycle() {
        hunger += 3
        happiness -= 3
        tiredness += 10
        age++
    }

    fun checkDeath(): String? {
        return when {
            hunger >= 100 -> "$name consumiu tudo... inclusive a si mesmo. Você perdeu."
            happiness <= 0 -> "$name se cansa desta realidade e parte para outra dimensão. Você perdeu."
            tiredness >= 100 -> "$name caiu num sono eterno. Até deuses precisam descansar. Você perdeu."
            bathroom >= 100 -> "$name não aguentou mais. O universo pagou o preço. Você perdeu."
            dirtiness >= 100 -> "$name agora é 90% lodo eldritchiano. Até para os padrões de \$name, isso é demais. Você perdeu."
            age >= 50 -> "$name existiu tempo suficiente. As estrelas não estão mais alinhadas. Você venceu!!"
            else -> null
        }
    }

    open fun FeedSanity(drained: Int): String {
        hunger -= (drained * 0.6f).toInt()
        happiness += (drained * 0.3f).toInt()
        bathroom += (drained * 0.2f).toInt()
        return when {
            drained >= 60 -> "Desespero delicioso. A mente deles se despedaçou lindamente."
            drained >= 30 -> "Adequado. Os gritos foram um belo toque."
            else          -> "Mal um petisco. Tente mais na próxima vez."
        }
    }
}