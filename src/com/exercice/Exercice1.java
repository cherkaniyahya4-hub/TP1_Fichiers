
package com.exercice;

import java.io.*;

public class Exercice1 {

    public static void main(String[] args) throws IOException {

        File repertoire = new File("tp_fichiers");

        if (!repertoire.exists()) {
            repertoire.mkdir();
        }

        System.out.println("Chemin : " + repertoire.getAbsolutePath());

        File a = new File(repertoire, "a.txt");
        File b = new File(repertoire, "b.txt");
        File archives = new File(repertoire, "archives");

        a.createNewFile();
        b.createNewFile();
        archives.mkdir();

        int nbFichiers = 0;
        int nbRepertoires = 0;
        String[] elements = repertoire.list();

        if (elements != null) {
            for (String nom : elements) {

                File f = new File(repertoire, nom);

                if (f.isDirectory()) {
                    nbRepertoires++;
                    System.out.println("[REP] " + nom);
                } else {
                    nbFichiers++;
                    System.out.println("[FICHIER] " + nom + " (" + f.length() + " octets)");
                }
            }
        }

        System.out.println(nbFichiers + " fichier(s), " + nbRepertoires + " répertoire(s)");
    }
}
