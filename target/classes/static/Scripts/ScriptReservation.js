// Nota: Para llamar las variables o botones desde el html usamos el numeral antes de la variable.
function ReadReservation()
{
    //Llamado AJAX
    $.ajax
    (
        {
            //Url de la base de datos.
            url : 'http://localhost:8080/api/Reservation/all',
            //Tipo de peticion que vamos a usar en este caso GET.
            type : 'GET',
            //Tipo de dato que acepta el ajax, en este caso JSON.
            dataType : 'json',
            //Que va a hacer si los datos que recibe son correctos
            success : function(Reservation)
            {
                console.log(Reservation);
                //Creacion de los items de la instancia clientes
                let rs=Reservation;
                //Instruccion para vaciar los campos de la lista de clientes
                $("#ListaReservas").empty();
                //Ciclo para recorrer todas las instancias de la base de datos.
                for( i=0;i<rs.length;i++)
                {
                    //Instruccion para agregar en la pagina web la informacion del div en este caso
                    //El ID, el nombre y el email.
                    $("#ListaReservas").append("<b>"+rs[i].idReservation+"</b> "+"<b>"+rs[i].startDate+"</b> "+"<b>"+rs[i].devolutionDate+"</b> "
                        +"<b>"+rs[i].client.idClient+"</b> "+"<b>"+rs[i].client.name+"</b> "+"<b>"+rs[i].client.email+"</b>");

                    //Instruccion para crear los botones de borrar justo al lado de la informacion del cliente.
                    $("#ListaReservas").append("<button onclick='DeleteReservation("+rs[i].idReservation+")'>Borrar Reserva</button> <br>");
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
                alert('Se muestran las Reservas');
            }
        }
    );
}

function SaveReservation()
{
    // Creacion de las variables que se van a recibir desde el html.
    let idReserva =$("#IdReserva").val();
    let Inicio =$("#StartDate").val();
    let Devolucion = $("#DevolutionDate").val();
    let Cliente = parseInt($("#IdClientR").val());
    let Nube =parseInt($("#IdCloudR").val());

    // Creacion de la lista de datos para guardarla en la base de datos como propiedades de la instancia Nube.
    let DataReservation =
        {
            idReservation:idReserva,
            startDate:Inicio,
            devolutionDate:Devolucion,
            client:{idClient:Cliente},
            cloud:{id:Nube}
        };
    // Conversion de datos Json a String.
    let DatosReservation=JSON.stringify(DataReservation)

    console.log(DataReservation);
    console.log(DatosReservation);
    //LLamado AJAX para la peticion POST.
    $.ajax
    (
        {
            url : 'http://localhost:8080/api/Reservation/save',
            type : 'POST',
            data:DatosReservation,
            // Tipo de Contenido que va a recibir el backend
            contentType:'application/json',
            success : function(Reservation)
            {
                // Instruccion para vaciar los campos de texto en el html.
                $("#IdReserva").val();
                $("#StartDate").val();
                $("#DevolutionDate").val();
                $("#IdClientR").val();
                $("#IdCloudR").val();
            },
            error : function()
            {
                alert('ha sucedido un problema');
            },
            complete : function()
            {
                // LLamado de la funcion Leer Clientes para que se actualicen los datos de manera automatica.
                ReadReservation();
                alert('Se guardo la reserva');
            }
        }
    );
}

function EditReservation()
{
    // Creacion de las variables que se van a recibir desde el html.
    let idReserva =$("#IdReserva").val();
    let Inicio =$("#StartDate").val();
    let Devolucion = $("#DevolutionDate").val();

    // Creacion de la lista de datos para guardarla en la base de datos como propiedades de la instancia Nube.
    let DataReservation =
        {
            idReservation:idReserva,
            startDate:Inicio,
            devolutionDate:Devolucion
            //client: {idClient:Cliente},
            //cloud:{id:Nube}
        };
    // Conversion de datos Json a String.
    let DatosReservation=JSON.stringify(DataReservation)

    console.log(DataReservation);
    console.log(DatosReservation);
    //LLamado AJAX para la peticion PUT.
    $.ajax
    (
        {
            url : 'http://localhost:8080/api/Reservation/update',
            type : 'PUT',
            data:DatosReservation,
            // Tipo de Contenido que va a recibir el backend
            contentType:'application/json',
            success : function(Reservation)
            {
                // Instruccion para vaciar los campos de texto en el html.
                $("#IdReserva").val("");
                $("#StartDate").val("");
                $("#DevolutionDate").val("");
                $("#IdClientR").val("");
                $("#IdCloudR").val("");
            },
            error : function()
            {
                alert('ha sucedido un problema');
            },
            complete : function()
            {
                // LLamado de la funcion Leer Clientes para que se actualicen los datos de manera automatica.
                ReadReservation();
                alert('Se actualizo la reserva');
            }
        }
    );
}
function DeleteReservation(idReservation)
{
    // LLamado Ajax del Delete.
    $.ajax
    (
        {
            url : 'http://localhost:8080/api/Reservation/'+ idReservation,
            type : 'DELETE',
            contentType:'application/json',
            success : function(Reservation)
            {
                $("#Brand").val("");
                $("#Year").val("");
                $("#Category").val("");
                $("#Name").val("");
                $("#Description").val("");
            },
            error : function()
            {
                alert('ha sucedido un problema');
            },
            complete : function()
            {
                // LLamado de la funcion Leer Clientes para que se actualicen los datos de manera automatica.
                ReadReservation();
                alert('Se elimino la reserva');
            }
        }
    );
}

