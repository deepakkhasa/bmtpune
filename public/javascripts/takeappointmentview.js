var takeappointmentView = Backbone.View.extend({
	
	 events: { } ,

	 initialize: function() {
	    Backbone.Validation.bind(this,{forceUpdate: true});
		 $.ajax({
		        type: "GET",
		        url: "/getDoctors",
		        success: function(data){
//		        	for(var i in data){
//		        		alert(data.length + " "+ i);
//		        	}
		        	if(data.length == 1){
		        		$("#heading").text("For booking appointment with " + data[0].name + " please click on the date when you want it.");
		        		getCalendarEvents(data[0].id);
		        	}
		        }
		    });

	 }
	 });
var takeappointmentview = new takeappointmentView();

function getCalendarEvents(doctorId){
	 $.ajax({
	        type: "GET",
	        url: "/getDoctorAppointments?doctorId=" + doctorId,
	        success: function(data){
	        	showCalendar(data,doctorId);
	        }
	    });

}
function showDoctorPopUp(doctorList){
    $("#modal_window_doc").lightbox_me({centered: true, onLoad: function() {
		$("#modal_window_doc").find("select:first").focus();
		}, onClose: function(){
			alert("You will now be taken to calendar for "+ $("#doctorname").text() +" Speciality: "+ $("#speciality").val());
	
		}
    });
    
}

function showCalendar(data,doctorId){
		var date = new Date();
 		var d = date.getDate();
 		var m = date.getMonth();
 		var y = date.getFullYear();
 		$("#doctorId").val(doctorId);
		var event ="";
		for(var i in data){
			var appment = data[i];
			event += "{title: " + appment.appointmentHeadline + "," + "start: "+ appment.dateOfAppointment + "},";
		}

 		$('#calendar').fullCalendar({
 			theme: true,
 			header: {
 				left: 'prev,next today',
 				center: 'title',
 				right: 'month,agendaWeek,agendaDay'
 			},
 			editable: true,
 			events: [event]
 		});

}


