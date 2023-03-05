// Nota: Para llamar las variables o botones desde el html usamos el numeral antes de la variable.
function ReadCloud()
{
    //Llamado AJAX
    $.ajax
    (
        { 
        //Url de la base de datos.
        url : 'http://localhost:8080/api/Cloud/all',
        //Tipo de peticion que vamos a usar en este caso GET.
        type : 'GET',
        //Tipo de dato que acepta el ajax, en este caso JSON.
        dataType : 'json',
            //Que va a hacer si los datos que recibe son correctos
            success : function(Clouds)
            {
                //Creacion de los items de la instancia clientes
                let cs=Clouds;
                //Instruccion para vaciar los campos de la lista de clientes
                $("#ListaNubes").empty();
                //Ciclo para recorrer todas las instancias de la base de datos.
                for( i=0;i<cs.length;i++)
                {
                    //Instruccion para agregar en la pagina web la informacion del div en este caso
                    //El ID, el nombre y el email.
                    $("#ListaNubes").append("<b>"+cs[i].brand+"</b> "+"<b>"+cs[i].year+"</b>" +
                        "<b>" +cs[i].name + "</b> "+ "<b>" + cs[i].description + "</b> ");

                    //Instruccion para crear los botones de borrar justo al lado de la informacion del cliente.
                    $("#ListaNubes").append("<button onclick='DeleteCloud("+cs[i].id+")'>Borrar Nube</button> <br>");
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
            alert('Se muestran las Nubes');
            }
        }
    );
}

function SaveCloud()
{
    // Creacion de las variables que se van a recibir desde el html.
    let MarcaNube =$("#Brand").val();
    let YNube = parseInt($("#Year").val());
    let CategoriaNube = parseInt($("#Category").val());
    let NombreNube =$("#Name").val();
    let DescriptionNube =$("#Description").val();
    
    // Creacion de la lista de datos para guardarla en la base de datos como propiedades de la instancia Nube.
    let DataCloud =
    {
        brand:MarcaNube,
        year:YNube,
        category: {id:CategoriaNube},
        name:NombreNube,
        description:DescriptionNube
    };
    // Conversion de datos Json a String.
    let DatosCloud=JSON.stringify(DataCloud)

    console.log(DataCloud);
    console.log(DatosCloud);
    //LLamado AJAX para la peticion POST.
    $.ajax
    (
        { 
        url : 'http://localhost:8080/api/Cloud/save',
        type : 'POST',
        data:DatosCloud,
        // Tipo de Contenido que va a recibir el backend
        contentType:'application/json',
            success : function(Clouds)
            {
                // Instruccion para vaciar los campos de texto en el html.
                $("#IdNube").val("");
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
                ReadCloud();
            alert('Se guardo la nube');
            }
        }
    );
}

function EditCloud()
{
    // Creacion de las variables que se van a recibir desde el html.
    let idNube= $("#IdNube").val();
    let MarcaNube =$("#Brand").val();
    let YNube = parseInt($("#Year").val());
    let CategoriaNube = parseInt($("#Category").val());
    let NombreNube =$("#Name").val();
    let DescriptionNube =$("#Description").val();

    // Creacion de la lista de datos para guardarla en la base de datos como propiedades de la instancia Nube.
    // Importante:
    // Es importante no enviar el id de la Categoria, sino no permite hacer la operacion.
    let DataCloud =
        {
            id:idNube,
            brand:MarcaNube,
            year:YNube,
            //category: {id:CategoriaNube},
            name:NombreNube,
            description:DescriptionNube
        };
    // Conversion de datos Json a String.
    let DatosCloud=JSON.stringify(DataCloud)

    console.log(DataCloud);
    console.log(DatosCloud);
    //LLamado AJAX para la peticion PUT.
    $.ajax
    (
        {
            url : 'http://localhost:8080/api/Cloud/update',
            type : 'PUT',
            data:DatosCloud,
            // Tipo de Contenido que va a recibir el backend
            contentType:'application/json',
            success : function(Clouds)
            {
                // Instruccion para vaciar los campos de texto en el html.
                $("#IdNube").val("");
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
                ReadCloud();
                alert('Se actualizo la nube');
            }
        }
    );
}
function DeleteCloud(idNube)
{
    // LLamado Ajax del Delete.
    $.ajax
    (
        { 
        url : 'http://localhost:8080/api/Cloud/'+ idNube,
        type : 'DELETE',
        contentType:'application/json',
            success : function(Clouds)
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
            ReadCloud();
            alert('Se elimino la nube');
            }
        }
    );
}

