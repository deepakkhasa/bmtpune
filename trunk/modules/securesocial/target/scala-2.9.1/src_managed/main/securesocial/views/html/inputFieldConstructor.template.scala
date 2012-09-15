
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
object inputFieldConstructor extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[views.html.helper.FieldElements,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(elements: views.html.helper.FieldElements):play.api.templates.Html = {
        _display_ {import play.api.i18n._

import views.html.helper._


Seq[Any](format.raw/*1.45*/("""

"""),format.raw/*5.1*/("""
<div class="control-group """),_display_(Seq[Any](/*6.28*/elements/*6.36*/.args.get('_class))),format.raw/*6.54*/(""" """),_display_(Seq[Any](/*6.56*/if(elements.hasErrors)/*6.78*/ {_display_(Seq[Any](format.raw/*6.80*/("""error""")))})),format.raw/*6.86*/("""" id=""""),_display_(Seq[Any](/*6.93*/elements/*6.101*/.args.get('_id).getOrElse(elements.id + "_field"))),format.raw/*6.150*/("""">
    <label class="control-label" for=""""),_display_(Seq[Any](/*7.40*/elements/*7.48*/.id)),format.raw/*7.51*/("""">"""),_display_(Seq[Any](/*7.54*/elements/*7.62*/.label(elements.lang))),format.raw/*7.83*/("""</label>
    <div class="controls">
        """),_display_(Seq[Any](/*9.10*/elements/*9.18*/.input)),format.raw/*9.24*/("""
        <span class="help-inline">"""),_display_(Seq[Any](/*10.36*/elements/*10.44*/.errors(elements.lang).mkString(", "))),format.raw/*10.81*/("""</span>
        <span class="help-block">"""),_display_(Seq[Any](/*11.35*/elements/*11.43*/.infos(elements.lang).mkString(", "))),format.raw/*11.79*/("""</span>
    </div>
</div>"""))}
    }
    
    def render(elements:views.html.helper.FieldElements) = apply(elements)
    
    def f:((views.html.helper.FieldElements) => play.api.templates.Html) = (elements) => apply(elements)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Tue Sep 11 21:58:14 IST 2012
                    SOURCE: C:/Users/padmaraj/nibd/appment/modules/securesocial/app/securesocial/views/inputFieldConstructor.scala.html
                    HASH: 329330adf25720b9192566d36032026e03a03ae4
                    MATRIX: 559->1|730->44|758->98|821->126|837->134|876->152|913->154|943->176|982->178|1019->184|1061->191|1078->199|1149->248|1226->290|1242->298|1266->301|1304->304|1320->312|1362->333|1442->378|1458->386|1485->392|1557->428|1574->436|1633->473|1711->515|1728->523|1786->559
                    LINES: 19->1|25->1|27->5|28->6|28->6|28->6|28->6|28->6|28->6|28->6|28->6|28->6|28->6|29->7|29->7|29->7|29->7|29->7|29->7|31->9|31->9|31->9|32->10|32->10|32->10|33->11|33->11|33->11
                    -- GENERATED --
                */
            