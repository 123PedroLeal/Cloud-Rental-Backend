package MVC_Model.Web;


import MVC_Model.Service.CloudService;
import MVC_Model.Model.Cloud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/*@RestController hace referencia a que esta sera la clase encargada de hacer las funciones del
controlador*/
/*@RequestMapping hace referencia a que esa sera la ruta a seguir para hacer las operaciones del CRUD*/
/*@CrossOrigin Todavia no termino de entender para que lo uso*/
@RestController
@RequestMapping("/api/Cloud")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class CloudController
{
    @Autowired
    private CloudService cloudService;
    /*@GetMapping hace referencia a cual va a ser el siguiente proceso en la URL y con eso hacer la peticion Get
    * de todos los objetos*/
    @GetMapping("/all")
    public List<Cloud> GetNubes()
    {
        return cloudService.GetAll();
    }
    /*@GetMapping hace referencia a cual va a ser el siguiente proceso en la URL y con eso hacer la peticion Get
     * de un solo objeto*/
    @GetMapping("/{id}")
    public Optional<Cloud> GetNube(@PathVariable("id")int id)
    {
        return cloudService.getNube(id);
    }
    /*@PostMapping hace referencia a cual va a ser el siguiente proceso en la URL y con eso hacer la peticion Post
     * de un objeto, es decir guardar o crear el objeto*/
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Cloud save(@RequestBody Cloud n)
    {
        return cloudService.save(n);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Cloud update(@RequestBody Cloud n)
    {
        return cloudService.update(n);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id")int id)
    {
        return cloudService.deleteCloud(id);
    }
}
