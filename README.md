Este es un cliente en Java para el API
https://github.com/LabExperimental-SIUA/buses-api


# Uso de código
--------

###Importando 
    import org.labexp.traces; 

###Se inicia un trazado 

    String deviceId = "a194cd833e33fa"; 
    Trace trace = new Trace(deviceId ); 
    trace.start(); 

###Agregando puntos de trazado 

    // Agregar un punto como parte de la ruta

    trace.addPoint (-542342, 123123); 

    // Agregar una parada

    trace.addStop(222.512, -123.3123 ); 

###Definiendo metadatos

    // Se crean metadatos 

    trace.setMetadata ("código", "nombre", "costo"); 

###Finalizando traza 
  
  	// si se quiere guardar 
    trace.finish();
    // o si se quiere eliminar: 
    trace.discard ();


#Diagramas
--------

###Clases
![](/Diseño/Diagrama de clases SDK.png)

###Secuencia  
![](/Diseño/Diagrama de secuencia.png)



