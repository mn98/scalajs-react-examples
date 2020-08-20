package app

import app.components._
import org.scalajs.dom
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.{BackendScope, ReactEventFromInput, ScalaComponent}

object TodoApp {

  case class State(items: Seq[String], text: String)

  class Backend($: BackendScope[Unit, State]) {
    def onChange(e: ReactEventFromInput) = {
      val newValue = e.target.value
      $.modState(_.copy(text = newValue))
    }

    def handleSubmit(e: ReactEventFromInput) =
      e.preventDefaultCB >>
        $.modState(s => State(s.items :+ s.text, ""))

    def render(state: State) =
      <.div(
        <.h3("TODO"),
        Todos(state.items),
        <.form(^.onSubmit ==> handleSubmit,
          <.input(^.onChange ==> onChange, ^.value := state.text),
          <.button("Add #", state.items.length + 1)
        )
      )
  }

  val TodoApp = ScalaComponent.builder[Unit]
    .initialState(State(Nil, ""))
    .renderBackend[Backend]
    .build

  def main(args: Array[String]): Unit = {
    TodoApp() renderIntoDOM dom.document.getElementById("app")
  }

}
