package controller
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import tornadofx.*
import kotlin.math.roundToInt
import kotlin.random.Random

class HomeController: Controller() {
    val kills = SimpleDoubleProperty()
    val deaths = SimpleDoubleProperty()
    val assists = SimpleDoubleProperty()
    val position = SimpleStringProperty()
    var ratio = 0.0

    val game = SimpleStringProperty("League of Legends")
    val games = FXCollections.observableArrayList("League of Legends", "Counter Strike")


    val pushups = SimpleBooleanProperty(true)
    val crunches = SimpleBooleanProperty(true)
    val jacks = SimpleBooleanProperty(true)
    val squats = SimpleBooleanProperty(true)

    var exercises = ArrayList<String>()
    var output = FXCollections.observableArrayList<String>()

    fun calculateExercise(){
        output.clear()
        exercises.clear()

        if (game.value.equals("League of Legends")){
            if(position.equals("Sup")){
                ratio = ((kills.value / 2) + assists.value) / deaths.value
            }else{
                ratio = ((assists.value / 2) + kills.value) / deaths.value
            }
        }else if(game.value.equals("Counter Strike")){
            ratio = ((assists.value / 2) + kills.value) / deaths.value
        }

        if(pushups.value) exercises.add("Pushups")
        if(crunches.value) exercises.add("Crunches")
        if(jacks.value) exercises.add("Jumping Jacks")
        if(squats.value) exercises.add("Squats")

        if(ratio.isNaN()){
            output.add("Perfect Game You deserve a break!")
        }else{
            for(x in exercises){
                var exerciseCount = Random.nextInt(5, 20) / ratio
                output.add("${exerciseCount.roundToInt().coerceIn(3,20)} $x" )
            }
        }
    }
}
