
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/christopherpaschal/Documents/tech4/summer/cs2340/garagesail/Garage_Sail/conf/routes
// @DATE:Wed Jun 08 20:11:49 EDT 2016


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
