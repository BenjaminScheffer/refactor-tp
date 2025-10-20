package org.iut.refactoring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestGestionPersonnel {

    @Test
    @DisplayName("Test sur la taille de la liste des employées")
    public void testEmployeesSize() {
        var gestionPersonnel = new GestionPersonnel();
        gestionPersonnel.ajouteSalarie("DEVELOPPEUR", "Alice", 50000, 6, "IT");
        gestionPersonnel.ajouteSalarie("CHEF DE PROJET", "Bob", 60000, 4, "RH");
        gestionPersonnel.ajouteSalarie("STAGIAIRE", "Charlie", 20000, 0, "IT");
        gestionPersonnel.ajouteSalarie("DEVELOPPEUR", "Dan", 55000, 12, "IT");
        assertEquals(gestionPersonnel.employes.size(),4);
    }

    @Test
    @DisplayName("Test sur les salaires")
    public void testSalaire() {
        var gestionPersonnel = new GestionPersonnel();
        gestionPersonnel.ajouteSalarie("DEVELOPPEUR", "Alice", 50000, 6, "IT");
        gestionPersonnel.ajouteSalarie("DEVELOPPEUR", "Juliette", 50000, 2, "IT");
        gestionPersonnel.ajouteSalarie("CHEF DE PROJET", "Bob", 60000, 4, "RH");
        gestionPersonnel.ajouteSalarie("CHEF DE PROJET", "Jean", 60000, 2, "RH");
        gestionPersonnel.ajouteSalarie("STAGIAIRE", "Charlie", 20000, 0, "IT");
        gestionPersonnel.ajouteSalarie("DEVELOPPEUR", "Dan", 55000, 12, "IT");
        gestionPersonnel.ajouteSalarie("ALTERNANT", "Dan2", 55000, 12, "IT");
        double[] array = {69000.0, 75900.0, 12000.0, 99000.00000000001,60000.0,90000.0,55000.0};
        System.out.println(gestionPersonnel.salairesEmployes.values());
        for(var d:array){
            assertTrue(gestionPersonnel.salairesEmployes.values().contains(d));
        }
    }
}
