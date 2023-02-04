package nekogochan.petclinic.entity;

import nekogochan.petclinic.testutil.TestBuilder;

public class VetTestBuilder implements TestBuilder<Vet> {
    private String name = "name";

    private VetTestBuilder() {
    }

    public VetTestBuilder(VetTestBuilder that) {
        this.name = that.name;
    }

    public VetTestBuilder name(String name) {
        var that = new VetTestBuilder(this);
        that.name = name;
        return that;
    }

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
