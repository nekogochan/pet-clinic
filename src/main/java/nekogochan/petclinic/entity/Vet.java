package nekogochan.petclinic.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nekogochan.petclinic.core.entity.BaseLongIdEntity;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "VET")
@Entity
public class Vet extends BaseLongIdEntity {

    @NotNull
    @Length(max = 255)
    @Column(name = "NAME", nullable = false)
    String name;
}
