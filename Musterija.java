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

/**
 *
 * @author User
 */
public class Musterija extends Osoba implements IOperacije {
    private Soba soba;
    private int brojNocenja;
    boolean popust;
    
    public Musterija(String ime, String prezime,Soba soba,int brojNocenja,boolean popust) {
        super(ime, prezime);
        this.soba = soba;
        this.brojNocenja=brojNocenja;
        this.popust=popust;
    }

    public Musterija(String ime, String prezime, Soba soba, int brojNocenja) {
        super(ime, prezime);
        this.soba = soba;
        this.brojNocenja = brojNocenja;
        this.popust=false;
    }

    public Soba getSoba() {
        return soba;
    }

    public void setSoba(Soba soba) {
        this.soba = soba;
    }

    public int getBrojNocenja() {
        return brojNocenja;
    }

    public void setBrojNocenja(int brojNocenja) {
        this.brojNocenja = brojNocenja;
    }

    public boolean hasPopust() {
        return popust;
    }

    public void setPopust(boolean popust) {
        this.popust = popust;
    }
    
    public int izracunajCenu(){
       int cena=this.getSoba().getBrojKreveta()*this.getBrojNocenja()*1000;
       if(this.popust)
           return (int) (cena-(cena*0.1));
       else
           return cena;
    }

    @Override
    public String toString() {
        return "Musterija:" + super.getIme() + " " + super.getPrezime()+ ", boravi u sobi:" + this.getSoba().getBrojSobe();
    }
    
    public static void ispisSvihMusterija(ArrayList<Musterija>listaMusterija){
        System.out.println("Sve musterije:");
        for(Musterija m:listaMusterija)
            System.out.println(m);
    }
    
      public static void potragaPoImenu(ArrayList<Musterija>listaMusterija){
        Scanner skener=new Scanner(System.in);
        boolean pronadjen=false;
        System.out.println("Unesite ime musterije:");
        String ime=skener.nextLine();
        System.out.println("Unesite prezime musterije");
        String prezime=skener.nextLine();
        if(!listaMusterija.isEmpty()){
        for(Musterija m:listaMusterija){          
            if(m.getIme().toLowerCase().equals(ime.toLowerCase())&&m.getPrezime().toLowerCase().equals(prezime.toLowerCase()))
                    pronadjen=true;
                    System.out.println("Musterija "+m.getIme()+", "+m.getIme()+" se nalazi u sobi "+m.getSoba().getBrojSobe());
            
        }
            if(!pronadjen)
                System.out.println("Musterija nije pronadjena");
        }
        else{System.out.println("Spisak musterija je prazan!");}
    }
    
    public  void ispisCene(){
        System.out.println("Cena boravka musterije:"+this.getIme()+", "+this.getPrezime()+", sa cenom od:"+this.izracunajCenu());
    }
     
    public static void izbrisiMusteriju(ArrayList<Musterija> listaMusterija){
        int brojSobe;
        Scanner skener=new Scanner(System.in);
        System.out.println("Unesite broj sobe:");
        brojSobe=skener.nextInt();
        if(brojSobe!=0){
            if(!listaMusterija.isEmpty()){
                for(Musterija m:listaMusterija){
                    if( m.getSoba().getBrojSobe()==brojSobe){
                        m.getSoba().setMusterija(null);
                        m.getSoba().setRezervisana(false);
                        listaMusterija.remove(m);
                    }
                }
                System.out.println("Musterija je izbrisana");
            }else{System.out.println("Lista musterija ne postoji");}
        }else{System.out.println("Nije uneta ispravna vrednost");}
    }
    @Override
    public void snimiPodatke() {
        try {
            BufferedWriter bufferedWriter= new BufferedWriter(new FileWriter("musterije.txt",true));
            String redZaUpis=this.getIme()+" "+this.getPrezime()+": "+this.getSoba().getBrojSobe();
            bufferedWriter.write(redZaUpis);
            bufferedWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }    
    }
    @Override
      public void procitajPodatke() {
        try {
            ArrayList<String> linije = new ArrayList<String>();
            BufferedReader bufferedReader=new BufferedReader(new FileReader("musterije.txt"));
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
            writer = new PrintWriter("musterije.txt");
            writer.print("");
            writer.close();  
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }    }

}
