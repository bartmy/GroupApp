package io.github.bartmy.App.lang;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import lombok.*;

@Getter
@Setter

@Entity
@Table(name = "languages")
public class Lang {

    @Id
    @GeneratedValue(generator="inc")
    @GenericGenerator(name="inc", strategy = "increment")
    private Integer id;
    private String welcomeMsg;
    private String code;

    /**
     * Hibernate (JPA) needs it.
     */
    @SuppressWarnings("unused")
    Lang() {
    }

    public Lang(Integer id, String welcomeMsg, String code) {
        this.id = id;
        this.welcomeMsg = welcomeMsg;
        this.code = code;
    }
}
