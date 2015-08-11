var reporte = function(url,contenedor){
	$.ajax({url:url}).done(function(data){
		datosReporte = JSON.parse(data);
	
		  
		  
		$('#'+contenedor).highcharts({

	        title: {
	            text: datosReporte.titulo
	        },

	        subtitle: {
	            text: datosReporte.subtitulo
	        },
            xAxis: datosReporte.xAxis ,
            yAxis: datosReporte.yAxis,
	       

	        plotOptions: {
	            series: {
	                marker: {
	                    lineWidth: 1
	                }
	            }
	        },

	        series: datosReporte.series
	    });
	});	
}
