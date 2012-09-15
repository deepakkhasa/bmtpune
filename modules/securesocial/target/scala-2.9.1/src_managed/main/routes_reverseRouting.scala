// @SOURCE:C:/Users/padmaraj/nibd/appment/modules/securesocial/conf/routes
// @HASH:881d4ff90023137cbeccb37bcecd51f30f552d6a
// @DATE:Tue Sep 11 21:58:12 IST 2012

import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._


import Router.queryString


// @LINE:15
// @LINE:14
// @LINE:11
// @LINE:10
// @LINE:7
// @LINE:6
package securesocial.controllers {

// @LINE:15
// @LINE:14
// @LINE:7
// @LINE:6
class ReverseLoginPage {
    


 
// @LINE:7
def logout() = {
   Call("GET", "/logout")
}
                                                        
 
// @LINE:15
def authenticateByPost(provider:String) = {
   Call("POST", "/authenticate/" + implicitly[PathBindable[String]].unbind("provider", provider))
}
                                                        
 
// @LINE:6
def login() = {
   Call("GET", "/login")
}
                                                        
 
// @LINE:14
def authenticate(provider:String) = {
   Call("GET", "/authenticate/" + implicitly[PathBindable[String]].unbind("provider", provider))
}
                                                        

                      
    
}
                            

// @LINE:11
// @LINE:10
class ReverseRegistration {
    


 
// @LINE:11
def handleSignUp() = {
   Call("POST", "/signup")
}
                                                        
 
// @LINE:10
def signUp() = {
   Call("GET", "/signup")
}
                                                        

                      
    
}
                            
}
                    

// @LINE:20
package controllers {

// @LINE:20
class ReverseAssets {
    


 
// @LINE:20
def at(file:String) = {
   Call("GET", "/assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                                                        

                      
    
}
                            
}
                    


// @LINE:15
// @LINE:14
// @LINE:11
// @LINE:10
// @LINE:7
// @LINE:6
package securesocial.controllers.javascript {

// @LINE:15
// @LINE:14
// @LINE:7
// @LINE:6
class ReverseLoginPage {
    


 
// @LINE:7
def logout = JavascriptReverseRoute(
   "securesocial.controllers.LoginPage.logout",
   """
      function() {
      return _wA({method:"GET", url:"/logout"})
      }
   """
)
                                                        
 
// @LINE:15
def authenticateByPost = JavascriptReverseRoute(
   "securesocial.controllers.LoginPage.authenticateByPost",
   """
      function(provider) {
      return _wA({method:"POST", url:"/authenticate/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("provider", provider)})
      }
   """
)
                                                        
 
// @LINE:6
def login = JavascriptReverseRoute(
   "securesocial.controllers.LoginPage.login",
   """
      function() {
      return _wA({method:"GET", url:"/login"})
      }
   """
)
                                                        
 
// @LINE:14
def authenticate = JavascriptReverseRoute(
   "securesocial.controllers.LoginPage.authenticate",
   """
      function(provider) {
      return _wA({method:"GET", url:"/authenticate/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("provider", provider)})
      }
   """
)
                                                        

                      
    
}
                            

// @LINE:11
// @LINE:10
class ReverseRegistration {
    


 
// @LINE:11
def handleSignUp = JavascriptReverseRoute(
   "securesocial.controllers.Registration.handleSignUp",
   """
      function() {
      return _wA({method:"POST", url:"/signup"})
      }
   """
)
                                                        
 
// @LINE:10
def signUp = JavascriptReverseRoute(
   "securesocial.controllers.Registration.signUp",
   """
      function() {
      return _wA({method:"GET", url:"/signup"})
      }
   """
)
                                                        

                      
    
}
                            
}
                    

// @LINE:20
package controllers.javascript {

// @LINE:20
class ReverseAssets {
    


 
// @LINE:20
def at = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"/assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                                                        

                      
    
}
                            
}
                    


// @LINE:15
// @LINE:14
// @LINE:11
// @LINE:10
// @LINE:7
// @LINE:6
package securesocial.controllers.ref {

// @LINE:15
// @LINE:14
// @LINE:7
// @LINE:6
class ReverseLoginPage {
    


 
// @LINE:7
def logout() = new play.api.mvc.HandlerRef(
   securesocial.controllers.LoginPage.logout(), HandlerDef(this, "securesocial.controllers.LoginPage", "logout", Seq())
)
                              
 
// @LINE:15
def authenticateByPost(provider:String) = new play.api.mvc.HandlerRef(
   securesocial.controllers.LoginPage.authenticateByPost(provider), HandlerDef(this, "securesocial.controllers.LoginPage", "authenticateByPost", Seq(classOf[String]))
)
                              
 
// @LINE:6
def login() = new play.api.mvc.HandlerRef(
   securesocial.controllers.LoginPage.login(), HandlerDef(this, "securesocial.controllers.LoginPage", "login", Seq())
)
                              
 
// @LINE:14
def authenticate(provider:String) = new play.api.mvc.HandlerRef(
   securesocial.controllers.LoginPage.authenticate(provider), HandlerDef(this, "securesocial.controllers.LoginPage", "authenticate", Seq(classOf[String]))
)
                              

                      
    
}
                            

// @LINE:11
// @LINE:10
class ReverseRegistration {
    


 
// @LINE:11
def handleSignUp() = new play.api.mvc.HandlerRef(
   securesocial.controllers.Registration.handleSignUp(), HandlerDef(this, "securesocial.controllers.Registration", "handleSignUp", Seq())
)
                              
 
// @LINE:10
def signUp() = new play.api.mvc.HandlerRef(
   securesocial.controllers.Registration.signUp(), HandlerDef(this, "securesocial.controllers.Registration", "signUp", Seq())
)
                              

                      
    
}
                            
}
                    

// @LINE:20
package controllers.ref {

// @LINE:20
class ReverseAssets {
    


 
// @LINE:20
def at(path:String, file:String) = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]))
)
                              

                      
    
}
                            
}
                    
                