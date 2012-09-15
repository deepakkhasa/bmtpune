
package securesocial.views.html.Registration

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
object signUp extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[Form[securesocial.controllers.Registration.RegistrationInfo],play.api.mvc.Flash,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(signUpForm:Form[securesocial.controllers.Registration.RegistrationInfo])(implicit flash: play.api.mvc.Flash):play.api.templates.Html = {
        _display_ {import helper._

implicit def /*3.2*/implicitFieldConstructor/*3.26*/ = {{ FieldConstructor(securesocial.views.html.inputFieldConstructor.f) }};
Seq[Any](format.raw/*1.111*/("""
"""),format.raw/*3.99*/("""

"""),_display_(Seq[Any](/*5.2*/securesocial/*5.14*/.views.html.main( Messages("securesocial.signup.title") )/*5.71*/ {_display_(Seq[Any](format.raw/*5.73*/("""
    <div class="page-header">
        <h1>Sign Up</h1>
    </div>

    """),_display_(Seq[Any](/*10.6*/flash/*10.11*/.get("error").map/*10.28*/ { msg =>_display_(Seq[Any](format.raw/*10.37*/("""
    <div class="alert alert-error">
        """),_display_(Seq[Any](/*12.10*/Messages(msg))),format.raw/*12.23*/("""
    </div>
    """)))})),format.raw/*14.6*/("""

    """),_display_(Seq[Any](/*16.6*/helper/*16.12*/.form(action = securesocial.controllers.routes.Registration.handleSignUp(), 'class -> "form-horizontal", 'autocomplete -> "off")/*16.140*/ {_display_(Seq[Any](format.raw/*16.142*/("""
        <fieldset>

            """),_display_(Seq[Any](/*19.14*/helper/*19.20*/.inputText(
                signUpForm("userName"),
                '_label -> Messages("securesocial.signup.username"),
                'class -> "input-xlarge"
            ))),format.raw/*23.14*/("""

            """),_display_(Seq[Any](/*25.14*/helper/*25.20*/.inputText(
                signUpForm("fullName"),
                '_label -> Messages("securesocial.signup.fullName"),
                'class -> "input-xlarge"
            ))),format.raw/*29.14*/("""

            """),_display_(Seq[Any](/*31.14*/helper/*31.20*/.inputText(
                signUpForm("email.email1"),
                '_label -> Messages("securesocial.signup.email1"),
                'class -> "input-xlarge"
            ))),format.raw/*35.14*/("""

            """),_display_(Seq[Any](/*37.14*/helper/*37.20*/.inputText(
                signUpForm("email.email2"),
                '_label -> Messages("securesocial.signup.email2"),
                'class -> "input-xlarge"
            ))),format.raw/*41.14*/("""

            """),_display_(Seq[Any](/*43.14*/helper/*43.20*/.inputPassword(
                signUpForm("password.password1"),
                '_label -> Messages("securesocial.signup.password1"),
                'class -> "input-xlarge"
            ))),format.raw/*47.14*/("""

            """),_display_(Seq[Any](/*49.14*/helper/*49.20*/.inputPassword(
                signUpForm("password.password2"),
                '_label -> Messages("securesocial.signup.password2"),
                'class -> "input-xlarge"
            ))),format.raw/*53.14*/("""

            <div class="form-actions">
                <button type="submit" class="btn btn-primary">"""),_display_(Seq[Any](/*56.64*/Messages("securesocial.signup.createAccount"))),format.raw/*56.109*/("""</button>
                <a class="btn" href=""""),_display_(Seq[Any](/*57.39*/securesocial/*57.51*/.controllers.routes.LoginPage.login())),format.raw/*57.88*/("""">"""),_display_(Seq[Any](/*57.91*/Messages("securesocial.signup.cancel"))),format.raw/*57.129*/("""</a>
            </div>
        </fieldset>
    """)))})),format.raw/*60.6*/("""
""")))})))}
    }
    
    def render(signUpForm:Form[securesocial.controllers.Registration.RegistrationInfo],flash:play.api.mvc.Flash) = apply(signUpForm)(flash)
    
    def f:((Form[securesocial.controllers.Registration.RegistrationInfo]) => (play.api.mvc.Flash) => play.api.templates.Html) = (signUpForm) => (flash) => apply(signUpForm)(flash)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Tue Sep 11 21:58:14 IST 2012
                    SOURCE: C:/Users/padmaraj/nibd/appment/modules/securesocial/app/securesocial/views/Registration/signUp.scala.html
                    HASH: fe5c3c48c53e784b5738b5c394a170687ce80382
                    MATRIX: 605->1|799->129|831->153|935->110|963->226|1000->229|1020->241|1085->298|1124->300|1232->373|1246->378|1272->395|1319->404|1401->450|1436->463|1484->480|1526->487|1541->493|1679->621|1720->623|1790->657|1805->663|2002->838|2053->853|2068->859|2265->1034|2316->1049|2331->1055|2530->1232|2581->1247|2596->1253|2795->1430|2846->1445|2861->1451|3073->1641|3124->1656|3139->1662|3351->1852|3491->1956|3559->2001|3643->2049|3664->2061|3723->2098|3762->2101|3823->2139|3903->2188
                    LINES: 19->1|22->3|22->3|23->1|24->3|26->5|26->5|26->5|26->5|31->10|31->10|31->10|31->10|33->12|33->12|35->14|37->16|37->16|37->16|37->16|40->19|40->19|44->23|46->25|46->25|50->29|52->31|52->31|56->35|58->37|58->37|62->41|64->43|64->43|68->47|70->49|70->49|74->53|77->56|77->56|78->57|78->57|78->57|78->57|78->57|81->60
                    -- GENERATED --
                */
            