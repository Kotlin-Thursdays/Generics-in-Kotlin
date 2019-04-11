package misc

sealed class SlothStates
object Sleeping: SlothStates()
data class Running(val speed: Double): SlothStates()
class Pooping: SlothStates()


class Behaviors{
    fun invariantError() {

    }

    // Removing the variance modifier works
    interface MutableReportList<E>: MutableList<E>

    fun mutableListOfSealedClass() {
        val slothActivities = mutableListOf<SlothStates>()

        slothActivities.add(Sleeping)
        slothActivities.add(Running(Double.MAX_VALUE))
        slothActivities.add(Pooping())
    }
}