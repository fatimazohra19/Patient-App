package com.example.demo.commons.jpa.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;
import java.io.Serializable;
import java.util.Objects;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Persistable;
import org.springframework.data.util.ProxyUtils;
import org.springframework.lang.Nullable;

@MappedSuperclass
@SuperBuilder
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public abstract class CustomAbstractPersistable<PK extends Serializable> implements Persistable<PK> {
    @Id
    @GeneratedValue
    @Nullable
    protected PK id;

    @Transient
    public boolean isNew() {
        return this.getId() == null;
    }



    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (this == obj) {
            return true;
        } else if (!this.getClass().equals(ProxyUtils.getUserClass(obj))) {
            return false;
        } else {
            CustomAbstractPersistable<?> that = (CustomAbstractPersistable)obj;
            return this.getId() == null ? false : this.getId().equals(that.getId());

        }
    }

    public int hashCode() {
/*        int hashCode = 17;
     hashCode += this.getId() == null ? 0 : this.getId().hashCode() * 31;*/

        return Objects.hashCode(id);
    }
}
