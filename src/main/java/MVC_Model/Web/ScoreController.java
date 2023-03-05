package MVC_Model.Web;


import MVC_Model.Service.ScoreService;
import MVC_Model.Model.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Score")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class ScoreController
{
    @Autowired
    private ScoreService scoreService;

    @GetMapping("/all")
    public List<Score> GetCalificaciones() {return scoreService.GetAll();}

    @GetMapping("/{id}")
    public Optional<Score> GetCalificacion(@PathVariable("id")int id){return scoreService.getCalificacion(id);}

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Score save(@RequestBody Score c){return scoreService.save(c);}

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Score update(@RequestBody Score c)
    {
        return scoreService.update(c);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id")int id) {return scoreService.deleteScore(id);}
}
