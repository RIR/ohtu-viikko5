package ohtu.intjoukkosovellus;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/*
Luokassa mielestäni vieläkin paljon koodihajua, mutta tein refaktorointia
mielestäni tehtävänannon mukaan, enkä puuttunut muihin luokkiin. Metodeja on nyt 
luokassa todella paljon. Koodin sisennys on Netbeansin automaattista sisennystä
enkä ole sitä tiivistänyt "säästääkseeni" rivejä. En havainnut miksi lisää ja poista metodit ovat 
booleaneja,mutta en viitsinyt muuttaa palautustyyppejä, kun en tiennyt saiko niin tehdä. 

Taulukkometodit irtoaisivat varmaankin suht siististi omaan luokkaansa, mutta taas en ollut oikein
varma kuuluuko tehtävänantoon.
 */
public class IntJoukko {

    public final static int OLETUSKAPASITEETTI = 5, OLETUSKASVATUS = 5;

    private int kasvatuskoko;
    private int[] lukujoukko;
    private int alkioidenLkm;

    public IntJoukko() {
        this(OLETUSKAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        this(kapasiteetti, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        tarkastaKapasiteetti(kapasiteetti);
        tarkastaKasvatuskoko(kasvatuskoko);
        lukujoukko = new int[kapasiteetti];
        this.kasvatuskoko = kasvatuskoko;
    }

    private void tarkastaKasvatuskoko(int kasvatuskoko1) throws IndexOutOfBoundsException {
        if (kasvatuskoko1 < 0) {
            throw new IndexOutOfBoundsException("kasvatuskoko ei voi olla negatiivinen");//heitin vaan jotain :D
        }
    }

    private void tarkastaKapasiteetti(int kapasiteetti) throws IndexOutOfBoundsException {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasiteetti ei voi olla negatiivinen");//heitin vaan jotain :D
        }
    }

    public boolean lisaaLuku(int luku) {
        if (!kuuluuLukujoukkoon(luku)) {
            lisaaAlkio(luku);
            if (taulukkoTaysi()) {
                kasvataTaulukkoa();
            }
            return true;
        }
        return false;
    }

    private void lisaaAlkio(int luku) {
        lukujoukko[alkioidenLkm] = luku;
        alkioidenLkm++;
    }

    private boolean taulukkoTaysi() {
        return alkioidenLkm == lukujoukko.length;
    }

    private void kasvataTaulukkoa() {
        int[] vanhaTaulukko = new int[lukujoukko.length];
        kopioiTaulukko(lukujoukko, vanhaTaulukko);
        lukujoukko = new int[alkioidenLkm + kasvatuskoko];
        kopioiTaulukko(vanhaTaulukko, lukujoukko);
    }

    public boolean kuuluuLukujoukkoon(int luku) {
        return IntStream.of(lukujoukko).anyMatch(x -> x == luku);
    }

    private int haeIndeksi(int luku) {
        return Arrays.stream(lukujoukko).boxed().collect(Collectors.toList()).indexOf(luku);
    }

    public boolean poista(int luku) {
        if (kuuluuLukujoukkoon(luku)) {
            int poistettava = haeIndeksi(luku);
            poistaAlkio(poistettava);         
            return true;
        }
        return false;
    }

    private void poistaAlkio(int alkaen) {        
        for (int i = alkaen; i < alkioidenLkm; i++) {
            lukujoukko[i] = lukujoukko[i + 1];
        }
        alkioidenLkm--;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        System.arraycopy(vanha, 0, uusi, 0, vanha.length);
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        return IntStream.of(lukujoukko)
                .boxed()
                .filter(i -> i != 0)
                .map(String::valueOf)
                .collect(Collectors.joining(", ", "{", "}"));
    }

    public int[] getLukujoukko() {
        int[] taulu = new int[alkioidenLkm];
        System.arraycopy(lukujoukko, 0, taulu, 0, taulu.length);
        return taulu;
    }

    public void lisaaLuvut(IntJoukko lisattavat) {
        IntStream.of(lisattavat.getLukujoukko()).boxed().forEach(i -> lisaaLuku(i));
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko yhdiste = new IntJoukko();
        yhdiste.lisaaLuvut(a);
        yhdiste.lisaaLuvut(b);
        return yhdiste;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko leikkaus = new IntJoukko();

        IntStream.of(a.getLukujoukko()).boxed().forEach((Integer i) -> {
            if (b.kuuluuLukujoukkoon(i)) {
                leikkaus.lisaaLuku(i);
            }
        });
        return leikkaus;
    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko erotus = new IntJoukko();

        IntStream.of(a.getLukujoukko()).boxed().forEach((Integer i) -> {
            if (!b.kuuluuLukujoukkoon(i)) {
                erotus.lisaaLuku(i);
            }
        });

        return erotus;
    }
}
