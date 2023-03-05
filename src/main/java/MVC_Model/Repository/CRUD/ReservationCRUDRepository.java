package MVC_Model.Repository.CRUD;


import MVC_Model.Model.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

//Interfaz encargada de recibir los datos de las entidades para realizar los servicios CRUD.
public interface ReservationCRUDRepository extends CrudRepository<Reservation,Integer>
{

    //3er punto Reto5
    //@Query("SELECT r.cloud,COUNT (r.cloud)FROM Reserva AS r GROUP BY r.cloud ORDER BY COUNT (r.cloud) DESC ")
    //public List<Object[]> TotalReservasporNubes();

    // [client,total]
    @Query("SELECT r.client,COUNT (r.client)FROM Reservation AS r GROUP BY r.client ORDER BY COUNT (r.client) DESC")
    public List<Object[]> TotalReservasporClients();

    // SELECT * FROM RESERVA WHERE idReservation BETWEEN a AND b;
    //public void findAllByIdReservationBetweenAnd(Integer a,Integer b);

    // SELECT * FROM RESERVA WHERE StartDate AFTER FechaInicio AND FechaDevolución BEFORE FechaFin;
    public List<Reservation> findAllByStartDateAfterAndDevolutionDateBefore (Date dateOne, Date dateTwo);

    // SELECT * FROM RESERVA WHERE status = 'canceled'
    public List<Reservation> findAllByStatus (String status);
}
