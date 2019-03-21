open class Mammal(val name: String) {
    fun eat() {}
    fun sleep() {}
}

data class Sloths(val slothName: String, val isTwoFingered: Boolean): Mammal(slothName)

data class Pandas(val pandaName: String) : Mammal(pandaName)

fun feedCrews(crew: List<Mammal>) {
    crew.forEach {
        it.eat()
    }
}

fun main() {
    val slothCrew = listOf(
        Sloths("Jerry", false),
        Sloths("Bae", true),
        Sloths("Alex", false)
    )

    val pandaCrew = listOf(
        Pandas("Tegan"),
        Pandas("Peggy")
    )

    feedCrews(slothCrew)
    feedCrews(pandaCrew)

    val crewCrewCrew = listOf(
        Sloths("Jerry", false),
        Sloths("Bae", true),
        Sloths("Alex", false),
        Pandas("Tegan"),
        Pandas("Peggy")
    )

    val compareByNames = Comparator { a: Mammal, b: Mammal ->
        a.name.first().toInt() - b.name.first().toInt()
    }

    println(crewCrewCrew.sortedWith(compareByNames))
}