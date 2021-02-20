/**
 * @file FilmCopiable.java
 * Projet BPO - IUT de Paris
 * @author Christian Gamo & Monica Huynh
 * @version 1 - 01/05/2020
 * @brief Sous-interface de Film permettant le clonage d'un film
 */

package bibliotheque;

import film.Film;

/** Sous-interface de Film permettant le clonage d'un film 
 * 
 * Attention, lorsque des films ne sont pas "indépendants" 
 * (lorsqu'ils reférencent une même instance), leur combinaison 
 * au sein d'un même montage peut donner des résultats incohérents.
 * Pour éviter ce problème, on peut appeler la méthode clone()
 * dans l'instanciation d'une classe de la bibliothèque 
 * dépendant de 1 ou plusieurs FilmCopiable.
 * 
 * Aussi, si vous souhaitez projeter ou sauvegarder deux films à la suite 
 * qui ne sont pas indépendants, il faut utiliser la méthode rembobiner() 
 * entre les deux projetages/sauvegardes.
 */


public interface FilmCopiable extends Film {
		
	/** 
	 * Méthode abstraite pour cloner un FilmCopiable
	 */
	FilmCopiable copie();
	

}
