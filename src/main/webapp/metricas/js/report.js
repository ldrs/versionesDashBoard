var dibujaReporte = function(url,contenedor,titulo,subtitulo){
	$.ajax({url:url}).done(function(data){
		datosReporte = JSON.parse(data);



		$('#'+contenedor).highcharts({

	        title: {
	            text: titulo
	        },

	        subtitle: {
	            text: subtitulo
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

	        series: datosReporte.series,
	          credits: {
		      enabled: false
  			}
	    });
	});
}