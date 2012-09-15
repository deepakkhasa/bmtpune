
package securesocial.views.html

import play.templates._
import play.templates.TemplateMagic._

import play.api.templates._
import play.api.templates.PlayMagic._
import models._
import controllers._
import play.api.i18n._
import play.api.mvc._
import play.api.data._
import views.html._
/**/
object login extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template3[Iterable[securesocial.core.IdentityProvider],Form[scala.Tuple2[String, String]],Option[String],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(providers: Iterable[securesocial.core.IdentityProvider], loginForm: Form[(String,String)], errorMsg: Option[String] = None):play.api.templates.Html = {
        _display_ {import helper._

implicit def /*4.2*/implicitFieldConstructor/*4.26*/ = {{ FieldConstructor(securesocial.views.html.inputFieldConstructor.f) }};
Seq[Any](format.raw/*1.126*/("""

"""),format.raw/*4.99*/("""

"""),_display_(Seq[Any](/*6.2*/main(Messages("securesocial.login.title"))/*6.44*/ {_display_(Seq[Any](format.raw/*6.46*/("""
    <div class="page-header">
        <h1>Login</h1>
    </div>

    """),_display_(Seq[Any](/*11.6*/errorMsg/*11.14*/.map/*11.18*/ { msg =>_display_(Seq[Any](format.raw/*11.27*/("""
        <div class="alert alert-error">
            """),_display_(Seq[Any](/*13.14*/Messages(msg))),format.raw/*13.27*/("""
        </div>
    """)))})),format.raw/*15.6*/("""

    <div class="clearfix">
        <p>"""),_display_(Seq[Any](/*18.13*/Messages("securesocial.login.instructions"))),format.raw/*18.56*/("""</p>

        <p>
            """),_display_(Seq[Any](/*21.14*/for(p <- providers if p.authMethod != securesocial.core.AuthenticationMethod.UserPassword) yield /*21.104*/ {_display_(Seq[Any](format.raw/*21.106*/("""
                """),_display_(Seq[Any](/*22.18*/defining( "images/providers/%s.png".format(p.providerId) )/*22.76*/ { imageUrl =>_display_(Seq[Any](format.raw/*22.90*/("""
                    <a href=""""),_display_(Seq[Any](/*23.31*/p/*23.32*/.authenticationUrl)),format.raw/*23.50*/(""""> <img src=""""),_display_(Seq[Any](/*23.64*/routes/*23.70*/.Assets.at(imageUrl))),format.raw/*23.90*/(""""/></a>
                """)))})),format.raw/*24.18*/("""
            """)))})),format.raw/*25.14*/("""
        </p>
    </div>

    <div class="clearfix">
        <p>"""),_display_(Seq[Any](/*30.13*/Messages("securesocial.login.useEmailAndPassword"))),format.raw/*30.63*/("""</p>

        """),_display_(Seq[Any](/*32.10*/helper/*32.16*/.form(action = securesocial.controllers.routes.LoginPage.authenticateByPost("userpass"), 'class -> "form-horizontal",  'autocomplete -> "off")/*32.158*/ {_display_(Seq[Any](format.raw/*32.160*/("""
            <fieldset>

                """),_display_(Seq[Any](/*35.18*/helper/*35.24*/.inputText(
                loginForm("username"),
                '_label -> Messages("securesocial.signup.username"),
                'class -> "input-xlarge"
                ))),format.raw/*39.18*/("""

                """),_display_(Seq[Any](/*41.18*/helper/*41.24*/.inputPassword(
                loginForm("password"),
                '_label -> Messages("securesocial.signup.password1"),
                'class -> "input-xlarge"
                ))),format.raw/*45.18*/("""

                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">"""),_display_(Seq[Any](/*48.68*/Messages("securesocial.login.title"))),format.raw/*48.104*/("""</button>
                    <a class="btn" href=""""),_display_(Seq[Any](/*49.43*/securesocial/*49.55*/.controllers.routes.LoginPage.login())),format.raw/*49.92*/("""">"""),_display_(Seq[Any](/*49.95*/Messages("securesocial.signup.cancel"))),format.raw/*49.133*/("""</a>
                </div>

            </fieldset>
        """)))})),format.raw/*53.10*/("""
    </div>


    <div class="clearfix">
        <p>"""),_display_(Seq[Any](/*58.13*/Messages("securesocial.login.signUp"))),format.raw/*58.50*/(""" <a href=""""),_display_(Seq[Any](/*58.61*/securesocial/*58.73*/.controllers.routes.Registration.signUp())),format.raw/*58.114*/("""">"""),_display_(Seq[Any](/*58.117*/Messages("securesocial.login.here"))),format.raw/*58.152*/("""</a></p>
    </div>
""")))})))}
    }
    
    def render(providers:Iterable[securesocial.core.IdentityProvider],loginForm:Form[scala.Tuple2[String, String]],errorMsg:Option[String]) = apply(providers,loginForm,errorMsg)
    
    def f:((Iterable[securesocial.core.IdentityProvider],Form[scala.Tuple2[String, String]],Option[String]) => play.api.templates.Html) = (providers,loginForm,errorMsg) => apply(providers,loginForm,errorMsg)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Tue Sep 11 21:58:14 IST 2012
                    SOURCE: C:/Users/padmaraj/nibd/appment/modules/securesocial/app/securesocial/views/login.scala.html
                    HASH: ea4126ffe7a83d976fed489e7b3d1367da6ef9e4
                    MATRIX: 606->1|815->145|847->169|951->125|980->242|1017->245|1067->287|1106->289|1212->360|1229->368|1242->372|1289->381|1379->435|1414->448|1466->469|1543->510|1608->553|1675->584|1782->674|1823->676|1877->694|1944->752|1996->766|2063->797|2073->798|2113->816|2163->830|2178->836|2220->856|2277->881|2323->895|2424->960|2496->1010|2547->1025|2562->1031|2714->1173|2755->1175|2833->1217|2848->1223|3048->1401|3103->1420|3118->1426|3323->1609|3471->1721|3530->1757|3618->1809|3639->1821|3698->1858|3737->1861|3798->1899|3892->1961|3981->2014|4040->2051|4087->2062|4108->2074|4172->2115|4212->2118|4270->2153
                    LINES: 19->1|22->4|22->4|23->1|25->4|27->6|27->6|27->6|32->11|32->11|32->11|32->11|34->13|34->13|36->15|39->18|39->18|42->21|42->21|42->21|43->22|43->22|43->22|44->23|44->23|44->23|44->23|44->23|44->23|45->24|46->25|51->30|51->30|53->32|53->32|53->32|53->32|56->35|56->35|60->39|62->41|62->41|66->45|69->48|69->48|70->49|70->49|70->49|70->49|70->49|74->53|79->58|79->58|79->58|79->58|79->58|79->58|79->58
                    -- GENERATED --
                */
            