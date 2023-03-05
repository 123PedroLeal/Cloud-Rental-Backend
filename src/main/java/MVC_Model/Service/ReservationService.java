package MVC_Model.Service;
import MVC_Model.Model.Reservation;
import MVC_Model.Repository.ReservationRepository;
import MVC_Model.Model.DTOs.CountClient;
import MVC_Model.Model.DTOs.CountStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

//@Service hace referencia a que la clase va a hacer el servicio.
@Service
public class ReservationService
{
    @Autowired
    private ReservationRepository reservationRepository;

    //Metodo para obtener todos los objetos de tipo nube en el repositorio.
    public List<Reservation> GetAll() {
        return reservationRepository.GetAll();
    }

    //Metodo para obtener un solo objeto de tipo nube en el repositorio.
    public Optional<Reservation> getReserva(int id) {return reservationRepository.getReserva(id);}

    //Metodo para guardar un objeto de tipo nube que viene del repositorio.
    public Reservation save(Reservation r) {
        // Metodo para que en caso de que el objeto enviado no tenga id nulo de todas formas lo guarde.
        if (r.getIdReservation() == null) {
            return reservationRepository.save(r);
        } else {
            // Metodo para saber que si se obtiene una Nube vacia igual guarde la operacion
            Optional<Reservation> raux = reservationRepository.getReserva(r.getIdReservation());
            if (!raux.isPresent()) {
                return reservationRepository.save(r);
            }
            // Sino que guarde la instancia creada.
            else {
                return r;
            }
        }
    }

    public boolean deleteReservation(int id)
    {
        Boolean r = getReserva(id).map(Reserva-> {
            reservationRepository.delete(Reserva);
            return true;
        }).orElse(false);
        return r;
    }

    public Reservation update(Reservation r)
    {
        if (r.getIdReservation() != null)
        {
            Optional<Reservation> raux = reservationRepository.getReserva(r.getIdReservation());
            if (raux.isPresent())
            {
                if (r.getStartDate()!=null)
                {
                    raux.get().setStartDate(r.getStartDate());
                }
                if (r.getDevolutionDate()!=null)
                {
                    raux.get().setDevolutionDate(r.getDevolutionDate());
                }
                if (r.getStatus()!=null)
                {
                    raux.get().setStatus(r.getStatus());
                }
                if (r.getCloud()!=null)
                {
                    raux.get().setCloud(r.getCloud());
                }

                if (r.getClient()!=null)
                {
                    raux.get().setClient(r.getClient());
                }

                reservationRepository.save(raux.get());
                return raux.get();
            }
            else
            {
                return r;
            }
        }
        else
        {
            return r;
        }
    }

    //Reto5 (Estudiar mas a fondo).

    //1er Punto

    public CountStatus getReservaporestado()
    {
        List<Reservation> completed = reservationRepository.getReservaporestado("completed");
        List<Reservation> cancelled = reservationRepository.getReservaporestado("cancelled");

        return new CountStatus((long) completed.size(), (long) cancelled.size());
    }

    //2do Punto
    public List<Reservation> getPeriodoReserva(String DateA, String DateB)
    {
        SimpleDateFormat parseador = new SimpleDateFormat("yyyy-MM-dd"); //2022-01-01
        Date a = new Date();
        Date b = new Date();
        try
        {
            a = parseador.parse(DateA);
            b = parseador.parse(DateB);

        }catch (ParseException e)
        {
            e.printStackTrace();
        }
        if(a.before(b))
        {
            return reservationRepository.getPeriodoReserva(a,b);
        }
        else
        {
            return new ArrayList<>();
        }
    }
    //3er Punto
    public List<CountClient> getTopClients()
    {
        return reservationRepository.getTopClients();
    }
}