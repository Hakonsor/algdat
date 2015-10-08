# algdat
Skole oppgaver
////// Løsningsforslag Oblig 1 ////////////////////////

import java.util.Arrays;
import java.util.NoSuchElementException;

public class Oblig1
{
  private Oblig1() { }

  // Metoder fra undervisningen

  private static void bytt(int[] a, int i, int j)
  {
    int temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }

  private static void bytt(char[] a, int i, int j)
  {
    char temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }

  private static void snu(char[] c, int v, int h)
  {
    while (v < h) bytt(c, v++, h--);
  }

  ///// Oppgave 1 ////////////////////////////////////// 

  /*
  La tabellen a ha n verdier. Først sammenlignes a[0] og a[1], 
  så a[1] og a[2], osv. Tilsammen blir det n - 1 sammenligninger.

  Det blir færrest ombyttinger (dvs. ingen) hvis a er sortert 
  stigende og flest (dvs. n - 1 stykker) hvis den største verdien 
  ligger først. Hvis alle verdiene er forskjellige, blir det en 
  ombytting hver gang det kommer en verdi som ikke er større enn 
  den største inntil da. Av de n - 1 verdiene som kommer etter
  den første, er det i gjennomsnitt H(n) - 1 stykker som er større 
  enn den største foran. Dermed blir svaret n - 1 - (H(n) - 1) =
  n - H(n) der H(n) er det n-te harmoniske tallet.

  Denne metoden er derfor vesentlig dårligere enn de maks-metodene vi 
  har laget tidligere.
  */
  public static int maks(int[] a)
  {
    if (a.length < 1)
    {
      throw new NoSuchElementException("Tabellen a er tom!");
    }

    for (int i = 1; i < a.length; i++)
    {
      if (a[i - 1] > a[i])
      {
        bytt(a, i - 1, i);
      }
    }
    return a[a.length - 1];
  }

  public static int ombyttinger(int[] a)
  {
    if (a.length < 1)
    {
      throw new NoSuchElementException("Tabellen a er tom!");
    }

    int antall = 0;

    for (int i = 1; i < a.length; i++)
    {
      if (a[i - 1] > a[i])
      {
        bytt(a, i - 1, i);
        antall++;
      }
    }
    return antall;
  }

  ///// Oppgave 2 ////////////////////////////////////// 

  /*
  La tabellen ha n verdier. Metoden vil alltid utføre n - 1 sammenligninger
  i første runde. Hvis tabellen var sortert på forhånd, vil metoden (slik 
  som den er kodet nedenfor) stoppe. Metoden har derfor orden n i det beste
  tilfelet.

  I det verste tilfellet og i gjennomsnitt vil det bli n - 2 sammenligninger
  i andre runde, n - 3 stykker i tredje runde, osv. 
  Tilsammen n - 1 + n - 2 + . . . + 1 = n(n-1)/2 sammenligninger. Det betyr
  at metoden er av kvadratisk i det gjennomsnittlige og i det verste
  tilfellet. 

  Metoden fortsetter til at alle inversjoner er fjernet. Siden det i en
  permutasjon av tallene fra 1 til n er i gjennomsnitt n(n - 1)/4
  inversjoner, vil dette også være det gjennomsnittlige antallet
  ombyttinger i metoden.

  Denne teknikken kalles boblesortering.
  */
  public static void sortering(int[] a)
  {
    for (int i = a.length; i > 1 ; i--)
    {
      boolean ferdig = true;
      for (int j = 1; j < i; j++)
      {
        if (a[j - 1] > a[j])
        {
          bytt(a, j - 1, j);
          ferdig = false;  // ombytting - ikke ferdig ennå
        }
      }
      if (ferdig) return;
    }
  }


  ///// Oppgave 3 ////////////////////////////////////// 

  // Nedenfor er det satt opp to løsningsforslag for medlemsnummer

  /*
  Med kun 0, 1, 2, 3, 4 og 5 som siffer, svarer et medlemsnummer til 
  et tall som har en heltallsrepresentasjon med grunntall 6. Tallet
  100000 betyr da tallet 6^4 = 1296 og 55555 blir 6^5 - 1 = 7775.
  */

  public static int medlemsnummer()  // 1. forslag
  {
    int antall = 0;

    for (int i = 1296; i <= 7775; i++)
    {
      char[] c = Integer.toString(i, 6).toCharArray();  // sifrene som tegn
      int[] a = new int[6];
      for (char d : c) a[d - '0']++;
      if (maks(a) < 3) antall++;     // bruker metoden fra Oppgave 1
    }

    return antall;
  }

  // hjelpemetode som adderer i tallsystemet med 6 som grunntall
  public static void pluss1(int[] a)
  {
    for (int i = a.length - 1; i >= 0; i--)
    {
      if (++a[i] == 6) a[i] = 0; else break;
    }
  }

  public static int medlemsnummer2()      // 2. forslag
  {
    int antall = 0;
    int[] a = {1,0,0,0,0};                // 10000

    while (a[0] != 0)                     // stopper når a = 55555 + 1 = 00000
    {
      int[] siffer = new int[6];          // antall-tabell for siffer

      for (int s : a) siffer[s]++;        // teller opp sifrene
      if (maks(siffer) < 3) antall++;     // bruker metoden fra Oppgave 1  

      pluss1(a);                          // hjelpemetoden legger til 1
    }

    return antall;
  }

  ///// Oppgave 4 ////////////////////////////////////// 

  public static int antallUlikeUsortert(int[] a)
  {
    if (a.length < 2) return a.length;

    int antallUlike = 1;

    for (int i = 1; i < a.length; i++)
    {
      int j = 0;
      for (; j < i; j++)
      {
        int verdi = a[i];
        if (a[j] == verdi) break;  // samme verdi to steder
      }
      if (j == i) antallUlike++;   // verdi er ny
    }

    return antallUlike;
  }

  ///// Oppgave 5 ////////////////////////////////////// 

  public static void rotasjon(char[] a)
  {
    if (a.length > 1)
    {
      char temp = a[a.length - 1];
      for (int i = a.length - 1; i > 0; i--)
      {
        a[i] = a[i - 1];
      }
      a[0] = temp;
    }
  }

  ///// Oppgave 6 ////////////////////////////////////// 

  // Se Avsnitt 1.3.15 i kompendiet. Siste del av avsnittet
  // handler om rotasjoner. Der står flere måter å gjøre det
  // på, bl.a. flg. måte:

  public static void rotasjon(char[] a, int k)
  {
    int n = a.length;
    if (n <= 1) return;

    // Vet nå at n > 1

    k %= n;
    if (k < 0) k += n;

    // bruker hjelpemetoden snu - se øverst
    snu(a, 0, n - 1);    // snur hele tabellen
    snu(a, 0, k - 1);    // snur de k første
    snu(a, k, n - 1);    // snur resten
  }

  ///// Oppgave 7 ////////////////////////////////////// 

  public static String flett(String s, String t)  // 1. forslag med StringBuilder
  {
    StringBuilder sb = new StringBuilder();

    int m = Math.min(s.length(), t.length());

    for (int i = 0; i < m; i++)
    {
      sb.append(s.charAt(i)).append(t.charAt(i));
    }

    for (int i = m; i < s.length(); i++) sb.append(s.charAt(i));  // tar med resten
    for (int i = m; i < t.length(); i++) sb.append(t.charAt(i));  // tar med resten

    return sb.toString();
  }

  public static String flett2(String s, String t)  // 2. forslag - med en tabell
  {
    char[] c = s.toCharArray();
    char[] d = t.toCharArray();

    char[] flett = new char[c.length + d.length];

    int m = Math.min(c.length, d.length);

    for (int i = 0, j = 0; i < m; i++)
    {
      flett[j++] = c[i];
      flett[j++] = d[i];
    }

    System.arraycopy(c, m, flett, 2*m, c.length - m);  // tar med resten
    System.arraycopy(d, m, flett, 2*m, d.length - m);  // tar med resten

    return String.valueOf(flett);
  }

  public static String flett(String... s)
  {
    if (s.length == 0) return "";

    int m = s[0].length();

    // finner lengden på den lengste strengen
    for (int i = 1; i < s.length; i++)
    {
      int lengde = s[i].length();
      if (lengde > m) m = lengde;
    }

    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < m; i++)
    {
      for (String t : s)
      {
        if (i < t.length()) sb.append(t.charAt(i));
      }
    }

    return sb.toString();
  }

  ///// Oppgave 8 ////////////////////////////////////// 

  public static int[] indeks(int[] a)
  {
    int[] indeks = {0,1,2};

    if (a[indeks[0]] > a[indeks[1]]) bytt(indeks,0,1);
    if (a[indeks[1]] > a[indeks[2]]) bytt(indeks,1,2);
    if (a[indeks[0]] > a[indeks[1]]) bytt(indeks,0,1);

    return indeks;
  }

  public static int[] tredjeMin(int[] a)
  {
    int n = a.length;     // tabellens lengde

    if (n < 3)     // må ha minst tre verdier
    {
      throw new NoSuchElementException("a.length(" + n + ") < 3!");
    }

    int[] indeks = indeks(a);

    int m =  indeks[0];   // m er indeksen til minste verdi
    int nm = indeks[1];   // nm er indeksen til nest minste verdi
    int tm = indeks[2];   // tm er indeksen til tredje minste verdi

    int minverdi = a[m];         // minste verdi
    int nestminverdi = a[nm];    // nest minste verdi
    int tredjeminverdi = a[tm];  // tredje minste verdi

    for (int i = 3; i < n; i++)
    {
      int verdi = a[i];

      if (verdi < tredjeminverdi)
      {
        if (verdi < nestminverdi)
        {
          if (verdi < minverdi)
          {
            tm = nm;
            tredjeminverdi = nestminverdi;

            nm = m;
            nestminverdi = minverdi;

            m = i;
            minverdi = verdi;
          }
          else  // verdi < nestminverdi && verdi >= minverdi
          {
            tm = nm;
            tredjeminverdi = nestminverdi;

            nm = i;
            nestminverdi = verdi;
          }
        }
        else // verdi < tredjeminverdi && verdi >= nestminverdi
        {
          tm = i;
          tredjeminverdi = verdi;
        }
      }

    } // for

    return new int[] { m, nm, tm };
  }

  ///// Oppgave 9 ////////////////////////////////////// 

  public static int[] kMinst(int[] a, int k)
  {
    if (k < 1)
    {
     throw new NoSuchElementException
        ("k(" + k + ") må være positiv!!");
    }
    else if (k > a.length)
    {
      throw new IllegalArgumentException
        ("k(" + k + ") må være <= a.length(" + a.length + ")!");
    }

    int[] verdier = Arrays.copyOf(a, k);   // de k første verdiene i a
    Arrays.sort(verdier);                  // sorterer

    for (int i = k; i < a.length; i++)
    {
      int verdi = a[i];

      if (verdi < verdier[k-1])
      {
        // verdi skal inn på rett sortert plass i verdier
        int j = k - 2;
        for ( ; j >= 0 && verdi < verdier[j]; j--)
        {
          verdier[j+1] = verdier[j];    // flytter i verdier
        }
        verdier[j+1] = verdi;           // legger inn på plass j + 1
      }
    }

    return verdier;  // returnerer indeks-tabellen
  }

  ///// Oppgave 10 ////////////////////////////////////// 

  // Den optimale teknikken er å telle opp forekomstene av de
  // forskjellige bokstavene i de to tegnstrengene. Oppgaveteksten
  // sier at det kan tas som gitt at de inneholder kun store bokstaver

  // I 1. forslag gjør vi opptellingen i en tabell med plass
  // til de 29 bokstavene fra A til Å. Opptellingen utføres i
  // en hjelpemetode:

  public static int bokstavNr(char bokstav)  // tar store bokstaver som gitt
  {
    if (bokstav <= 'Z') return bokstav - 'A';  // A -> 0, B -> 1, C -> 2, osv.
    else if (bokstav == 'Ø') return 27;        // Ø -> 27
    else if (bokstav == 'Å') return 28;        // Å -> 28
    else return 26;                            // Æ -> 26   
  }

  public static boolean inneholdt(String a, String b)  // 1. forslag
  {
    if (a.length() > b.length()) return false;  // a har flere bokstaver enn b

    int[] antall = new int[29];  // fra A til Å - 29 bokstaver

    for (int i = 0; i < a.length(); i++) antall[bokstavNr(a.charAt(i))]++;
    for (int i = 0; i < b.length(); i++) antall[bokstavNr(b.charAt(i))]--;

    for (int i = 0; i < antall.length; i++) if (antall[i] > 0) return false;

    return true;
  }

  public static boolean inneholdt2(String a, String b)  // 2. forslag
  {
    if (a.length() > b.length()) return false; // a har flere bokstaver enn b 

    // Lager plass til alle mulige tegn. Det er litt
    // sløsing med plassen, men det gir kortere og enklere kode

    int[] antall = new int[256];

    int n = a.length(), m = b.length();

    for (int i = 0; i < n; i++) antall[a.charAt(i)]++;  // teller opp
    for (int i = 0; i < m; i++) antall[b.charAt(i)]--;  // teller ned

    for (int i = 0; i < antall.length; i++) if (antall[i] > 0) return false;

    return true;
  }

}  // Oblig1
