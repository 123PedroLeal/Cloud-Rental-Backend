// Nota: Para llamar las variables o botones desde el html usamos el numeral antes de la variable.
function ReadAdmin()
{
    //Llamado AJAX
    $.ajax
    (
        {
            //Url de la base de datos.
            url : 'http://localhost:8080/api/Admin/all',
            //Tipo de peticion que vamos a usar en este caso GET.
            type : 'GET',
            //Tipo de dato que acepta el ajax, en este caso JSON.
            dataType : 'json',
            //Que va a hacer si los datos que recibe son correctos
            success : function(Admin)
            {
                //Creacion de los items de la instancia clientes
                let as=Admin;
                //Instruccion para vaciar los campos de la lista de clientes
                $("#ListaAdmins").empty();
                //Ciclo para recorrer todas las instancias de la base de datos.
                for( i=0;i<as.length;i++)
                {
                    //Instruccion para agregar en la pagina web la informacion del div en este caso
                    //El ID, el nombre y el email.
                    $("#ListaAdmins").append("<b>"+as[i].email+"</b> "+"<b>" + as[i].name +"</b> ");

                    //Instruccion para crear los botones de borrar justo al lado de la informacion del cliente.
                    $("#ListaAdmins").append("<button onclick='DeleteAdmin("+as[i].idAdmin+")'>Borrar Admin</button> <br>");
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
                alert('Se muestran los Administradores');
            }
        }
    );
}

function SaveAdmin()
{
    // Creacion de las variables que se van a recibir desde el html.
    let IdAdmin =parseInt($("#IdAdmin").val());
    let Correo =$("#email").val();
    let Contrasena = $("#password").val();
    let Nombre =$("#name").val();

    // Creacion de la lista de datos para guardarla en la base de datos como propiedades de la instancia Nube.
    let DataAdmin =
        {
            email:Correo,
            password:Contrasena,
            name:Nombre
        };
    // Conversion de datos Json a String.
    let DatosAdmin=JSON.stringify(DataAdmin)

    console.log(DataAdmin);
    console.log(DatosAdmin);
    console.log($("#ListaCalificacion"));
    //LLamado AJAX para la peticion POST.
    $.ajax
    (
        {
            url : 'http://localhost:8080/api/Admin/save',
            type : 'POST',
            data:DatosAdmin,
            // Tipo de Contenido que va a recibir el backend
            contentType:'application/json',
            success : function(Admin)
            {
                // Instruccion para vaciar los campos de texto en el html.
                $("#IdAdmin").val("");
                $("#email").val("");
                $("#password").val("");
                $("#name").val("");
            },
            error : function()
            {
                alert('ha sucedido un problema');
            },
            complete : function()
            {
                // LLamado de la funcion Leer Clientes para que se actualicen los datos de manera automatica.
                ReadAdmin();
                alert('Se guardo el admin');
            }
        }
    );
}

function EditAdmin()
{
    // Creacion de las variables que se van a recibir desde el html.
    let IdAdmin = parseInt($("#IdAdmin").val());
    let Correo =$("#email").val();
    let Contrasena = $("#password").val();
    let Nombre =$("#name").val();

    // Creacion de la lista de datos para guardarla en la base de datos como propiedades de la instancia Nube.
    let DataAdmin =
        {
            idAdmin:IdAdmin,
            email:Correo,
            password:Contrasena,
            name:Nombre
        };
    // Conversion de datos Json a String.
    let DatosAdmin=JSON.stringify(DataAdmin)

    console.log(DataAdmin);
    console.log(DatosAdmin);
    console.log($("#ListaCalificacion"));
    //LLamado AJAX para la peticion POST.
    $.ajax
    (
        {
            url : 'http://localhost:8080/api/Admin/update',
            type : 'PUT',
            data:DatosAdmin,
            // Tipo de Contenido que va a recibir el backend
            contentType:'application/json',
            success : function(Admin)
            {
                // Instruccion para vaciar los campos de texto en el html.
                $("#IdAdmin").val("");
                $("#email").val("");
                $("#password").val("");
                $("#name").val("");
            },
            error : function()
            {
                alert('ha sucedido un problema');
            },
            complete : function()
            {
                // LLamado de la funcion Leer Clientes para que se actualicen los datos de manera automatica.
                ReadAdmin();
                alert('Se actualizo el admin');
            }
        }
    );
}
function DeleteAdmin(idAdmin)
{
    // LLamado Ajax del Delete.
    $.ajax
    (
        {
            url : 'http://localhost:8080/api/Admin/'+ idAdmin,
            type : 'DELETE',
            contentType:'application/json',
            success : function(Admin)
            {
                $("#idAdmin").val("");
                $("#email").val("");
                $("#password").val("");
                $("#name").val("");
            },
            error : function()
            {
                alert('ha sucedido un problema');
            },
            complete : function()
            {
                // LLamado de la funcion Leer Clientes para que se actualicen los datos de manera automatica.
                ReadAdmin();
                alert('Se elimino el admin');
            }
        }
    );
}
