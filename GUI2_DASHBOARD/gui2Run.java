import java.time.LocalDate;
public class gui2Run {
    public static void main(String[] args) throws PracownikisUzytkownik {

        DzialPracownikow d1 = DzialPracownikow.createDzial("Dzial1");
        DzialPracownikow d2 = DzialPracownikow.createDzial("Dzial2");

        Pracownik p1 = new Pracownik("Michal", "Majkel", LocalDate.now(), d1);
        Pracownik p2 = new Pracownik("Michal", "Wasik", LocalDate.now(), d2);
        Pracownik p3 = new Pracownik("Michal", "Kometa", LocalDate.now(), d1);

        Uzytkownik u1 = new Uzytkownik("Tomasz", "Chic", LocalDate.now(), d1, "Tomasz", "123");
        Uzytkownik u2 = new Uzytkownik("Franek", "Cola", LocalDate.now(), d2, "Franek", "qwerty");
        Brygadzista br1 = new Brygadzista("Janusz", "Podloga", LocalDate.now(), d2, "janusz", "123456");
        Brygadzista br2 = new Brygadzista("Tomek", "Krzeslo", LocalDate.now(), d1, "Tomek", "123456");
        Brygadzista br3 = new Brygadzista("Franek", "Oaza", LocalDate.now(), d2, "a", "a");
        Brygadzista br4 = new Brygadzista("Oliwia", "Amazon", LocalDate.now(), d1, "Oliwia", "123456");
        Brygadzista br5 = new Brygadzista("Oliwier", "Daniels", LocalDate.now(), d1, "Oliwier", "123456");

        Brygada brygada1 = new Brygada("Brygada1", br1);
        brygada1.addPracownik(br2);
        brygada1.addPracownik(br3);
        brygada1.addPracownik(p1);
        brygada1.addPracownik(p2);

        Brygada brygada2 = new Brygada("Brygada2", br4);
        brygada2.addPracownik(br3);
        brygada2.addPracownik(br5);
        brygada2.addPracownik(p3);

        Zlecenie zlecenie1 = new Zlecenie(brygada1, true);
        Zlecenie zlecenie2 = new Zlecenie(brygada2, true);

        Praca praca1 = new Praca(Praca.RodzajPracy.DEMONTAZ, 20, "praca1");
        Praca praca2 = new Praca(Praca.RodzajPracy.DEMONTAZ, 20, "praca2");
        Praca praca3 = new Praca(Praca.RodzajPracy.DEMONTAZ, 20, "praca3");
        Praca praca4 = new Praca(Praca.RodzajPracy.DEMONTAZ, 20, "praca4");

        zlecenie1.addPraca(praca1);
        zlecenie1.addPraca(praca2);
        zlecenie2.addPraca(praca3);
        zlecenie2.addPraca(praca4);

        new myFrame();
    }
}