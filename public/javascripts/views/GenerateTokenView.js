define([
  'jquery',
  'jqueryui',
  'underscore',
  'backbone',
  'text!templates/generateTokenTemplate.html',
  'msgbox',
  'timepicker'
], function($,jqueryUI,_, Backbone,generateTokenTemplate,MsgBox,Timepicker){
  var GenerateTokenView = Backbone.View.extend({
    el: $(".toppart"),
    events: {'click #generateToken' : 'generateToken'/*,
        "change #donate_modal_form :input" :"changedForm2"*/} ,
    render: function(sessionid, on,email){
    	this.sessionid = sessionid;
    	this.sessiondate = on;
    	this.email = email;
    	this.fadeIn(generateTokenTemplate, this.$el);
    },
    generateToken: function(){
		 $.ajax({
		        type: "GET",
		        url: "/generateToken?sessionid="+this.sessionid+"&email="+this.email+"&day="+this.sessiondate,
		        success: function(data){
		        	console.log(data);
		      		 $.msgBox({
						    title:"Dr. Vijay",
						    content:"Token generated and email sent!",
						    type:"info"
						});
		        }
	    });
    }
  });

  return GenerateTokenView;
});
