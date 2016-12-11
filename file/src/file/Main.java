/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

/**
 *
 * @author Strabi
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String tomb[] = new String[50];
        String rosszfajta[] = new String[20];

        int i=0;
        int rosszDb=0;

        StringBuilder sb = new StringBuilder();
        boolean finom, konzol;


        try {

            String s;
            
            BufferedReader beo = new BufferedReader(new FileReader("be.txt"));
            BufferedReader beo2 = new BufferedReader(new FileReader("rossz.txt"));

            BufferedWriter kio = new BufferedWriter(new FileWriter("ki.txt"));



            // beolvasás
            while((s=beo.readLine())!=null)
            {
                tomb[i]=s;
                i++;
            }
            
            //beolvasás 2
            while((s=beo2.readLine())!=null)
            {
                rosszfajta[rosszDb]=s;
                rosszDb++;
            }


            konzol=isKonzol();
           
            for (int j = 0; j < i; j++) {

                // kisbetűssé tétel
                tomb[j]=tomb[j].toLowerCase();
                // az első betű eltárolása
                char c=tomb[j].charAt(0);
                if(j==0)
                    c=tomb[j].charAt(1);
                
                // stringbuilder tartalmának a törlése
                sb.delete(0, sb.length());

                if(c=='a' || c=='á' || c=='e' || c=='é' || c=='i' || c=='í'
                        || c=='o' || c=='ó' || c=='ö' || c=='ő' || c=='u' 
                        || c=='ü' || c=='ű')
                    sb.append("Az ");
                else
                    sb.append("A ");

                sb.append(tomb[j]);

                // megállapítjuk, hogy finom-e a pálinka
                if(konzol)
                    finom=isFinom(sb.toString());
                else
                    finom=isFinom(tomb[j], rosszfajta, rosszDb);

                // hozzáfűzzük a péinka szót
                sb.append(" pálinka ");

               // if(tomb[j].equals("pancsolt"))
                if(!finom)
                    sb.append("NEM ");
                
                sb.append("finom.");

                kio.write(sb.toString());
                kio.write("\r\n");              // sortörés
                System.out.println(sb.toString());
            }
            
            // beolvasás, kiolvasás lezárása
            beo.close();
            kio.close();

        } catch (Exception e) {     // kivételkezelés
            System.out.println("Hiba: "+e);
        }

       // System.out.println(tomb.length);

    }

    public static boolean isFinom(String palinka)
    {
        Scanner be = new Scanner(System.in);
        String valasz;

        palinka=palinka.toLowerCase();

        do
        {
            System.out.println("Szereted " +palinka+ " pálinkát? [I/N]");
            valasz=be.nextLine();
            valasz=valasz.toLowerCase();

            if(valasz.equals("i"))
                return true;

        }while(!(valasz.equals("i") || valasz.equals("n")));

        return false;
    }

    public static boolean isFinom(String palinka, String[] rosszPalinka, int rosszDb)
    {
        for (int i = 0; i < rosszDb; i++)
            if(rosszPalinka[i].equals(palinka))
                return false;

        return true;
    }

     public static boolean isKonzol()
    {
        Scanner be = new Scanner(System.in);
        String valasz;


        do
        {
            System.out.println("Konzolról, vagy fájlból olvasol-e be? [K/F]");
            valasz=be.nextLine();
            valasz=valasz.toLowerCase();

            if(valasz.equals("k"))
                return true;

        }while(!(valasz.equals("k") || valasz.equals("f")));

        return false;
    }



}
