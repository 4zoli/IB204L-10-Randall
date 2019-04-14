package palya;

public class Palya {
    private int meret;
    private Mezo palya[][];
    private Mezo kezdoPont;
    private Mezo vegPont;



    private Mezo randall;

    public Palya(int meret) {
        if (meret < 6) {
            throw new IllegalArgumentException("Minimum 6*6-os palya lehetseges");
        }
        this.meret = meret;

        palya = new Mezo[meret][meret];
        for (int i = 0; i < meret; i++) {
            for (int j = 0; j < meret; j++) {
                palya[i][j] = new Preri();
            }
        }
    }

/*
    public Palya() {
        this(6); //meghivja a sajat konstruktorat
    }
*/
    public void rajzol() {
        for (int i = 0; i < meret; i++) {
            for (int j = 0; j < meret; j++) {
                Mezo most = palya[i][j];
                if (randall == most) {
                    System.out.print("\\o/");
                } else if (this.vegPont == most) {
                    System.out.print("END");
                } else if (this.kezdoPont == most) {
                    System.out.print("STR");
                } else {
                    System.out.print("[" + most.rajzol() + "]");
                }
            }
            System.out.println();
        }
    }

    public void setMezo(int x, int y, Mezo mezo) {
        this.palya[x][y] = mezo;
    }

/*
    public Mezo getKezdoPont() {
        return kezdoPont;
    }
*/

    public void setKezdoPont(Mezo kezdoPont) {
        if (this.kezdoPont != null) {
            System.out.println("Csak egy indulo allomas lehetseges.");
            return;
        }


        if (!kezdoPont.allomase()) {
            System.out.println("Allomasnak kell lennie.");
            return;
        }
        this.randall = kezdoPont;
        this.kezdoPont = kezdoPont;
        this.kezdoPont.megjelol();
    }

/*
    public Mezo getVegPont() {
        return vegPont;
    }
*/

    public void setVegPont(Mezo vegPont) {
        if (this.vegPont != null) {
            System.out.println("Csak egy vegallomas lehet.");
            return;
        }
//        if (!vegPont.allomase()) {
//            System.out.println("Allomasnak kell lennie.");
//            return;
//        }
        this.vegPont = vegPont;
    }

    public int getX(Mezo mezo) {
        for (int x = 0; x < meret; x++) {
            for (int j = 0; j < meret; j++) {
                if (mezo == palya[x][j]) {
                    return x;
                }
            }
        }
        return -1;
    }

    public int getY(Mezo mezo) {
        for (int x = 0; x < meret; x++) {
            for (int y = 0; y < meret; y++) {
                if (mezo == palya[x][y]) {
                    return y;
                }
            }
        }
        return -1;
    }

    public Mezo getRandall() {
        return randall;
    }

    public void setRandall(Mezo randall) {
        this.randall = randall;
    }

    public Mezo getMezo(int x, int y) {
        return this.palya[x][y];
    }

    public boolean celbaerte() {

        for (int x = 0; x < meret; x++) {
            for (int y = 0; y < meret; y++) {
                if (palya[x][y].allomase() && !palya[x][y].isVoltRajta()) {
                    return false;
                }
            }
        }


        return this.vegPont == this.randall;
    }


    public Mezo getKezdoPont() {
        return kezdoPont;
    }

    public Mezo getVegPont() {
        return vegPont;
    }

    public Mezo[][] getPalya() {
        return palya;
    }


}
