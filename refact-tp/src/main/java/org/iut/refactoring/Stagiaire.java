package org.iut.refactoring;

public class Stagiaire extends Employe{

    public Stagiaire(String equipe, int experience, String nom, double salaireDeBase){
        super("STAGIAIRE",equipe, experience, nom, salaireDeBase);
    }
    @Override
    double calculerSalaire() {
        double salaireFinal = this.salaireDeBase;
        salaireFinal = salaireDeBase * 0.6;
        return salaireFinal;
    }

    @Override
    double calculerBonus() {
        return 0;
    }

    @Override
    double bonusAnnuel() {
        return 0;
    }
}
