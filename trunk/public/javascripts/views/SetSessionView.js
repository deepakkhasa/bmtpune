define([
  'jquery',
  'jqueryui',
  'underscore',
  'backbone',
  'text!templates/setSessionTemplate.html',
  'msgbox',
  'timepicker'
], function($,jqueryUI,_, Backbone,setSessionTemplate,MsgBox,Timepicker){
  var SetSessionView = Backbone.View.extend({
    el: $(".toppart"),
    events: {'click .ui-datepicker-close' : 'selectDate'/*,
        "change #donate_modal_form :input" :"changedForm2"*/} ,
    render: function(session){
    	this.session = session;//.substring(1,session.length-1);
    	this.fadeIn(setSessionTemplate, this.$el);
		 $('#alt_example_4').onAvailable(function(){
			 $('#alt_example_4').datetimepicker({
					altField: "#alt_example_4_alt",
					altFieldTimeOnly: false,
					numberOfMonths: 2,
					minDate: new Date()
				});

		 });
    },
    selectDate: function(){
		 $.ajax({
		        type: "POST",
		        url: "/createSession?datetime="+$('#alt_example_4').val()+"&id="+this.session,
		        success: function(data){
		        	alert("Session created!!! Email has been sent out!");
/*		      		 $.msgBox({
						    title:"Appment",
						    content:"Successfully Saved!",
						    type:"info"
						});
*/		        }
	    });
    }
  });

  return SetSessionView;
});
