package MVC_Model.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

//@Entity es para crear el objeto que vamos a usar en este caso nube.
//@Table es para decirle el nombre de la tabla que vamos a usar.
@Entity
@Table(name="reservation")
public class Reservation implements Serializable
{
    //@Id es para especificar cual es el Id o llave primaria de esa tabla.
    //@GeneratedValue no se para que es.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReservation;
    private Date startDate;
    private Date devolutionDate;
    private String status = "created";

    /*@ManyToOne sirve para indicar la relación entre la entidad Nube con la entidad categoria en este
    caso que significa que hay muchas categorias en una sola nube.*/
    /* @JoinColumn hace referencia a cual es la columna que vamos a usar para unir la información
    de esa entidad con la otra.*/
    /* @JsonIgnoreProperties hace referencia a que cuando me imprima la instancia categoria no me
    imprima otra vez la instancia nube.*/
    @ManyToOne
    @JoinColumn(name = "idcloud")
    @JsonIgnoreProperties({"reservations"})
    public Cloud cloud;

    @ManyToOne
    @JoinColumn(name = "idclient")
    @JsonIgnoreProperties({"messages","reservations"})
    public Client client;

    @OneToOne
    @JsonIgnoreProperties("reservations")
    public Score score;

    public Integer getIdReservation()
    {
        return idReservation;
    }

    public void setIdReservation(Integer idReservation)
    {
        this.idReservation = idReservation;
    }

    public Date getStartDate()
    {
        return startDate;
    }

    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }

    public Date getDevolutionDate()
    {
        return devolutionDate;
    }

    public void setDevolutionDate(Date devolutionDate)
    {
        this.devolutionDate = devolutionDate;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public Cloud getCloud()
    {
        return cloud;
    }

    public void setCloud(Cloud cloud)
    {
        this.cloud = cloud;
    }

    public Client getClient()
    {
        return client;
    }

    public void setClient(Client client)
    {
        this.client = client;
    }

    public Score getScore()
    {
        return score;
    }

    public void setScore(Score score)
    {
        this.score = score;
    }
}
