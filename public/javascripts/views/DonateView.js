define([
  'jquery',
  'underscore',
  'backbone',
  'lightboxme',
  'text!templates/donateTemplate.html',
  'msgbox',
  'verimail'
], function($,_, Backbone,lightbox,donateTemplate,MsgBox,VeriMail){
  var DonateView = Backbone.View.extend({
    el: $(".modal"),
    events: {'click #donatesubmitForm' : 'submitRequest1',
        "change #donate_modal_form :input" :"changedForm1"} ,
    render: function(){
        var Donate = Backbone.Model.extend({urlRoot:'/donateForm'});
        this.model = new Donate();
    	$(".modal").html(donateTemplate);
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
    submitRequest1: function(event){
    	var error =false;
    	$("#name-message").text("");
    	$("#amount-message").text("");
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
          	this.model.save();
        	$(".msg").html("Email has been sent to Dr. Vijay Ramanan. You will hear back soon. Thanks!");
      	}
      	 
      },
      changedForm1: function(evt){
          var changed = evt.currentTarget;
          var value = $("#"+changed.id).val();
          var obj = "{\""+changed.id +"\":\""+value+"\"}";
          var objInst = JSON.parse(obj);
          this.model.set(objInst);
      }
  });

  return DonateView;
});
