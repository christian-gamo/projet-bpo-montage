package bibliotheque;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exemple.LaDiagonaleDuFou;
import film.Films;

class ExtractionTest {

	@Test
	void testSuivante() {
		int nbDeb = 2;
		int nbFin = 5;
		FilmCopiable diag = new LaDiagonaleDuFou();
		FilmCopiable extrait = new Extraction(diag.copie(),nbDeb,nbFin);
		char[][] écran = Films.getEcran(diag);
		char[][] écran2 = Films.getEcran(extrait);
		char[][] écranPoubelle = Films.getEcran(extrait);
		
		for(int i = 0; i < nbDeb; ++i) {
			diag.suivante(écranPoubelle);
		}
		
		for(int i = 0; i <= nbFin - nbDeb;++i) {
			diag.suivante(écran);
			extrait.suivante(écran2);
			
			assertEquals(Films.toString(écran),Films.toString(écran2));
			Films.effacer(écran);
			Films.effacer(écran2);
		}
		
	}
	
	@Test
	void testRembobiner() {
		FilmCopiable diag = new LaDiagonaleDuFou();
		int noDeb = 2;
		int noFin = 3;
		FilmCopiable extraction = new Extraction(diag,noDeb,noFin);
		char[][] écran = Films.getEcran(extraction);
		char[][] frame1 = Films.getEcran(extraction);
		
		extraction.suivante(frame1);
		extraction.rembobiner();
		extraction.suivante(écran);

		assertEquals(Films.toString(écran),Films.toString(frame1));
		Extraction.testRembobinerCpt();
		
	}
	
	@Test
	void testClone() {
		FilmCopiable diag = new LaDiagonaleDuFou();
		int noDeb = 2;
		int noFin = 3;
		FilmCopiable extraction = new Extraction(diag,noDeb,noFin);
		FilmCopiable copie = extraction.copie();
		assertTrue(copie.getClass() == extraction.getClass() 
				   && copie != extraction && copie.equals(extraction));
	}
	
	@Test
	void testHauteur() {
		FilmCopiable diag = new LaDiagonaleDuFou();
		int noDeb = 2;
		int noFin = 3;
		FilmCopiable extraction = new Extraction(diag,noDeb,noFin);
		
		assertEquals(diag.hauteur(), extraction.hauteur());
	}
	
	@Test
	void testLargeur() {
		FilmCopiable diag = new LaDiagonaleDuFou();
		int noDeb = 2;
		int noFin = 3;
		FilmCopiable extraction = new Extraction(diag,noDeb,noFin);
		
		assertEquals(diag.largeur(), extraction.largeur());
	}
			
}
