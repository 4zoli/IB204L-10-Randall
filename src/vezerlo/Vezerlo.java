package vezerlo;

import palya.*;
import parancs.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Vezerlo {
    private List<Parancs> parancsok;
    private Palya palya;

    private int parancsFelSzam;
    private int parancsLeSzam;
    private int parancsBalSzam;
    private int parancsJobbSzam;
    private int parancsDinamitSzam;
    private int parancsHidSzam;
    private int parancsCiklusSzam;

    private Scanner sc = new Scanner(System.in);


    public void indit() {
        System.out.println("1es gomb: Jatek || 2es gomb: Palyakeszites");
        int modValaszto = sc.nextInt();
        if ( modValaszto == 1) {
            start();
        }
        else if ( modValaszto == 2) {
            palyaKeszites();
        }
        else return;
    }

    public void start() {
        jatekLeiras();
        System.out.println("A billentyuzet szamai segitsegevel valassz egy palyat, vagy keszits sajatot!");
        System.out.println("Palyak: 1-6.-ig");
        int szint = sc.nextInt();
        if (szint == 1) {
            ElsoSzint();
        } else if (szint == 2) {
            MasodikSzint();
        } else if (szint == 3) {
            HarmadikSzint();
        } else if (szint == 4) {
            NegyedikSzint();
        } else if (szint == 5) {
            OtodikSzint();
        } else if (szint == 6) {
            HatodikSzint();
        } else return;

            Parancs p;
            while ((p = parancsValaszt()) != null) {
                parancsok.add(p);
            }

            try {
                for (Parancs parancs : parancsok) {
                    parancs.vegrehajt(palya);
                }

                if (!palya.celbaerte()) {
                    throw new RuntimeException("nem ertel a celba");
                }

                System.out.println("Gratulalok, teljesitetted a szinted.");
                palya.rajzol();
                start();

            } catch (Exception ex) {
                System.out.println("Sajnalom, " + ex.getMessage());
                palya.rajzol();
                System.out.println("0. Ujrakezdes, vagy kilepes.");
                int folytatE = sc.nextInt();

                if (folytatE != 0) {
                    return;
                } else start();
            }

            palya.rajzol();
        }


    private void jatekLeiras()  {
        System.out.println("A jatek leirasa.");
        System.out.println("Te vagy Randall (\\o/)");
        System.out.println("Feladatod a sinhalozat(=) kiepitese a kezdo(STR), es a vegallomas kozott.");
        System.out.println("A szint sikeres teljesitesehez azonban lehet hogy tobb allomason(A) is keresztul kell menned");
        System.out.println("Utadat akadalyozzak vizek(~) illetve sziklak(R) is.");
        System.out.println("Ezeket egy hid lerakasaval, vagy dinamittal kikuszobolheted.");
        System.out.println("Hasznalatuk: Miutan kivalasztottad a speci eszkozt, meg kell adnod merre tegye le Randall, erre a mezore ra is fog lepni magatol.");
        System.out.println("Van meg egy specialis modja a robotnak, megpedig a Szorzo!");
        System.out.println("Megfontoltan hasznald, eloszor kivalasztod, majd megadod hogy hanyszor hajtsa vegre, aztan a feladatot. DE");
        System.out.println("Ha a szorzot specialis muvelethez hasznalod, utana meg kell adnod hogy merre menjen");
        System.out.println("Es vegul a legfontosabb, Szorzo muvelet utan MINDIG LE KELL ZARNOD ( 8. ) kulonben osszezavarodik randall.");
    }

    private void parancsRajzol() {
        palya.rajzol();
        lepesRajzol();
        epitoParancsRajzol();
        System.out.println("7. Szorzo inditas: " + parancsCiklusSzam + "db");
        System.out.println("8. Szorzo lezaras ! Fontos, szorzo inditas utan mindig le kell zarnod !");
        System.out.println("0. Lepesek lezarasa, sin lerakasa, eredmeny megtekintese.");
    }

    private void lepesRajzol() {
        System.out.println("1. Bal lepes: " + parancsBalSzam + " db");
        System.out.println("2. Jobb lepes: " + parancsJobbSzam + " db");
        System.out.println("3. Le lepes: " + parancsLeSzam + " db");
        System.out.println("4. Fel lepes: " + parancsFelSzam + " db");
    }

    private void epitoParancsRajzol() {
        System.out.println("5. Hid epites: " + parancsHidSzam + " db");
        System.out.println("6. Dinamit dobas: " + parancsDinamitSzam + " db");
    }

    private Parancs parancsValaszt() {
        parancsRajzol();
        System.out.println("Add meg a lepeseket");
        Parancs valasztottParancs = null;
        int lepes = sc.nextInt();
        switch (lepes) {
            case 1:
                parancsBalSzam--;
                valasztottParancs = new LepesBalra();
                break;
            case 2:
                parancsJobbSzam--;
                valasztottParancs = new LepesJobbra();
                break;
            case 3:
                parancsLeSzam--;
                valasztottParancs = new LepesLe();
                break;
            case 4:
                parancsFelSzam--;
                valasztottParancs = new LepesFel();
                break;
            case 5:
                parancsHidSzam--;
                valasztottParancs = new Hid(lepesParancsValaszt());
                break;
            case 6:
                parancsDinamitSzam--;
                valasztottParancs = new Dinamit(lepesParancsValaszt());
                break;
            case 7:
                parancsCiklusSzam--;
                valasztottParancs = ciklusValaszt();
                break;
        }

        return valasztottParancs;
    }

    private Lepes lepesParancsValaszt() {
        lepesRajzol();
        int lepesIndex = sc.nextInt();
        Lepes p = null;
        switch (lepesIndex) {
            case 1:
                parancsBalSzam--;
                p = new LepesBalra();
                break;
            case 2:
                parancsJobbSzam--;
                p = new LepesJobbra();
                break;
            case 3:
                parancsLeSzam--;
                p = new LepesLe();
                break;
            case 4:
                parancsFelSzam--;
                p = new LepesFel();
                break;
        }

        return p;
    }

    private Parancs ciklusValaszt() {
        List<Parancs> ciklusParancsok = new ArrayList<>();
        Parancs p;

        System.out.println("SZORZO!");

        System.out.println("Ismetles szam:");
        int ismetles = sc.nextInt();

        while ((p = parancsValaszt()) != null) {
            ciklusParancsok.add(p);
        }
        System.out.println("SZORZO VEGE!");

        return new Ciklus(ismetles, ciklusParancsok);
    }

    private void ElsoSzint() {
        System.out.println("Elso Palya");
        palya = new Palya(6);

        Preri kezdoPont = new Preri();
        kezdoPont.setAllomas(true);

        Preri vegPont = new Preri();
        vegPont.setAllomas(true);

        palya.setMezo(0, 0, kezdoPont);
        palya.setMezo(3, 2, vegPont);
        palya.setMezo(0, 4, new Viz());
        palya.setMezo(1, 1, new Szikla());
        palya.setMezo(1, 2, new Szikla());
        palya.setMezo(1, 4, new Viz());
        palya.setMezo(1, 5, new Viz());
        palya.setMezo(2, 1, new Szikla());
        palya.setMezo(2, 2, new Szikla());
        palya.setMezo(2, 4, new Viz());
        palya.setMezo(3, 3, new Szikla());
        palya.setMezo(3, 4, new Szikla());
        palya.setMezo(4, 0, new Viz());
        palya.setMezo(4, 1, new Viz());
        palya.setMezo(5, 1, new Viz());
        palya.setMezo(5, 2, new Viz());

        palya.setKezdoPont(kezdoPont);
        palya.setVegPont(vegPont);

        parancsBalSzam = 1;
        parancsJobbSzam = 3;
        parancsLeSzam = 3;
        parancsDinamitSzam = 1;
        parancsHidSzam = 0;
        parancsCiklusSzam = 2;
        parancsok = new ArrayList<>();
    }

    private void MasodikSzint() {
        System.out.println("Masodik Palya");
        palya = new Palya(6);

        Preri kezdoPont = new Preri();
        kezdoPont.setAllomas(true);

        Preri vegPont = new Preri();
        vegPont.setAllomas(true);

        Preri allomas1 = new Preri();
        allomas1.setAllomas(true);

        palya.setMezo(5, 2, kezdoPont);
        palya.setMezo(0, 5, vegPont);
        palya.setMezo(0, 0, new Szikla());
        palya.setMezo(0, 1, new Szikla());
        palya.setMezo(1, 0, new Szikla());
        palya.setMezo(1, 1, new Szikla());
        palya.setMezo(1, 3, new Viz());
        palya.setMezo(1, 4, new Szikla());
        palya.setMezo(2, 1, allomas1);
        palya.setMezo(3, 2, new Viz());
        palya.setMezo(3, 3, new Viz());
        palya.setMezo(4, 0, new Szikla());
        palya.setMezo(4, 2, new Viz());
        palya.setMezo(4, 3, new Viz());
        palya.setMezo(4, 4, new Viz());
        palya.setMezo(5, 0, new Szikla());
        palya.setMezo(5, 1, new Szikla());
        palya.setMezo(5, 3, new Viz());

        palya.setKezdoPont(kezdoPont);
        palya.setVegPont(vegPont);

        parancsBalSzam = 1;
        parancsJobbSzam = 2;
        parancsLeSzam = 0;
        parancsFelSzam = 3;
        parancsDinamitSzam = 3;
        parancsHidSzam = 2;
        parancsCiklusSzam = 2;
        parancsok = new ArrayList<>();
    }

    private void HarmadikSzint() {
        System.out.println("Harmadik Palya");
        palya = new Palya(6);

        Preri kezdoPont = new Preri();
        kezdoPont.setAllomas(true);

        Preri vegPont = new Preri();
        vegPont.setAllomas(true);

        Preri allomas1 = new Preri();
        allomas1.setAllomas(true);


        palya.setMezo(3, 2, kezdoPont);
        palya.setMezo(4, 2, vegPont);
        palya.setMezo(0, 0, new Szikla());
        palya.setMezo(0, 1, new Szikla());

        palya.setMezo(1, 1, new Szikla());
        palya.setMezo(1, 2, allomas1);
        palya.setMezo(1, 3, new Viz());

        palya.setMezo(1, 5, new Szikla());
        palya.setMezo(2, 1, new Viz());
        palya.setMezo(2, 2, new Viz());
        palya.setMezo(2, 4, new Szikla());
        palya.setMezo(3, 3, new Viz());
        palya.setMezo(3, 4, new Viz());
        palya.setMezo(4, 0, new Viz());
        palya.setMezo(5, 1, new Viz());
        palya.setMezo(5, 2, new Viz());

        palya.setKezdoPont(kezdoPont);
        palya.setVegPont(vegPont);

        parancsBalSzam = 1;
        parancsJobbSzam = 1;
        parancsLeSzam = 3;
        parancsFelSzam = 2;
        parancsDinamitSzam = 1;
        parancsHidSzam = 2;
        parancsCiklusSzam = 0;
        parancsok = new ArrayList<>();
    }
    private void NegyedikSzint() {
        System.out.println("Negyedik Palya");
        palya = new Palya(6);

        Preri kezdoPont = new Preri();
        kezdoPont.setAllomas(true);

        Preri vegPont = new Preri();
        vegPont.setAllomas(true);

        Preri allomas1 = new Preri();
        allomas1.setAllomas(true);

        Preri allomas2 = new Preri();
        allomas2.setAllomas(true);

        palya.setMezo(3, 2, kezdoPont);
        palya.setMezo(4, 2, vegPont);
        palya.setMezo(0, 0, new Szikla());
        palya.setMezo(0, 1, new Szikla());
        palya.setMezo(0, 3, new Szikla());
        palya.setMezo(0, 5, allomas2);
        palya.setMezo(1, 1, new Szikla());
        palya.setMezo(1, 2, allomas1);
        palya.setMezo(1, 3, new Viz());
        palya.setMezo(1, 4, new Szikla());
        palya.setMezo(1, 5, new Szikla());
        palya.setMezo(2, 1, new Viz());
        palya.setMezo(2, 2, new Viz());
        palya.setMezo(2, 4, new Szikla());
        palya.setMezo(2, 5, new Szikla());
        palya.setMezo(3, 0, new Viz());
        palya.setMezo(3, 3, new Viz());
        palya.setMezo(3, 4, new Viz());
        palya.setMezo(4, 0, new Viz());
        palya.setMezo(4, 1, new Viz());
        palya.setMezo(4, 3, new Viz());
        palya.setMezo(4, 4, new Viz());
        palya.setMezo(5, 0, new Viz());
        palya.setMezo(5, 1, new Viz());
        palya.setMezo(5, 2, new Viz());

        palya.setKezdoPont(kezdoPont);
        palya.setVegPont(vegPont);

        parancsBalSzam = 2;
        parancsJobbSzam = 2;
        parancsLeSzam = 4;
        parancsFelSzam = 2;
        parancsDinamitSzam = 3;
        parancsHidSzam = 2;
        parancsCiklusSzam = 3;
        parancsok = new ArrayList<>();
    }
    private void OtodikSzint() {
        System.out.println("Otodik Palya");
        palya = new Palya(7);

        Preri kezdoPont = new Preri();
        kezdoPont.setAllomas(true);

        Preri vegPont = new Preri();
        vegPont.setAllomas(true);

        Preri allomas1 = new Preri();
        allomas1.setAllomas(true);

        palya.setMezo(6, 6, kezdoPont);
        palya.setMezo(0, 0, vegPont);
        palya.setMezo(0, 1, new Viz());
        palya.setMezo(0, 5, new Szikla());
        palya.setMezo(0, 6, new Szikla());
        palya.setMezo(1, 3, new Viz());
        palya.setMezo(1, 5, new Szikla());
        palya.setMezo(1, 6, new Szikla());
        palya.setMezo(2, 0, new Szikla());
        palya.setMezo(2, 1, new Szikla());
        palya.setMezo(2, 2, new Szikla());
        palya.setMezo(3, 0, new Szikla());
        palya.setMezo(3, 2, new Viz());
        palya.setMezo(3, 3, allomas1);
        palya.setMezo(3, 4, new Szikla());
        palya.setMezo(3, 5, new Szikla());
        palya.setMezo(4, 0, new Szikla());
        palya.setMezo(4, 1, new Viz());
        palya.setMezo(4, 2, new Viz());
        palya.setMezo(4, 4, new Szikla());
        palya.setMezo(4, 5, new Szikla());
        palya.setMezo(4, 6, new Szikla());
        palya.setMezo(5, 0, new Szikla());
        palya.setMezo(5, 1, new Viz());
        palya.setMezo(5, 2, new Viz());
        palya.setMezo(5, 3, new Viz());
        palya.setMezo(5, 5, new Szikla());
        palya.setMezo(5, 6, new Szikla());
        palya.setMezo(6, 0, new Szikla());
        palya.setMezo(6, 3, new Szikla());



        palya.setKezdoPont(kezdoPont);
        palya.setVegPont(vegPont);

        parancsBalSzam = 3;
        parancsJobbSzam = 0;
        parancsLeSzam = 0;
        parancsDinamitSzam = 1;
        parancsHidSzam = 2;
        parancsCiklusSzam = 3;
        parancsFelSzam = 4;
        parancsok = new ArrayList<>();
    }
    private void HatodikSzint() {
        System.out.println("Hatodik Palya");
        palya = new Palya(10);

        Preri kezdoPont = new Preri();
        kezdoPont.setAllomas(true);

        Preri vegPont = new Preri();
        vegPont.setAllomas(true);

        Preri allomas1 = new Preri();
        allomas1.setAllomas(true);
        Preri allomas2 = new Preri();
        allomas2.setAllomas(true);
        Preri allomas3 = new Preri();
        allomas3.setAllomas(true);
        Preri allomas4 = new Preri();
        allomas4.setAllomas(true);
        Preri allomas5 = new Preri();
        allomas5.setAllomas(true);
        Preri allomas6 = new Preri();
        allomas6.setAllomas(true);
        Preri allomas7 = new Preri();
        allomas7.setAllomas(true);
        Preri allomas8 = new Preri();
        allomas8.setAllomas(true);
        Preri allomas9 = new Preri();
        allomas9.setAllomas(true);
        Preri allomas10 = new Preri();
        allomas10.setAllomas(true);

        palya.setMezo(8, 1, kezdoPont);
        palya.setMezo(7, 1, vegPont);
        palya.setMezo(0, 7, allomas1);
        palya.setMezo(1, 0, allomas2);
        palya.setMezo(3, 9, allomas3);
        palya.setMezo(4, 0, allomas4);
        palya.setMezo(4, 3, allomas5);
        palya.setMezo(6, 1, allomas6);
        palya.setMezo(6, 3, allomas7);
        palya.setMezo(0, 2, allomas8);
        palya.setMezo(7, 6, allomas9);
        palya.setMezo(8, 3, allomas10);
        palya.setMezo(0, 0, new Szikla());
        palya.setMezo(0, 1, new Szikla());
        palya.setMezo(0, 2, new Szikla());
        palya.setMezo(0, 6, new Szikla());
        palya.setMezo(1, 4, new Viz());
        palya.setMezo(1, 2, new Viz());
        palya.setMezo(1, 8, new Viz());
        palya.setMezo(2, 1, new Viz());
        palya.setMezo(2, 2, new Viz());
        palya.setMezo(2, 3, new Viz());
        palya.setMezo(2, 4, new Viz());
        palya.setMezo(2, 5, new Viz());
        palya.setMezo(2, 6, new Viz());
        palya.setMezo(2, 7, new Viz());
        palya.setMezo(2, 8, new Viz());
        palya.setMezo(3, 2, new Viz());
        palya.setMezo(3, 3, new Viz());
        palya.setMezo(2, 3, new Viz());
        palya.setMezo(3, 4, new Szikla());
        palya.setMezo(3, 5, new Szikla());
        palya.setMezo(3, 6, new Szikla());
        palya.setMezo(3, 7, new Szikla());
        palya.setMezo(3, 8, new Szikla());
        palya.setMezo(4, 4, new Szikla());
        palya.setMezo(4, 5, new Szikla());
        palya.setMezo(4, 6, new Szikla());
        palya.setMezo(4, 7, new Szikla());
        palya.setMezo(4, 8, new Szikla());
        palya.setMezo(5, 0, new Szikla());
        palya.setMezo(5, 1, new Szikla());
        palya.setMezo(5, 2, new Szikla());
        palya.setMezo(5, 4, new Szikla());
        palya.setMezo(5, 5, new Szikla());
        palya.setMezo(5, 6, new Szikla());
        palya.setMezo(5, 7, new Szikla());
        palya.setMezo(5, 8, new Szikla());
        palya.setMezo(6, 0, new Szikla());
        palya.setMezo(6, 4, new Szikla());
        palya.setMezo(6, 5, new Szikla());
        palya.setMezo(7, 9, new Viz());
        palya.setMezo(8, 8, new Viz());
        palya.setMezo(8, 9, new Viz());
        palya.setMezo(9, 2, new Viz());


        palya.setKezdoPont(kezdoPont);
        palya.setVegPont(vegPont);

        parancsBalSzam = 7;
        parancsJobbSzam = 3;
        parancsLeSzam = 4;
        parancsDinamitSzam = 0;
        parancsHidSzam = 2;
        parancsCiklusSzam = 11;
        parancsFelSzam = 2;
        parancsok = new ArrayList<>();
    }

    private void palyaKeszites()    {
        System.out.println("Udvozol a palyakeszitomod.");
        System.out.println("Eloszoris, hanyszor hanyas palyat szeretnel kesziteni? ");
        int keszitoMeret = sc.nextInt();
        palya = new Palya(keszitoMeret);
        palya.rajzol();

        System.out.println("Add meg a kezdopont koordinatait: ");
        int kezdoX = sc.nextInt();
        int kezdoY = sc.nextInt();
        Preri kezdoPont = new Preri();
        kezdoPont.setAllomas(true);
        palya.setMezo(kezdoX, kezdoY, kezdoPont);

        System.out.println("Add meg a vegallomas koordinatait: ");
        Preri vegPont = new Preri();
        vegPont.setAllomas(true);
        int vegX = sc.nextInt();
        int vegY = sc.nextInt();
        palya.setMezo(vegX,vegY,vegPont);
        palya.rajzol();

        int folytat = 1;
        while (folytat != 0){
            System.out.println("Mit szeretnel letenni? \n1. Viz 2. Szikla 3. Nincs mas");

            int mit = sc.nextInt();
            int xKoor;
            int yKoor;
            if ( mit == 1 )  {
                    System.out.println("Kerem a koordinatait: ");
                    xKoor = sc.nextInt();
                    yKoor = sc.nextInt();
                    palya.setMezo(xKoor, yKoor, new Viz());
                }
                if ( mit == 2 ) {
                    System.out.println("Kerem a koordinatait: ");
                    xKoor = sc.nextInt();
                    yKoor = sc.nextInt();
                    palya.setMezo(xKoor, yKoor, new Szikla());
                }

            palya.rajzol();
            System.out.println("Ha folytatod nyomj (1)-est, ha vegeztel nyomj (0)-st.");
            folytat = sc.nextInt();

            parancsHidSzam = 10;
            parancsDinamitSzam = 10;
            parancsLeSzam = 10;
            parancsFelSzam = 10;
            parancsJobbSzam = 10;
            parancsBalSzam = 10;
            parancsCiklusSzam = 10;

        }

    }


}
