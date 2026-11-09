
package com.exercice;

import java.io.*;

public class Exercice3 {

    public static void copierAvecNumeros(String source, String dest) throws IOException {

        try (BufferedReader br = new BufferedReader(new FileReader(source));
             BufferedWriter bw = new BufferedWriter(new FileWriter(dest))) {

            String ligne;
            int numero = 1;

            while ((ligne = br.readLine()) != null) {
                bw.write(numero + ": " + ligne);
                bw.newLine();
                numero++;
            }
        }
    }

    public static long copierBinaire(String source, String dest) throws IOException {

        long nbOctets = 0;

        try (FileInputStream entree = new FileInputStream(source);
             FileOutputStream sortie = new FileOutputStream(dest)) {

            int octet;

            while ((octet = entree.read()) != -1) {
                sortie.write(octet);
                nbOctets++;
            }
        }

        return nbOctets;
    }

    public static void main(String[] args) throws IOException {

        File repertoire = new File("tp_fichiers");
        File archives = new File(repertoire, "archives");
        archives.mkdirs();

        File source = new File(repertoire, "produits.txt");
        File avecNumeros = new File(repertoire, "produits_num.txt");
        File copie = new File(archives, "produits_copie.txt");

        copierAvecNumeros(source.getPath(), avecNumeros.getPath());

        long nbOctets = copierBinaire(source.getPath(), copie.getPath());

        System.out.println("Nombre d'octets copiés : " + nbOctets);
        System.out.println("Taille de l'original : " + source.length() + " octets");
        System.out.println("Taille de la copie : " + copie.length() + " octets");
        System.out.println("Tailles identiques : " + (source.length() == copie.length()));
    }
}
