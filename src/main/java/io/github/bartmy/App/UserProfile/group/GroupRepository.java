package io.github.bartmy.App.UserProfile.group;

import io.github.bartmy.App.UserProfile.user.User;
import io.github.bartmy.App.util.HibernateUtil;

import java.util.List;
import java.util.Optional;

public class GroupRepository {

    public List<Group> findAll(){
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();

        var result = session.createQuery("from Group", Group.class).list();

        transaction.commit();
        session.close();
        return result;
    }
    public Optional<Group> findById(Integer id){
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();

        var result = Optional.ofNullable(session.get(Group.class, id));

        transaction.commit();
        session.close();
        return result;
    }
}
