define([
  'jquery',
  'jqueryui',
  'underscore',
  'backbone',
  'text!templates/internContactTemplate.html',
  'msgbox',
  'verimail'
], function($, _,jqueryui, Backbone,internContactTemplate,MsgBox,VeriMail){
  var InternationalContactView = Backbone.View.extend({
    el: $(".toppart"),
    events: {"change #contact_us :input" :"changedValues","click #submitContactForm":"setupSession"} ,
    render: function(){
    	this.fadeIn(internContactTemplate, this.$el);
        var Contact = Backbone.Model.extend({urlRoot:'/requestContact'});
        this.model = new Contact();
		 $('.internationContact').onAvailable(function(){
				$("input#emailid").verimail({
					denyTempEmailDomains: true,
					messageElement: "p#email-message"
				});
				$( "#dateOfBirth" ).datepicker();
				  $( "#country" ).autocomplete({
				      source: ["India","United States of America(USA)","United Kingdom(UK)", "South Arabia","Iraq","Kuwait","Pakistan","Srilanka","Nepal"]
				    });
		 });
		 
    },
    changedValues: function(evt){
        var changed = evt.currentTarget;
        var value="";
        if($("#"+changed.id).attr('type') =="checkbox"){
        	value = $("#"+changed.id).is(":checked")?"Y":"N";
        }else{
            value = $("#"+changed.id).val();
        }
        var obj = "{\""+changed.id +"\":\""+value+"\"}";
        var objInst = JSON.parse(obj);
        this.model.set(objInst);
        
    },
    setupSession: function(){
       	var error =false;
    	$("#name-message").text("");
    	$("#query-message").text("");
       	if( (!this.model.get("name") || this.model.get("name")=="")){
    		$("#name-message").text("Please provide us the name.")
    		error=true;
    	}
      	if( !this.model.get("emailid") || this.model.get("emailid")=="" || $("#email-message").text()=="Email is not correctly formatted"){
    		$("#email-message").text("Please provide a valid email ID.")
    		error=true;
    	}else{
    		$("#email-message").text("");
    	}
    	if(!error){
          	this.model.save(null, {
          	    success: function (model, response) {
          	    	 $(".msg").html("Email has been sent to Dr. Vijay Ramanan. You will hear back soon. Thanks!");
          	    }
          	});
        	
      	}
    	
    }
  });

  return InternationalContactView;
});
