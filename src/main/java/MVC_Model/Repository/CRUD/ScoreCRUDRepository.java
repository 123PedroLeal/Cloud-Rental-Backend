package MVC_Model.Repository.CRUD;


import MVC_Model.Model.Score;
import org.springframework.data.repository.CrudRepository;

//Interfaz encargada de recibir los datos de las entidades para realizar los servicios CRUD.
public interface ScoreCRUDRepository extends CrudRepository<Score,Integer>
{
}
