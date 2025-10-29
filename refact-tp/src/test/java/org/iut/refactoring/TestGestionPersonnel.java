package org.iut.refactoring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

public class TestGestionPersonnel {

    @Test
    @DisplayName("Test sur la taille de la liste des employées")
    public void testEmployeesSize() {
        var gestionPersonnel = new GestionPersonnel();
        var dev1 = new Developpeur("IT",6,"Alice",50000);
        var chef = new ChefProjet("RH",4,"Bob",60000);
        var stagiaire = new Stagiaire("IT",0,"Charlie",20000);
        var dev2 = new Developpeur("IT",12,"Dan",55000);
        gestionPersonnel.ajouteSalarie(dev1);
        gestionPersonnel.ajouteSalarie(chef);
        gestionPersonnel.ajouteSalarie(stagiaire);
        gestionPersonnel.ajouteSalarie(dev2);
        assertEquals(gestionPersonnel.employes.size(),4);
    }

    @Test
    @DisplayName("Test sur les salaires")
    public void testSalaire() {
        var gestionPersonnel = new GestionPersonnel();
        var dev1 = new Developpeur("IT",6,"Alice",50000);
        var dev2 = new Developpeur("IT",2,"Juliette",50000);
        var chef1 = new ChefProjet("RH",4,"Bob",60000);
        var chef2 = new ChefProjet("RH",2,"Jean",60000);
        var stagiaire = new Stagiaire("IT",0,"Charlie",20000);
        var dev3 = new Developpeur("IT",12,"Dan",55000);
        gestionPersonnel.ajouteSalarie(dev1);
        gestionPersonnel.ajouteSalarie(dev2);
        gestionPersonnel.ajouteSalarie(chef1);
        gestionPersonnel.ajouteSalarie(chef2);
        gestionPersonnel.ajouteSalarie(stagiaire);
        gestionPersonnel.ajouteSalarie(dev3);
        System.out.println(gestionPersonnel.salairesEmployes.values());
        double[] array = {99000.00000000001, 75900.0, 90000.0, 69000.0, 12000.0, 60000.0};
        for(var d:array){
            assertTrue(gestionPersonnel.salairesEmployes.values().contains(d));
        }
    }
}
