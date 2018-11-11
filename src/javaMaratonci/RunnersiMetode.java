package javaMaratonci;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class RunnersiMetode {
	//Arraylista na nivou cijele klase i ona ce se koristi u svim ostalim klasama i metodama 
		static ArrayList<Ucesnik> maratonci = new ArrayList<Ucesnik>();
		
	public static void citac() throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("maraton.txt"));
		String[] array = new String[2];// 0 ZA IME I 1 ZA VRIJEME
		try {
			// uzeli smo ovdje 1 liniju
			String line = br.readLine();
			while (line != null) {
				// sad tu liniju podijelimo na razmaku i napravimo objekat ucesnik,
				// dole je i njegov konstruktor
				// a reference na te objekte imat ce lista maratonci
				array = line.split(" ");
				maratonci.add(new Ucesnik(array[0], Integer.parseInt(array[1])));
				// ovdje uzimamo od 2-ge linije do kraja i dodajemo u maratonce
				line = br.readLine();
			}
		} finally {
			br.close();
		}
	}
		
	// prodjemo kroz maratonce i ako nadjemo nekog maratonca sa manjim vremenom
	// onda njegov index ce biti index indexNajbrzeg
	// jer nam treba taj index da bi ispisali objekat citav a ne samo vrijeme
	public static void najbrziUcesnik() {
		int indexNajbrzeg = 0;
		for (int i = 1; i < maratonci.size(); i++) {
			if (maratonci.get(indexNajbrzeg).vrijeme >= maratonci.get(i).vrijeme) {
				// posto i-ti ima manje vrijeme sad je on taj indexNajbrzeg
				indexNajbrzeg = i;
			}
		}
		System.out.println("Najbrzi maratonac je :" + maratonci.get(indexNajbrzeg).ime + " "
				+ maratonci.get(indexNajbrzeg).vrijeme + "\n");
	}
		
	public static void sortiranjePoVremenu() {
		// array u kojem ce biti sortirana vremena
		int[] array = new int[maratonci.size()];
		// ubacimo sad u taj array vremena svih ucesnika, tj maratonaca
		for (int i = 0; i < maratonci.size(); i++)
			array[i] = maratonci.get(i).vrijeme;

		// sortiramo ih i trenutno to nema nikave veze sa maratoncima sortiramo
		// obican niz sa nekim trocifrenim brojevima
		Arrays.sort(array); // sortiramo
		// ovdje ubacujemo one maratonce za koje smo dobili poklapanje vremena sa njihovim vremenom
		// kako kasnije ako naidjemo u array na isto vrijeme ne bi istog maratonca ispisali
		// jer postoji mogucnost da ima i neki drugi to vrijeme
		ArrayList<Ucesnik> pamtiIndexe = new ArrayList<Ucesnik>();
		// ovdje prodjemo kroz sve i-te elemente array-a(sortirana vremena)
		// i njih ima maratonci.size() jer je toliko ljudi
		for (int i = 0; i < maratonci.size(); i++) {
			// za svako i-to vrijeme(iz sortiranog array) provjeri svakog j-tog maratonca
			for (int j = 0; j < maratonci.size(); j++) {
				if (array[i] == maratonci.get(j).vrijeme && !pamtiIndexe.contains(maratonci.get(j))) {
					System.out.println(maratonci.get(j).ime + " " + array[i]);
					// dodamo npr. Amer kad se poklopi i sljedeci put nece Amer moci da se ispise
					// jer
					// njega lista pamtiIndexe vec ima tj. ona njega "contains" :)
					pamtiIndexe.add(maratonci.get(j));
					break;
				}
			}
		}
		//razmak od izbornika
		System.out.println();
	}

	public static void unosImena(Scanner input) {

		System.out.println("Unesite ime ucesnika maratona: ");
		// linija ispod sluzi da kursor Scanner klase prebacimo u novi red kako
		// bi mogao ucitati sta korisnik unosi
		input.nextLine();
		String ime = input.nextLine();

		for (int i = 0; i < maratonci.size(); i++) {
			if (maratonci.get(i).ime.equalsIgnoreCase(ime)) {
				System.out.println(maratonci.get(i).ime + " " + maratonci.get(i).vrijeme);
				return; // return jer smo nasli i skroz napustamo sve
			}
		}
		// znaci nije bio return jer nije bio if(true)
		System.out.println("Nepostojece ime");
	}

	public static void prosjek() {
		double suma = 0;
		for (int i = 0; i < maratonci.size(); i++) {
			suma += maratonci.get(i).vrijeme;
		}
		System.out.println("Prosjek vremena potrebnog maratoncima za zavrsetak utrke je: " + suma / maratonci.size() + " min.");
	}
	
	public static void najboljiMaratonci() throws Exception {
		// u ovaj array stavljamo sve ispod 300 minuta
		ArrayList<String> array = new ArrayList<String>();
		for (int i = 0; i < maratonci.size(); i++) {
			if (maratonci.get(i).vrijeme < 300) {
				array.add(maratonci.get(i).ime + " " + maratonci.get(i).vrijeme);
			}
		}
		// ovdje ih zapisujemo u file najboljiMaratonci.txt
		PrintWriter writer = new PrintWriter("najboljiMaratonci.txt", "UTF-8");

		for (int i = 0; i < array.size(); i++)
			writer.println(array.get(i));
		writer.close();
		// poruka korisniku da je fajl napravljen
		System.out.println("\nFile je napravljen\n");
	}
		
	public static void linkBrojLinija() {

		try {
			// ovdje koristimo od URL klase objekat a on je zapravo ovaj link na neki nacin
			// i taj link mi cemo pomocu openStream metode citati,
			// tj. citati sadrzaj linka koji je u ovom slucaju txt file,
			java.net.URL url = new java.net.URL("http://www.textfiles.com/science/astronau.txt");
			int count = 0;
			Scanner input = new Scanner(url.openStream());
			while (input.hasNext()) { // hasNext je dok input ima nesto novo kod sebe
				// pa onda mi predjemo u novi red i brojimo koliko puta smo presli u novi red
				input.nextLine();
				count++;
			}
			System.out.println("File ima " + count + " linija");
			input.close();

		} catch (java.net.MalformedURLException ex) {
			System.out.println("Invalid URL");
		} catch (java.io.IOException ex) {
			System.out.println("I/O Errors: no such file");
		}
	}
	
	public static void sortiranaImena() throws Exception {
		// prvo napravimo arraylistu da bi lakse dodavali linije iz txt file-a
		// nismo mogli odmah array obicni praviti, nije nam poznato za sada koliko ima
		// linija,
		ArrayList<String> imena = new ArrayList<String>();

		BufferedReader br = new BufferedReader(new FileReader("imena.txt"));
		try {
			// ovo je samo prva linija ucitana
			imena.add(br.readLine());
			// uvijek provjerimo da li je zadnja dodana linija iz while petlje prazna tj.
			// nema vise imena
			while (imena.get(imena.size() - 1) != null) {
				// ovdje dodajemo od 2 linije do null linije ukljucujuci null liniju
				imena.add(br.readLine());
			}
		} finally {
			br.close();
		}
		// ovdje otklonimo tu null liniju iz liste imena
		imena.remove(imena.size() - 1);
		// ovdje napravimo array od array liste jer Arrays klasa ima sort metodu
		// pa nju mozemo koristiti za sortiranje
		System.out.println(); // obican razmak
		String[] sortirani = new String[imena.size()];
		for (int i = 0; i < imena.size(); i++) {
			sortirani[i] = imena.get(i);
		}

		// Arrays ima tu build in metodu, koja odradi posao sortiranja
		Arrays.sort(sortirani);
		// ispis tog sortiranog arraya
		for (String e : sortirani) {
			System.out.println(e);
		}
		// razmak
		System.out.println();

	}
}
