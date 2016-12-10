Projet de programmation 2 : jeu de plateforme
=============================================
EPFL : introduction à la programmation  
Samuel (IN-BA1) et Daniel (SC-BA1)

Lancer le jeu
-------------
1. Télécharger l'archive zip du jeu
2. Créer un _Java Project_ dans votre environnement de développement préféré
3. Importer l'archive dans le _Java Project_ 
4. Exécuter en tant que _Java application_ (la classe main se situe dans _src/platform/Program.java_)

Contrôles
---------

### Menus
- Sélection du niveau : souris
- Recommencer un niveau : touche _R_
- Revenir au menu principal : touche _Q_

### Jeu
- Sauter : Flèche haut
- Se déplacer à gauche : Flèche gauche
- Se déplacer à droite : Flèche droite
- Lancer une boule de feu : espace
- Interagir avec un levier : touche _E_
- Souffler : touche _B_

Quelques règles
---------------
- Le but du jeu est d'atteindre la porte de sortie en vie
- Le joueur dispose de 5 vies
  - Il peut, par exemple, perdre de la vie s'il croise un personnage dangereux ou s'il se pique sur des pointes
  - Il peut regagner de la vie en ramassant des coeurs
- Le joueur est contraint à se déplacer sur les blocs de pierre. En cas de chute, la partie recommence...
- Les blocs de bois, les torches, certains personnages sont sensibles au feu... Testez l'effet de cet outil ! :-)
- Pour atteindre la porte de sortie, il est parfois nécessaire de récolter des indices, actionner des levier, etc...

Astuces
-------
- Les trampolines permettent de sauter à des hauteurs vertigineuses !
- Le personnage peut jouir de frottement contre les murs s'il est sur le point de tomber et ainsi remonter sur sa plateforme
- Le chemin le plus évident n'est pas forcément le plus rapide ! :-)
