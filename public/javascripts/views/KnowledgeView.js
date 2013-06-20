define([
  'jquery',
  'underscore',
  'backbone',
  'text!templates/knowledgeTemplate.html'
], function($, _, Backbone,knowledgeTemplate){
  var VideosView = Backbone.View.extend({
    el: $(".toppart"),
   // events: {'click #showdocuments' : 'showDocuments'} ,
    render: function(){
    	//this.$el.html(welcomeTemplate);
    	this.fadeIn(knowledgeTemplate, this.$el);
		 $.ajax({
		        type: "GET",
		        url: "/knowledgecenter",
		        success: function(data){
		        	var documentNames="<div class=\"knowledgediv\"><h1>Featured</h1></div><table class=\"knowledgetable\">";
		        	var row=0;
		        	var totalrows=1;
		        	for(var i=0;i< data.length;i++){
		        		if(row ==0){
		        			documentNames = documentNames+"<tr>";
		        		}	
		        		documentNames = documentNames+'<td width="15%">'+'<a href="#"  onClick="loadDocument('+data[i].documentId+',\''+data[i].accessKey+'\')">'+'<img src="'+data[i].thumbNailURI+'"></a></td><td width="35%"><div><h2><a href="#"  onClick="loadDocument('+data[i].documentId+',\''+data[i].accessKey+'\')">'+data[i].title+'</a></h2><div>'+data[i].description+'</div></div></td>';
		        		row=row+1;
		        		if(row == 2){
		        			documentNames = documentNames+"</tr>";
		        			row=0;
		        			totalrows=totalrows+1;
		        		}
		        		
		        	}
		        	documentNames = documentNames+'</table>';		        	
//		        	totalrows=totalrows*180;
//		        	$('.toppart').animate({height:totalrows+'px'}, 500);
		        	$('#nav-menu').html(documentNames);

		        }
		    });

    }
  });

  return VideosView;
});


function loadDocument(docId,accessKey){
	$('#nav-menu').hide();
	$('#showdocuments').show();
	$('#showdocuments').click(function(){
		window.location.hash="knowledge";
	});
	$('#documentdisplay').show();
	 var scribd_doc = scribd.Document.getDoc(docId, accessKey);
	 scribd_doc.write('documentdisplay');
} 