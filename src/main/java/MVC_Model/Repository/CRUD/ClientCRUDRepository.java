package MVC_Model.Repository.CRUD;

import MVC_Model.Model.Client;
import org.springframework.data.repository.CrudRepository;

//Interfaz encargada de recibir los datos de las entidades para realizar los servicios CRUD.
public interface ClientCRUDRepository extends CrudRepository<Client,Integer>
{
}
