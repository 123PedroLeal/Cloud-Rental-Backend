// Nota: Para llamar las variables o botones desde el html usamos el numeral antes de la variable.
function ReadCategory()
{
    //Llamado AJAX
    $.ajax
    (
        {
            //Url de la base de datos.
            url : 'http://localhost:8080/api/Category/all',
            //Tipo de peticion que vamos a usar en este caso GET.
            type : 'GET',
            //Tipo de dato que acepta el ajax, en este caso JSON.
            dataType : 'json',
            //Que va a hacer si los datos que recibe son correctos
            success : function(Category)
            {
                console.log(Category);
                //Creacion de los items de la instancia clientes
                let cs=Category;
                //let cls= Clouds;
                //let cls=Clouds;
                //Instruccion para vaciar los campos de la lista de clientes
                $("#ListaCategorias").empty();
                //$("#ListaNubes").empty();
                //Ciclo para recorrer todas las instancias de la base de datos.

                //for( i=0;i<cls.length;i++)
                //{
                    //Instruccion para agregar en la pagina web la informacion del div en este caso
                    //El ID, el nombre y el email.
                    //$("#ListaNubes").append("<b>" + cls[i].brand + "</b> " + "<b>" + cls[i].year + "</b> + <b>"
                        //+ cls[i].name + "</b> " + "<b>" + cls[i].description + "</b> ");
                //}
                for( i=0;i<cs.length;i++)
                {
                    //Instruccion para agregar en la pagina web la informacion del div en este caso
                    //El ID, el nombre y el email.
                    $("#ListaCategorias").append("<b>"+cs[i].name+"</b> "+"<b>"+cs[i].description+"</b> "
                    + "<b>"+cs.clouds+"</b> ");

                    //Instruccion para crear los botones de borrar justo al lado de la informacion del cliente.
                    $("#ListaCategorias").append("<button onclick='DeleteCategory("+cs[i].id+")'>Borrar Categoria</button> <br>");
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
                alert('Se muestran las Categorias');
            }
        }
    );
}

function SaveCategory()
{
    // Creacion de las variables que se van a recibir desde el html.
    let NombreCategoria =$("#Name").val();
    let DescriptionCategoria =$("#Description").val();

    // Creacion de la lista de datos para guardarla en la base de datos como propiedades de la instancia Nube.
    let DataCategory =
        {
            name:NombreCategoria,
            description:DescriptionCategoria
        };
    // Conversion de datos Json a String.
    let DatosCategory=JSON.stringify(DataCategory)
    console.log(DataCategory);
    console.log(DatosCategory);

    //LLamado AJAX para la peticion POST.
    $.ajax
    (
        {
            url : 'http://localhost:8080/api/Category/save',
            type : 'POST',
            data:DatosCategory,
            // Tipo de Contenido que va a recibir el backend
            contentType:'application/json',
            success : function(Categories)
            {
                // Instruccion para vaciar los campos de texto en el html.
                $("#IdCategory").val("");
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
                ReadCategory();
                alert('Se guardo la categoria');
            }
        }
    );
}

function EditCategory()
{
    // Creacion de las variables que se van a recibir desde el html.
    let IdCategoria=$("#IdCategory").val();
    let NombreCategoria =$("#Name").val();
    let DescriptionCategoria =$("#Description").val();

    // Creacion de la lista de datos para guardarla en la base de datos como propiedades de la instancia Nube.
    let DataCategory =
        {
            id:IdCategoria,
            name:NombreCategoria,
            description:DescriptionCategoria
        };
    // Conversion de datos Json a String.
    let DatosCategory=JSON.stringify(DataCategory)

    console.log(DataCategory);
    console.log(DatosCategory);
    //LLamado AJAX para la peticion POST.
    $.ajax
    (
        {
            url : 'http://localhost:8080/api/Category/update',
            type : 'PUT',
            data:DatosCategory,
            // Tipo de Contenido que va a recibir el backend
            contentType:'application/json',
            success : function(Categories)
            {
                // Instruccion para vaciar los campos de texto en el html.
                $("#IdCategory").val("");
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
                ReadCategory();
                alert('Se actualizo la categoria');
            }
        }
    );
}
function DeleteCategory(idCategory)
{
    // LLamado Ajax del Delete.
    $.ajax
    (
        {
            url : 'http://localhost:8080/api/Category/'+ idCategory,
            type : 'DELETE',
            contentType:'application/json',
            success : function(Categories)
            {
                $("#IdCategory").val("");
                $("#name").val("");
                $("#description").val("");
            },
            error : function()
            {
                alert('ha sucedido un problema');
            },
            complete : function()
            {
                // LLamado de la funcion Leer Clientes para que se actualicen los datos de manera automatica.
                ReadCategory();
                alert('Se elimino la categoria');
            }
        }
    );
}
