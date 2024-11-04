/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.progetto_cavalli_2;

/**
 *
 * @author lucascarabattoli
 */
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class GaraCavallo {  
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        
        System.out.println("Inserisci la lunghezza del percorso della gara (in metri): ");
        int lunghezzaGara = scanner.nextInt();

        
        System.out.println("Inserisci il numero dei cavalli che partecipano: ");
        int numCavalli = scanner.nextInt();
        scanner.nextLine();  

        List<Cavallo> CavalliPartecipanti = new ArrayList<>();
        List<Thread> cavalli = new ArrayList<>();

        
        for (int i = 0; i < numCavalli; i++) {
            
            System.out.println("Inserisci il nome del cavallo " + (i + 1) + ": ");
            String nomeCavallo = scanner.nextLine();
            
            System.out.println("Inserisci la velocità del cavallo " + (i + 1) + ": ");
            int Velocitacavallo = scanner.nextInt();
            scanner.nextLine();

           
            Cavallo cavallo = new Cavallo(nomeCavallo, 0, lunghezzaGara,Velocitacavallo);
            
            Thread cavalloThread = new Thread(cavallo);  
            cavalli.add(cavalloThread);  
        }

       
        System.out.println("La gara è iniziata!");
        for (Thread cavallo : cavalli) {
            cavallo.start(); 
        }

       
        for (Thread cavallo : cavalli) {
            try {
                cavallo.join(); 
            } catch (InterruptedException e) {
                System.out.println("La gara è stata interrotta.");
            }
        }
        
        Cavallo primo = null, secondo = null, terzo = null;
        for (Cavallo cavallo : CavalliPartecipanti) {
            if (primo == null || cavallo.getdistanzaPercorsa() > primo.getdistanzaPercorsa()) {
                terzo = secondo;
                secondo = primo;
                primo = cavallo;
            } else if (secondo == null || cavallo.getdistanzaPercorsa() > secondo.getdistanzaPercorsa()) {
                terzo = secondo;
                secondo = cavallo;
            } else if (terzo == null || cavallo.getdistanzaPercorsa() > terzo.getdistanzaPercorsa()) {
                terzo = cavallo;
            }
        }
        
        String classifica = "Classifica dei primi 3 cavalli:\n";
        if (primo != null) classifica += "1. " + primo.getnome() + " - " + primo.getdistanzaPercorsa() + " metri\n";
        if (secondo != null) classifica += "2. " + secondo.getnome() + " - " + secondo.getdistanzaPercorsa() + " metri\n";
        if (terzo != null) classifica += "3. " + terzo.getnome() + " - " + terzo.getdistanzaPercorsa() + " metri\n";
        
         System.out.println(classifica);

        System.out.println("Inserisci il nome del file per salvare i risultati della gara: ");
        String nomeFile = scanner.nextLine();
     
      try (FileWriter writer = new FileWriter(nomeFile, true)) { 
    writer.write(classifica);  // Scrive il contenuto della classifica nel file
    System.out.println("I risultati sono stati salvati nel file: " + nomeFile);
} catch (IOException e) {
    System.out.println("Errore durante il salvataggio dei risultati: " + e.getMessage());
}

System.out.println("La gara è finita!");
    }
    
    
   
}

