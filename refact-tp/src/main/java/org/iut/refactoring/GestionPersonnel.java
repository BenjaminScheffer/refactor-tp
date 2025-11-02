package org.iut.refactoring;

import java.util.*;
import java.time.*;

public class GestionPersonnel {
    
    public ArrayList<Employe> employes = new ArrayList<>();
    public HashMap<String, Double> salairesEmployes = new HashMap<>();
    public ServiceStockageLogs logs;
    public ServicePrintLogs printer;

    public GestionPersonnel() {
        this.logs = new ArrayListLogs();
        this.printer = new PrintConsole();
    }

    public GestionPersonnel(ServiceStockageLogs logs,ServicePrintLogs printer) {
        this.logs = logs;
        this.printer = printer;
    }

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
    
    public void generationRapport(TypeRapport typeRapport, String filtre) {
        typeRapport.generer(employes, filtre);
        logs.add(LocalDateTime.now() + " - Rapport généré: " + typeRapport.getNom());
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
    
    public void printLogs() {
        printer.print(logs.getLogs());
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
        return emp.bonusAnnuel();
    }
}



