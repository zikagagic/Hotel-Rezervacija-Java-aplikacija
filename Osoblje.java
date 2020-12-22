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
public class Osoblje extends Osoba implements IOperacije{
    private int plata;
    private String sektor;

    public Osoblje(String ime, String prezime,int plata,String sektor) {
        super(ime, prezime);
        this.plata = plata;
        this.sektor=sektor;
    }
    public Osoblje(String ime, String prezime,int plata){
        super(ime, prezime);
        this.plata = plata;
        this.sektor="Recepcija";
    }
    public int getPlata() {
        return plata;
    }

    public void setPlata(int plata) {
        this.plata = plata;
    }

    public String getSektor() {
        return sektor;
    }

    public void setSektor(String sektor) {
        this.sektor = sektor;
    }

    @Override
    public String toString() {
        return "Osoblje: "+super.getIme()+" "+super.getPrezime()+" radi u sektoru: "+this.sektor;
    }
    
    public static void ispisOsoblja(ArrayList<Osoblje> listaOsoblja){
        System.out.println("Svo osoblje koje radi u hotelu:");
        for(Osoblje o:listaOsoblja)
            System.out.println(o);
    }
    public static void ispisPoSektoru(ArrayList<Osoblje> listaOsoblja){
        Scanner skener=new Scanner(System.in);
        System.out.println("Unesite sektor za pretragu:");
        String sektor=skener.nextLine();
        System.out.println("Spisak svih zaposlenih koje rade u sektoru "+sektor+":");
        for(Osoblje o:listaOsoblja)
            if(o.getSektor().toLowerCase().equals(sektor.toLowerCase()))
            System.out.println(o.getIme()+", "+o.getPrezime()+", plata:"+o.plata);
    }

    @Override
    public void snimiPodatke() {
        try {
                   BufferedWriter bufferedWriter= new BufferedWriter(new FileWriter("osoblje.txt",true));
                   String redZaUpis=this.getIme()+" "+this.getPrezime()+", radi u sektoru: "+this.getSektor();
                   bufferedWriter.write(redZaUpis);
                   bufferedWriter.close();
               } catch (IOException ex) {
                   ex.printStackTrace();
               }    }

    @Override
    public void procitajPodatke() {
         try {
            ArrayList<String> linije = new ArrayList<String>();
            BufferedReader bufferedReader=new BufferedReader(new FileReader("osoblje.txt"));
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

    @Override
    public void izbrisiPodatke() {
        PrintWriter writer;
        try {
            writer = new PrintWriter("osoblje.txt");
            writer.print("");
            writer.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }    
    }    
}

