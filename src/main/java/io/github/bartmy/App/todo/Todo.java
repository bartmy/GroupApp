package io.github.bartmy.App.todo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.*;

@Getter
@Setter

@Entity
@Table(name = "todos")
class Todo {

    @Id
    @GeneratedValue(generator="inc")
    @GenericGenerator(name="inc", strategy = "increment")
    private Integer id;
    private String text;
    private boolean done;

    /**
     * Hibernate (JPA) needs it.
     */
    @SuppressWarnings("unused")
    Todo() {
    }
}
