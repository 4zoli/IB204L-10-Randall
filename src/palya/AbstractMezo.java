package palya;

public abstract class AbstractMezo implements Mezo {
    private String jel;
    private boolean allomas = false;
    private boolean voltRajta = false;


    public AbstractMezo(String jel) {
        this.jel = jel;
    }

    @Override
    public String rajzol() {
        if (allomas)    {
            return  "A";
        }
        if (voltRajta)  {
            return "=";
        }
        return jel;
    }

    @Override
    public boolean allomase() {
        return allomas;
    }

    public void setAllomas(boolean allomas) {
        this.allomas = allomas;
    }

    @Override
    public void megjelol() {
        if(voltRajta && !allomas)   {
            throw new RuntimeException("Erre a mezore mar raleptel");
        }
        voltRajta = true;
    }

    public boolean isVoltRajta() {
        return voltRajta;
    }


}
