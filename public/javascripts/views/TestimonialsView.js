define([
  'jquery',
  'underscore',
  'backbone',
  'bxslider',
  'text!templates/testimonialsTemplate.html'
], function($,_, Backbone,bxSlider,testimonialsTemplate){
  var TestimonialsView = Backbone.View.extend({
    el: $(".toppart"),
/*    events: {'click #submitForm' : 'submitRequest',
        "change #modal_form :input" :"changedForm"} ,
*/    render: function(){
		this.fadeIn(testimonialsTemplate, this.$el)
	//this.$el.queue(me.fadeIn(testimonialsTemplate, me.$el)).queue($('.vban').delay(2000).css('display','block'));
		
		 $('.vbantest').onAvailable(function(){
		        $('.bxslider').bxSlider({
		        	  mode: 'fade',
		        	  auto: true,
		        	  captions: true,
		        	  adaptiveHeight:true
		        	});
			    $('.vbantest').css('display','block') ;   
		});

	}
   });

  return TestimonialsView;
});

