var dashboardView = Backbone.View.extend({
	
	 events: { 'click #uploadbtn' : 'uploadDialog' } ,

	 initialize: function() {
	     Backbone.Validation.bind(this,{forceUpdate: true});
	     		var date = new Date();
	     		var d = date.getDate();
	     		var m = date.getMonth();
	     		var y = date.getFullYear();
	     		
	     		$('#calendar').fullCalendar({
	     			theme: true,
	     			header: {
	     				left: 'prev,next today',
	     				center: 'title',
	     				right: 'month,agendaWeek,agendaDay'
	     			},
	     			editable: true,
	     			events: [
	     				{
	     					title: 'All Day Event',
	     					start: new Date(y, m, 1)
	     				}
	     			]
		});
	 },

	uploadDialog: function(data) {
		 $("#overlayEffect").fadeIn("slow");
		 $("#popupdetails").fadeIn("slow");

  	}
});
var dashboardview = new dashboardView();



