package hotelprogram;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class HotelRezervacija {

    private static boolean glavniMeni = true;
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        ArrayList<Soba> listaSoba=new ArrayList();
        ArrayList<Osoblje> listaOsoblja=new ArrayList();
        ArrayList<Musterija> listaMusterija=new ArrayList();
        Osoblje osoblje1=new Osoblje("Milan","Milanovic",30000,"Kuhinja");
        Osoblje osoblje2=new Osoblje("Marko","Markovic",30000,"Kuhinja");
        Osoblje osoblje3=new Osoblje("Pera","Peric",35000,"Recepcija");
        Osoblje osoblje4=new Osoblje("Ivana","Ivanovic",35000,"Sanitacija");
        Osoblje osoblje5=new Osoblje("Dusko","Petrovic",28000,"Obezbedjenje");
        listaOsoblja.add(osoblje1);
        listaOsoblja.add(osoblje2);
        listaOsoblja.add(osoblje3);
        listaOsoblja.add(osoblje4);
        inicijalizacija(listaSoba);
        System.out.println("¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬");
        System.out.println("Dobrodosli u program za hotel rezervaciju.");
        while (glavniMeni) {
            while (true) {
                System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
                System.out.println("Izaberite jednu od opcija");
                System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
                System.out.println("A: Rezervisite sobu.");
                System.out.println("---------------------------------------------------------------------------------------");
                System.out.println("B: Prikaz svih soba.");
                System.out.println("---------------------------------------------------------------------------------------");
                System.out.println("C: Prikaz slobodnih soba.");
                System.out.println("---------------------------------------------------------------------------------------");                
                System.out.println("D: Prikaz svih zaposlenih u hotelu");
                System.out.println("---------------------------------------------------------------------------------------");
                System.out.println("E: Prikaz zaposlenih po sektoru");
                System.out.println("---------------------------------------------------------------------------------------");
                System.out.println("F: Prikaz svih musterija.");
                System.out.println("---------------------------------------------------------------------------------------");
                System.out.println("G: Potraga sobe po imenu musterije.");
                System.out.println("---------------------------------------------------------------------------------------");  
                System.out.println("H: Ispis cena boravka musterija");
                System.out.println("---------------------------------------------------------------------------------------");                
                System.out.println("I: Izbrisi musteriju i oslobodi sobu");
                System.out.println("---------------------------------------------------------------------------------------");
                System.out.println("J: Sacuvaj sadrzaj programa u datoteke");
                System.out.println("---------------------------------------------------------------------------------------");
                System.out.println("K: Izbrisite sadrzaj datoteka");
                System.out.println("---------------------------------------------------------------------------------------"); 
                System.out.println("L: Ispisite sadrzaj datoteka");
                System.out.println("---------------------------------------------------------------------------------------");
                System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
                System.out.println("¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬");
                String izbor = input.next();
                switch (izbor.toUpperCase()) {
                    case "A":
                        Soba.rezervacijaSobe(listaSoba, listaMusterija);
                        break;
                    case "B":
                        Soba.prikazSvihSoba(listaSoba);
                        break;
                    case "C":
                        System.out.println("Sobe koje nisu rezervisane:\n");
                        Soba.proveraRezervacije(listaSoba);
                        break;
                    case "D":
                        System.out.println("Svi zaposleni u hotelu:\n");
                        Osoblje.ispisOsoblja(listaOsoblja);
                        break;
                    case "E":
                        Osoblje.ispisPoSektoru(listaOsoblja);
                        break;   
                    case "F":
                       Musterija.ispisSvihMusterija(listaMusterija);
                        break;
                    case "G":
                        Musterija.potragaPoImenu(listaMusterija);
                        break;
                    case "H":
                        if(!listaMusterija.isEmpty()){
                        for(Musterija m:listaMusterija)
                            m.ispisCene();
                        }else{System.out.println("Lista musterija je prazna");}
                        break;    
                    case "I":
                        Musterija.izbrisiMusteriju(listaMusterija);
                        break;        
                    case "J":
                        for(Musterija m:listaMusterija)
                            m.snimiPodatke();
                        for(Soba s: listaSoba)
                            s.snimiPodatke();
                        for(Osoblje o:listaOsoblja) 
                            o.snimiPodatke();
                        System.out.println("Uspesno sacuvano!");
                        break;
                    case "K":
                        Soba sobaBrisanje=listaSoba.get(0);
                        sobaBrisanje.izbrisiPodatke();
                        System.out.println("Uspesno izbrisan sadrzaj sobe.txt");
                        if(!listaMusterija.isEmpty()){
                        Musterija musterijaBrisanje=listaMusterija.get(0);
                        musterijaBrisanje.izbrisiPodatke();
                        System.out.println("Uspesno izbrisan sadrzaj musterije.txt");                        
                        }else{System.out.println("Datoteka musterije.txt ne postoji!");}
                        Osoblje osobljeBrisanje=listaOsoblja.get(0);
                        osobljeBrisanje.izbrisiPodatke();
                        System.out.println("Uspesno izbrisan sadrzaj osoblje.txt");
                        break;
                    case "L":
                        System.out.println("Sadrzaj datoteke soba.txt");
                        listaSoba.get(0).procitajPodatke();
                        if(!listaMusterija.isEmpty()){
                        System.out.println("Sadrzaj datoteke musterije.txt");
                            listaMusterija.get(0).procitajPodatke();
                        }else{System.out.println("Datoteka musterije.txt je prazna");}
                        System.out.println("Sadrzaj datoteke osoblje.txt");
                            listaOsoblja.get(0).procitajPodatke();
                       break;
                    default:
                        System.out.println("Nepostojeci izbor!");
                        break;
                }
                System.out.println("¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬");
                System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
                System.out.println("Da li zelite da izaberite jos neku opcija\n1 ) Da\n2 ) Ne(Izlaz)");
                System.out.println("¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬");
                System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
                if (input.nextInt()==1){
                    glavniMeni = true;} 
                else {
                      System.exit(0);
                }
            }
        }        
    }    
        public static void inicijalizacija(ArrayList<Soba> listaSoba){
        Random random=new Random();
        int brojSoba=20;
        int brojSobe=100;
        int brojJednokrevetnih=0;
        int brojDvokrevetnih=0;
        int brojTrokrevetnih=0;
        int brojCetvorekrevetnih=0;
        int brojac=0;
        for(int i=1;i<=brojSoba;i++){
            brojac++;
            Soba soba = new Soba(brojSobe,brojac);
            listaSoba.add(soba);
            brojSobe+=10;
            if(brojac==4)
                brojac=0;
            }
        }
  } 