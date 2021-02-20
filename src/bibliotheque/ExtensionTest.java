 package bibliotheque;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exemple.LaDiagonaleDuFou;
import film.Films;

class ExtensionTest {
	
	@Test
	void testSuivante() {
		FilmCopiable diag1 = new LaDiagonaleDuFou();
		FilmCopiable diag2 = new LaDiagonaleDuFou();
		FilmCopiable extension = new Extension(diag1.copie(),diag2.copie());
		
		int cptPartie1 = 0;
		int cptPartie2 = 0;
		int cptExtension = 0;
		
		char[][] écran1 = Films.getEcran(diag1);
		char[][] écran2 = Films.getEcran(diag2);
		char[][] écran3 = Films.getEcran(extension);
		
		while (diag1.suivante(écran1)) 
			++cptPartie1;
		
		
		while (diag2.suivante(écran2)) 
			++cptPartie2;
		
		while (extension.suivante(écran3))
			++cptExtension;
		
		assertEquals(cptPartie1+cptPartie2,cptExtension);		
		
		extension.rembobiner();
		diag1.rembobiner();
		diag2.rembobiner();
		Films.effacer(écran1);
		Films.effacer(écran2);
		Films.effacer(écran3);
		
		for(int i = 0; i<cptPartie1 ; ++i) {
			diag1.suivante(écran1);
			extension.suivante(écran3);
			assertEquals(Films.toString(écran1),Films.toString(écran3));
			Films.effacer(écran1);
			Films.effacer(écran3);
		}
		
		for(int i = 0; i<cptPartie2 ; ++i) {
			diag2.suivante(écran2);
			extension.suivante(écran3);
			assertEquals(Films.toString(écran2),Films.toString(écran3));
			Films.effacer(écran2);
			Films.effacer(écran3);
		}
		
		
	}
	
	@Test
	void testRembobiner() {
		FilmCopiable diag1 = new LaDiagonaleDuFou();
		FilmCopiable diag2 = new LaDiagonaleDuFou();
		FilmCopiable extension = new Extension(diag1.copie(),diag2.copie());
		char[][] écran = Films.getEcran(extension);
		char[][] frame1 = Films.getEcran(extension);
		
		extension.suivante(frame1);
		extension.rembobiner();
		extension.suivante(écran);

		assertEquals(Films.toString(écran),Films.toString(frame1));
		
		Extension.testRembobinerPartie1Fini();
		
	}
	
	@Test
	void testClone() {
		FilmCopiable diag1 = new LaDiagonaleDuFou();
		FilmCopiable diag2 = new LaDiagonaleDuFou();
		FilmCopiable extension = new Extension(diag1.copie(),diag2.copie());
		FilmCopiable copie = extension.copie();
		assertTrue(copie.getClass() == extension.getClass() 
				   && copie != extension && copie.equals(extension));
	}
	
	@Test
	void testHauteur() {
		FilmCopiable diag1 = new LaDiagonaleDuFou();
		FilmCopiable diag2 = new LaDiagonaleDuFou();
		FilmCopiable extension = new Extension(diag1.copie(),diag2.copie());
		
		assertEquals(diag1.hauteur(), extension.hauteur());
	}
	
	@Test
	void testLargeur() {
		FilmCopiable diag1 = new LaDiagonaleDuFou();
		FilmCopiable diag2 = new LaDiagonaleDuFou();
		FilmCopiable extension = new Extension(diag1.copie(),diag2.copie());
		
		assertEquals(diag1.largeur(), extension.largeur());
	}

}
