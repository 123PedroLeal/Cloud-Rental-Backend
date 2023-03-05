package MVC_Model.Service;


import MVC_Model.Model.Category;
import MVC_Model.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService
{
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> GetAll()
    {
        return categoryRepository.GetAll();
    }

    public Optional<Category> getCategoria(int id)
    {
        return categoryRepository.getCategoria(id);
    }

    public Category save(Category c)
    {
        if (c.getId()==null)
        {
            return categoryRepository.save(c);
        }
        else
        {
            Optional<Category> caux= categoryRepository.getCategoria(c.getId());
            if(!caux.isPresent())
            {
                return categoryRepository.save(c);
            }
            else
            {
                return c;
            }
        }
    }

    public boolean deleteCategory(int id)
    {
        Boolean c = getCategoria(id).map(Categoria-> {
            categoryRepository.delete(Categoria);
            return true;
        }).orElse(false);
        return c;
    }

    public Category update(Category c)
    {
        if (c.getId() != null)
        {
            Optional<Category> caux = categoryRepository.getCategoria(c.getId());
            if (caux.isPresent())
            {
                if (c.getName()!=null)
                {
                    caux.get().setName(c.getName());
                }
                if (c.getDescription()!=null)
                {
                    caux.get().setDescription(c.getDescription());
                }
                if (c.getClouds()!=null)
                {
                    caux.get().setClouds(c.getClouds());
                }

                categoryRepository.save(caux.get());
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
