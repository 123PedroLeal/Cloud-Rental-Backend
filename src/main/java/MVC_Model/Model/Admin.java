package MVC_Model.Model;

import javax.persistence.*;
import java.io.Serializable;

//@Entity es para crear el objeto que vamos a usar en este caso nube.
//@Table es para decirle el nombre de la tabla que vamos a usar.
@Entity
@Table(name="admin")
public class Admin implements Serializable
{
    //@Id es para especificar cual es el Id o llave primaria de esa tabla.
    //@GeneratedValue no se para que es.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAdmin;
    @Column(name = "email",length = 45)
    private String email;
    @Column(name = "password",length = 45)
    private String password;
    @Column(name = "name",length = 45)
    private String name;

    /*@ManyToOne sirve para indicar la relación entre la entidad Nube con la entidad categoria en este
    caso que significa que hay muchas categorías en una sola nube.*/
    /* @JoinColumn hace referencia a cual es la columna que vamos a usar para unir la información
    de esa entidad con la otra.*/
    /* @JsonIgnoreProperties hace referencia a que cuando me imprima la instacia categoria no me
    imprima otra vez la instancia nube.*/

    public Integer getIdAdmin()
    {
        return idAdmin;
    }

    public void setIdAdmin(Integer idAdmin)
    {
        this.idAdmin = idAdmin;
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
}
