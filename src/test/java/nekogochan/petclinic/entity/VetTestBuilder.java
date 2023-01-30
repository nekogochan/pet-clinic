package nekogochan.petclinic.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;
import nekogochan.petclinic.testutil.TestBuilder;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@With
public class VetTestBuilder implements TestBuilder<Vet> {
    private String name = "name";

    public static VetTestBuilder aVet() {
        return new VetTestBuilder();
    }

    @Override
    public Vet build() {
        var vet = new Vet();
        vet.setName(name);
        return vet;
    }
}
