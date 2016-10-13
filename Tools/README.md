# External tools for metric measurement and Code Style Analysis

## Table of Contents
1. [CheckStyle](#Checkstyle)
2. [JAutoDocs](#jautodocs)
3. [Java Metrics2](#java-metrics2)

### CheckStyle

Herramienta utilizada para revisar el estandar de Google de manera automatica en Eclipse.

Para instalarlo se debe utilizar el siguiente link: https://marketplace.eclipse.org/content/checkstyle-plug

1. Se debe arrastrar el boton de instalar hacia el ambiente de eclipse, se suelta en la barra de herrmientas.

	![Alt text](imgs/CheckStyle1.png?raw=true "CheckStyle1")

2. Se presiona Confirmar
	
	![Alt text](imgs/CheckStyle2.png?raw=true "CheckStyle2")

3. Se aceptan las condiciones de uso y se presiona Finalizar

	![Alt text](imgs/CheckStyle3.png?raw=true "CheckStyle3")

4. Se presiona Ok, no hay problema de peligros de seguridad

	![Alt text](imgs/CheckStyle4.png?raw=true "CheckStyle4")

5. Se debe reiniciar Eclipse

	![Alt text](imgs/CheckStyle5.png?raw=true "CheckStyle5")

6. Se accede en Window -> Preferences

	![Alt text](imgs/CheckStyle6.png?raw=true "CheckStyle6")

7. Se revisa qu este or default el estandar de Google

	![Alt text](imgs/CheckStyle7.png?raw=true "CheckStyle7")

8. Se le da click derecho al proyecto -> CheckStyle -> Check Code with CheckStyle

	![Alt text](imgs/CheckStyle8.png?raw=true "CheckStyle8")

9. Apareceran las siguientes anotaciones en caso de que el codigo no este segun el estandar, se puede modificar Tabs para evaluarlo.

	![Alt text](imgs/CheckStyle9.png?raw=true "CheckStyle9")

### JAutoDocs

Herramienta utilizada para generar lo javadocs de manera automatica.

Para instalarlo se debe utilizar el siguiente link: https://marketplace.eclipse.org/content/jautodoc

El siguiente proceso es para instalar y un ejemplo para generar el javadoc de una clase, sin embargo ya se genero el Javadoc de las clases por lo tanto no es necesario de instalar y volver a generar los Javadocs.

1. Se debe arrastrar el boton de instalar hacia el ambiente de eclipse, se suelta en la barra de herrmientas.

	![Alt text](imgs/JAutoDocs1.png?raw=true "JAutoDocs1")

2. Se presiona Confirmar
	
	![Alt text](imgs/JAutoDocs2.png?raw=true "JAutoDocs2")

3. Se aceptan las condiciones de uso y se presiona Finalizar

	![Alt text](imgs/JAutoDocs3.png?raw=true "JAutoDocs3")

4. Se presiona Ok, no hay problema de peligros de seguridad

	![Alt text](imgs/JAutoDocs4.png?raw=true "JAutoDocs4")

5. Se debe reiniciar Eclipse

	![Alt text](imgs/JAutoDocs5.png?raw=true "JAutoDocs5")

6. En un codigo.java, se le da click derecho en algun espacio en blanco, se selecciona JAutoDoc y la segunda opcion de Add Javadoc

	![Alt text](imgs/JAutoDocs6.png?raw=true "JAutoDocs6")

7. En esta pagina se puede customizar el javadoc, se recomienda elegir el modo segun se requiera y marcar que se agregue el Header, el cual tambien se puede modificar con el Edit. 

	![Alt text](imgs/JAutoDocs7.png?raw=true "JAutoDocs7")

8. En el explorador del proyecto, se busca la clase, se l da click derecho, en la opcion de JAutodoc se selecciona Add Javadoc. De esta manera se genera todo el Javadoc para la clase

	![Alt text](imgs/JAutoDocs8.png?raw=true "JAutoDocs8")

### Java Metrics2

Herramienta utilizada para generar el indice ciclomatico de todas las clases y los paquetes de manera automatica

Esta es la pagina principal, que muestra todas las caracteristicas de la herramienta: http://metrics2.sourceforge.net/

El siguiente proceso es para instalar y visulizar las metricas, sin embargo ya se hizo el analisis por lo tanto no es un proceso necesario. 

1. En el ambiente de Eclipse, se selecciona Help -> Install New Software.

	![Alt text](imgs/JavaMetrics1.png?raw=true "JavaMetrics1")

2. En la siguiente pantalla se seecciona Add...
	
	![Alt text](imgs/JavaMetrics2.png?raw=true "JavaMetrics2")

3. Se debe agregar el nombre: Metrics y en Location: http://metrics2.sourceforge.net/update/  despues presionar ok.

	![Alt text](imgs/JavaMetrics3.png?raw=true "JavaMetrics3")

4. Se debe seleccionar el check del plugin y el boton Next

	![Alt text](imgs/JavaMetrics4.png?raw=true "JavaMetrics4")

5. Despues se selecciona Finish

	![Alt text](imgs/JavaMetrics5.png?raw=true "JavaMetrics5")

6. No se debe preocupar por el warning, se selecciona ok

	![Alt text](imgs/JavaMetrics6.png?raw=true "JavaMetrics6")

7. Se debe reiniciar Eclipse para ver los cambios y usar la nueva herramienta 

	![Alt text](imgs/JavaMetrics7.png?raw=true "JavaMetrics7")

8. Para la visualizacion se selecciona Window -> Show View -> Other...

	![Alt text](imgs/JavaMetrics8.png?raw=true "JavaMetrics8")

9. Se selecciona Metrics View y se presiona el boton OK

	![Alt text](imgs/JavaMetrics9.png?raw=true "JavaMetrics9")

10. Despues de volver a compilar o hacerle un "Clean Build" al projecto, se deben observar los nuevos valores en el tab nuevo en view

	![Alt text](imgs/JavaMetrics10.png?raw=true "JavaMetrics10")

