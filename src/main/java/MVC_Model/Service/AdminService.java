package MVC_Model.Service;

import MVC_Model.Model.Admin;
import MVC_Model.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service hace referencia a que la clase va a hacer el servicio.
@Service
public class AdminService
{
    @Autowired
    private AdminRepository adminRepository;

    //Metodo para obtener todos los objetos de tipo nube en el repositorio.
    public List<Admin> GetAll() {
        return adminRepository.GetAll();
    }

    //Metodo para obtener un solo objeto de tipo nube en el repositorio.
    public Optional<Admin> getAdmin(int id) {return adminRepository.getAdmin(id);}

    //Metodo para guardar un objeto de tipo nube que viene del repositorio.
    public Admin save(Admin a) {
        // Metodo para que en caso de que el objeto enviado no tenga id nulo de todas formas lo guarde.
        if (a.getIdAdmin() == null) {
            return adminRepository.save(a);
        } else {
            // Metodo para saber que si se obtiene una Nube vacia igual guarde la operacion
            Optional<Admin> aaux = adminRepository.getAdmin(a.getIdAdmin());
            if (!aaux.isPresent()) {
                return adminRepository.save(a);
            }
            // Sino que guarde la instancia creada.
            else {
                return a;
            }
        }
    }
    public boolean deleteAdmin(int id)
    {
        Boolean a = getAdmin(id).map(Admin-> {
            adminRepository.delete(Admin);
            return true;
        }).orElse(false);
        return a;
    }

    public Admin update(Admin a)
    {
        if (a.getIdAdmin() != null)
        {
            Optional<Admin> aaux = adminRepository.getAdmin(a.getIdAdmin());
            if (aaux.isPresent())
            {
                if (a.getEmail()!=null)
                {
                    aaux.get().setEmail(a.getEmail());
                }
                if (a.getPassword()!=null)
                {
                    aaux.get().setPassword(a.getPassword());
                }
                if (a.getName()!=null)
                {
                    aaux.get().setName(a.getName());
                }

                adminRepository.save(aaux.get());
                return aaux.get();
            }
            else
            {
                return a;
            }
        }
        else
        {
            return a;
        }
    }
}