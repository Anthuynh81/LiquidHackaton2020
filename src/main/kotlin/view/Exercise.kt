package view
import controller.HomeController
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Insets
import tornadofx.*

class ExerciseView: View(){
    val controller: HomeController by inject()

    override val root = borderpane(){
        this.padding = Insets(10.0,10.0,10.0,10.0)
        top{
            vbox{
                label("Your Exercises")
            }
        }

        center{
            listview(controller.output)
        }

        bottom {
            button("Go Back"){
                action{
                    replaceWith<MasterView>()
                }
            }
        }
    }
}