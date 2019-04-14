package parancs;

import palya.Palya;

import java.util.List;

public class Ciklus implements Parancs {
    private int ismetles;
    private List<Parancs>  parancsok;

    public Ciklus(int ismetles, List<Parancs> parancsok) {
        this.ismetles = ismetles;
        this.parancsok = parancsok;
    }

    @Override
    public void vegrehajt(Palya palya) {
        for ( int i = 0; i < ismetles; i++) {
            for ( Parancs p : parancsok)    {
                p.vegrehajt(palya);
            }
        }
    }



}
