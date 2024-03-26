package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user, Car car) {

        sessionFactory.getCurrentSession().save(user);

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public void getUserByCar(String carModel, int carSeries) {
        String HQL = "SELECT c.user FROM Car c WHERE c.model=:model AND c.series=:series";
        User user = sessionFactory.getCurrentSession().createQuery(HQL, User.class)
                .setParameter("model", carModel)
                .setParameter("series", carSeries).uniqueResult();
        if (user == null) {
            System.out.println("Пользователь с машиной: " + carModel + " серии: " + carSeries + " не найден");
        }
        System.out.println(user);
    }


}
