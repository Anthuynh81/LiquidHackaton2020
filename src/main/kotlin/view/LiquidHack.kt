package view

import controller.HomeController
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.layout.BorderPane
import javafx.scene.text.TextAlignment
import tornadofx.*

class MyApp : App(MasterView::class)

//Starting Screen
class MasterView : View() {
    override val root = borderpane() {
        this.padding = Insets(10.0,10.0,10.0,10.0)
        top<TopView>()
        bottom<BottomView>()
    }
}

class TopView : View() {
    val controller: HomeController by inject()
    val positions = FXCollections.observableArrayList("Top","Mid","Bot","Sup","Jg")

    override val root = borderpane() {

        top{
            val lbl = label("Exercise Evolved")

            BorderPane.setAlignment(lbl, Pos.CENTER)
        }

        center {
            vbox{
                label("Games")
                combobox(controller.game, controller.games)
            }
        }

        val leagueView = find<League>()
        val csgo = find<CS>()

        bottomProperty()
            .bind(controller.game.select  {
                if(it == "Counter Strike") {
                    csgo
                } else {
                    leagueView
                }.root.toProperty()
            })
    }
}

class BottomView : View() {
    val controller : HomeController by inject()
    override val root = borderpane() {
        left {
            button("Exercise") {
                action {
                    controller.calculateExercise()
                    controller.kills.value = 0.0
                    controller.deaths.value = 0.0
                    controller.assists.value = 0.0
                    find<MasterView>().replaceWith<ExerciseView>()
                }
            }
        }
        right {
            button("Settings") {
                action{
                    find<MasterView>().replaceWith<SettingView>()
                }
            }
        }
    }
}

class League : View() {
    val controller : HomeController by inject()
    val positions = FXCollections.observableArrayList("Top","Mid","Bot","Sup","Jg")

    override val root = form() {
        fieldset {
            field("Kills") {
                textfield(controller.kills)
            }
            field("Death") {
                textfield(controller.deaths)
            }
            field("Assists") {
                textfield(controller.assists)
            }
            field("Position"){
                combobox(controller.position, positions)
            }
        }
    }
}

class CS : View() {
    val controller : HomeController by inject()

    override val root = form() {
        fieldset {
            field("Kills") {
                textfield(controller.kills)
            }
            field("Death") {
                textfield(controller.deaths)
            }
            field("Assists") {
                textfield(controller.assists)
            }
        }
    }
}


fun main(args: Array<String>) {
    launch<MyApp>(args)
}


