package MVC_Model.Repository;


import MVC_Model.Model.Client;
import MVC_Model.Model.DTOs.CountClient;
import MVC_Model.Model.Reservation;
import MVC_Model.Repository.CRUD.ReservationCRUDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class ReservationRepository
{
    @Autowired
    private ReservationCRUDRepository reservationCRUDRepository;

    public List<Reservation> GetAll(){return (List<Reservation>) reservationCRUDRepository.findAll();}

    public Optional<Reservation> getReserva(int id) {return reservationCRUDRepository.findById(id);}

    public Reservation save(Reservation r) {return reservationCRUDRepository.save(r);}

    public void delete(Reservation r) {
        reservationCRUDRepository.delete(r);}

    //Reto5 (Estudiar mas a fondo).

    //1er punto

    public List<Reservation> getReservaporestado(String status)
    {
        return reservationCRUDRepository.findAllByStatus(status);
    }

    //2do punto

    public List<Reservation> getPeriodoReserva(Date a, Date b)
    {
        return reservationCRUDRepository.findAllByStartDateAfterAndDevolutionDateBefore(a,b);
    }

    //3er punto
    public List<CountClient> getTopClients()
    {
        List<CountClient> respuesta = new ArrayList<>();
        List<Object[]> reporte = reservationCRUDRepository.TotalReservasporClients();

        for(int i=0; i<reporte.size();i++)
        {
            //[client,total] ->  [total,client]
            respuesta.add(new CountClient((Long)reporte.get(i)[1], (Client) reporte.get(i)[0]));
        }
        return respuesta;
    }
}
