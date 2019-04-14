package parancs;

import palya.Mezo;
import palya.Palya;
import palya.Preri;

public abstract class Lepes implements Parancs {
    private int x;
    private int y;

    public Lepes(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void vegrehajt(Palya palya) {
        Mezo aktualis = palya.getRandall();
        int x = palya.getX(aktualis);
        int y = palya.getY(aktualis);
        x += this.x;
        y += this.y;
        Mezo mezo = palya.getMezo(x, y);
        if ( mezo instanceof Preri)    {
            mezo.megjelol();
            palya.setRandall(mezo);
        }
        else {
            throw new RuntimeException("Nem jo mezore akartál lépni");
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
