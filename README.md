Lista de tareas cumplidas
Inicio de aplicación por primera vez
Al iniciar la aplicación recién instalada, se despliega un cuadro de dialogo en el cual pregunta si desea cargar o no la copia de seguridad almacenada en la nube. Esta copia se genera con el botón backup.  Si no desea cargarla, se solicitará la configuración inicial de la APP que es: Datos de la finca, hierro, propietario. Si desea cargar la copia, se toma de la nube y se carga en el dispositivo.

Información por defecto en los formularios de registro:
Razas: Comercial, Cebú, normanda, Parda.
Género: Macho, Hembra.
Etapa de producción: Si es macho: Ternero, Novillo, Toro. Si es hembra: Ternera, Novilla y vaca.
Propósito: Engorde, Lechería, Cría, doble propósito y triple propósito.

Registro de información:
-	Animales:
o	Al partir.
	Se registra un animal al partir cuando el dueño realiza un negocio con otro propietario. Este negocio consiste en partir las ganancias en un determinado tiempo. La información que se registra en este caso es: Código del animal, nombre del animal, genero, etapa de producción, raza, propietario, hierro, propósito, peso actual [Kg], peso recibido [Kg], fecha de recibido, fecha de nacimiento. Como campo obligatorio está el código del animal.
o	Crías nacidas.
	Se registra un animal en crías cuando nace en la finca. La información que se registra en este caso es: Código del animal, nombre del animal, genero, etapa de producción, raza, propietario, hierro, propósito, Código mama, Código papa, peso al hacer [Kg], peso actual [Kg], fecha de nacimiento.  Como campo obligatorio está el código del animal.

o	Comprados.
	Se registra la información de un animal cuando es comprado. La información que se registra en este caso es: Código del animal, nombre del animal, genero, etapa de producción, raza, vendedor, Nuevo propietario, hierro, propósito, peso al comprar [Kg], peso actual [Kg], valor de compra, fecha de compra y fecha de nacimiento.  Como campo obligatorio está el código del animal.
o	
-	Medicamentos:
o	Registro de medicamento por código de animal, medicamento aplicado, detalle y costo del medicamento. Tiene como campos obligatorios: Código, Medicamento, Detalle, Costo.
-	Ventas de animales:
o	Venta de un animal por el código del mismo. Además se registra el peso, algún detalle de la venta, nombre del comprador y el valor de la venta. Tiene como campos obligatorios: Código, peso, detalle, Comprador, y el valor de la venta.
-	Litros de leche producidos por una vaca.
o	Este registro se realiza por el código del animal. Además se guarda la fecha y la cantidad de litros de leche producida por el animal. Los campos obligatorios son: Un código existente, los litros de leche si se deja en blanco guarda cero litros.

Consulta de información:
-	Resumen de finca: En este campo de la APP se puede observar a detalle las cantidades de animales de los diferentes tipos de ingresos disponibles, como se puede observar en la figura:
 
-	Resumen financiero: En este campo de la APP, el usuario tiene a su disposición un filtro de fechas para poder encontrar información precisa en un tiempo determinado. Esta se observa en la siguiente gráfica.
-	 
En este resumen se puede apreciar el total de compras, ventas, costos en medicamentos y el total de leche producida. Todo lo anterior lo cuenta entre el rango de fechas definidas en toda la base de datos disponible en el teléfono. Nota: Este consulta la realiza entre las fechas, no sobre la fecha.
-	Consulta de animales registrados:
o	Búsqueda por código: Pide el código del animal a investigar y luego despliega la información disponible en la base de datos acerca de él. Además en este campo, permite actualizar diferentes campos o eliminar el animal.
o	Animales al partir registrados: Muestra un listado total.
o	Crías registradas: Muestra un listado total.
o	Animales comprados registrados: Muestra un listado total.
-	Medicamentos aplicados: Permite consultar el listado de medicamentos aplicados a un animal en específico mediante su código. Muestra el código del animal, nombre del medicamento, un detalle del medicamento y el precio del medicamento.
-	Litros de leche registrados: Se verifica que el código ingresado sea una vaca.  Si lo es, despliega una lista de los litros de leche registrados a ese código para esa vaca.
-	Ventas realizadas: Me muestra un listado total de todas las ventas realizadas desde que se instaló la aplicación.
Copia de seguridad: Pendiente, se realizara con la autenticación en la nube.
Reporte: Se genera un archivo en Excel, que puede ser abierto en la computadora para analizarlo o imprimirlo. Este es guardado en la carpeta descargas.
Configuración: En esta parte de la APP, se ingresa información obligatoria en los formularios de registro de información. Esta se puede cargar al inicio de la APP o después de ingresar múltiple registros a cada campo; Es decir, ingresar múltiples propietarios, o hierros.
-	Finca: Se registra información e la finca tal como lo es: Código, nombre, hectáreas, divisiones y los lotes.
-	Propietarios: Se solicita el nombre y apellido de los propietarios a registrar.
-	Hierro: Pide todos los hierros que el usuario desee ingresar.

Autenticación: Este se realizara con internet. Está en proceso.


Nota: Si existe un campo obligatorio, el usuario no quiere completar esa información, puede escribir un espacio o un punto y seguir con el formulario.
