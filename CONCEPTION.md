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
###Breakable
###Command
###Snail
###Slime

Code particulier et expérience vidéoludique
-------------------------------------------

A améliorer
-----------
