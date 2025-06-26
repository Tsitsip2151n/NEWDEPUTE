package main;

import java.util.*;
import niveau.*;
import vivant.*;
import interface_ui.*;

public class Main {
    public static void main(String[] args) {
        List<Region> regions = new ArrayList<>();

        Groupe g1 = new Groupe("G1", 1988);
        Groupe g2 = new Groupe("G2", 1994);
        Groupe g3 = new Groupe("G3", 1997);

        Personne suppleantC1 = new Personne("Suppléant C1");
        Personne suppleantC2 = new Personne("Suppléant C2");
        Personne suppleantC3 = new Personne("Suppléant C3");
        Personne suppleantC4 = new Personne("Suppléant C4");
        Personne suppleantC5 = new Personne("Suppléant C5");
        Personne suppleantC6 = new Personne("Suppléant C6");
        Personne suppleantC7 = new Personne("Suppléant C7");
        Personne suppleantC8 = new Personne("Suppléant C8");

        Depute c1 = new Depute("C1", g1, suppleantC1);
        Depute c2 = new Depute("C2", g2, suppleantC2);
        Depute c3 = new Depute("C3", g2, suppleantC3);
        Depute c4 = new Depute("C4", g3, suppleantC4);
        Depute c5 = new Depute("C5", g1, suppleantC5);
        Depute c6 = new Depute("C6", g3, suppleantC6);
        Depute c7 = new Depute("C7", g3, suppleantC7);
        Depute c8 = new Depute("C8", g1, suppleantC8);

        BureauVote bv101 = new BureauVote("BV101", true);
        bv101.addDepute(c1);
        bv101.addDepute(c2);

        BureauVote bv102 = new BureauVote("BV102", true);
        bv102.addDepute(c1);
        bv102.addDepute(c2);

        BureauVote bv201 = new BureauVote("BV201", true);
        bv201.addDepute(c3);
        bv201.addDepute(c4);

        BureauVote bv202 = new BureauVote("BV202", true);
        bv202.addDepute(c3);
        bv202.addDepute(c4);

        BureauVote bv301 = new BureauVote("BV301", true);
        bv301.addDepute(c5);
        bv301.addDepute(c6);

        BureauVote bv302 = new BureauVote("BV302", true);
        bv302.addDepute(c5);
        bv302.addDepute(c6);

        BureauVote bv401 = new BureauVote("BV401", true);
        bv401.addDepute(c7);
        bv401.addDepute(c8);

        BureauVote bv402 = new BureauVote("BV402", true);
        bv402.addDepute(c7);
        bv402.addDepute(c8);

        BureauVote bv403 = new BureauVote("BV403", true);
        bv403.addDepute(c7);
        bv403.addDepute(c8);

        Commune amplatafika = new Commune("Amplatafika");
        amplatafika.addBureau(bv101);
        amplatafika.addBureau(bv102);

        Commune ambohimena = new Commune("Ambohimena");
        ambohimena.addBureau(bv201);
        ambohimena.addBureau(bv202);

        Commune tanambao = new Commune("Tanambao Verrerie");
        tanambao.addBureau(bv301);
        tanambao.addBureau(bv302);

        Commune ambatomanga = new Commune("Ambatomanga");
        ambatomanga.addBureau(bv401);

        Commune talataDondona1 = new Commune("Talata-Dondona");
        talataDondona1.addBureau(bv402);

        Commune talataDondona2 = new Commune("Talata-Dondona");
        talataDondona2.addBureau(bv403);

        District antsimondrano = new District("Antananarivo Atsimondrano", true);
        antsimondrano.addCommune(amplatafika);

        District antsirabe = new District("Antsirabe I", true);
        antsirabe.addCommune(ambohimena);

        District toamasina = new District("Toamasina I", true);
        toamasina.addCommune(tanambao);

        District arivonimamo = new District("Arivonimamo", true);
        arivonimamo.addCommune(ambatomanga);

        District soavinandriana = new District("Soavinandriana", true);
        soavinandriana.addCommune(talataDondona1);

        District miarinarivo = new District("Miarinarivo", true);
        miarinarivo.addCommune(talataDondona2);

        Region analamanga = new Region("Analamanga");
        analamanga.addDistrict(antsimondrano);

        Region vakinankaratra = new Region("Vakinankaratra");
        vakinankaratra.addDistrict(antsirabe);

        Region atsinanana = new Region("Atsinanana");
        atsinanana.addDistrict(toamasina);

        Region itasy = new Region("Itasy");
        itasy.addDistrict(arivonimamo);
        itasy.addDistrict(soavinandriana);
        itasy.addDistrict(miarinarivo);

        regions.add(analamanga);
        regions.add(vakinankaratra);
        regions.add(atsinanana);
        regions.add(itasy);

        new GestionApplication(regions);
    }
}