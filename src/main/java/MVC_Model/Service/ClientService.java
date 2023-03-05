package MVC_Model.Service;


import MVC_Model.Model.Client;
import MVC_Model.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service hace referencia a que la clase va a hacer el servicio.
@Service
public class ClientService
{
    @Autowired
    private ClientRepository clientRepository;

    //Metodo para obtener todos los objetos de tipo nube en el repositorio.
    public List<Client> GetAll() {
        return clientRepository.GetAll();
    }

    //Metodo para obtener un solo objeto de tipo nube en el repositorio.
    public Optional<Client> getClient(int id) {
        return clientRepository.getClient(id);
    }

    //Metodo para guardar un objeto de tipo nube que viene del repositorio.
    public Client save(Client c)
    {
        // Metodo para que en caso de que el objeto enviado no tenga id nulo de todas formas lo guarde.
        if (c.getIdClient() == null)
        {
            return clientRepository.save(c);
        } else {
            // Metodo para saber que si se obtiene una Nube vacia igual guarde la operacion
            Optional<Client> caux = clientRepository.getClient(c.getIdClient());
            if (!caux.isPresent()) {
                return clientRepository.save(c);
            }
            // Sino que guarde la instancia creada.
            else {
                return c;
            }
        }
    }

    public boolean deleteClient(int id)
    {
        Boolean c = getClient(id).map(Client-> {
            clientRepository.delete(Client);
            return true;
        }).orElse(false);
        return c;
    }

    public Client update(Client c)
    {
        if (c.getIdClient() != null)
        {
            Optional<Client> caux = clientRepository.getClient(c.getIdClient());
            if (caux.isPresent())
            {
                if (c.getEmail()!=null)
                {
                    caux.get().setEmail(c.getEmail());
                }
                if (c.getPassword()!=null)
                {
                    caux.get().setPassword(c.getPassword());
                }
                if (c.getName()!=null)
                {
                    caux.get().setName(c.getName());
                }
                if (c.getAge()!=null)
                {
                    caux.get().setAge(c.getAge());
                }
                if (c.getMessages()!=null)
                {
                    caux.get().setMessages(c.getMessages());
                }
                if (c.getReservations()!=null)
                {
                    caux.get().setReservations(c.getReservations());
                }
                clientRepository.save(caux.get());
                return caux.get();
            }
            else
            {
                return c;
            }
        }
        else
        {
            return c;
        }
    }
}