# Uso de código
--------

###Importando 
    import org.labexp.traces; 

###Agregando puntos de trazado 
    //Se inicia un trazado 
    int deviceId = 1; 
    trace nuevaTraza = new Trace(deviceId ); 
    nuevaTraza.start(); 

    // Se agrega una lista de puntos como parte de la ruta

    list ArrayList<MapPoint> = new ArrayList<MapPoint>(); 
    list.add (new MapPoint(-542342, 123123)); 
    nuevaTraza.addPoint (list); 

###Agregando puntos de paradas

    // Para agregar una parada se agrega un punto

    nuevaTraza.addStop(222.512, -123.3123 ); 

###Agregando de metadatos
    // Se crean metadatos 

    nuevaTraza.setMetadata ("código", "nombre", "costo"); 

###Finalizando traza 
  
    nuevaTraza.finish();
    //o si se quiere eliminar: 
    nuevaTraza.discard ();


#Diagramas
--------

###Clases
![](/Diseño/Diagrama de clases SDK.png)

###Secuencia  
![](/Diseño/Diagrama de secuencia.png)



