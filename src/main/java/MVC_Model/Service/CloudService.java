package MVC_Model.Service;


import MVC_Model.Model.Cloud;
import MVC_Model.Repository.CloudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service hace referencia a que la clase va a hacer el servicio.
@Service
public class CloudService
{
    @Autowired
    private CloudRepository cloudRepository;

    //Metodo para obtener todos los objetos de tipo nube en el repositorio.
    public List<Cloud> GetAll() {
        return cloudRepository.GetAll();
    }

    //Metodo para obtener un solo objeto de tipo nube en el repositorio.
    public Optional<Cloud> getNube(int id) {
        return cloudRepository.getNube(id);
    }

    //Metodo para guardar un objeto de tipo nube que viene del repositorio.
    public Cloud save(Cloud n) {
        // Metodo para que en caso de que el objeto enviado no tenga id nulo de todas formas lo guarde.
        if (n.getId() == null) {
            return cloudRepository.save(n);
        } else {
            // Metodo para saber que si se obtiene una Nube vacia igual guarde la operacion
            Optional<Cloud> naux = cloudRepository.getNube(n.getId());
            if (!naux.isPresent()) {
                return cloudRepository.save(n);
            }
            // Sino que guarde la instancia creada.
            else {
                return n;
            }
        }
    }

    public boolean deleteCloud(int id)
    {
        Boolean n = getNube(id).map(Nube-> {
            cloudRepository.delete(Nube);
            return true;
        }).orElse(false);
        return n;
    }

    public Cloud update(Cloud n)
    {
        if (n.getId() != null)
        {
            Optional<Cloud> naux = cloudRepository.getNube(n.getId());
            if (naux.isPresent())
            {
                if (n.getName()!=null)
                {
                    naux.get().setName(n.getName());
                }
                if (n.getBrand()!=null)
                {
                    naux.get().setBrand(n.getBrand());
                }
                if (n.getYear()!=null)
                {
                    naux.get().setYear(n.getYear());
                }
                if (n.getDescription()!=null)
                {
                    naux.get().setDescription(n.getDescription());
                }
                if (n.getCategory()!=null)
                {
                    naux.get().setCategory(n.getCategory());
                }
                if (n.getMessages()!=null)
                {
                    naux.get().setMessages(n.getMessages());
                }
                if (n.getReservations()!=null)
                {
                    naux.get().setReservations(n.getReservations());
                }
                cloudRepository.save(naux.get());
                return naux.get();
            }
            else
            {
                return n;
            }
        }
        else
        {
            return n;
        }
    }
}