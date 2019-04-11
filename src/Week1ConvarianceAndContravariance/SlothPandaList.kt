package Week1ConvarianceAndContravariance

data class Sloth(val name: String, val isTwoFingered: Boolean) {
    fun eat() {}
    fun sleep() {}
}

data class Panda(val name: String) {
    fun eat() {}
    fun sleep() {}
}

fun feedCrew(crew: List<Sloth>) {
    crew.forEach {
        it.eat()
    }
}

fun <T> List<T>.feedCrews() {
    forEach {
        // it.eat()
    }
}

fun main() {
    val slothGang = listOf(
        Sloth("Jerry", false),
        Sloth("Bae", true),
        Sloth("Chrissy", false)
    )

    val slothReg: List<Sloth> = listOf()
    val slothReg2 = listOf<Sloth>()
}