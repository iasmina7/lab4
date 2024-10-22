package problemaechipament;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

enum StareEchipament {
    ACHIZITIONAT, EXPUS, VANDUT
}

class Echipament {

    String denumire;
    int nrInv;
    double pret;
    StareEchipament stare;

    public Echipament(String denumire, int nrInv, double pret, StareEchipament stare) {
        this.denumire = denumire;
        this.nrInv = nrInv;
        this.pret = pret;
        this.stare = stare;
    }

    @Override
    public String toString() {
        return denumire + " (nrInv: " + nrInv + ", pret: " + pret + ", stare: " + stare + ")";
    }
}

class Imprimanta extends Echipament {
    int ppm;
    String rezolutie;
    int paginiCartus;
    String modTiparire;

    public Imprimanta(String denumire, int nrInv, double pret, StareEchipament stare, int ppm, String rezolutie, int paginiCartus, String modTiparire) {
        super(denumire, nrInv, pret, stare);
        this.ppm = ppm;
        this.rezolutie = rezolutie;
        this.paginiCartus = paginiCartus;
        this.modTiparire = modTiparire;
    }

    @Override
    public String toString() {
        return super.toString() + ", ppm: " + ppm + ", rezolutie: " + rezolutie + ", pagini/cartus: " + paginiCartus + ", mod tiparire: " + modTiparire;
    }
}

class Copiator extends Echipament {
    int vitezaCopiere;
    String formatHartie;

    public Copiator(String denumire, int nrInv, double pret, StareEchipament stare, int vitezaCopiere, String formatHartie) {
        super(denumire, nrInv, pret, stare);
        this.vitezaCopiere = vitezaCopiere;
        this.formatHartie = formatHartie;
    }

    @Override
    public String toString() {
        return super.toString() + ", viteza copiere: " + vitezaCopiere + " pag/min, format hartie: " + formatHartie;
    }
}

class SistemCalcul extends Echipament {
    String monitor;
    double vitezaProcesor;
    int spatiuDisc;
    String sistemOperare;

    public SistemCalcul(String denumire, int nrInv, double pret, StareEchipament stare, String monitor, double vitezaProcesor, int spatiuDisc, String sistemOperare) {
        super(denumire, nrInv, pret, stare);
        this.monitor = monitor;
        this.vitezaProcesor = vitezaProcesor;
        this.spatiuDisc = spatiuDisc;
        this.sistemOperare = sistemOperare;
    }

    @Override
    public String toString() {
        return super.toString() + ", monitor: " + monitor + ", viteza procesor: " + vitezaProcesor + " GHz, spatiu disc: " + spatiuDisc + " GB, sistem operare: " + sistemOperare;
    }
}

class Magazin {
    static List<Echipament> echipamente = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            citesteEchipamenteDinFisier("D:\\lucru_java_intellij\\lab4iasmina\\src\\problemaechipament\\echipamente.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Fisierul nu a fost gasit.");
            return;
        }

        boolean running = true;
        while (running) {
            System.out.println("1. Afișare toate echipamentele");
            System.out.println("2. Afișare imprimante");
            System.out.println("3. Afișare copiatoare");
            System.out.println("4. Afișare sisteme de calcul");
            System.out.println("5. Modificare stare echipament");
            System.out.println("6. Ieșire");

            int optiune = scanner.nextInt();

            switch (optiune) {
                case 1:
                    afisareToateEchipamentele();
                    break;
                case 2:
                    afisareImprimante();
                    break;
                case 3:
                    afisareCopiatoare();
                    break;
                case 4:
                    afisareSistemeCalcul();
                    break;
                case 5:
                    modificareStare();
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Opțiune invalidă!");
            }
        }
    }

    static void citesteEchipamenteDinFisier(String filePath) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(new File(filePath));

        while (fileScanner.hasNextLine()) {
            String linie = fileScanner.nextLine();
            String[] campuri = linie.split(";");

            String denumire = campuri[0];
            int nrInv = Integer.parseInt(campuri[1]);
            double pret = Double.parseDouble(campuri[2]);
            StareEchipament stare = StareEchipament.valueOf(campuri[4].toUpperCase());

            switch (campuri[5].toLowerCase()) {
                case "imprimanta":
                    int ppm = Integer.parseInt(campuri[6]);
                    String rezolutie = campuri[7];
                    int paginiCartus = Integer.parseInt(campuri[8]);
                    String modTiparire = campuri[9];
                    echipamente.add(new Imprimanta(denumire, nrInv, pret, stare, ppm, rezolutie, paginiCartus, modTiparire));
                    break;
                case "copiator":
                    int vitezaCopiere = Integer.parseInt(campuri[6]);
                    String formatHartie = campuri[7];
                    echipamente.add(new Copiator(denumire, nrInv, pret, stare, vitezaCopiere, formatHartie));
                    break;
                case "sistem de calcul":
                    String monitor = campuri[6];
                    double vitezaProcesor = Double.parseDouble(campuri[7]);
                    int spatiuDisc = Integer.parseInt(campuri[8]);
                    String sistemOperare = campuri[9];
                    echipamente.add(new SistemCalcul(denumire, nrInv, pret, stare, monitor, vitezaProcesor, spatiuDisc, sistemOperare));
                    break;
                default:
                    System.out.println("Tip echipament necunoscut: " + campuri[5]);
            }
        }
        fileScanner.close();
    }

    static void afisareToateEchipamentele() {
        for (Echipament e : echipamente) {
            System.out.println(e);
        }
    }

    static void afisareImprimante() {
        for (Echipament e : echipamente) {
            if (e instanceof Imprimanta) {
                System.out.println(e);
            }
        }
    }

    static void afisareCopiatoare() {
        for (Echipament e : echipamente) {
            if (e instanceof Copiator) {
                System.out.println(e);
            }
        }
    }

    static void afisareSistemeCalcul() {
        for (Echipament e : echipamente) {
            if (e instanceof SistemCalcul) {
                System.out.println(e);
            }
        }
    }

    static void modificareStare() {
        System.out.println("Introdu denumirea echipamentului:");
        String denumire =  scanner.nextLine();
        for (Echipament e : echipamente) {
            if (e.denumire.equals(denumire)) {
                System.out.println("Alege noua stare: 1. ACHIZITIONAT 2. EXPUS 3. VANDUT");
                int optiune = scanner.nextInt();
                switch (optiune) {
                    case 1:
                        e.stare = StareEchipament.ACHIZITIONAT;
                        break;
                    case 2:
                        e.stare = StareEchipament.EXPUS;
                        break;
                    case 3:
                        e.stare = StareEchipament.VANDUT;
                        break;
                    default:
                        System.out.println("Opțiune invalidă.");
                        return;
                }
                System.out.println("Starea echipamentului a fost modificată.");
                return;
            }
        }
        System.out.println("Echipament nu a fost găsit.");
    }
}
