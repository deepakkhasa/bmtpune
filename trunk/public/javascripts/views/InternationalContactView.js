define([
  'jquery',
  'underscore',
  'backbone',
  'text!templates/internContactTemplate.html'
], function($, _, Backbone,internContactTemplate){
  var InternationalContactView = Backbone.View.extend({
    el: $(".toppart"),
    //events: {'click .whyus' : 'showwhyus','click .icontactus' : 'showcontactus'} ,
    render: function(){
    	this.fadeIn(internContactTemplate, this.$el);
    },
    showwhyus: function(event){
    	$(".modal").html(coreAreaTemplate);
        $(".modal").lightbox_me({centered: true, onLoad: function() {
			}, onClose: function(){
				
			}
        });
   	 
   }
  });

  return InternationalContactView;
});
