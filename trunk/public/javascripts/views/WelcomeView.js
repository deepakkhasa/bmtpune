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
    	'click .knowledge':'showKnowldge', 'click .askus':'showAskus','click .international':'showInternational','click .testimonies':'showTestimonials',
    	'click .clinical':'showClinical','click .donate':'showDonate'} ,
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
    	event.preventDefault();
    	this.goTo("aboutus");
   	 
   },
   showcontactus: function(event){
	   event.preventDefault();
   	this.goTo("contactus");
  	 
  },
   showhome: function(event){
    	window.location.href="/";
    },
    showVideos: function(event){
    	event.preventDefault();
    	this.goTo("videos");
    },
    showKnowldge:function(event){
    	event.preventDefault();
    	this.goTo("knowledge");
    },
    showAskus:function(event){
    	event.preventDefault();
    	this.goTo("askme");
    },
    showInternational:function(event){
    	event.preventDefault();
    	this.goTo("international");
    },
    showTestimonials:function(event){
    //	$('.testimony').html(testimonialsTemplate);
    	event.preventDefault();
    	this.goTo("testimonials");
    },
    showClinical:function(event){
    //	$('.testimony').html(testimonialsTemplate);
    	event.preventDefault();
    	this.goTo("clinical");
    },
    showDonate:function(event){
        //	$('.testimony').html(testimonialsTemplate);
        	event.preventDefault();
        	this.goTo("donate");
        }

    
    
  });

  return WelcomeView;
});
