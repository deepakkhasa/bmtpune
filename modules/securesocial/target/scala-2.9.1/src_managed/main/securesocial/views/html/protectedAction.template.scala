
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
object protectedAction extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[securesocial.core.SocialUser,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(user: securesocial.core.SocialUser):play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.38*/("""

"""),_display_(Seq[Any](/*3.2*/main("SecureSocial - Sample Protected Page")/*3.46*/ {_display_(Seq[Any](format.raw/*3.48*/("""
<div class="page-header">
    <h2><img src=""""),_display_(Seq[Any](/*5.20*/user/*5.24*/.avatarUrl)),format.raw/*5.34*/("""" alt=""""),_display_(Seq[Any](/*5.42*/user/*5.46*/.displayName)),format.raw/*5.58*/("""" width="40px" height="40px"/> Welcome """),_display_(Seq[Any](/*5.98*/user/*5.102*/.displayName)),format.raw/*5.114*/("""</h2>
</div>

<div class="clearfix">
    <h2>User Details</h2>

    <ul>
        <li>User Id: """),_display_(Seq[Any](/*12.23*/user/*12.27*/.id.id)),format.raw/*12.33*/("""</li>
        <li>Logged in from: """),_display_(Seq[Any](/*13.30*/user/*13.34*/.id.providerId)),format.raw/*13.48*/("""</li>
        <li>Email: """),_display_(Seq[Any](/*14.21*/user/*14.25*/.email.map/*14.35*/ { email =>_display_(Seq[Any](format.raw/*14.46*/(""" """),_display_(Seq[Any](/*14.48*/email)),format.raw/*14.53*/(""" """)))}/*14.55*/.getOrElse("Not Available"))),format.raw/*14.82*/("""</li>
        <li>Authentication method: """),_display_(Seq[Any](/*15.37*/user/*15.41*/.authMethod)),format.raw/*15.52*/("""</li>
    </ul>

    """),_display_(Seq[Any](/*18.6*/user/*18.10*/.oAuth1Info.map/*18.25*/ { info =>_display_(Seq[Any](format.raw/*18.35*/("""
        <h2>OAuth1 Info</h2>

        <ul>
            <li>Token: """),_display_(Seq[Any](/*22.25*/info/*22.29*/.token)),format.raw/*22.35*/("""</li>
            <li>Secret: """),_display_(Seq[Any](/*23.26*/info/*23.30*/.secret)),format.raw/*23.37*/("""</li>
        </ul>
    """)))})),format.raw/*25.6*/("""

    """),_display_(Seq[Any](/*27.6*/user/*27.10*/.oAuth2Info.map/*27.25*/ { info =>_display_(Seq[Any](format.raw/*27.35*/("""
        <h2>OAuth2 Info</h2>

        <ul>
            <li>Access Token: """),_display_(Seq[Any](/*31.32*/info/*31.36*/.accessToken)),format.raw/*31.48*/("""</li>
            """),_display_(Seq[Any](/*32.14*/info/*32.18*/.tokenType.map/*32.32*/ { t =>_display_(Seq[Any](format.raw/*32.39*/(""" <li>Token Type: """),_display_(Seq[Any](/*32.57*/t)),format.raw/*32.58*/("""</li> """)))})),format.raw/*32.65*/("""
            """),_display_(Seq[Any](/*33.14*/info/*33.18*/.expiresIn.map/*33.32*/ { exp =>_display_(Seq[Any](format.raw/*33.41*/(""" <li>Expires in: """),_display_(Seq[Any](/*33.59*/exp)),format.raw/*33.62*/(""" seconds</li>""")))})),format.raw/*33.76*/("""
            """),_display_(Seq[Any](/*34.14*/info/*34.18*/.refreshToken.map/*34.35*/ { rt =>_display_(Seq[Any](format.raw/*34.43*/(""" <li>Refresh Token: """),_display_(Seq[Any](/*34.64*/rt)),format.raw/*34.66*/("""</li>""")))})),format.raw/*34.72*/("""
        </ul>
    """)))})),format.raw/*36.6*/("""
    <hr>
    <a class="btn" href=""""),_display_(Seq[Any](/*38.27*/securesocial/*38.39*/.controllers.routes.LoginPage.logout())),format.raw/*38.77*/("""">Logout</a>
</div>
""")))})))}
    }
    
    def render(user:securesocial.core.SocialUser) = apply(user)
    
    def f:((securesocial.core.SocialUser) => play.api.templates.Html) = (user) => apply(user)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Tue Sep 11 21:58:14 IST 2012
                    SOURCE: C:/Users/padmaraj/nibd/appment/modules/securesocial/app/securesocial/views/protectedAction.scala.html
                    HASH: 07e076952278408f2a019f56dd25c45de405e173
                    MATRIX: 550->1|663->37|700->40|752->84|791->86|872->132|884->136|915->146|958->154|970->158|1003->170|1078->210|1091->214|1125->226|1256->321|1269->325|1297->331|1368->366|1381->370|1417->384|1479->410|1492->414|1511->424|1560->435|1598->437|1625->442|1646->444|1695->471|1773->513|1786->517|1819->528|1876->550|1889->554|1913->569|1961->579|2065->647|2078->651|2106->657|2173->688|2186->692|2215->699|2271->724|2313->731|2326->735|2350->750|2398->760|2509->835|2522->839|2556->851|2611->870|2624->874|2647->888|2692->895|2746->913|2769->914|2808->921|2858->935|2871->939|2894->953|2941->962|2995->980|3020->983|3066->997|3116->1011|3129->1015|3155->1032|3201->1040|3258->1061|3282->1063|3320->1069|3371->1089|3443->1125|3464->1137|3524->1175
                    LINES: 19->1|22->1|24->3|24->3|24->3|26->5|26->5|26->5|26->5|26->5|26->5|26->5|26->5|26->5|33->12|33->12|33->12|34->13|34->13|34->13|35->14|35->14|35->14|35->14|35->14|35->14|35->14|35->14|36->15|36->15|36->15|39->18|39->18|39->18|39->18|43->22|43->22|43->22|44->23|44->23|44->23|46->25|48->27|48->27|48->27|48->27|52->31|52->31|52->31|53->32|53->32|53->32|53->32|53->32|53->32|53->32|54->33|54->33|54->33|54->33|54->33|54->33|54->33|55->34|55->34|55->34|55->34|55->34|55->34|55->34|57->36|59->38|59->38|59->38
                    -- GENERATED --
                */
            