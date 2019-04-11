package Week2HigherOrderFunctions
import kotlin.random.Random
import kotlin.reflect.KFunction1

/**
 *  Generics - Higher Order Functions
 */

sealed class Mammal(val name: String) {
    fun eat() {}
    fun sleep() {}
    fun swim() {
        println("${name.toUpperCase()} CAN SWIM")
    }
    open fun relief() {}
}

data class Sloth(val slothName: String,
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

data class Panda(val pandaName: String) : Mammal(pandaName)

data class Manatee(val manateeName: String): Mammal(manateeName)

fun Mammal.vertebraeCount(): Int {
    return when (this) {
        is Manatee -> 6
        is Sloth -> 10
        else -> 7
    }
}

// Sealed classes allows for hierarchical control so that the exhaustive
// types in when statements are within the sealed class type
fun Mammal.knownSpeciesCount(): Int {
    return when (this) {
        is Sloth -> 6
        is Panda -> 2
        is Manatee -> 3
    }
}

fun Mammal.isEndangered(): Boolean {
    return when (this) {
        is Sloth -> true
        is Panda -> true
        is Manatee -> false
    }
}

// Higher order function is the second parameter
fun mammalFactCheck(mammal: Mammal, factCheck: KFunction1<Mammal, Int>): Int {
    return factCheck(mammal)
}

fun main() {

    val crewCrewCrew = listOf(
        Sloth("Jerry", false, 15),
        Panda("Tegan"),
        Manatee("Manny")
    )

    crewCrewCrew.forEach {
        mammalFactCheck(it, Mammal::vertebraeCount)
        mammalFactCheck(it, Mammal::knownSpeciesCount)
        // Week2HigherOrderFunctions.Week3ReifiedGenerics.mammalFactCheck(it, Week3ReifiedGenerics.Mammal::Week2HigherOrderFunctions.Week3ReifiedGenerics.isEndangered)
    }
}