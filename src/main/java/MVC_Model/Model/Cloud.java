package MVC_Model.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

//@Entity es para crear el objeto que vamos a usar en este caso nube.
//@Table es para decirle el nombre de la tabla que vamos a usar.
@Entity
@Table(name="cloud")
public class Cloud implements Serializable
{
    //@Id es para especificar cual es el Id o llave primaria de esa tabla.
    //@GeneratedValue no se para que es.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "brand",length = 45)
    private String brand;

    @Column(name = "years")
    private Integer year;

    @Column(name = "name",length = 45)
    private String name;

    @Column(name = "description",length = 250)
    private String description;

    /*@ManyToOne sirve para indicar la relación entre la entidad Nube con la entidad categoria en este
    caso que signfica que hay muchas categorias en una sola nube.*/
    /* @JoinColumn hace referencia a cual es la columna que vamos a usar para unir la información
    de esa entiedad con la otra.*/
    /* @JsonIgnoreProperties hace referencia a que cuando me imprima la instacia categoria no me
    imprima otra vez la instancia nube.*/
    @ManyToOne
    @JoinColumn(name = "categoryid")
    @JsonIgnoreProperties({"clouds"})
    private Category category;

    @OneToMany (cascade = {CascadeType.PERSIST},mappedBy = "cloud")
    @JsonIgnoreProperties({"cloud","client"})
    public List<Message> messages;

    // Cascade sirve para que revise el mapeado de todos los datos que contengan los datos de la nube.
    @OneToMany (cascade = {CascadeType.PERSIST},mappedBy = "cloud")
    @JsonIgnoreProperties({"cloud","messages"})
    public List <Reservation> reservations;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getBrand()
    {
        return brand;
    }

    public void setBrand(String brand)
    {
        this.brand = brand;
    }

    public Integer getYear()
    {
        return year;
    }

    public void setYear(Integer year)
    {
        this.year = year;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Category getCategory()
    {
        return category;
    }

    public void setCategory(Category category)
    {
        this.category = category;
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
