import kotlin.random.Random
import kotlin.reflect.KFunction1

sealed class Mammal(val name: String) {
    fun eat() {}
    fun sleep() {}
    fun swim() {
        println("${name.toUpperCase()} CAN SWIM")
    }
    open fun relief() {}
}

data class Sloths(val slothName: String,
                  val isTwoFingered: Boolean,
                  var slothWeight: Int): Mammal(slothName) {
    override fun relief() {
        val oldWeight = slothWeight
        val weightShed = Random.nextInt(0, slothWeight/3)
        val newWeight = slothWeight - weightShed
        println("${slothName.toUpperCase()} FINALLY WENT THIS WEEK")
        println("\tOld weight: $oldWeight \t|\t New weight: $newWeight")
    }
}

data class Pandas(val pandaName: String) : Mammal(pandaName)

data class Manatee(val manateeName: String): Mammal(manateeName)

fun Mammal.vertebraeCount(): Int {
    return when (this) {
        is Manatee -> 6
        is Sloths -> 10
        else -> 7
    }
}

fun Mammal.knownSpeciesCount(): Int {
    return when (this) {
        is Sloths -> 6
        is Pandas -> 2
        is Manatee -> 3
    }
}

fun Mammal.isEndangered(): Boolean {
    return when (this) {
        is Sloths -> true
        is Pandas -> true
        is Manatee -> false
    }
}

fun mammalFactCheck(mammal: Mammal, factCheck: KFunction1<Mammal, Int>): Int {
    return factCheck(mammal)
}

fun slothActivity(sloth: Sloths, action: Unit) {
    sloth.run { action }
}

fun feedCrews(crew: List<Mammal>) {
    crew.forEach {
        it.eat()
    }
}

fun main() {
    val slothCrew = listOf(
        Sloths("Jerry", false, 15),
        Sloths("Bae", true, 12),
        Sloths("Alex", false, 15)
    )

    slothCrew.forEach {
        slothActivity(it, it.swim())
        slothActivity(it, it.relief())
    }


    val pandaCrew = listOf(
        Pandas("Tegan"),
        Pandas("Peggy")
    )

    feedCrews(slothCrew)
    feedCrews(pandaCrew)

    val crewCrewCrew = listOf(
        Sloths("Jerry", false, 15),
        Pandas("Tegan"),
        Manatee("Manny")
    )

    crewCrewCrew.forEach {
        mammalFactCheck(it, Mammal::vertebraeCount)
        mammalFactCheck(it, Mammal::knownSpeciesCount)
        mammalFactCheck(it, Mammal::isEndangered)
    }

    val compareByNames = Comparator { a: Mammal, b: Mammal ->
        a.name.first().toInt() - b.name.first().toInt()
    }

    println(crewCrewCrew.sortedWith(compareByNames))
}