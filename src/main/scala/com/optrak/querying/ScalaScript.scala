package com.optrak.querying

import javax.script.{ScriptContext, ScriptEngine, ScriptEngineManager, ScriptException}

import scala.tools.nsc.interpreter._

object ScalaScript {
  def main(args: Array[String]): Unit = {
    val engine = new ScriptEngineManager().getEngineByName("scala")
    engine.asInstanceOf[IMain].settings.usejavacp.value = true
    val settings = engine.asInstanceOf[IMain].settings
    // or
    // ((scala.tools.nsc.interpreter.IMain)engine).settings().processArgumentString("-usejavacp");
    val sbtClasspath = System.getProperty("sbt-classpath")

    settings.classpath.value += s":.:$sbtClasspath"

    engine.getContext.setAttribute("label", new Integer(4), ScriptContext.ENGINE_SCOPE);
    try {
      engine.eval("println(2 + label)")
    } catch {
      case ex: ScriptException => ex.printStackTrace()
    }

  }
}