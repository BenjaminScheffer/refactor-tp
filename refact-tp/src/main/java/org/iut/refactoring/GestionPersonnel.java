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
        return employes.stream()
                .filter(e -> e.getUuid().equals(employeId))
                .findFirst()
                .map(Employe::calculerBonusSalaire)
                .orElseGet(() -> {
                    System.out.println("ERREUR: impossible de trouver l'employé");
                    return 0.0;
                });
    }
    
    public void generationRapport(TypeRapport typeRapport, String filtre) {
        typeRapport.generer(employes, filtre);
        logs.add(LocalDateTime.now() + " - Rapport généré: " + typeRapport.getNom());
    }
    
    public void avancementEmploye(String employeId, String newType) {
        Optional<Employe> optEmp = employes.stream()
                .filter(emp -> emp.getUuid().equals(employeId))
                .findFirst();

        if (optEmp.isPresent()) {
            Employe emp = optEmp.get();
            emp.setType(newType);
            double nouveauSalaire = calculSalaire(employeId);
            salairesEmployes.put(employeId, nouveauSalaire);

            logs.add(LocalDateTime.now() + " - Employé promu: " + emp.getNom());
            System.out.println("Employé promu avec succès!");
        } else {
            System.out.println("ERREUR: impossible de trouver l'employé");
        }
    }
    
    public void printLogs() {
        printer.print(logs.getLogs());
    }
    
    public double calculBonusAnnuel(String employeId) {
        return employes.stream()
                .filter(e -> e.getUuid().equals(employeId))
                .findFirst()
                .map(Employe::bonusAnnuel)
                .orElse(0.0);
    }
}



