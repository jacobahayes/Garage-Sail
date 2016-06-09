
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/JacobHayes/Documents/GarageSail/Garage_Sail/conf/routes
// @DATE:Wed Jun 08 19:35:50 EDT 2016


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
