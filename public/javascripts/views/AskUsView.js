define([
  'jquery',
  'underscore',
  'backbone',
  'lightboxme',
  'text!templates/askTemplate.html',
  'msgbox',
  'verimail'
], function($,_, Backbone,lightbox,askTemplate,MsgBox,VeriMail){
  var AskUsView = Backbone.View.extend({
    el: $(".modal"),
    events: {'click #submitForm' : 'submitRequest',
        "change #modal_form :input" :"changedForm"} ,
    render: function(){
        Ask = Backbone.Model.extend({urlRoot:'/askForm'});
        this.model = new Ask();
    	$(".modal").html(askTemplate);
        $(".modal").lightbox_me({centered: true, onLoad: function() {
			$(".modal_window").find("input:first").focus();
			}, onClose: function(){
				
			}
        });
		$("input#emailid").verimail({
			denyTempEmailDomains: true,
			messageElement: "p#email-message"
		});

    },
    submitRequest: function(event){
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
      	if( (!this.model.get("query") || this.model.get("query")=="")){
    		$("#query-message").text("Please provide us the query.")
    		error=true;
    	}
      	if(!error){
          	this.model.save();
        	$(".msg").html("Email has been sent to Dr. Vijay Ramanan. You will hear back soon. Thanks!");
      	}
      	 
      },
      changedForm: function(evt){
          var changed = evt.currentTarget;
          var value = $("#"+changed.id).val();
          var obj = "{\""+changed.id +"\":\""+value+"\"}";
          var objInst = JSON.parse(obj);
          this.model.set(objInst);
      }
  });

  return AskUsView;
});
