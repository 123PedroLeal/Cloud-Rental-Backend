// Nota: Para llamar las variables o botones desde el html usamos el numeral antes de la variable.
function ReadScore()
{
    //Llamado AJAX
    $.ajax
    (
        {
            //Url de la base de datos.
            url : 'http://localhost:8080/api/Score/all',
            //Tipo de peticion que vamos a usar en este caso GET.
            type : 'GET',
            //Tipo de dato que acepta el ajax, en este caso JSON.
            dataType : 'json',
            //Que va a hacer si los datos que recibe son correctos
            success : function(Score)
            {
                //Creacion de los items de la instancia clientes
                let ss=Score;
                //Instruccion para vaciar los campos de la lista de clientes
                $("#ListaCalificacion").empty();
                //Ciclo para recorrer todas las instancias de la base de datos.
                for( i=0;i<ss.length;i++)
                {
                    //Instruccion para agregar en la pagina web la informacion del div en este caso
                    //El ID, el nombre y el email.
                    $("#ListaCalificacion").append("<b>"+ss[i].stars+"</b> "+"<b>"+ss[i].messagetext+"</b> "
                        + "<b>" + ss[i].reservations.idReservation +"</b> ");

                    //Instruccion para crear los botones de borrar justo al lado de la informacion del cliente.
                    $("#ListaCalificacion").append("<button onclick='DeleteScore("+ss[i].idScore+")'>Borrar Calificacion</button> <br>");
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
                alert('Se muestran las Calificaciones');
            }
        }
    );
}

function SaveScore()
{
    // Creacion de las variables que se van a recibir desde el html.
    let IdScore =$("#idScore").val();
    let Calificacion = parseInt($("#Score").val());
    let Mensaje = $("#MessageScore").val();
    let idReserva =parseInt($("#IdReserva").val());

    // Creacion de la lista de datos para guardarla en la base de datos como propiedades de la instancia Nube.
    let DataScore =
        {
            stars:Calificacion,
            messagetext:Mensaje,
            reservations:{idReservation:idReserva}
        };
    // Conversion de datos Json a String.
    let DatosScore=JSON.stringify(DataScore)

    console.log(DataScore);
    console.log(DatosScore);
    console.log($("#ListaCalificacion"));
    //LLamado AJAX para la peticion POST.
    $.ajax
    (
        {
            url : 'http://localhost:8080/api/Score/save',
            type : 'POST',
            data:DatosScore,
            // Tipo de Contenido que va a recibir el backend
            contentType:'application/json',
            success : function(Score)
            {
                // Instruccion para vaciar los campos de texto en el html.
                $("#idScore").val();
                $("#Score").val();
                $("#MessageScore").val();
                $("#IdReserva").val();
            },
            error : function()
            {
                alert('ha sucedido un problema');
            },
            complete : function()
            {
                // LLamado de la funcion Leer Clientes para que se actualicen los datos de manera automatica.
                ReadScore();
                alert('Se guardo la Calificacion');
            }
        }
    );
}

function EditScore()
{
    // Creacion de las variables que se van a recibir desde el html.
    let IdScore =$("#idScore").val();
    let Calificacion = parseInt($("#Score").val());
    let Mensaje = $("#MessageScore").val();

    // Creacion de la lista de datos para guardarla en la base de datos como propiedades de la instancia Nube.
    let DataScore =
        {
            idScore: IdScore,
            stars:Calificacion,
            messagetext:Mensaje
        };
    // Conversion de datos Json a String.
    let DatosScore=JSON.stringify(DataScore)

    console.log(DataScore);
    console.log(DatosScore);
    //LLamado AJAX para la peticion POST.
    $.ajax
    (
        {
            url : 'http://localhost:8080/api/Score/update',
            type : 'PUT',
            data:DatosScore,
            // Tipo de Contenido que va a recibir el backend
            contentType:'application/json',
            success : function(Score)
            {
                // Instruccion para vaciar los campos de texto en el html.
                $("#idScore").val();
                $("#Score").val();
                $("#MessageScore").val();
                $("#IdReserva").val();
            },
            error : function()
            {
                alert('ha sucedido un problema');
            },
            complete : function()
            {
                // LLamado de la funcion Leer Clientes para que se actualicen los datos de manera automatica.
                ReadScore();
                alert('Se actualizo la Calificacion');
            }
        }
    );
}
function DeleteScore(idScore)
{
    // LLamado Ajax del Delete.
    $.ajax
    (
        {
            url : 'http://localhost:8080/api/Score/'+ idScore,
            type : 'DELETE',
            contentType:'application/json',
            success : function(Score)
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
                ReadScore();
                alert('Se elimino la calificacion');
            }
        }
    );
}

