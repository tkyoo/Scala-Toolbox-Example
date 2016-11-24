import scala.reflect.runtime.universe.Quasiquote
import scala.reflect.runtime._
import scala.tools.reflect.ToolBox

object toolbox {
  def main(args: Array[String]) : Unit = {
    println("Start ToolBox Example!")

    val cm = universe.runtimeMirror(getClass.getClassLoader)
    val toolBox = cm.mkToolBox()

    val parsed = toolBox.parse("""
    object hello { 
      def run( x : String) = x
    }
""")
    val helloClass = toolBox.define(parsed.asInstanceOf[toolBox.u.ImplDef])

    val testString = "Hello, World!"

    val run = toolBox.eval(q"$helloClass.run($testString)")

    println(run)

    println("Ended")
  }
}
