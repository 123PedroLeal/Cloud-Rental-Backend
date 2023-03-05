package MVC_Model.Web;


import MVC_Model.Service.ClientService;
import MVC_Model.Model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Client")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class ClientController
{
    @Autowired
    private ClientService clientService;

    @GetMapping("/all")
    public List<Client> GetClientes() {return clientService.GetAll();}

    @GetMapping("/{id}")
    public Optional<Client> GetCliente(@PathVariable("id")int id)
    {
        return clientService.getClient(id);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Client save(@RequestBody Client c)
    {
        return clientService.save(c);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Client update(@RequestBody Client c)
    {
        return clientService.update(c);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id")int id)
    {
        return clientService.deleteClient(id);
    }
}
