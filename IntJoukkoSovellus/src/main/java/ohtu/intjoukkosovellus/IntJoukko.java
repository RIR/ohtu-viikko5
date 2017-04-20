package ohtu.intjoukkosovellus;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
Taulukkometodit irtoaisivat varmaankin suht siististi omaan luokkaansa, mutta taaskaan en ollut oikein
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
        if (kasvatuskoko >= 0 && kapasiteetti >= 0) {
            this.kasvatuskoko = kasvatuskoko;
            lukujoukko = new int[kapasiteetti];
        }
    }

    public boolean lisaa(int luku) {
        if (!kuuluuLukujoukkoon(luku)) {
            lisaaAlkio(luku);
            return true;
        }
        return false;
    }

    private void lisaaAlkio(int luku) {
        lukujoukko[alkioidenLkm] = luku;
        alkioidenLkm++;

        if (taulukkoTaysi()) {
            kasvataTaulukkoa();
        }
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
            poistaAlkio(haeIndeksi(luku));
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

    public int[] getLukujoukko() {
        int[] taulu = new int[alkioidenLkm];
        System.arraycopy(lukujoukko, 0, taulu, 0, taulu.length);
        return taulu;
    }

    public void lisaaLuvut(IntJoukko lisattavat) {
        IntStream.of(lisattavat.getLukujoukko()).boxed().forEach(i -> lisaa(i));
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko yhdiste = new IntJoukko();
        yhdiste.lisaaLuvut(a);
        yhdiste.lisaaLuvut(b);
        return yhdiste;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko leikkaus = new IntJoukko();
        Arrays.stream(a.getLukujoukko()).boxed().filter(i -> b.kuuluuLukujoukkoon(i)).forEach(i-> leikkaus.lisaa(i));
        return leikkaus;
    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko erotus = new IntJoukko();
        Arrays.stream(a.getLukujoukko()).boxed().filter(i -> !b.kuuluuLukujoukkoon(i)).forEach(i-> erotus.lisaa(i));
        return erotus;
    }

    @Override
    public String toString() {
        return IntStream.of(lukujoukko)
                .boxed()
                .filter(i -> i != 0)
                .map(String::valueOf)
                .collect(Collectors.joining(", ", "{", "}"));
    }
}
