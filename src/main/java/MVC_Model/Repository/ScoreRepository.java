package MVC_Model.Repository;


import MVC_Model.Model.Score;
import MVC_Model.Repository.CRUD.ScoreCRUDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ScoreRepository
{
    @Autowired
    private ScoreCRUDRepository scoreCRUDRepository;

    public List<Score> GetAll(){return (List<Score>) scoreCRUDRepository.findAll();}

    public Optional<Score> getCalificacion(int id) {return scoreCRUDRepository.findById(id);}

    public Score save(Score c) {return scoreCRUDRepository.save(c);}

    public void delete(Score c) {
        scoreCRUDRepository.delete(c);}
}
