define([
  'jquery',
  'underscore',
  'backbone',
  'text!templates/internationalTemplate.html',
  'text!templates/coreAreaTemplate.html'
], function($, _, Backbone,internationalTemplate,coreAreaTemplate){
  var InternationalView = Backbone.View.extend({
    el: $(".toppart"),
    events: {'click .whyus' : 'showwhyus'/*,'click .patappointment' : 'showpatview','click .docappointmentprint' : 'showrddocview'*/} ,
    render: function(){
    	//this.$el.html(welcomeTemplate);
    	this.fadeIn(internationalTemplate, this.$el);
    },
    showwhyus: function(event){
    	$(".modal").html(coreAreaTemplate);
        $(".modal").lightbox_me({centered: true, onLoad: function() {
			}, onClose: function(){
				
			}
        });
   	 
   }
  });

  return InternationalView;
});
