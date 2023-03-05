// Nota: Para llamar las variables o botones desde el html usamos el numeral antes de la variable.
function ReadClient()
{
    //Llamado AJAX
    $.ajax
    (
        {
            //Url de la base de datos.
            url : 'http://localhost:8080/api/Client/all',
            //Tipo de peticion que vamos a usar en este caso GET.
            type : 'GET',
            //Tipo de dato que acepta el ajax, en este caso JSON.
            dataType : 'json',
            //Que va a hacer si los datos que recibe son correctos
            success : function(Client)
            {
                //Creacion de los items de la instancia clientes
                let cs=Client;
                //Instruccion para vaciar los campos de la lista de clientes
                $("#ListaClientes").empty();
                //Ciclo para recorrer todas las instancias de la base de datos.
                for( i=0;i<cs.length;i++)
                {
                    //Instruccion para agregar en la pagina web la informacion del div en este caso
                    //El ID, el nombre y el email.
                    $("#ListaClientes").append("<b>"+cs[i].name+"</b> "+"<b>"+cs[i].email+"</b> "+
                        "<b>" + cs[i].age + "</b> ");

                    //Instruccion para crear los botones de borrar justo al lado de la informacion del cliente.
                    $("#ListaClientes").append("<button onclick='DeleteClient("+cs[i].idClient+")'>Borrar Cliente</button> <br>");
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
                alert('Se muestran los Clientes');
            }
        }
    );
}

function SaveClient()
{
    // Creacion de las variables que se van a recibir desde el html.
    let NombreCliente =$("#name").val();
    let EmailCliente =$("#email").val();
    let PassCliente = $("#password").val();
    let EdadCliente = parseInt($("#age").val());

    // Creacion de la lista de datos para guardarla en la base de datos como propiedades de la instancia Nube.
    let DataClient =
        {
            name:NombreCliente,
            email:EmailCliente,
            password: PassCliente,
            age:EdadCliente,
        };
    // Conversion de datos Json a String.
    let DatosClient=JSON.stringify(DataClient)

    console.log(DataClient);
    console.log(DatosClient);
    //LLamado AJAX para la peticion POST.
    $.ajax
    (
        {
            url : 'http://localhost:8080/api/Client/save',
            type : 'POST',
            data:DatosClient,
            // Tipo de Contenido que va a recibir el backend
            contentType:'application/json',
            success : function(Client)
            {
                // Instruccion para vaciar los campos de texto en el html.
                $("#IdClient").val("");
                $("#name").val("");
                $("#email").val("");
                $("#password").val("");
                $("#age").val("");
            },
            error : function()
            {
                alert('ha sucedido un problema');
            },
            complete : function()
            {
                // LLamado de la funcion Leer Clientes para que se actualicen los datos de manera automatica.
                ReadClient();
                alert('Se guardo el cliente');
            }
        }
    );
}

function EditClient()
{
    // Creacion de las variables que se van a recibir desde el html.
    let idCliente = parseInt($("#IdClient").val());
    let NombreCliente =$("#name").val();
    let EmailCliente =$("#email").val();
    let PassCliente = $("#password").val();
    let EdadCliente = parseInt($("#age").val());

    // Creacion de la lista de datos para guardarla en la base de datos como propiedades de la instancia Nube.
    let DataClient =
        {
            idClient:idCliente,
            name:NombreCliente,
            password: PassCliente,
            age:EdadCliente,
        };
    // Conversion de datos Json a String.
    let DatosClient=JSON.stringify(DataClient)

    console.log(DataClient);
    console.log(DatosClient);
    //LLamado AJAX para la peticion POST.
    $.ajax
    (
        {
            url : 'http://localhost:8080/api/Client/update',
            type : 'PUT',
            data:DatosClient,
            // Tipo de Contenido que va a recibir el backend
            contentType:'application/json',
            success : function(Client)
            {
                // Instruccion para vaciar los campos de texto en el html.
                $("#IdClient").val("");
                $("#name").val("");
                $("#email").val("");
                $("#password").val("");
                $("#age").val("");
            },
            error : function()
            {
                alert('ha sucedido un problema');
            },
            complete : function()
            {
                // LLamado de la funcion Leer Clientes para que se actualicen los datos de manera automatica.
                ReadClient();
                alert('Se actualizo el cliente');
            }
        }
    );
}
function DeleteClient(idClient)
{
    // LLamado Ajax del Delete.
    $.ajax
    (
        {
            url : 'http://localhost:8080/api/Client/'+ idClient,
            type : 'DELETE',
            contentType:'application/json',
            success : function(Client)
            {
                $("#IdClient").val("");
                $("#name").val("");
                $("#email").val("");
                $("#password").val("");
                $("#age").val("");
            },
            error : function()
            {
                alert('ha sucedido un problema');
            },
            complete : function()
            {
                // LLamado de la funcion Leer Clientes para que se actualicen los datos de manera automatica.
                ReadClient();
                alert('Se elimino el cliente');
            }
        }
    );
}
