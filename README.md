# MissionToMars

## Java project
Marie-Amelie Defresne, Ayrwan Guillermo, Adrien Grivey

### Statistics :
* Refactorisation U1 et U2 vers U pour faciliter l'implémentation des méthodes statistiques
* Nouveau paramètre distribution à fourni à U1 et U2 (par défaut distribution uniforme si aucun paramètre renseigné)
* Utilisiation de la bibiliothèque org.apache.commons.math3.distribution et plus particulièrement les RealDistribution : à ajouter dans vos lib et configurer vos projets sur vos IDE
* A priori les réécriture de launch et land avec des distributions passées en paramètres (de la classe) devrait être assez générale si on considère que landExploRate et launchExploRate sont les proba d'explosion maximales lorsque chargment maximal.
* Les distributions de proba doivent a priori prendre leurs valeurs dans [0,1] (vu que l'argument dans les fonctions de probabilité c'est un ratio poids/poids max). 

## Partie 1:
### item:
Cette classe représente les objets que l'on peut mettre dans une fusée. L'objectif du projet est de pouvoir modéliser l'envoi d'objets sur Mars via le lancement de fusée.  

#### Attributs:
* name : *String* -> nom de l'objet.
* weight : *int* -> poids de l'objet (en tonnes).
* cost : *int* -> prix de l'objet (en million de $).
* nbrPeople : *int* -> nombre de personne dans l'objet (si celui-ci est habité).
Nous avons décidés d'ajouter un prix et un nombre de personne par objet, car il est important de copter les pertes du chargement lors de l'envoie d'une fusée. Avec la modélisation initiale la façon dont les objets étaient placées dans les fusée n'avait aucune importance (à poid identique). 

#### Méthode:
* Geter / Seter.
* toString : reformate la façon dont on affiche l'objet pour integrer les atributs.

### rocket:
Les classes *spaceship*/*rocket*/*U1*/*U2* modélisent les fuséee envoyées dans l'espace.

#### Attributs de classe:
* initialCost : *int* -> prix initiale de la fusée sans chargement (en million de $).
* initialWeight : *int* -> poids initiale de la fusée sans chargement(en tonnes).
* initialMaxWeight : *int* -> poids maximale de la fusée (en tonnes).
* initialNbrPeople : *int* -> nombre initial de personne dans la fusée sans chargement (3 pour U1 & 4 pour U2).

#### Attributs:
* cost : *int* -> prix de la fusée (en million de $).
* weight : *int* -> poids total de la fusée en comprenant le chargement(en tonnes).
* nbrPeople : *int* -> nombre de personne dans la fusée en comprenant le chargement.
* landExplosionRate : *double* -> taux d'explosion de la fusée au lancement sans prise en compte du poids.
* launchExplosionRate : *double* -> taux d'explosion de la fusée à l'amarsissage sans prise en compte du poids.

#### Méthode:
* Geter/Seter.
* launch : *boolean* -> succès de lancement.
* land: *boolean* -> succès d'amarsissage.
* toString : reformate la façon dont on affiche les fusée pour integrer les atributs.

## Partie 2:
### Simulation:
#### Méthode:
* loaditems : crée une liste d'objets a partir d'un fichier formaté
* loadU1/U2 : On essaie d'ajouter l'item dans toutes les rockets, la première qui peut prendre l'objet le fait. Sinon on crée une nouvelle rocket.
* loadU1/U2PeopleSafe : Comme Load sauf si il y a des personnes dans l'item -> dans ce cas une nouvelle fusée est créer uniquement pour cet item.
* runSimulation : execution de la simulation. Lance chaque fusée dans la liste envoyé en parametre, en cas d'explosion envoie une nouvelle fusée. Retourne le budget total de cette liste de fusée (en prenant en compte les explosions).

## Issue:
- java: package org.junit.jupiter.api does not exist -> add junit to compile scope in settings (Ctlr + Alt + Shift + s)
