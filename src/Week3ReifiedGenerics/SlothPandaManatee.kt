package Week3ReifiedGenerics

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

fun Mammal.knownSpeciesCount(): Int {
    return when (this) {
        is Sloth -> 6
        is Panda -> 2
        is Manatee -> 3
    }
}

fun numberOfToes(mammal: Mammal): Int {
    return when (mammal) {
        is Sloth -> { if (mammal.isTwoFingered) 2 else 3 }
        is Panda, is Manatee -> 5
    }
}

fun mammalFactCheck(mammal: Mammal, factCheck: KFunction1<Mammal, Int>): Int {
    return factCheck(mammal)
}

fun <T: Mammal> List<T>.printAllAnimalResults(factCheck: Mammal.() -> Int): List<T> {
    if (this.isNotEmpty()) {
        this.forEach {
                println("${it.javaClass.name} - ${it.factCheck()}")
            }
    }
    return this
}

fun <T: Mammal> printAnimalResultFiltered(
    clazz: Class<T>,
    list: List<Mammal>,
    factCheck: Mammal.() -> Int
): List<Mammal> {
    if (list.isNotEmpty()) {
        list.forEach {
            if (clazz.isInstance(it)) println("${it.javaClass.name} - ${it.factCheck()}")
        }
    }
    return list
}

inline fun <reified T: Mammal> printAnimalResultFiltered(
    list: List<Mammal>,
    factCheck: Mammal.() -> Int
): List<Mammal> {

    if (list.isNotEmpty()) {
        list.filterIsInstance<T>()
            .forEach { println("${it.javaClass.name} - ${it.factCheck()}") }
    }
    return list
}

fun <T: Mammal> List<Mammal>.printAnimalResultsExtensionFiltered(
    clazz: Class<T>,
    factCheck: Mammal.() -> Int
): List<Mammal> {
    if (this.isNotEmpty()) {
        this.filter { clazz.isInstance(it) }
            .forEach {
                println("${it.javaClass.name} - ${it.factCheck()}")
            }
    }
    return this
}

inline fun <reified T: Mammal> List<Mammal>.printAnimalResultsExtensionFiltered(
    factCheck: Mammal.() -> Int
): List<Mammal> {
    if (this.isNotEmpty()) {
        this.filterIsInstance<T>()
            .forEach {
                println("${it.javaClass.name} - ${it.factCheck()}")
            }
    }
    return this
}

fun main() {

    val crewCrewCrew = listOf(
        Sloth("Jerry", false, 15),
        Panda("Tegan"),
        Manatee("Manny")
    )

    println("\nTotal species count:")
    crewCrewCrew.printAllAnimalResults(Mammal::knownSpeciesCount)

    println("\nSpecies count calculated in main:")
    crewCrewCrew.filterIsInstance<Sloth>()
        .forEach {
            println("${it.javaClass.name} - " +
                    "${mammalFactCheck(it, Mammal::knownSpeciesCount)}"
            )
        }

    println("\nSpecies count with list as param:")
    printAnimalResultFiltered<Sloth>(crewCrewCrew, Mammal::knownSpeciesCount)
    println("\nSpecies count with list as param reified:")
    printAnimalResultFiltered(Sloth::class.java, crewCrewCrew, Mammal::knownSpeciesCount)


    println("\nSpecies count with list extension function:")
    crewCrewCrew.printAnimalResultsExtensionFiltered(Sloth::class.java, Mammal::knownSpeciesCount)
    println("\nSpecies count with list extension function reified:")
    crewCrewCrew.printAnimalResultsExtensionFiltered<Sloth>(Mammal::knownSpeciesCount)

}