/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelprogram;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class Soba implements IOperacije{
    private Musterija musterija;
    private int brojSobe;
    private int brojKreveta;
    private boolean rezervisana;

    public boolean isRezervisana() {
        return rezervisana;
    }

    public void setRezervisana(boolean rezervisana) {
        this.rezervisana = rezervisana;
    }
    

    public Soba(int brojSobe, int brojKreveta,Musterija musterija) {
        this.brojSobe = brojSobe;
        this.brojKreveta = brojKreveta;
        this.musterija=musterija;
        this.rezervisana=true;
    }
    public Soba(int brojSobe, int brojKreveta){
        this.brojSobe=brojSobe;
        this.brojKreveta=brojKreveta;
        this.rezervisana=false;
    }
   

    public int getBrojSobe() {
        return brojSobe;
    }

    public void setBrojSobe(int brojSobe) {
        this.brojSobe = brojSobe;
    }


    public int getBrojKreveta() {
        return brojKreveta;
    }

    public void setBrojKreveta(int brojKreveta) {
        this.brojKreveta = brojKreveta;
    }
    
    
    public static void proveraRezervacije(ArrayList<Soba> listaSoba){
        System.out.println("Nerezervisane sobe su:\n");
        for(Soba s:listaSoba)
            if(s.isRezervisana()==false)
                System.out.println(s+"\n"); 
    }

    public Musterija getMusterija() {
        return musterija;
    }

    public void setMusterija(Musterija musterija) {
        this.musterija = musterija;
    }
    
    public static void prikazSvihSoba(ArrayList<Soba> listaSoba){
        for(Soba s: listaSoba){
            System.out.println(s);
        }
        
    }

    @Override
    public String toString() {
        return "Soba:" +  brojSobe + " ("+ brojKreveta + ".krevetna"+")";
    }
    public static void rezervacijaSobe(ArrayList<Soba> listaSoba,ArrayList<Musterija> listaMusterija){

            boolean sobaSlobodna=false;
            Soba soba;
            ArrayList<Soba> listaIzabranih=new ArrayList<Soba>();
            Scanner input = new Scanner(System.in); 
            System.out.println("Unesite ime:");
            String ime=input.nextLine();
            if(ime.length()>0){
                ime=ime.substring(0,1).toUpperCase()+ime.substring(1);
                System.out.println("Unesite prezime");
                String prezime=input.nextLine();
                if(prezime.length()>0){
                prezime=prezime.substring(0,1).toUpperCase()+prezime.substring(1);
                    System.out.println("Sa koliko kreveta biste zeleli da iznajmite sobu?:");
                    int izbor=Integer.parseInt(input.nextLine());

                    for(Soba s:listaSoba){
                        if(s.getBrojKreveta()==izbor&&s.isRezervisana()==false)
                            listaIzabranih.add(s);
                    }
                     System.out.println("Lista soba koje su "+izbor+".krevetne:");
                     for(Soba s:listaIzabranih){
                         System.out.println(s);
                     }
                     System.out.println("Izaberite sobu koju zelite:");
                     int brojSobe=Integer.parseInt(input.nextLine());
                            for(Soba s:listaSoba){
                               if(brojSobe==s.getBrojSobe()&&s.isRezervisana()==false)
                                  sobaSlobodna=true;
                            }
                     if(sobaSlobodna){
                        System.out.println("Koliko noci cete boraviti?(Unesite broj)");
                         int brojNocenja=Integer.parseInt(input.nextLine());
                         if(brojNocenja!=0){
                            System.out.println("Da li imate popust na rezervaciju?:\n1) Da 2) Ne\n");
                            int opcija=Integer.parseInt(input.nextLine());
                            if(opcija==1){
                                System.out.println("Unesite kod za popust:");
                                String popust=input.nextLine();
                                if(popust.trim().toLowerCase().equals("popust123")){
                                    for(Soba s:listaSoba){
                                        if(s.getBrojSobe()==brojSobe){
                                            soba=s;
                                            Musterija musterija=new Musterija(ime,prezime,soba,brojNocenja,true); 
                                            listaMusterija.add(musterija);
                                            soba.setRezervisana(true);
                                            System.out.println("\nUspesno ste rezervisali sobu!");
                                            System.out.println("Cena boravka je:"+musterija.izracunajCenu()+"din.");
                                        }
                                            
                                    }                                 
                                }else{System.out.println("Niste uneli tacan kod za popust!");}
                            }
                            else if(opcija==2){
                                for(Soba s:listaSoba){
                                    if(s.getBrojSobe()==brojSobe){
                                        soba=s;
                                        Musterija musterija=new Musterija(ime,prezime,soba,brojNocenja); 
                                        listaMusterija.add(musterija);
                                        soba.setRezervisana(true);
                                        System.out.println("Uspesno ste rezervisali sobu!\n");
                                        System.out.println("Cena boravka je:"+musterija.izracunajCenu()+"din.");
                                    }       
                                }
                            }else{System.out.println("Niste uneli 1 ili 2");}                                 
                         }else{System.out.println("Niste uneli doro broj nocenja!");}     
                    }else{System.out.println("Soba je vec rezervisana ili soba ne postoji");}                     
                }else{System.out.println("Niste uneli dobro prezime!");}                       
            }else{System.out.println("Niste uneli dobro ime");}
        }

     public void snimiPodatke() {
        try {
            BufferedWriter bufferedWriter= new BufferedWriter(new FileWriter("sobe.txt",true));
            String upis="Soba:"+this.brojSobe+", rezervacija:"+this.isRezervisana()+"\n";
            bufferedWriter.write(upis);
            bufferedWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }    
    }

    @Override
    public void procitajPodatke() {
        try {
            ArrayList<String> linije = new ArrayList<String>();
            BufferedReader bufferedReader=new BufferedReader(new FileReader("sobe.txt"));
            String linija = "";
            while ((linija = bufferedReader.readLine()) != null)
            linije.add(linija);
          for(String ispis:linije){
              System.out.println(ispis);
          }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

        
    public  void izbrisiPodatke() {
        PrintWriter writer;
        try {
            writer = new PrintWriter("sobe.txt");
            writer.print("");
            writer.close();  
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
  
}
