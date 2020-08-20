package app

import japgolly.scalajs.react.ScalaFnComponent
import japgolly.scalajs.react.vdom.html_<^._

package object components {

  val Todo =
    ScalaFnComponent[String] { props =>
      <.li(props)
    }

  val Todos =
    ScalaFnComponent[Seq[String]] { props =>
      <.ul(props.toVdomArray(s => Todo(s)))
    }

}
