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
  'views/AskUsView'
], function($, _, Backbone, WelcomeView,AboutUsView, ContactUsView,VideosView,KnowledgeView,AskUsView) {
  
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
      // Default
      '*default': 'decideAction'
    }
  });
  
  var initialize = function(){

    var app_router = new AppRouter;
    
    app_router.on('route:showAboutus', function(){
//    	$(".mainpanel").html("Loading...");
    	event.preventDefault();
//		this.feedsView.render();
    	var aboutusView= new AboutUsView();
    	aboutusView.render();

    });
    app_router.on('route:showContactus', function(){
//    	$(".mainpanel").html("Loading...");
    	event.preventDefault();
//		this.feedsView.render();
    	var contactusView= new ContactUsView();
    	contactusView.render();

    });

    app_router.on('route:showVideos', function(){
//    	$(".mainpanel").html("Loading...");
    	event.preventDefault();
//		this.feedsView.render();
    	var videosView= new VideosView();
    	videosView.render();

    });
    
    
    app_router.on('route:showAskme', function(){
//    	$(".mainpanel").html("Loading...");
    	event.preventDefault();
//		this.feedsView.render();
    	var askUsView= new AskUsView();
    	askUsView.render();

    });

    
    app_router.on('route:showKnowledge', function(){
//    	$(".mainpanel").html("Loading...");
    	event.preventDefault();
//		this.feedsView.render();
    	var knowledgeView= new KnowledgeView();
    	knowledgeView.render();

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

     Backbone.history.start();
  };
  return { 
    initialize: initialize
  };
});
