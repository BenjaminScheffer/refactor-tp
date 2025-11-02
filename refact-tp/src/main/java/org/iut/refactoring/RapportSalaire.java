package org.iut.refactoring;

import java.util.List;

public class RapportSalaire implements TypeRapport {

    private final GestionPersonnel gestion;

    public RapportSalaire(GestionPersonnel gestion) {
        this.gestion = gestion;
    }

    @Override
    public void generer(List<Employe> employes, String filtre) {
        System.out.println("=== RAPPORT: SALAIRE ===");
        employes.stream()
                .filter(emp -> filtre == null || filtre.isEmpty() || emp.getEquipe().equals(filtre))
                .forEach(emp -> {
                    double salaire = gestion.calculSalaire(emp.getUuid());
                    System.out.println(emp.getNom() + ": " + salaire + " €");
                });
    }

    @Override
    public String getNom() {
        return "SALAIRE";
    }
}
