package MVC_Model.Service;


import MVC_Model.Model.Message;
import MVC_Model.Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service hace referencia a que la clase va a hacer el servicio.
@Service
public class MessageService
{
    @Autowired
    private MessageRepository messageRepository;

    //Metodo para obtener todos los objetos de tipo nube en el repositorio.
    public List<Message> getAll(){return messageRepository.getAll();}

    //Metodo para obtener un solo objeto de tipo nube en el repositorio.
    public Optional<Message> getMensaje(int id) {return messageRepository.getMensaje(id);}

    //Metodo para guardar un objeto de tipo nube que viene del repositorio.
    public Message save(Message m)
    {
        // Metodo para que en caso de que el objeto enviado no tenga id nulo de todas formas lo guarde.
        if (m.getIdMessage() == null)
        {
            return messageRepository.save(m);
        } else
        {
            // Metodo para saber que si se obtiene una Nube vacia igual guarde la operacion
            Optional<Message> maux = messageRepository.getMensaje(m.getIdMessage());
            if (!maux.isPresent()) {
                return messageRepository.save(m);
            }
            // Sino que guarde la instancia creada.
            else {
                return m;
            }
        }
    }

    public boolean deleteMessage(int id)
    {
        Boolean m = getMensaje(id).map(Mensaje-> {
            messageRepository.delete(Mensaje);
            return true;
        }).orElse(false);
        return m;
    }

    public Message update(Message m)
    {
        if (m.getIdMessage() != null)
        {
            Optional<Message> maux = messageRepository.getMensaje(m.getIdMessage());
            if (maux.isPresent())
            {
                if (m.getMessageText()!=null)
                {
                    maux.get().setMessageText(m.getMessageText());
                }
                if (m.getCloud()!=null)
                {
                    maux.get().setCloud(m.getCloud());
                }
                if (m.getClient()!=null)
                {
                    maux.get().setClient(m.getClient());
                }
                messageRepository.save(maux.get());
                return maux.get();
            }
            else
            {
                return m;
            }
        }
        else
        {
            return m;
        }
    }
}