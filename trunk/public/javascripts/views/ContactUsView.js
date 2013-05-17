define([
  'jquery',
  'underscore',
  'backbone',
  'text!templates/contactusTemplate.html'
], function($, _, Backbone,contactusTemplate){
  var AboutUsView = Backbone.View.extend({
    el: $(".toppart"),
 //   events: {'click .docappointment' : 'showdocview','click .patappointment' : 'showpatview','click .docappointmentprint' : 'showrddocview'} ,
    render: function(){
    	//this.$el.html(welcomeTemplate);
    	this.fadeIn(contactusTemplate, this.$el);

    }
  });

  return AboutUsView;
});
