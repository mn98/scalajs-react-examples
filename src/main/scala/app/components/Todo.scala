package app.components

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

object Todo {

  case class Props(todo: String)

  val Todo =
    ScalaFnComponent[String] { props =>
      <.li(props)
    }

}
