
package com.exercice;

import java.io.*;
import java.util.ArrayList;

class Produit implements Serializable {

    private static final long serialVersionUID = 1L;

    private int num;
    private String designation;
    private float prix;

    public Produit(int num, String designation, float prix) {
        this.num = num;
        this.designation = designation;
        this.prix = prix;
    }

    @Override
    public String toString() {
        return num + " -- " + designation + " -- " + prix;
    }
}

public class Exercice4 {

    public static void sauvegarder(ArrayList<Produit> liste, String nom) throws IOException {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nom))) {
            oos.writeObject(liste);
        }
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Produit> charger(String nom) throws IOException, ClassNotFoundException {

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nom))) {
            return (ArrayList<Produit>) ois.readObject();

        } catch (FileNotFoundException e) {
            return new ArrayList<Produit>();
        }
    }

    public static void main(String[] args) {

        File repertoire = new File("tp_fichiers");
        File fichierTexte = new File(repertoire, "produits.txt");
        File fichierBinaire = new File(repertoire, "stock.dat");

        try {

            ArrayList<Produit> liste = new ArrayList<Produit>();

            try (BufferedReader br = new BufferedReader(new FileReader(fichierTexte))) {

                String ligne;

                while ((ligne = br.readLine()) != null) {

                    String[] parties = ligne.split(";");

                    int num = Integer.parseInt(parties[0]);
                    String designation = parties[1];
                    float prix = Float.parseFloat(parties[2]);

                    liste.add(new Produit(num, designation, prix));
                }
            }

            sauvegarder(liste, fichierBinaire.getPath());

            System.out.println(liste.size() + " produits sauvegardés");

            ArrayList<Produit> listeChargee = charger(fichierBinaire.getPath());

            listeChargee.add(new Produit(6, "Webcam", 350.0f));

            sauvegarder(listeChargee, fichierBinaire.getPath());

            ArrayList<Produit> resultat = charger(fichierBinaire.getPath());

            for (Produit produit : resultat) {
                System.out.println(produit);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Fichier introuvable : " + e.getMessage());

        } catch (IOException e) {
            System.out.println("Erreur de lecture ou d'écriture : " + e.getMessage());

        } catch (ClassNotFoundException e) {
            System.out.println("Classe introuvable : " + e.getMessage());

        } catch (NumberFormatException e) {
            System.out.println("Format de nombre invalide : " + e.getMessage());
        }
    }
}
