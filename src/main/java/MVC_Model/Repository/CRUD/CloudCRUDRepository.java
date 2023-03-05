package MVC_Model.Repository.CRUD;


import MVC_Model.Model.Cloud;
import org.springframework.data.repository.CrudRepository;

//Interfaz encargada de recibir los datos de las entidades para realizar los servicios CRUD.
public interface CloudCRUDRepository extends CrudRepository<Cloud,Integer>
{
}
