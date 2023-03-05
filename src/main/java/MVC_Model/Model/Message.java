package MVC_Model.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

//@Entity es para crear el objeto que vamos a usar en este caso nube.
//@Table es para decirle el nombre de la tabla que vamos a usar.
@Entity
@Table(name="message")
public class Message implements Serializable
{
    //@Id es para especificar cual es el Id o llave primaria de esa tabla.
    //@GeneratedValue no se para que es.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMessage;
    private String messageText;

    /*@ManyToOne sirve para indicar la relación entre la entidad Nube con la entidad categoria en este
    caso que significa que hay muchas categoras en una sola nube.*/
    /* @JoinColumn hace referencia a cual es la columna que vamos a usar para unir la información
    de esa entidad con la otra.*/
    /* @JsonIgnoreProperties hace referencia a que cuando me imprima la instancia categoria no me
    imprima otra vez la instancia nube.*/

    @ManyToOne
    @JoinColumn(name = "idCloud")
    @JsonIgnoreProperties({"messages","reservations"})
    private Cloud cloud;

    @ManyToOne
    @JoinColumn(name = "idClient")
    @JsonIgnoreProperties({"messages","reservations"})
    private Client client;

    public Integer getIdMessage()
    {
        return idMessage;
    }

    public void setIdMessage(Integer idMessage)
    {
        this.idMessage = idMessage;
    }

    public String getMessageText()
    {
        return messageText;
    }

    public void setMessageText(String messageText)
    {
        this.messageText = messageText;
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
}
