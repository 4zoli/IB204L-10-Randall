package parancs;

import palya.Mezo;
import palya.Palya;
import palya.Preri;
import palya.Viz;

public class Hid implements Parancs {
    private Lepes lepes;

    public Hid(Lepes lepes) {
        this.lepes = lepes;
    }

    @Override
    public void vegrehajt(Palya palya) {
        Mezo aktualis = palya.getRandall();
        int x = palya.getX(aktualis);
        int y = palya.getY(aktualis);
        x += this.lepes.getX();
        y += this.lepes.getY();
        Mezo mezo = palya.getMezo(x, y);
        if ( mezo instanceof Viz)    {
            palya.setMezo(x,y, new Preri());
            lepes.vegrehajt(palya);
        }
        else {
            throw new RuntimeException("Nem jo mezore akartál lépni");
        }
    }
}

