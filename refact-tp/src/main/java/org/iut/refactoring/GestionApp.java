package org.iut.refactoring;

class GestionApp {
    public static void main(String[] args) {
        GestionPersonnel app = new GestionPersonnel();
        var dev1 = new Developpeur("IT",6,"Alice",50000);
        var chef = new ChefProjet("RH",4,"Bob",60000);
        var stagiaire = new Stagiaire("IT",0,"Charlie",20000);
        var dev2 = new Developpeur("IT",12,"Dan",55000);
        app.ajouteSalarie(dev1);
        app.ajouteSalarie(chef);
        app.ajouteSalarie(stagiaire);
        app.ajouteSalarie(dev2);
        
        String aliceId = app.employes.getFirst().getUuid();
        
        System.out.println("Salaire de Alice: " + app.calculSalaire(aliceId) + " €");
        System.out.println("Bonus de Alice: " + app.calculBonusAnnuel(aliceId) + " €");
        
        app.generationRapport("SALAIRE", "IT");
        app.generationRapport("EQUIPE", null);
        
        app.avancementEmploye(aliceId, "CHEF DE PROJET");
        
        app.printLogs();
    }
}
