# Projet final de programmation orientée objet avec Java, DE2

Ce projet a été réalisé dans le cadre du module de programmation orientée objet avec java. Il a pour principaux objectifs de :
- mettre en application les acquis du module de manière générale.
- développer un programme exécutable en mode bash.
- mettre en place une programme qui exécute des requêtes sur une base de données relationnelles.
- implémenter des tests unitaires avec JUnit


## Installation du projet depuit github
Pour cloner ce projet, exécutez la ligne de commande suivante
```
git clone https://github.com/ivancocold/de2_java_remboursement
```

Le script sql permet de créer la base de données sur MySQL.


## Description du programme
Le programme parcourt un dossier donné et recherche les fichiers csv nommés sous un format spécifié ("users_<YYYYMMDDHHmmSS>.csv").
Il parse ce fichier à la recherche des colonnes dont l'en-tête est au format <Numero_Securite_Sociale>, <Nom>, <Prenom>, <Date_Naissance>, <Numero_Telephone>, <E_Mail>, <ID_Remboursement>, <Code_Soin>, <Montant_Remboursement>.
Il insère les lignes de ce fichier dans une base de données MySQL si l'ID_remboursement n'y était pas déjà présent, si non il actualise les lignes dont l'ID est déjà renseigné.
Après avoir géré un fichier, le programme le déplace pour l'archiver dans un dossier renseigné préalablement.

## Description des classes
5 classes ont été développées dans ce projet.
1. connectiontodatabase : elle gère les objets et les méthodes liés à la connexion entre le programme et la base de données.
2. processcsv : gère les méthodes et objets permettant de parser un dossier à la recherche d'un fichier de notre choix et d'en extraire les données nécessaires.
3. removefile : permet de déplacer le fichier qui vient d'être géré.
4. main : permet de lancer et exécuter le programme en faisant intéragir les différentes classes. Elle lance notamment la recherche des fichiers à traiter dans un dossier spécifié. Elle applique aux fichiers d'intérêt différentes méthodes développées dans les différentes autres classes.
5. test : implémente les tests unitaires avec les framework JUnit et Mockito.

## Variables à actualiser
La portabilité de ce projet est assurée par l'usage de variables de configuration. Classe par classe, nous retrouvons les variables suivantes:
1. connectiontodatabase:
* public static final String db_server : serveur de base de données.
* public static final String port: numéro de port de la base. MySQL est sur le port 3306 par défaut.
* public static final String db_name= nom de la base de données.
* public static final String table: nom de la table.
* public static final String DB_USER: nom d'utilisateur.
* public static final String DB_PASSWORD: mot de passe.

2. processcsv :
* public static final String DIRECTORY_PATH: chemin vers le dossier de recherche des CSV.
* public static final String ARCHIVE_DIRECTORY: dossier d'archivage des fichiers traités.

3. test: j'ai utilisé des données factices pour réaliser mes tests. Vous pouvez les adapter à votre bon vouloir. Les commentaires du code vous guideront sur les modifications à réaliser.

## Choix des technologies utilisées
1. MySQL : MySQL est un serveur de base données relationneles très pratique. Etant donné que j'ai déjà réalisé un projet java - oracle et que j'essaie de monter en compétence sur MySQL depuis quelques mois, j'ai fait le choix de la sécurité et de la maitrise, d'autant que le projet java est tombé sur une période critique (plusieurs examens et projets à rendre en si peu de temps). Enfin, mon ordinateur a des ressources limitées et devoir installer un nouveau Système de Gestion de Base de Données Relationnelles aurait causer quelques difficultés.
MySQL a une grande documentation et une communauté assez large en cas d'un éventuel besoin d'aide.

2. IntelliJ IDEA 2024.1.2 : le choix d'intelliJ est assez annecdotique. On peut utiliser n'importe quel autre IDE java.

## Choix des principales bibliothèques et frameworks utilisés
1. slf4j-api et logback-classic : permettent de gérer la gestion des exceptions avec un logging robuste.

2. mockito-core et junit : mockito est un framework open source de programmation de doublures pour le langage de programmation Java. Mockito est utilisé pour simuler des interfaces afin qu'une fonctionnalité fictive puisse remplacer une fonctionnalité réelle que l'on ne souhaite pas utiliser dans un test unitaire. *Il  peut être utilisé en conjonction avec JUnit. Il permet de créer des objets doublures à partir de n'importe quelle classe ou d'interface.
JUnit est un framework de test largement utilisé pour les applications Java, conçu pour aider à écrire, organiser et exécuter des tests unitaires.  Il fournit un ensemble d'annotations, d'assertions et d'exécuteurs de tests pour créer, gérer et exécuter des cas de test. JUnit se concentre principalement sur le test d’unités de code individuelles, telles que des méthodes ou des classes, de manière isolée.
La paire mockito-core et junit m'apportent une série d'avantages pour l'écriture de tests unitaires efficaces et robustes.

3. mysql-connector-java : ce connecteur me permet de réaliser des requêtes SQL sur ma base de données MySQL.

4. maven : maven est une outil contraint par le commanditaire du projet. Tout de même, c'est un gestionnaire de projet java qui offre les fonctionnalités suivantes:
* Automatisation de la gestion de projets Java.
* Compilation et déploiement des applications Java (JAR, WAR)
* Gestion des librairies requises par l'application
* Exécution des tests unitaires
* Génération des documentations du projet (site web, pdf, Latex)
* Intégration dans différents IDE (Eclipse, JBulder)
* Bonne gestion des dépendances, d'automatisation de la construction et de standardisation des projets

5. java.io : package fondamental de la bibliothèque standard Java qui me sert dans la manipulation des fichiers et des répertoires, notamment à travers sa classe File, FileReader et FileWriter. Il a l'avantage de ne pas requérir l'utilisation davantage de dépendances.

Il existe d'autres packages que j'ai utilisés et dont le détail est donné dans les commentaires du code.

## Tests unitaires réalisés

1. Traitement de fichiers CSV : vérifie que les fichiers CSV sont correctement traités et supprimés. Cela inclue l'insertion et l'actualisation des lignes cpncernées dans la base de données.
2. Exctraction de date :  vérifie que la date est correctement extraite et convertie à partir du nom de fichier.
3. Déplacement de fichiers: vérifie que les fichiers sont déplacés correctement vers un répertoire d'archivage.

Ce projet montre comment utiliser les acquis du module de programmation orientée objet pour développer un programme Java complet. Les perspectives d'amélioration incluent l'optimisation des performances, l'ajout de nouvelles fonctionnalités (chiffrement des données échangées entre le code java et la base données) et l'amélioration de la portabilité.
