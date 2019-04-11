package Week1ConvarianceAndContravariance

/**
 *  Generics - Covariance and Contravariance
 */

open class Mammal(val name: String) {
    fun eat() {}
    fun sleep() {}
}

data class SlothGeneric(val slothName: String,
                  val isTwoFingered: Boolean,
                  var slothWeight: Int): Mammal(slothName) {}

data class PandaGeneric(val pandaName: String) : Mammal(pandaName)

fun slothActivity(sloth: SlothGeneric, action: Unit) {
    sloth.run { action }
}

fun feedCrews(crew: List<Mammal>) {
    crew.forEach {
        it.eat()
    }
}

fun main() {
    val slothCrew = listOf(
        SlothGeneric("Jerry", false, 15),
        SlothGeneric("Bae", true, 12),
        SlothGeneric("Alex", false, 15)
    )

    val pandaCrew = listOf(
        PandaGeneric("Tegan"),
        PandaGeneric("Peggy")
    )

    feedCrews(slothCrew)
    feedCrews(pandaCrew)

    /**
     * Covariance with the List interface:
     *
     *  interface List<out E> : Collection<E> { ... }
     *
     */
    val crewCrewCrew = listOf(
        SlothGeneric("Jerry", false, 15),
        PandaGeneric("Tegan")
    )

    /**
     * Contravariance with the Comparator interface:
     *
     *  interface Comparator<in T> {  fun compare(e1: T, e2: T): Int {...} }
     *
     */
    val compareByNames = Comparator { a: Mammal, b: Mammal ->
        a.name.first().toInt() - b.name.first().toInt()
    }

    println(crewCrewCrew.sortedWith(compareByNames))
}