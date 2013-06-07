// Filename: router.js
define([
  'jquery',
  'underscore',
  'backbone',
  'views/WelcomeView',
  'views/AboutUsView',
  'views/ContactUsView',
  'views/VideosView',
  'views/KnowledgeView',
  'views/AskUsView',
  'views/InternationalView',
  'views/InternationalContactView',
  'views/TestimonialsView',
  'views/ClinicalView',
  'views/DonateView',
  'views/SetSessionView',
  'views/GenerateTokenView',
  'views/OpenSessionView'
], function($, _, Backbone, WelcomeView,AboutUsView, ContactUsView,VideosView,KnowledgeView,AskUsView,
		InternationalView,InternationalContactView,TestimonialsView,ClinicalView,DonateView,SetSessionView,GenerateTokenView,OpenSessionView) {
  
  var AppRouter = Backbone.Router.extend({
	  initialize: function(el) {
			this.el = el;
			
			//this.welcomeView = new WelcomeView();
		},
    routes: {
      // Define some URL routes
  //    'projects': 'showProjects',
      'aboutus': 'showAboutus',
      'contactus': 'showContactus',
      'videos' : 'showVideos',
      'knowledge': 'showKnowledge',
      'askme' : 'showAskme',
      'international' : 'showInternational',
      'icontact' : 'showiContactUs',
      'testimonials':'showTestimonials',
      'clinical': 'showClinical',
      'donate':'showDonate',
      'setsession?session=:session': 'setSession',
      'generatetoken?sessionid=:sessionid&on=:on&email=:email': 'generateToken',
      'opensession?session=:session': 'openSession',
      // Default
      '*default': 'decideAction'
    }
  });
  
  var initialize = function(){

    var app_router = new AppRouter;
    
    app_router.on('route:showAboutus', function(){
//    	$(".mainpanel").html("Loading...");
//    	event.preventDefault();
//		this.feedsView.render();
    	var aboutusView= new AboutUsView();
    	aboutusView.render();

    });
    app_router.on('route:showContactus', function(){
//    	$(".mainpanel").html("Loading...");
 //   	event.preventDefault();
//		this.feedsView.render();
    	var contactusView= new ContactUsView();
    	contactusView.render();

    });

    app_router.on('route:showVideos', function(){
//    	$(".mainpanel").html("Loading...");
  //  	event.preventDefault();
//		this.feedsView.render();
    	var videosView= new VideosView();
    	videosView.render();

    });
    
    
    app_router.on('route:showAskme', function(){
//    	$(".mainpanel").html("Loading...");
    //	event.preventDefault();
//		this.feedsView.render();
    	var askUsView= new AskUsView();
    	askUsView.render();

    });

    
    app_router.on('route:showKnowledge', function(){
//    	$(".mainpanel").html("Loading...");
    	//event.preventDefault();
//		this.feedsView.render();
    	var knowledgeView= new KnowledgeView();
    	knowledgeView.render();

    });
    app_router.on('route:showInternational', function(){
//    	$(".mainpanel").html("Loading...");
    	//event.preventDefault();
//		this.feedsView.render();
    	var internationalView= new InternationalView();
    	internationalView.render();

    });

    app_router.on('route:showiContactUs', function(){
    	//event.preventDefault();
    	var internationalContactView= new InternationalContactView();
    	internationalContactView.render();

    });
    app_router.on('route:showTestimonials', function(){
    	//event.preventDefault();
    	var testimonialsView= new TestimonialsView();
    	testimonialsView.render();

    });

    app_router.on('route:showClinical', function(){
    	var clinicalView= new ClinicalView();
    	clinicalView.render();

    });
    app_router.on('route:showDonate', function(){
    	var donateView= new DonateView();
    	donateView.render();

    });

    
    app_router.on('route:setSession', function(session){
    	var setSessionView= new SetSessionView();
    	setSessionView.render(session);

    });

    app_router.on('route:generateToken', function(sessionid,on,email){
    	var generateTokenView= new GenerateTokenView();
    	generateTokenView.render(sessionid,on,email);

    });

    app_router.on('route:openSession', function(session){
    	var openSessionView= new OpenSessionView();
    	openSessionView.render(session);

    });

    
    app_router.on('route:decideAction', function(){
    	var welcomeView = new WelcomeView();
    	welcomeView.render();
    });
    Backbone.View.prototype.fadeIn = function(template, wrapper) {
        wrapper.is(':hidden') ? 
        wrapper.html(template).show(300) : 
        wrapper.hide(300, function(){ wrapper.html(template).show(300) });
    };
    Backbone.View.prototype.goTo = function (loc) {
    	app_router.navigate();
    	app_router.navigate(loc, true);
      };
      Backbone.View.prototype.getRouter= app_router;
      $.fn.onAvailable = function(fn){
    	    var sel = this.selector;
    	    var timer;
    	    var self = this;
    	    if (this.length > 0) {
    	        fn.call(this);   
    	    }
    	    else {
    	        timer = setInterval(function(){
    	            if ($(sel).length > 0) {
    	                fn.call($(sel));
    	                clearInterval(timer);  
    	            }
    	        },50);  
    	    }
    	};
     Backbone.history.start();
  };
  return { 
    initialize: initialize
  };
});
