package palya;

public class Szikla extends AbstractMezo {


    public Szikla() {
        super("R");
    }

    @Override
    public void setAllomas(boolean allomas) {
        throw new RuntimeException("A Szikla nem lehet allomas");
    }
}
