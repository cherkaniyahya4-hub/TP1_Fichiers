
package com.exercice;

import java.io.*;

public class Exercice2 {

    public static void main(String[] args) throws IOException {

        File repertoire = new File("tp_fichiers");
        repertoire.mkdirs();

        File produits = new File(repertoire, "produits.txt");

        try (PrintWriter pw = new PrintWriter(produits)) {
            pw.println("1;Ordinateur;5600.0");
            pw.println("2;Imprimante;1200.5");
            pw.println("3;Clavier;150.0");
            pw.println("4;Ecran;1800.0");
        }

        int nbProduits = 0;
        double total = 0;
        double prixMax = 0;
        String produitCher = "";

        try (BufferedReader br = new BufferedReader(new FileReader(produits))) {

            String ligne;

            while ((ligne = br.readLine()) != null) {

                String[] parties = ligne.split(";");

                int id = Integer.parseInt(parties[0]);
                String nom = parties[1];
                double prix = Double.parseDouble(parties[2]);

                nbProduits++;
                total += prix;

                if (prix > prixMax) {
                    prixMax = prix;
                    produitCher = nom;
                }

                System.out.println(id + " -- " + nom + " -- " + prix + " DH");
            }
        }

        System.out.println("Nombre de produits : " + nbProduits);
        System.out.println("Prix total : " + total + " DH");
        System.out.println("Produit le plus cher : " + produitCher + " (" + prixMax + " DH)");

        try (FileWriter fw = new FileWriter(produits, true)) {
            fw.write("5;Souris;80.0" + System.lineSeparator());
        }

        int nbCaracteres = 0;
        int nbLignes = 0;

        try (FileReader fr = new FileReader(produits)) {

            int c;

            while ((c = fr.read()) != -1) {
                nbCaracteres++;

                if (c == '\n') {
                    nbLignes++;
                }
            }
        }

        System.out.println("Nombre de caractères : " + nbCaracteres);
        System.out.println("Nombre de lignes : " + nbLignes);
    }
}
