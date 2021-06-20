package web.dao;


import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.User;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class UserDaoHibernate implements UserDAO {
    private EntityManager entityManager ;
    private final Logger logger = Logger.getLogger("UserDaoHibernateLogger");

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User getUserById(long id) throws HibernateException {
        return entityManager.find(User.class, id);
    }

    @Override
    public void insertUser(User user) throws HibernateException {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            entityManager.persist(user);
            transaction.commit();
        } catch (Exception e) {
            logger.warning("Context : insertUser Problem : entityManager Transaction error");
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (Exception ex) {
                /*NOP*/
            }
        }
    }

    @Override
    public void updateUser(User user) throws HibernateException {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            entityManager.merge(user);
            transaction.commit();
        } catch (Exception e) {
            logger.warning("Context : updateUser Problem : EntityManger Transaction error");
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (Exception ex) {
                /*NOP*/
            }
        }
    }

    @Override
    public void deleteUser(long id) throws HibernateException {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            entityManager.remove(entityManager.find(User.class, id));
            transaction.commit();
        } catch (Exception e) {
            logger.warning("Context : deleteUser Problem : EntityManager Transaction error");
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (Exception ex) {
                /*NOP*/
            }
        }
    }


    @Override
    public List<User> getUsersList() throws HibernateException {
        return entityManager.createQuery("select e from User e", User.class).getResultList();
    }
}
//
//    public List<User> getUsersList() throws HibernateException {
//        EntityTransaction transaction = null;
//        List<User> users = new ArrayList<>();
//        String sql = "FROM " + User.class.getSimpleName();
//        try {
//            transaction = entityManager.getTransaction();
//            Query query = entityManager.createQuery(sql);
//            users = query.getResultList();
//            transaction.commit();
//        } catch (Exception e) {
//            try {
//                logger.warning("Context : getUserList Problem : session getAll error");
//                if (transaction != null) {
//                    transaction.rollback();
//                }
//            } catch (Exception ex) {
//                /*NOP*/
//            }
//        }
//        return users;
//    }