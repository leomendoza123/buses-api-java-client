Este es un cliente en Java para el API
https://github.com/LabExperimental-SIUA/buses-api


# Uso de código
--------

###Importando 
    import org.labexp.traces; 

###Se inicia un trazado 

    String DEVICE_ID = "a194cd833e33fa";
    String API_SERVER = "http://10.173.1.153";
    
    Trace trace = new Trace(DEVICE_ID);
    trace.start(API_SERVER); 

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
    trace.finished();
    // o si se quiere eliminar: 
    trace.discarded ();


#Diagramas
--------

###Clases
![](/Diseño/Diagrama de clases SDK.png)

###Secuencia  
![](/Diseño/Diagrama de secuencia.png)



