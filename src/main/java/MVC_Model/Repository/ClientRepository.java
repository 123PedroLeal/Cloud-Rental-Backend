package MVC_Model.Repository;


import MVC_Model.Model.Client;
import MVC_Model.Repository.CRUD.ClientCRUDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/*@Repository hace referencia a que es la clase que va a recibir los datos de la interfaz
CRUDRepositorio y va a entonces a recibir los codigos Get,Post,Put y Delete*/
@Repository
public class ClientRepository
{
    @Autowired
    private ClientCRUDRepository clientCRUDRepository;

    // Metodo para obtener todos los objetos de tipo nube o hacer la petición Get de todos los objetos.
    public List<Client> GetAll()
    {
        return (List<Client>) clientCRUDRepository.findAll();
    }
    // Metodo para obtener un objeto de tipo nube o solo hacer una petición Get a un solo objeto.
    public Optional<Client> getClient(int id)
    {
        return clientCRUDRepository.findById(id);
    }
    // Metodo para guardar el objeto nube o hacer la petición Post.
    public Client save(Client c)
    {
        return clientCRUDRepository.save(c);
    }

    public void delete(Client c) {
        clientCRUDRepository.delete(c);}
}
