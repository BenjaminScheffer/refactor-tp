package org.iut.refactoring;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RapportDivision implements TypeRapport {

    @Override
    public void generer(List<Employe> employes, String filtre) {
        System.out.println("=== RAPPORT: DIVISION ===");
        Map<String, Integer> compteur = new HashMap<>();

        for (Employe emp : employes) {
            String division = emp.getEquipe();
            int count = compteur.containsKey(division) ? compteur.get(division) + 1 : 1;
            compteur.put(division, count);
        }

        for (Map.Entry<String, Integer> entry : compteur.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " employés");
        }
    }

    @Override
    public String getNom() {
        return "DIVISION";
    }
}
