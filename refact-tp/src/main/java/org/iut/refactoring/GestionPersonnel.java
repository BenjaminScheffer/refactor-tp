package org.iut.refactoring;

import java.util.*;
import java.time.*;

public class GestionPersonnel {
    
    public ArrayList<Employe> employes = new ArrayList<>();
    public HashMap<String, Double> salairesEmployes = new HashMap<>();
    public ArrayList<String> logs = new ArrayList<>();
    
    public void ajouteSalarie(Employe emp) {
        employes.add(emp);
        double salaireFinal = emp.calculerSalaire();
        salairesEmployes.put(emp.getUuid(), salaireFinal);
        logs.add(LocalDateTime.now() + " - Ajout de l'employé: " + emp.getNom());
    }
    
    public double calculSalaire(String employeId) {
        Employe emp = null;
        for (Employe e : employes) {
            if (e.getUuid().equals(employeId)) {
                emp = e;
                break;
            }
        }
        if (emp == null) {
            System.out.println("ERREUR: impossible de trouver l'employé");
            return 0;
        }
        return emp.calculerBonusSalaire() ;
    }
    
    public void generationRapport(String typeRapport, String filtre) {
        System.out.println("=== RAPPORT: " + typeRapport + " ===");
        
        if (typeRapport.equals("SALAIRE")) {
            for (Employe emp : employes) {
                if (filtre == null || filtre.isEmpty() || 
                    emp.getEquipe().equals(filtre)) {
                    String id = emp.getUuid();
                    String nom =  emp.getNom();
                    double salaire = calculSalaire(id);
                    System.out.println(nom + ": " + salaire + " €");
                }
            }
        } else if (typeRapport.equals("EXPERIENCE")) {
            for (Employe emp : employes) {
                if (filtre == null || filtre.isEmpty() || 
                    emp.getEquipe().equals(filtre)) {
                    String nom = emp.getNom();
                    int exp = emp.getExperience();
                    System.out.println(nom + ": " + exp + " années");
                }
            }
        } else if (typeRapport.equals("DIVISION")) {
            HashMap<String, Integer> compteurDivisions = new HashMap<>();
            for (Employe emp : employes) {
                String div = emp.getEquipe();
                compteurDivisions.put(div, compteurDivisions.getOrDefault(div, 0) + 1);
            }
            for (Map.Entry<String, Integer> entry : compteurDivisions.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue() + " employés");
            }
        }
        logs.add(LocalDateTime.now() + " - Rapport généré: " + typeRapport);
    }
    
    public void avancementEmploye(String employeId, String newType) {
        for (Employe emp : employes) {
            if (emp.getUuid().equals(employeId)) {
                emp.setType(newType);
                double nouveauSalaire = calculSalaire(employeId);
                salairesEmployes.put(employeId, nouveauSalaire);
                
                logs.add(LocalDateTime.now() + " - Employé promu: " + emp.getNom());
                System.out.println("Employé promu avec succès!");
                return;
            }
        }
        System.out.println("ERREUR: impossible de trouver l'employé");
    }
    
    public ArrayList<Employe> getEmployesParDivision(String division) {
        ArrayList<Employe> resultat = new ArrayList<>();
        for (Employe emp : employes) {
            if (emp.getEquipe().equals(division)) {
                resultat.add(emp); 
            }
        }
        return resultat;
    }
    
    public void printLogs() {
        System.out.println("=== LOGS ===");
        for (String log : logs) {
            System.out.println(log);
        }
    }
    
    public double calculBonusAnnuel(String employeId) {
        Employe emp = null;
        for (Employe e : employes) {
            if (e.getUuid().equals(employeId)) {
                emp = e;
                break;
            }
        } 
        if (emp == null) return 0;
        
        String type = emp.getType();
        int experience = emp.getExperience();
        double salaireDeBase = emp.getSalaireDeBase();
        
        double bonus = 0;
        if (type.equals("DEVELOPPEUR")) {
            bonus = salaireDeBase * 0.1;
            if (experience > 5) {
                bonus = bonus * 1.5;
            }
        } else if (type.equals("CHEF DE PROJET")) {
            bonus = salaireDeBase * 0.2;
            if (experience > 3) {
                bonus = bonus * 1.3;
            }
        } else if (type.equals("STAGIAIRE")) {
            bonus = 0; // Pas de bonus
        }
        return bonus;
    }
}



