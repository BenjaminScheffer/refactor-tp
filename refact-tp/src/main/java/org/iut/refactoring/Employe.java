package org.iut.refactoring;

import java.util.UUID;

public abstract class Employe {
    String type,equipe,nom,uuid; double salaireDeBase; int experience;
    public Employe(String type, String equipe, int experience, String nom, double salaireDeBase) {
        this.type = type;
        this.equipe = equipe;
        this.nom = nom;
        this.salaireDeBase = salaireDeBase;
        this.experience = experience;
        this.uuid = UUID.randomUUID().toString();
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getEquipe() {
        return equipe;
    }

    public void setEquipe(String equipe) {
        this.equipe = equipe;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public double getSalaireDeBase() {
        return salaireDeBase;
    }

    public void setSalaireDeBase(double salaireDeBase) {
        this.salaireDeBase = salaireDeBase;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Object[] toArray(){
        Object[] tab = new Object[6];
        tab[0] = uuid;
        tab[1] = type;
        tab[2] = nom;
        tab[3] = salaireDeBase;
        tab[4] = experience;
        tab[5] = equipe;
        return tab;
    }

    abstract double calculerSalaire();
}
