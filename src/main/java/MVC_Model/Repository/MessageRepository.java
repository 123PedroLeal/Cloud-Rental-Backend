package MVC_Model.Repository;


import MVC_Model.Model.Message;
import MVC_Model.Repository.CRUD.MessageCRUDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MessageRepository
{
    @Autowired
    private MessageCRUDRepository messageCRUDRepository;

    public List<Message> getAll(){return (List<Message>) messageCRUDRepository.findAll();}

    public Optional<Message> getMensaje(int id) {return messageCRUDRepository.findById(id);}

    public Message save(Message m) {return messageCRUDRepository.save(m);}

    public void delete(Message m) {
        messageCRUDRepository.delete(m);}
}
