﻿# TALLER ARQUITECTURAS EN AWS


## Descripción

La Aplicación de Registro de Mensajes es una solución web que permite a los usuarios ingresar mensajes de texto a través de una interfaz simple, los cuales son almacenados en una base de datos MongoDB ejecutándose en un contenedor Docker dentro de una máquina virtual EC2 de AWS; un servicio RESTful de balanceo de carga distribuye las solicitudes de registro de mensajes entre múltiples instancias de un servicio RESTful de registro (LogService) utilizando un algoritmo round-robin, mientras que cada instancia de LogService almacena el mensaje en la base de datos MongoDB y genera una respuesta JSON con el historial de los 10 últimos mensajes registrados y sus fechas, respuesta que es devuelta al cliente web a través del balanceador de carga para actualizar la interfaz de usuario, aprovechando así la escalabilidad, alta disponibilidad y rendimiento que brinda esta arquitectura de microservicios.

## Demostración en AWS


## Requisitos

https://github.com/MiguelBarreraD/ARQUITECTURALES_AWS/assets/80360472/9a4d2108-7655-4152-9f8b-07c271662fef


    1. git - Control de versiones
    2. Maven - Gestor de dependencias
    3. Java - Lenguaje de programación
    4. Docker - Motor de contenedores
    5. Docker Compose - Generador de contenedores


## Instalación local

Para poder intalar  el proyecto se requiere tener instalado los requisitos anteriores: 

Clone el repositorio

    $ git clone https://github.com/MiguelBarreraD/ARQUITECTURALES_AWS.git

Entre a la carpeta del proyecto

    $ cd ARQUITECTUAL__AWS

Compilar el código fuente de la aplicación, ejecuta las pruebas unitarias y empaqueta el código compilado en un formato que puede ser ejecutado o desplegado, como un archivo JAR para aplicaciones Java. 

    $ mvn clean install

Generar los contenedores con el archivo docker-compose.yml

    $ docker-compose up -d




## Arquitecura de la aplicación 

Esta arquitectura implementa un servicio de registro de mensajes compuesto por los siguientes componentes:

![](img/FotoArquitectura.png)


**MongoDB Container:** Una instancia de MongoDB ejecutándose en un contenedor Docker dentro de una máquina virtual EC2 de AWS. Esta base de datos NoSQL almacena los mensajes registrados.

**LogService:** Un servicio RESTful que recibe cadenas de texto, las almacena en la base de datos MongoDB y responde con un objeto JSON que contiene las 10 últimas cadenas registradas y sus fechas de registro.

**APP-LB-RoundRobin:**
1. **Weblogroundrobin**: Una aplicación web con un campo de entrada de texto y un botón. Cuando se envía un mensaje, se lo pasa al servicio RESTful.
2. **RRInvoke**: Implementa un balanceador de carga Round Robin que distribuye las solicitudes entrantes entre tres instancias del servicio LogService.


## Autores ✒️
* Miguel Angel Barrera Diaz
