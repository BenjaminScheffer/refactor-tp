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

    @Override
    double calculerBonus() {
        return 5000;
    }

    @Override
    double bonusAnnuel() {
        double bonus = salaireDeBase * 0.2;
        if (experience > 3) {
            bonus = bonus * 1.3;
        }
        return bonus;
    }
}
