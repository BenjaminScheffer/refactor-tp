package org.iut.refactoring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

public class TestGestionPersonnel {

    private GestionPersonnel gestion;

    @BeforeEach
    void setup() {
        gestion = new GestionPersonnel();
        var dev1 = new Developpeur("IT",6,"Alice",50000);
        var chef = new ChefProjet("RH",4,"Bob",60000);
        var stagiaire = new Stagiaire("IT",0,"Charlie",20000);
        var dev2 = new Developpeur("IT",12,"Dan",55000);
        gestion.ajouteSalarie(dev1);
        gestion.ajouteSalarie(chef);
        gestion.ajouteSalarie(stagiaire);
        gestion.ajouteSalarie(dev2);
    }
    @Test
    @DisplayName("Test sur la taille de la liste des employées")
    public void testEmployeesSize() {
        assertEquals(4, gestion.employes.size());
    }

    @Test
    @DisplayName("Test sur les salaires")
    public void testSalaire() {
        var dev2 = new Developpeur("IT",2,"Juliette",50000);
        var chef2 = new ChefProjet("RH",2,"Jean",60000);
        gestion.ajouteSalarie(dev2);
        gestion.ajouteSalarie(chef2);
        System.out.println(gestion.salairesEmployes.values());
        double[] array = {99000.00000000001, 75900.0, 90000.0, 69000.0, 12000.0, 60000.0};
        for(var d:array){
            assertTrue(gestion.salairesEmployes.values().contains(d));
        }
    }

    @Test
    @DisplayName("Test sur le calcul des salaires")
    public void testCalculSalaire() {
        var gestionPersonnel = new GestionPersonnel();
        var dev1 = new Developpeur("IT",6,"Alice",50000);
        var dev2 = new Developpeur("IT",2,"Juliette",50000);
        var chef1 = new ChefProjet("RH",4,"Bob",60000);
        var chef2 = new ChefProjet("RH",2,"Jean",60000);
        var stagiaire = new Stagiaire("IT",0,"Charlie",20000);
        var dev3 = new Developpeur("IT",12,"Dan",55000);
        var dev4 = new Developpeur("IT",12,"Dan",55000);
        gestionPersonnel.ajouteSalarie(dev1);
        gestionPersonnel.ajouteSalarie(dev2);
        gestionPersonnel.ajouteSalarie(chef1);
        gestionPersonnel.ajouteSalarie(chef2);
        gestionPersonnel.ajouteSalarie(stagiaire);
        gestionPersonnel.ajouteSalarie(dev3);
        assertEquals(gestionPersonnel.calculSalaire(dev1.getUuid()),dev1.calculerSalaire());
        assertEquals(gestionPersonnel.calculSalaire(dev2.getUuid()),dev2.calculerSalaire());
        assertEquals(gestionPersonnel.calculSalaire(chef1.getUuid()),chef1.calculerSalaire()+5000);
        assertEquals(gestionPersonnel.calculSalaire(chef2.getUuid()),chef2.calculerSalaire()+5000);
        assertEquals(gestionPersonnel.calculSalaire(stagiaire.getUuid()),stagiaire.calculerSalaire());
        assertEquals(gestionPersonnel.calculSalaire(dev3.getUuid()),dev3.calculerSalaire()*1.05);
        assertEquals(0, gestionPersonnel.calculSalaire(dev4.getUuid()));
    }

    @Test
    void testGenerationRapportSalaire() {
        var output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        gestion.generationRapport(new RapportSalaire(gestion), "IT");
        String printed = output.toString();
        assertTrue(printed.contains("=== RAPPORT: SALAIRE ==="));
        assertTrue(printed.contains("Alice"));
        assertTrue(printed.contains("Charlie"));
        assertTrue(printed.contains("Dan"));
        assertFalse(printed.contains("Bob"));

        // Vérifie que le log a été ajouté
        assertTrue(gestion.logs.stream()
                .anyMatch(l -> l.contains("Rapport généré: SALAIRE")));
    }

    @Test
    void testGenerationRapportExperience() {
        var output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        gestion.generationRapport(new RapportExperience(), "IT");

        String printed = output.toString();
        assertTrue(printed.contains("=== RAPPORT: EXPERIENCE ==="));
        assertTrue(printed.contains("Alice: 6 années"));
        assertTrue(printed.contains("Charlie: 0 années"));
        assertTrue(printed.contains("Dan: 12 années"));
        assertFalse(printed.contains("Bob")); // Bob est dans RH, pas IT

        // Vérifie le log
        assertTrue(gestion.logs.stream()
                .anyMatch(l -> l.contains("Rapport généré: EXPERIENCE")));
    }

    @Test
    void testGenerationRapportDivision() {
        var output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        gestion.generationRapport(new RapportDivision(), null);

        String printed = output.toString();
        assertTrue(printed.contains("=== RAPPORT: DIVISION ==="));
        assertTrue(printed.contains("IT: 3 employés"));
        assertTrue(printed.contains("RH: 1 employés"));

        // Vérifie le log
        assertTrue(gestion.logs.stream()
                .anyMatch(l -> l.contains("Rapport généré: DIVISION")));
    }

    @Test
    @DisplayName("Test sur le bonus annuel")
    public void testBonusAnnuel() {
        var gestionPersonnel = new GestionPersonnel();
        var dev1 = new Developpeur("IT",6,"Alice",50000);
        var dev2 = new Developpeur("IT",2,"Juliette",50000);
        var chef1 = new ChefProjet("RH",4,"Bob",60000);
        var chef2 = new ChefProjet("RH",2,"Jean",60000);
        var stagiaire = new Stagiaire("IT",0,"Charlie",20000);
        var dev3 = new Developpeur("IT",12,"Dan",55000);
        var dev4 = new Developpeur("IT",12,"Dan",55000);
        gestionPersonnel.ajouteSalarie(dev1);
        gestionPersonnel.ajouteSalarie(dev2);
        gestionPersonnel.ajouteSalarie(chef1);
        gestionPersonnel.ajouteSalarie(chef2);
        gestionPersonnel.ajouteSalarie(stagiaire);
        gestionPersonnel.ajouteSalarie(dev3);
        assertEquals(7500.0, gestionPersonnel.calculBonusAnnuel(dev1.getUuid()));
        assertEquals(5000.0, gestionPersonnel.calculBonusAnnuel(dev2.getUuid()));
        assertEquals(15600.0, gestionPersonnel.calculBonusAnnuel(chef1.getUuid()));
        assertEquals(12000.0, gestionPersonnel.calculBonusAnnuel(chef2.getUuid()));
        assertEquals(0.0, gestionPersonnel.calculBonusAnnuel(stagiaire.getUuid()));
        assertEquals(0.0, gestionPersonnel.calculBonusAnnuel(dev4.getUuid()));
}

}
