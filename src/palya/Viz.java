package palya;

public class Viz extends AbstractMezo {


    public Viz() {
        super("~");

    }

    @Override
    public void setAllomas(boolean allomas) {
        throw new RuntimeException("A viz nem lehet allomas");
    }
}
