
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/JacobHayes/Documents/PlayFramework/activator-dist-1.3.10/bin/play-java/conf/routes
// @DATE:Sun Jun 05 11:10:51 EDT 2016


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
