package MVC_Model.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

//@Entity es para crear el objeto que vamos a usar en este caso nube.
//@Table es para decirle el nombre de la tabla que vamos a usar.
@Entity
@Table(name="score")
public class Score implements Serializable
{
    //@Id es para especificar cual es el Id o llave primaria de esa tabla.
    //@GeneratedValue no se para que es.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idScore;
    @Column(name = "messagetext",length = 250)
    private String messagetext;
    @Column(name = "stars")
    private Integer stars;

    /*@ManyToOne sirve para indicar la relación entre la entidad Nube con la entidad categoria en este
    caso que significa que hay muchas categorías en una sola nube.*/
    /* @JoinColumn hace referencia a cual es la columna que vamos a usar para unir la información
    de esa entidad con la otra.*/
    /* @JsonIgnoreProperties hace referencia a que cuando me imprima la instacia categoria no me
    imprima otra vez la instancia nube.*/

    @OneToOne
    @JsonIgnoreProperties("score")
    public Reservation reservations;

    public Integer getIdScore()
    {
        return idScore;
    }

    public void setIdScore(Integer idScore)
    {
        this.idScore = idScore;
    }

    public String getMessagetext()
    {
        return messagetext;
    }

    public void setMessagetext(String messagetext)
    {
        this.messagetext = messagetext;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars)
    {
        this.stars = stars;
    }

    public Reservation getReservations()
    {
        return reservations;
    }

    public void setReservations(Reservation reservations)
    {
        this.reservations = reservations;
    }
}
