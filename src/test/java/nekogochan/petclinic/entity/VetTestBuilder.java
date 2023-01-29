package nekogochan.petclinic.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;
import nekogochan.petclinic.testutil.TestBuilder;

@AllArgsConstructor
@NoArgsConstructor(staticName = "aVet")
@With
public class VetTestBuilder implements TestBuilder<Vet> {
    private String name = "name";

    @Override
    public Vet build() {
        var vet = new Vet();
        vet.setName(name);
        return vet;
    }
}
