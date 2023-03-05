package MVC_Model.Service;


import MVC_Model.Model.Score;
import MVC_Model.Repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service hace referencia a que la clase va a hacer el servicio.
@Service
public class ScoreService
{
    @Autowired
    private ScoreRepository scoreRepository;

    //Metodo para obtener todos los objetos de tipo nube en el repositorio.
    public List<Score> GetAll() {
        return scoreRepository.GetAll();
    }

    //Metodo para obtener un solo objeto de tipo nube en el repositorio.
    public Optional<Score> getCalificacion(int id) {return scoreRepository.getCalificacion(id);}

    //Metodo para guardar un objeto de tipo nube que viene del repositorio.
    public Score save(Score c)
    {
        // Metodo para que en caso de que el objeto enviado no tenga id nulo de todas formas lo guarde.
        if (c.getIdScore() == null) {
            return scoreRepository.save(c);
        } else {
            // Metodo para saber que si se obtiene una Nube vacia igual guarde la operacion
            Optional<Score> caux = scoreRepository.getCalificacion(c.getIdScore());
            if (!caux.isPresent()) {
                return scoreRepository.save(c);
            }
            // Sino que guarde la instancia creada.
            else {
                return c;
            }
        }
    }

    public boolean deleteScore(int id)
    {
        Boolean s = getCalificacion(id).map(Calificacion-> {
            scoreRepository.delete(Calificacion);
            return true;
        }).orElse(false);
        return s;
    }

    public Score update(Score c)
    {
        if (c.getIdScore() != null)
        {
            Optional<Score> caux =  scoreRepository.getCalificacion(c.getIdScore());
            if (caux.isPresent())
            {
                if (c.getMessagetext()!=null)
                {
                    caux.get().setMessagetext(c.getMessagetext());
                }
                if (c.getStars()!=null)
                {
                    caux.get().setStars(c.getStars());
                }
                if (c.getReservations()!=null)
                {
                    caux.get().setReservations(c.getReservations());
                }

                scoreRepository.save(caux.get());
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