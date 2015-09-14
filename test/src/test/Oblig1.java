/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author hakon
 */
public class Oblig1 {

    /**
     * @param args the command line arguments
     *
     * public static void Oblig1{ // TODO code application logic here
     *
     * int[] a = {8,3,5,7,1,6,10,2,4,3,9, 1}; int[] b = randPerm(500);
     * System.out.println(maks(b)); //System.out.println(maks(b));
     * System.out.println(ombyttinger(b)); }
     */
    public static int maks(int[] a) {
        int hjelpevariabel = 0;
        if (a.length < 1) {
            throw new NoSuchElementException("a er tom!");
        }
        //for(int j = 0; j < a.length-1; j++){    
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] > a[i + 1]) {
                hjelpevariabel = a[i];
                a[i] = a[i + 1];
                a[i + 1] = hjelpevariabel;
            }
            // }
        }
        return a[a.length - 1];
    }

    public static int ombyttinger(int[] a) {
        int hjelpevariabel = 0;
        int ombyttninger = 0;
        if (a.length < 1) {
            throw new NoSuchElementException("a er tom!");
        }
        //for(int j = 0; j < a.length-1; j++){    
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] > a[i + 1]) {
                hjelpevariabel = a[i];
                a[i] = a[i + 1];
                a[i + 1] = hjelpevariabel;
                ombyttninger++;
            }
        }

        return ombyttninger;

    }

    /*
     Det blir flest ombyttninger når den størte verdien starter i plass 0. 
     Da er det N ombyttinger. Det er ferrest ombyttinger når tabellen er sortert med 0 byttinger.
    
     Gjenomsnittet ser ut til å være er n-log(n), men ifølge Euler som har sett på 
     dette problemet har kommet frem til av gjennomsnitte er n - Hamoniske rekken av n.
     summen av hamoniske rekken av n er log(n)+0.577. Ved lav n som eks 10 er denne formelen ikke helt riktig.
     da kan den være lettere å regne ut den hamoniske rekken for hånd, h(10) = 1 + 1/2 + 1/3 +... 1/10
    
     Denne maks metoden er veldig mye dårligere en de vi har sett på tidligere fordi vi 
     sjekker og bytter tall enormt mange ganger. Siden vi faktigst bare finner et
     større tall log(n) ganger. Denne metoden sjekker tabbelen 2n ganger når vi 
     egentlig bare trenger å sjekke n ganger og lagre det størte tallet i en hjelpe variabel.
     */
    //Oppgave 2
    public static void sortering(int[] a) {


        int hjelpevariabel = 0;
        int byttinger = 0;

        for (int j = 0; j < a.length - 1; j++) {
            for (int i = 0; i < a.length - 1; i++) {
                if (a[i] > a[i + 1]) {
                    hjelpevariabel = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = hjelpevariabel;
                    byttinger++;
                }
            }
        }

    }//Sammenligninger av tabellverider blir 2N^2 fordi vi sammenligner alle 
    // tallene 2 ganger per gjenomgang og vi repiterer denne prosessen n ganger.
    // Jeg trodde først gjennomsnittet for ombyttinger var rundt (N - log(n)+0.577)^2
    // men etter på lest litt i kompendiet ser det ut til å være n(n-1)/4

    //oppgave 3
    public static int medlemsnummer() {
        int[] a = new int[5];

        int telle = 0;
        int t = 0;
        int første = 1, andre = 0, tredje = 0, fjerde = 1, femte = 1;
        int aa = 0, bb = 0, cc = 0, dd = 0, ee = 0, ff = 0;
        boolean lovlig = true;
        while (første != 0) {
            ++telle;
            femte = tallsystem(femte);
            if (femte == 0) {
                fjerde = tallsystem(fjerde);
                if (fjerde == 0) {
                    tredje = tallsystem(tredje);
                    if (tredje == 0) {
                        andre = tallsystem(andre);
                        if (andre == 0) {
                            første = tallsystem(første);
                        }
                    }
                }
            }
            a[0] = første;
            a[1] = andre;
            a[2] = tredje;
            a[3] = fjerde;
            a[4] = femte;
            aa = 0;
            bb = 0;
            cc = 0;
            dd = 0;
            ee = 0;
            ff = 0;
            for (int i = 0; i < 5; i++) {
              
                if (a[i] == 0) {
                    aa++;
                    if (aa > 2) {
                        lovlig = false;
                    }
                } else if (a[i] == 1) {
                    bb++;
                    if (bb > 2) {
                        lovlig = false;
                    }
                } else if (a[i] == 2) {
                    cc++;
                    if (cc > 2) {
                        lovlig = false;
                    }
                } else if (a[i] == 3) {
                    dd++;
                    if (dd > 2) {
                        lovlig = false;
                    }
                } else if (a[i] == 4) {
                    ee++;
                    if (ee > 2) {
                        lovlig = false;
                    }
                } else if (a[i] == 5) {
                    ff++;
                    if (ff > 2) {
                        lovlig = false;
                    }
                }
            }
            if (lovlig == false) {
                t++;
            }
            lovlig = true;
        }
        return telle - t;
    }

    private static int tallsystem(int siffer) {
        if (siffer < 5) {
            return ++siffer;
        }
        return 0;
    }
    //oppgave 4

    public static int antallUlikeUsortert(int[] a) {

        int maks = maks2(a); 
        int antall = 0;
        int count = 0;
        for (int i = 0; i <= maks; i++) {
            for (int j = 0; j < a.length; j++) {
                if (a[j] == i) {
                    count++;
                }
            }
            if (count != 0) {
                antall++;
            }
            count = 0;
        }
        return antall;
    }

    public static int maks2(int[] a) {
        int maks = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] > maks) {
                maks = a[i];
            }
        }
        return maks;
    }

    //oppgave 5
    public static void rotasjon(char[] a) {
        char v;
        for (int i = a.length - 1; i > 0; i--) {
            v = a[i];
            a[i] = a[i - 1];
            a[i - 1] = v;
        }
    }
// oppgave 6, 


    public static void rotasjon(char[] a, int k) {

        boolean negativ = false;


        if (a.length != 0) {
            k = k % a.length;
        } else {
            k = 0;
        }

        if (k < 0) {
            negativ = true;
            k = k * (-1);
        }

        char[] b;
        char[] c;

        if (negativ) {
            c = Arrays.copyOfRange(a, 0, k);
            b = Arrays.copyOfRange(a, k, a.length);
            System.arraycopy(b, 0, a, 0, b.length);
            System.arraycopy(c, 0, a, b.length, c.length);
        } else {
            c = Arrays.copyOfRange(a, a.length - k, a.length);
            b = Arrays.copyOfRange(a, 0, a.length - k);
            System.arraycopy(c, 0, a, 0, c.length);
            System.arraycopy(b, 0, a, c.length, b.length);
        }

    }

    // oppgave 7
    // a)
    public static String flett(String s, String t) {
        StringBuilder b = new StringBuilder();
        int oz;
        String rest;
        if (s.length() > t.length()) {
            oz = t.length();
            rest = s.substring(oz);
        } else {
            oz = s.length();
            rest = t.substring(oz);
        }
        for (int i = 0; i < oz; i++) {
            b.append(s.charAt(i));
            b.append(t.charAt(i));
        }
        rest = b.append(rest).toString();
        return rest;
    }

    // oppgave 7
    // b)
    public static String flett(String... s) {

        int[] temp = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            temp[i] = s[i].length();
        }
        int maks = maks2(temp);

        StringBuilder b = new StringBuilder();
        for (int j = 0; j < maks; j++) {
            for (int i = 0; i < s.length; i++) {

                if (s[i].length() > j) {
                    b.append(s[i].charAt(j));
                }
            }
        }

        return b.toString();
    }

    //Oppgave 8
    public static int[] indeks(int[] a) {
        int[] index = {1, 2, 3};
        if (a[0] > a[2]) {
            if (a[1] > a[2]) {
                index[0] = 2;
                if (a[0] > a[1]) {
                    index[1] = 1;
                    index[2] = 0;
                } else {
                    index[1] = 0;
                    index[2] = 1;
                }
            } else {
                index[2] = 0;
                if (a[0] > a[1]) {
                    index[0] = 1;
                    index[1] = 2;

                } else {
                    index[0] = 2;
                    index[1] = 1;

                }
            }
        } else {
            if (a[1] > a[2]) {
                index[0] = 0;
                if (a[0] > a[1]) {
                    index[1] = 2;
                    index[2] = 1;

                } else {
                    index[1] = 2;
                    index[2] = 1;
                }

            } else {
                index[2] = 2;
                if (a[0] > a[1]) {
                    index[0] = 1;
                    index[1] = 0;

                } else {
                    index[0] = 0;
                    index[1] = 1;
                }

            }
        }
        return index;

    }

    // misforsjo oppgave teksten i 8 a og trodde at jeg skulle 
    //sende tilbake index for alle verdiene i vilkårlig array
    public static int[] indeksFull(int[] a) {
        int[] c = Arrays.copyOf(a, a.length);
        int[] b = new int[a.length];

        int maks = maks2(a);
        int maksindex = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == maks) {
                maksindex = i;
            }
        }

        for (int i = 0; i < c.length - 1; i++) {
            int m = i;            
            int minverdi = c[i];  

            for (int j = 0; j < c.length; j++) {
                if (c[j] < minverdi) {
                    minverdi = c[j]; 
                    m = j;            
                }
            }
            
            int temp = c[i];
            b[i] = m;
            c[m] = maks;
        }
        b[b.length - 1] = maksindex;
        return b;
    }

    public static int[] tredjeMin(int[] a) 
    {
        int n = a.length;    

        if (n < 3) {
            throw // må ha minst to verdier
                    new NoSuchElementException("a.length(" + n + ") < 2!");
        }
        int[] b = indeks(a);
        int m = b[0];      
        int nm = b[1];    
        int nnm = b[2];

       
        int minstverdi = a[m];               
        int nestminstverdi = a[nm];         
        int nestnestminstverdi = a[nnm];
        for (int i = 3; i < n; i++) {
            if (a[i] < nestnestminstverdi) {
                if (a[i] < nestminstverdi) {
                    if (a[i] < minstverdi) {
                        nnm = nm;
                        nestnestminstverdi = nestminstverdi;
                        nm = m;
                        nestminstverdi = minstverdi;    
                        m = i;
                        minstverdi = a[m];             
                    } else {
                        nnm = nm;
                        nestnestminstverdi = nestminstverdi;       
                        nm = i;
                        nestminstverdi = a[nm];
                    }
                } else {
                    nnm = i;
                    nestnestminstverdi = a[nnm];
                }
            }
        } // for
        return new int[]{m, nm, nnm,};   

    }

    // oppgave 9
    public static int[] kMinst(int[] a, int k) {

        if (k < 1) {
            throw // må ha minst to verdier
                    new NoSuchElementException("Tabellen er tom");
        } else if (k > a.length) {
            throw // må ha minst to verdier
                    new IllegalArgumentException("k er lengere en a");
        } else if (k == 1) {
            int verdi = a[0];
            for (int i = 0; i < a.length; i++) {
                if (a[i] < a[0]) {
                    verdi = a[i];
                }
            }
            return new int[]{verdi};
        }
        int[] b = Arrays.copyOf(a, a.length);
        int verdi = a[0];
        for (int l = 0; l < k; l++) {
            bytt(b, l, min(b, l, b.length));
        }
        return Arrays.copyOf(b, k);
    }

    public static int min(int[] a, int fra, int til) {
        if (fra < 0 || til > a.length || fra >= til) {
            throw new IllegalArgumentException("Illegalt intervall!");
        }

        int m = fra;             
        int minverdi = a[fra];  

        for (int i = fra + 1; i < til; i++) {
            if (a[i] < minverdi) {
                m = i;               
                minverdi = a[m];    
            }
        }

        return m; 
    }

    public static int min(int[] a) 
    {
        return min(a, 0, a.length);    
    }

    public static void bytt(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    public static void bytt(char[] a, int i, int j) {
        char temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // oppgave 10
    public static boolean inneholdt(String a, String b) {
        int count = 0;

        if (a.length() < 1) {
            return true;
        }
        if (b.length() < 1) {
            return false;
        }
        char[] c = a.toCharArray();
        char[] d = b.toCharArray();

        flettesortering(c);
        flettesortering(d);

        int j = 0;
        for (int i = 0; i < d.length && j < c.length; i++) {
            
            if (d[i] == c[j]) {
                j++;
                count++;
            }
        }
        return count == c.length;
    }
    private static void flettesortering(char[] a, char[] b, int fra, int til)
  {
    if (til - fra <= 1) return;   
    int m = (fra + til)/2;        

    flettesortering(a,b,fra,m);  
    flettesortering(a,b,m,til);  

    if (a[m-1] > a[m]) flett(a,b,fra,m,til);  
  }
    private static void flett(char[] a, char[] b, int fra, int m, int til)
  {
    int n = m - fra;                
    System.arraycopy(a,fra,b,0,n); 

    int i = 0, j = m, k = fra;      

    while (i < n && j < til)        
    {                              
      a[k++] = b[i] <= a[j] ? b[i++] : a[j++];
    }

    while (i < n) a[k++] = b[i++];  
  }
      public static void flettesortering(char[] a)
  {
    char[] b = Arrays.copyOf(a, a.length/2);   
    flettesortering(a,b,0,a.length);          
  }

}
