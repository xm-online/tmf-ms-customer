package com.icthh.xm.ms.mstemplate.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A ExampleEntitySecond.
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "example_entity_second")
@ToString
public class ExampleEntitySecond implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "exampleEntitySecond")
    @JsonIgnoreProperties(value = { "exampleEntitySecond" }, allowSetters = true)
    private Set<ExampleEntityFirst> exampleEntityFirsts = new HashSet<>();

    public ExampleEntitySecond addExampleEntityFirst(ExampleEntityFirst exampleEntityFirst) {
        this.exampleEntityFirsts.add(exampleEntityFirst);
        exampleEntityFirst.setExampleEntitySecond(this);
        return this;
    }

    public ExampleEntitySecond removeExampleEntityFirst(ExampleEntityFirst exampleEntityFirst) {
        this.exampleEntityFirsts.remove(exampleEntityFirst);
        exampleEntityFirst.setExampleEntitySecond(null);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ExampleEntitySecond)) {
            return false;
        }
        return id != null && id.equals(((ExampleEntitySecond) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

}
