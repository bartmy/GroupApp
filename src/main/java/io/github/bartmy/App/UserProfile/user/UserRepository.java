package io.github.bartmy.App.UserProfile.user;

import io.github.bartmy.App.lang.Lang;
import io.github.bartmy.App.util.HibernateUtil;

import java.util.List;
import java.util.Optional;

public class UserRepository {

    public List<User> findAll(){
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();

        var result = session.createQuery("from User", User.class).list();

        transaction.commit();
        session.close();
        return result;
    }
    public Optional<User> findById(Integer id){
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();

        var result = Optional.ofNullable(session.get(User.class, id));

        transaction.commit();
        session.close();
        return result;
    }
}
