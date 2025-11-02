package org.iut.refactoring;

import java.util.List;

public class RapportExperience implements TypeRapport {

    @Override
    public void generer(List<Employe> employes, String filtre) {
        System.out.println("=== RAPPORT: EXPERIENCE ===");
        for (Employe emp : employes) {
            if (filtre == null || filtre.isEmpty() || emp.getEquipe().equals(filtre)) {
                System.out.println(emp.getNom() + ": " + emp.getExperience() + " années");
            }
        }
    }

    @Override
    public String getNom() {
        return "EXPERIENCE";
    }
}
