$(document).ready(function(){
	$.ajax({url:"http://localhost:8080/dashboard/api/metrica/aplicacion"}).done(function(data){
		data = JSON.parse(data);
		  var goodDeploy = new Array();   
		  var badDeploy = new Array();
		
		  for (i = 0; i < data.deploys.length; i++){
			  goodDeploy.push(i+1, data.deploys[i]);
          }

		  for (i = 0; i < data.falloDeploys.length; i++){
			  badDeploy.push(i+1, data.falloDeploys[i]);
          }
		  
		  
		$('#container').highcharts({

	        title: {
	            text: 'Reporte'
	        },

	        subtitle: {
	            text: 'Deploys por mes'
	        },
            xAxis: {
                categories: [
                    'Jan',
                    'Feb',
                    'Mar',
                    'Apr',
                    'May',
                    'Jun',
                    'Jul',
                    'Aug',
                    'Sep',
                    'Oct',
                    'Nov',
                    'Dec'
                ]
            },
            yAxis: {
                title: {
                    text: "Cantidad"
                }
            },
	       

	        plotOptions: {
	            series: {
	                marker: {
	                    lineWidth: 1
	                }
	            }
	        },

	        series: [{
	            name: 'Deploys',
	            lineWidth: 1,
	            data : goodDeploy
	        }, {
	        	 name: 'Fallos',
	        	 lineWidth: 1,
	            color: '#c4392d',
	            data : badDeploy
	        }]
	    });
	});	
})

