package org.iut.refactoring;

import java.util.List;

public interface TypeRapport {
    void generer(List<Employe> employes, String filtre);
    String getNom();
}