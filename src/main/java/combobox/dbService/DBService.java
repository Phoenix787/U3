package combobox.dbService;

import combobox.datasets.Category;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.Set;

/**
 * Created by Сергеева on 08.04.2016.
 */
public class DBService {
    private final static String hibernate_show_sql = "true";
    private final static String hibernate_hbm2ddl_auto = "update";

    private final SessionFactory sessionFactory;

    public DBService(){
        Configuration configuration = getH2Configuration();
        sessionFactory = createSessionFactory(configuration);
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    private Configuration getH2Configuration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Category.class);
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:h2:./h2db");
        configuration.setProperty("hibernate.connection.username", "tully");
        configuration.setProperty("hibernate.connection.password", "tully");
        configuration.setProperty("hibernate.show_sql", hibernate_show_sql);
        configuration.setProperty("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);
        return configuration;
    }

    public Category get(long id) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            CategoryDAO dao = new CategoryDAO(session);
            Category result = dao.getCategory(id);
            transaction.commit();
            session.close();
            return result;
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }

    public long addCategory(Category category) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            CategoryDAO dao = new CategoryDAO(session);
            long id = dao.insert(category);
            transaction.commit();
            session.close();
            return id;

        } catch (HibernateException e) {

            throw new DBException(e);
        }
    }

    public Set<Category> getCategories() throws DBException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            CategoryDAO dao = new CategoryDAO(session);
            Set<Category> result = dao.getCategories();
            transaction.commit();
            session.close();
            return result;
        } catch (HibernateException e) {
            throw new DBException(e);
        }

    }


}
