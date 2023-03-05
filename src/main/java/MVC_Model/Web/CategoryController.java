package MVC_Model.Web;


import MVC_Model.Service.CategoryService;
import MVC_Model.Model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Category")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class CategoryController
{
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/all")
    public List<Category> GetCategorias() {return categoryService.GetAll();}

    @GetMapping("/{id}")
    public Optional<Category> GetCategoria(@PathVariable("id")int id)
    {
        return categoryService.getCategoria(id);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Category save(@RequestBody Category c)
    {
        return categoryService.save(c);

    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Category update(@RequestBody Category c)
    {
        return categoryService.update(c);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id")int id)
    {
        return categoryService.deleteCategory(id);
    }
}
