define([
  'jquery',
  'underscore',
  'backbone',
  'text!templates/aboutusTemplate.html'
], function($, _, Backbone,aboutusTemplate){
  var AboutUsView = Backbone.View.extend({
    el: $(".toppart"),
 //   events: {'click .docappointment' : 'showdocview','click .patappointment' : 'showpatview','click .docappointmentprint' : 'showrddocview'} ,
    render: function(){
    	//this.$el.html(welcomeTemplate);
    	this.fadeIn(aboutusTemplate, this.$el);

    }
  });

  return AboutUsView;
});
