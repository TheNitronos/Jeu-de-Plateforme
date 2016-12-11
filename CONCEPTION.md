Projet de programmation 2 : jeu de plateforme
=============================================

CONCEPTION
----------

EPFL : introduction à la programmation  
Samuel (IN-BA1) et Daniel (SC-BA1)

Classes spéciales
-----------------

###SimpleSprite
Les _SimpleSprite_ sont utiles lors de la création de classes élémentaires. A l'aide de celles-là, il devient facile de créer de petits acteurs tels que panneaux de sortie, flammes brûlantes ou encore fonds d'écran. Cette classe dispose de deux constructeurs : un si l'image est faite pour s'éteindre et un si l'image reste affichée en continu. Pour instancier une _SimpleSprite_, il est nécessaire de lui fournir un nom d'image, une position, une taille, une priorité, une durée d'affichage (optionnel) ainsi que de l'informer si elle tourne et si elle disparaît.

###Fire, Background, ExitIndic

####Informations générales
Ces trois classes héritent directement de _SimpleSprite_. Un simple constructeur et quelques paramètres suffisent à la création de ces objets.

####Fire
Les objets _Fire_ viennent se placer sur les boîtes inflammables lorsque celles-ci prennent feu. Il s'agit d'une grosse boule de feu tournante et qui fini par s'éteindre si elle n'est pas alimentée.

####Background
Cette classe simplifie la mise en place d'un fond d'écran : un simple appel au constructeur et le fond d'écran est affiché.

####ExitIndic
Similaire à _Background_, il suffit de fournir en paramètre la position à laquelle doit se placer le panneau de sortie et l'instance est créée.

###LevelBlock
_LevelBlock_ hérite de _Block_, il a la particularité d'être cliquable et de lancer le niveau dont il porte le chiffre.

###Breakable
Les _Breakable_ sont des blocs sensibles aux dégâts de type _fire_. Ils prennent feu, brûlent et finissent par se casser.

###Command
Une instance _Command_ est mise dans chaque niveau. Elle permet de gérer les touches pour recommencer le niveau et revenir au menu principal.

###Selection
Ce niveau est lancé par défaut et permet de sélectionner le niveau en cliquant sur un _LevelBlock_.

Code particulier et expérience vidéoludique
-------------------------------------------

###Méthodes de _level_

####_protected void niveauDeJeu(World nWorld)_
Cette méthode permet de mettre en place le fond d'écran, les commandes et les limites. Elle est utile dans les niveaux de jeu.

####_protected void miseEnPlaceJoueur(World nWorld, Vector nPosition)_
Avec la position en paramètre, cette méthode instancie le _Player_ et son _Overlay_.

###Méthodes de _Player_
Comme le code de _Player_ est conséquent, les actions relatives aux touches ont été mises sous formes de méthodes

###Bêbêtes monstrueuses :-)

####Snail
Le _Snail_ est un petit escargot décoratif qui se déplace lentement et qui se cache s'il est attaqué.

####Slime
Le _Slime_ est un monstre plutôt moche et dangereux pour le joueur.

###Mort du joueur
Lorsque le joueur meurt, son expression devient morbide et il s'envole vers les Cieux.

###Spikes
Lorsque le joueur se pique, ses réflexes psycho-moteurs le poussent à sauter de douleur.

A améliorer
-----------

###Modularisation
Idéalement, il serait intéressant de partager les classe de _platform.game_ en différents paquetages.

###Dessin des niveaux
La création des niveaux est rudimentaire. On pourrait créer un système qui gèrerait la création de niveaux plus proprement. Un premier pas serait de remplacer certaines suites d'acteurs répétées par des boucles.

###Bêbêtes sauvages !
Améliorer leurs intelligences pour les faire changer de direction spontanément, par exemple.

###Système de points
Evaluer le score des parties permettrait aux joueurs de comparer leurs performances.

###Nouvelles plateformes et personnages
Pour diversifier le jeu, on pourrait considérer l'ajout de nouveaux acteurs. Voici quelques idées.
- Un tapis roulant qui augmente la vitesse maximale du joueur
- Un coeur doré qui rend le joueur immortel et hyper-actif pendant un laps de temps
- Une licorne sur laquelle le joueur pourrait monter pour jouir d'une nouvelle expérience vidéoludique
