package MVC_Model.Web;


import MVC_Model.Model.DTOs.CountClient;
import MVC_Model.Model.DTOs.CountStatus;
import MVC_Model.Model.Reservation;
import MVC_Model.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Reservation")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class ReservationController
{
    @Autowired
    private ReservationService reservationService;

    @GetMapping("/all")
    public List<Reservation> GetReservas() {return reservationService.GetAll();}

    @GetMapping("/{id}")
    public Optional<Reservation> GetReserva(@PathVariable("id")int id)
    {
        return reservationService.getReserva(id);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation save(@RequestBody Reservation r)
    {
        return reservationService.save(r);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation update(@RequestBody Reservation r)
    {
        return reservationService.update(r);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id")int id)
    {
        return reservationService.deleteReservation(id);
    }

    @GetMapping("/report-clients")
    public List <CountClient> getReportTopClients()
    {
        return reservationService.getTopClients();
    }

    @GetMapping("/report-dates/{dateOne}/{dateTwo}")
    public List <Reservation> getReservationDate(@PathVariable("dateOne") String dateOne, @PathVariable("dateTwo") String dateTwo)
    {
        return reservationService.getPeriodoReserva(dateOne,dateTwo);
    }

    @GetMapping("/report-status")
    public CountStatus getReportStatusReservations()
    {
        return reservationService.getReservaporestado();
    }
}
