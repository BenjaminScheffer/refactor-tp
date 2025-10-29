package org.iut.refactoring;

public class Developpeur extends Employe{

    public Developpeur(String equipe, int experience, String nom, double salaireDeBase){
        super("DEVELOPPEUR",equipe, experience, nom, salaireDeBase);
    }
    @Override
    double calculerSalaire() {
        double salaireFinal = salaireDeBase;
        salaireFinal = salaireDeBase * 1.2;
        if (experience > 5) {
            salaireFinal = salaireFinal * 1.15;
        }
        return salaireFinal;
    }
}
