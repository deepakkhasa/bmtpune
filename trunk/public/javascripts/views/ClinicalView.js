define([
  'jquery',
  'underscore',
  'backbone',
  'text!templates/clinicalTemplate.html'
], function($, _, Backbone,clinicalTemplate){
  var ClinicalView = Backbone.View.extend({
    el: $(".toppart"),
 //   events: {'click .docappointment' : 'showdocview','click .patappointment' : 'showpatview','click .docappointmentprint' : 'showrddocview'} ,
    render: function(){
    	//this.$el.html(welcomeTemplate);
    	this.fadeIn(clinicalTemplate, this.$el);
		 $('.clinicalsection').onAvailable(function(){
			    $("#faqs dd").hide();
			    $("#faqs dt").click(function () {
			        $(this).next("#faqs dd").slideToggle(500);
			        $(this).toggleClass("expanded");
			    });
		 });
    }
  });

  return ClinicalView;
});
