package org.iut.refactoring;

public class ChefProjet extends Employe{

    public ChefProjet(String equipe, int experience, String nom, double salaireDeBase){
        super("CHEF DE PROJET",equipe, experience, nom, salaireDeBase);
    }
    @Override
    double calculerSalaire() {
        double salaireFinal = salaireDeBase;
        salaireFinal = salaireDeBase * 1.5;
        if (experience > 3) {
            salaireFinal = salaireFinal * 1.1;
        }
        return salaireFinal;
    }
}
