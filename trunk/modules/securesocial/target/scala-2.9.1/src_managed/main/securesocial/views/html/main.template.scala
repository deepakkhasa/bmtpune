
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
object main extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[String,Html,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(title: String)(content: Html):play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.32*/("""

<!DOCTYPE html>

<html>
    <head>
        <title>"""),_display_(Seq[Any](/*7.17*/title)),format.raw/*7.22*/("""</title>
        <link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*8.54*/routes/*8.60*/.Assets.at("stylesheets/main.css"))),format.raw/*8.94*/("""">
        <link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*9.54*/routes/*9.60*/.Assets.at("stylesheets/global.css"))),format.raw/*9.96*/("""">
        <link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*10.54*/routes/*10.60*/.Assets.at("bootstrap/css/bootstrap.min.css"))),format.raw/*10.105*/("""">
        <link rel="shortcut icon" type="image/png" href=""""),_display_(Seq[Any](/*11.59*/routes/*11.65*/.Assets.at("images/icon.jpg"))),format.raw/*11.94*/("""">
	    <script type='text/javascript' src=""""),_display_(Seq[Any](/*12.43*/routes/*12.49*/.Assets.at("javascripts/jquery-1.8.1.min.js"))),format.raw/*12.94*/(""""></script>
        <script src=""""),_display_(Seq[Any](/*13.23*/routes/*13.29*/.Assets.at("javascripts/slides.min.jquery.js"))),format.raw/*13.75*/("""" type="text/javascript"></script>
        <script src=""""),_display_(Seq[Any](/*14.23*/routes/*14.29*/.Assets.at("javascripts/jcarousellite_1.0.1c4.js"))),format.raw/*14.79*/("""" type="text/javascript"></script>
        
    </head>
    <body>
           	<div id="maincontainer">
				<div id="topsection">
					<div id="logoarea">
						<a name="top"></a>
						<h1>
							<a href="/"><span>American Society of Hematology</span>
							</a>
						</h1>
					</div>
				</div>
				<div id="nav">
					<ul id="jsddm">
						<li><a href="#">CONTACT US</a></li>
						<li><a href="#">ABOUT US</a></li>
					    <li><a href="#">SERVICES</a>
					        <ul>
					            <li><a href="#">BONE MARROW TRANSPLANT UNIT</a></li>
					            <li><a href="#">ADULT & PAEDIATRIC HAEMATOLOGY</a></li>
					            <li><a href="#">SURGICAL SERVICES</a></li>
					            <li><a href="#">PATHOLOGY</a></li>
					            
					        </ul>
					    </li>
					    <li><a href="/">HOME</a></li>
					</ul>					
				</div>
			</div>    
	         <div class="container" style="padding-top:30px">
	            """),_display_(Seq[Any](/*46.15*/content)),format.raw/*46.22*/("""
	        </div>

<script type="text/javascript" src=""""),_display_(Seq[Any](/*49.38*/routes/*49.44*/.Assets.at("javascripts/appment.js"))),format.raw/*49.80*/(""""></script>	
</body>
</html>
"""))}
    }
    
    def render(title:String,content:Html) = apply(title)(content)
    
    def f:((String) => (Html) => play.api.templates.Html) = (title) => (content) => apply(title)(content)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Tue Sep 11 21:58:14 IST 2012
                    SOURCE: C:/Users/padmaraj/nibd/appment/modules/securesocial/app/securesocial/views/main.scala.html
                    HASH: a1d61a415f5a7c4ed2c217fb4f477603a3c71876
                    MATRIX: 522->1|629->31|717->84|743->89|840->151|854->157|909->191|1000->247|1014->253|1071->289|1163->345|1178->351|1246->396|1343->457|1358->463|1409->492|1490->537|1505->543|1572->588|1642->622|1657->628|1725->674|1818->731|1833->737|1905->787|2880->1726|2909->1733|3000->1788|3015->1794|3073->1830
                    LINES: 19->1|22->1|28->7|28->7|29->8|29->8|29->8|30->9|30->9|30->9|31->10|31->10|31->10|32->11|32->11|32->11|33->12|33->12|33->12|34->13|34->13|34->13|35->14|35->14|35->14|67->46|67->46|70->49|70->49|70->49
                    -- GENERATED --
                */
            