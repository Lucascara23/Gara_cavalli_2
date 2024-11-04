/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.progetto_cavalli_2;

/**
 *
 * @author lucascarabattoli
 */
public class Cavallo implements Runnable {
    private String nome;
    private int distanzaTotale;    
    private int distanzaPercorsa;  
    private int velocita;
    private boolean infortunato;
    
    public Cavallo(String nome, int distanzaTotale, int distanzaPercorsa, int velocita) {
        this.nome = nome; 
        this.distanzaTotale = distanzaTotale;
        this.velocita = velocita;
        this.distanzaPercorsa = 0; 
  
    }
   

    @Override
    public void run() {
        
         while (distanzaPercorsa < distanzaTotale && !infortunato) {
            int passo = (int) (Math.random() * velocita) + 1;
            distanzaPercorsa += passo;

            System.out.println(nome + " ha percorso " + distanzaPercorsa + " metri.");

            
            if (Math.random() < 0.01) { 
                infortunato = true;
                System.out.println(nome + " si è infortunato ed esce dalla gara.");
                break;
            }

           
            try {
                Thread.sleep(500); 
            } catch (InterruptedException e) {
                System.out.println(nome + " è stato interrotto durante la corsa.");
                Thread.currentThread().interrupt(); 
            }
        }

        if (!infortunato){
        System.out.println(nome + " ha terminato la gara!");
        }
    } 
    
     public String getnome(){
        return nome;
     }
     
     public int getdistanzaPercorsa(){
         return distanzaPercorsa;
     }
}

       
       

