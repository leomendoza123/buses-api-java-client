# Uso de código
--------

###Importando 
    Import org.labexp.traces 

###Agregando puntos de trazado 
    //Se inicia un trazado 
    Int deviceId = 1; 
    Trace nuevaTraza = new Trace(deviceId ); 
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

    sdk.Metadata nuevosMetadatos = new sdk.Metadata("código", "nombre", "costo") 
    nuevaTraza.setMetadata (nuevosMetadatos ); 

###Finalizando traza 
  
    nuevaTraza.finish()
    //o si se quiere eliminar: 
    nuevaTraza.discard ()


#Diagramas
--------

###Clases
![](/Diseño/classDiagram.png)

###Secuencia  
![](/Diseño/sequenceDiagram.png)



