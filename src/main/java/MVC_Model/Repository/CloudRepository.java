package MVC_Model.Repository;

import MVC_Model.Model.Cloud;
import MVC_Model.Repository.CRUD.CloudCRUDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/*@Repository hace referencia a que es la clase que va a recibir los datos de la interfaz
CRUDRepossitorio y va a entonces a recibir los codigos Get,Post,Put y Delete*/
@Repository
public class CloudRepository
{
    @Autowired
    private CloudCRUDRepository cloudCRUDRepository;

    // Metodo para obtener todos los objetos de tipo nube o hacer la peticion Get de todos los objetos.
    public List<Cloud> GetAll()
    {
        return (List<Cloud>) cloudCRUDRepository.findAll();
    }
    // Metodo para obtener un objeto de tipo nube o solo hacer una peticion Get a un solo objeto.
    public Optional<Cloud> getNube(int id)
    {
        return cloudCRUDRepository.findById(id);
    }
    // Metodo para guardar el objeto nube o hacer la peticion Post.
    public Cloud save(Cloud n)
    {
        return cloudCRUDRepository.save(n);
    }

    public void delete(Cloud n) {
        cloudCRUDRepository.delete(n);}
}
