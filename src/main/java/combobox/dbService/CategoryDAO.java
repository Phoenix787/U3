package combobox.dbService;

import combobox.datasets.Category;
import org.hibernate.Session;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Сергеева on 08.04.2016.
 */
class CategoryDAO {
    private Session session;
    CategoryDAO(Session session) {
        this.session = session;
    }

    Category getCategory(long id){
        return (Category) session.load(Category.class, id);
    }

    long insert(Category category) {
        return (long) session.save(category);
    }

    Set<Category> getCategories() {
        List<Category> list = session.createQuery("from Category order by name").list();
        return new HashSet<>(list);
    }
}
