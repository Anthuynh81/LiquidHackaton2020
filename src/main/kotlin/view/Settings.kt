package view
import controller.HomeController
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Insets
import tornadofx.*

class SettingView: View(){
    val controller : HomeController by inject()
    override val root = borderpane(){
        this.padding = Insets(10.0,10.0,10.0,10.0)
        top{
            vbox{
                label("Settings")
                label("You can enable and disable certain exercises here")
            }
        }

        center{
            vbox{
                checkbox("Pushups", controller.pushups)

                checkbox("Crunches", controller.crunches)

                checkbox("Jumping Jacks", controller.jacks)

                checkbox("Squats", controller.squats)
            }

        }

        bottom{
            button("Go Back"){
                action{
                    replaceWith<MasterView>()
                }
            }
        }

    }
}