package javaMaratonci;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MaratonciMain {
	
	static	Scanner input = new Scanner(System.in);


	public static void main(String[] args)throws Exception {

		izbornik: while(true) {	
			System.out.println("Maratonci izbornik:\n"
					+ "\n1. Najbrzi ucesnik na maratonu."
					+ "\n2. Maratonci sortirani po vremenu ostvarenom na maratonu"
					+ "\n3. Pretraga ucesnika maratona po imenu, ispis maratonca i njegovo vrijeme"
					+ "\n4. Prosjecno vrijeme potrebno maratoncima za zavrsetak utrke"
					+ "\n5. Najbolji maratonci (<300min) spremljeni u najboljiMaratonci.txt file"
					+ "\n6. Broj linija u dokumentu na adresi: "
					+ "http://www.textfiles.com/science/astronau.txt"
					+ "\n7. Sortirana imena maratonaca po abecedi"
					+ "\n0. Izlaz iz izbornika\n");
			
	// ovdje odmah prije bilo kakve opcije u switchu ucitamo arraylistu maratonaca pomocu citac metode
		RunnersiMetode.citac();
				
			switch (reTry()) {

			case 1:
				RunnersiMetode.najbrziUcesnik();
				break;
			case 2:
				RunnersiMetode.sortiranjePoVremenu();
				break;
			case 3:
				RunnersiMetode.unosImena(input);
				break;
			case 4:
				RunnersiMetode.prosjek();
				break;
			case 5:
				RunnersiMetode.najboljiMaratonci();
				break;
			case 6:
				RunnersiMetode.linkBrojLinija();
				break;
			case 7:
				RunnersiMetode.sortiranaImena();
				break;
			case 0:
				break izbornik;

			default:
				System.out.println("Pogresno unesen broj");
			}
		}

	}

	public static int reTry() {
		try {
			return input.nextInt();
		} catch (InputMismatchException ex) {
			System.out.println("Unesite opciju");
			input.nextLine();
			return reTry();
		}
	}
}
