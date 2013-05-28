define([
  'jquery',
  'underscore',
  'backbone',
  'bxslider',
  'text!templates/welcomeTemplate.html'
], function($, _, Backbone,bxSlider,welcomeTemplate){
  var WelcomeView = Backbone.View.extend({
    el: $("#maincontainer"),
    events: {'click .aboutus' : 'showaboutus','click .logo': 'showhome', 'click .contactus':'showcontactus', 'click .myvideos': 'showVideos',
    	'click .knowledge':'showKnowldge', 'click .askus':'showAskus','click .international':'showInternational'} ,
    render: function(){
    	//this.$el.html(welcomeTemplate);
    	//this.fadeIn(welcomeTemplate, this.$el);
        $('.bxslider').bxSlider({
      	  mode: 'fade',
      	  auto: true,
      	  captions: true,
      	  pager:false,
      	  controls:false
      	});
        $('.vban').css('display','block');

    },
    showaboutus: function(event){
    	this.goTo("aboutus");
   	 
   },
   showcontactus: function(event){
   	this.goTo("contactus");
  	 
  },
   showhome: function(event){
    	window.location.href="/";
    },
    showVideos: function(event){
    	this.goTo("videos");
    },
    showKnowldge:function(event){
    	this.goTo("knowledge");
    },
    showAskus:function(event){
    	this.goTo("askme");
    },
    showInternational:function(event){
    	this.goTo("international");
    }
    
    
    
  });

  return WelcomeView;
});
