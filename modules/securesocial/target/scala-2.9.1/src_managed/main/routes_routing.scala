// @SOURCE:C:/Users/padmaraj/nibd/appment/modules/securesocial/conf/routes
// @HASH:881d4ff90023137cbeccb37bcecd51f30f552d6a
// @DATE:Tue Sep 11 21:58:12 IST 2012

import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._


import Router.queryString

object Routes extends Router.Routes {


// @LINE:6
val securesocial_controllers_LoginPage_login0 = Route("GET", PathPattern(List(StaticPart("/login"))))
                    

// @LINE:7
val securesocial_controllers_LoginPage_logout1 = Route("GET", PathPattern(List(StaticPart("/logout"))))
                    

// @LINE:10
val securesocial_controllers_Registration_signUp2 = Route("GET", PathPattern(List(StaticPart("/signup"))))
                    

// @LINE:11
val securesocial_controllers_Registration_handleSignUp3 = Route("POST", PathPattern(List(StaticPart("/signup"))))
                    

// @LINE:14
val securesocial_controllers_LoginPage_authenticate4 = Route("GET", PathPattern(List(StaticPart("/authenticate/"),DynamicPart("provider", """[^/]+"""))))
                    

// @LINE:15
val securesocial_controllers_LoginPage_authenticateByPost5 = Route("POST", PathPattern(List(StaticPart("/authenticate/"),DynamicPart("provider", """[^/]+"""))))
                    

// @LINE:20
val controllers_Assets_at6 = Route("GET", PathPattern(List(StaticPart("/assets/"),DynamicPart("file", """.+"""))))
                    
def documentation = List(("""GET""","""/login""","""securesocial.controllers.LoginPage.login"""),("""GET""","""/logout""","""securesocial.controllers.LoginPage.logout"""),("""GET""","""/signup""","""securesocial.controllers.Registration.signUp"""),("""POST""","""/signup""","""securesocial.controllers.Registration.handleSignUp"""),("""GET""","""/authenticate/$provider<[^/]+>""","""securesocial.controllers.LoginPage.authenticate(provider:String)"""),("""POST""","""/authenticate/$provider<[^/]+>""","""securesocial.controllers.LoginPage.authenticateByPost(provider:String)"""),("""GET""","""/assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)"""))
             
    
def routes:PartialFunction[RequestHeader,Handler] = {        

// @LINE:6
case securesocial_controllers_LoginPage_login0(params) => {
   call { 
        invokeHandler(_root_.securesocial.controllers.LoginPage.login, HandlerDef(this, "securesocial.controllers.LoginPage", "login", Nil))
   }
}
                    

// @LINE:7
case securesocial_controllers_LoginPage_logout1(params) => {
   call { 
        invokeHandler(_root_.securesocial.controllers.LoginPage.logout, HandlerDef(this, "securesocial.controllers.LoginPage", "logout", Nil))
   }
}
                    

// @LINE:10
case securesocial_controllers_Registration_signUp2(params) => {
   call { 
        invokeHandler(_root_.securesocial.controllers.Registration.signUp, HandlerDef(this, "securesocial.controllers.Registration", "signUp", Nil))
   }
}
                    

// @LINE:11
case securesocial_controllers_Registration_handleSignUp3(params) => {
   call { 
        invokeHandler(_root_.securesocial.controllers.Registration.handleSignUp, HandlerDef(this, "securesocial.controllers.Registration", "handleSignUp", Nil))
   }
}
                    

// @LINE:14
case securesocial_controllers_LoginPage_authenticate4(params) => {
   call(params.fromPath[String]("provider", None)) { (provider) =>
        invokeHandler(_root_.securesocial.controllers.LoginPage.authenticate(provider), HandlerDef(this, "securesocial.controllers.LoginPage", "authenticate", Seq(classOf[String])))
   }
}
                    

// @LINE:15
case securesocial_controllers_LoginPage_authenticateByPost5(params) => {
   call(params.fromPath[String]("provider", None)) { (provider) =>
        invokeHandler(_root_.securesocial.controllers.LoginPage.authenticateByPost(provider), HandlerDef(this, "securesocial.controllers.LoginPage", "authenticateByPost", Seq(classOf[String])))
   }
}
                    

// @LINE:20
case controllers_Assets_at6(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        invokeHandler(_root_.controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String])))
   }
}
                    
}
    
}
                