package MVC_Model.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

//@Entity es para crear el objeto que vamos a usar en este caso nube.
//@Table es para decirle el nombre de la tabla que vamos a usar.
@Entity
@Table(name="client")
public class Client implements Serializable
{
    //@Id es para especificar cual es el Id o llave primaria de esa tabla.
    //@GeneratedValue no se para que es.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idClient;
    @Column(name = "email",length = 45)
    private String email;
    @Column(name = "password",length = 45)
    private String password;
    @Column(name = "name",length = 250)
    private String name;
    private Integer age;

    /*@ManyToOne sirve para indicar la relación entre la entidad Nube con la entidad categoria en este
    caso que significa que hay muchas categorías en una sola nube.*/
    /* @JoinColumn hace referencia a cual es la columna que vamos a usar para unir la información
    de esa entidad con la otra.*/
    /* @JsonIgnoreProperties hace referencia a que cuando me imprima la instacia categoria no me
    imprima otra vez la instancia nube.*/

    @OneToMany (cascade = {CascadeType.PERSIST},mappedBy = "client")
    @JsonIgnoreProperties("client")
    public List <Message> messages;

    // Cascade sirve para que revise el mapeado de todos los datos que contengan los datos del client.
    @OneToMany (cascade = {CascadeType.PERSIST},mappedBy = "client")
    @JsonIgnoreProperties("client")
    public List <Reservation> reservations;

    public Integer getIdClient()
    {
        return idClient;
    }

    public void setIdClient(Integer idClient)
    {
        this.idClient = idClient;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Integer getAge()
    {
        return age;
    }

    public void setAge(Integer age)
    {
        this.age = age;
    }

    public List<Message> getMessages()
    {
        return messages;
    }

    public void setMessages(List<Message> messages)
    {
        this.messages = messages;
    }

    public List<Reservation> getReservations()
    {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations)
    {
        this.reservations = reservations;
    }
}
