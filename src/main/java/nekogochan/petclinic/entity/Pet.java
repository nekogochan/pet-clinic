package nekogochan.petclinic.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nekogochan.petclinic.core.entity.BaseLongIdEntity;
import org.hibernate.validator.constraints.Length;

@Setter
@Getter
@NoArgsConstructor
@Table(name = "PET")
@Entity
public class Pet extends BaseLongIdEntity {

    @NotNull
    @Length(max = 255)
    @Column(name = "NAME", nullable = false)
    String name;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "PET_OWNER_ID", nullable = false)
    PetOwner petOwner;
}
