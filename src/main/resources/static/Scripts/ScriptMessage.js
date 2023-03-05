// Nota: Para llamar las variables o botones desde el html usamos el numeral antes de la variable.
function ReadMessage()
{
    //Llamado AJAX
    $.ajax
    (
        {
            //Url de la base de datos.
            url : 'http://localhost:8080/api/Message/all',
            //Tipo de peticion que vamos a usar en este caso GET.
            type : 'GET',
            //Tipo de dato que acepta el ajax, en este caso JSON.
            dataType : 'json',
            //Que va a hacer si los datos que recibe son correctos
            success : function(Messages)
            {
                //Creacion de los items de la instancia clientes
                let ms=Messages;
                //Instruccion para vaciar los campos de la lista de clientes
                $("#ListaMensajes").empty();
                //Ciclo para recorrer todas las instancias de la base de datos.
                for( i=0;i<ms.length;i++)
                {
                    //Instruccion para agregar en la pagina web la informacion del div en este caso
                    //El ID, el nombre y el email.
                    $("#ListaMensajes").append("<b>"+ms[i].messageText+"</b> ");

                    //Instruccion para crear los botones de borrar justo al lado de la informacion del cliente.
                    $("#ListaMensajes").append("<button onclick='DeleteMessage("+ms[i].idMessage+")'>Borrar Mensaje</button> <br>");
                }
            },
            // En caso de fallar con la recepcion de los datos emite esta alerta.
            error : function()
            {
                alert('ha sucedido un problema');
            },
            // En caso de completar las operaciones coloca el siguiente mensaje.
            complete : function()
            {
                alert('Se muestran los Mensajes');
            }
        }
    );
}

function SaveMessage()
{
    // Creacion de las variables que se van a recibir desde el html.
    let TxtMensaje =$("#TextMessage").val();
    let Cliente = parseInt($("#IdClientM").val());
    let Nube = parseInt($("#IdCloudM").val());

    // Creacion de la lista de datos para guardarla en la base de datos como propiedades de la instancia Nube.
    let DataMessage =
        {
            messageText:TxtMensaje,
            client:{idClient:Cliente},
            cloud: {id:Nube},
        };
    // Conversion de datos Json a String.
    let DatosMessage=JSON.stringify(DataMessage)

    console.log(DataMessage);
    console.log(DatosMessage);
    //LLamado AJAX para la peticion POST.
    $.ajax
    (
        {
            url : 'http://localhost:8080/api/Message/save',
            type : 'POST',
            data:DatosMessage,
            // Tipo de Contenido que va a recibir el backend
            contentType:'application/json',
            success : function(Messages)
            {
                // Instruccion para vaciar los campos de texto en el html.
                $("#IdMessage").val();
                $("#TextMessage").val();
                $("#IdClientM").val();
                $("#IdCloudM").val();
            },
            error : function()
            {
                alert('ha sucedido un problema');
            },
            complete : function()
            {
                // LLamado de la funcion Leer Clientes para que se actualicen los datos de manera automatica.
                ReadMessage();
                alert('Se guardo el mensaje');
            }
        }
    );
}

function EditMessage()
{
    // Creacion de las variables que se van a recibir desde el html.
    let IdMensaje =$("#IdMessage").val();
    let TxtMensaje =$("#TextMessage").val();

    // Creacion de la lista de datos para guardarla en la base de datos como propiedades de la instancia Nube.
    let DataMessage =
        {
            idMessage:IdMensaje,
            messageText:TxtMensaje
        };
    // Conversion de datos Json a String.
    let DatosMessage=JSON.stringify(DataMessage)

    console.log(DataMessage);
    console.log(DatosMessage);
    //LLamado AJAX para la peticion POST.
    $.ajax
    (
        {
            url : 'http://localhost:8080/api/Message/update',
            type : 'PUT',
            data:DatosMessage,
            // Tipo de Contenido que va a recibir el backend
            contentType:'application/json',
            success : function(Messages)
            {
                // Instruccion para vaciar los campos de texto en el html.
                $("#IdMessage").val();
                $("#TextMessage").val();
                $("#IdClientM").val();
                $("#IdCloudM").val();
            },
            error : function()
            {
                alert('ha sucedido un problema');
            },
            complete : function()
            {
                // LLamado de la funcion Leer Clientes para que se actualicen los datos de manera automatica.
                ReadMessage();
                alert('Se actualizo el mensaje');
            }
        }
    );
}
function DeleteMessage(idMessage)
{
    // LLamado Ajax del Delete.
    $.ajax
    (
        {
            url : 'http://localhost:8080/api/Message/'+ idMessage,
            type : 'DELETE',
            contentType:'application/json',
            success : function(Messages)
            {
                $("#IdMessage").val();
                $("#TextMessage").val();
                $("#IdClientM").val();
                $("#IdCloudM").val();
            },
            error : function()
            {
                alert('ha sucedido un problema');
            },
            complete : function()
            {
                // LLamado de la funcion Leer Clientes para que se actualicen los datos de manera automatica.
                ReadMessage();
                alert('Se elimino el mensaje');
            }
        }
    );
}