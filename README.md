# Projet JEE - Site de prêt de livres en ligne

Un site prenant en compte un ensemble de bibliothèques afin de permettre la réservation et le prêt de livres directement depuis internet en quelques clics.


## Fonctionnalités

- Rechercher et réserver un livre à partir d'un ensemble de bibliothèques
- Suivre les différents prêts en cours sur son profil, pouvoir modifier ses informations personnelles.
- Rechercher un auteur en particulier ou un livre spécifique
- Un espace administrateur pour pouvoir ajouter un livre/auteur/éditeur facilement


## Cas d'utilisation

Le site dispose d'un système d'authentification où chaque utilisateur peut s'inscrire afin d'accéder à l'entièreté des fonctionnalités du site. Il faut un grade administrateur pour accéder au panel de gestion des prêts.

Nous avons fait le choix de garder la base de données h2 en type mémoire (elle ne conserve aucune donnée une fois le serveur éteint) afin de faciliter les tests et la démonstration. Il est toutefois possible de passer la base de données en stockage disque pour une utilisation finale du site.

Nous avons créé deux utilisateurs pour pouvoir tester l'entièreté des fonctionnalités ainsi que des bibliothèques/livres/auteurs/éditeurs par défaut.
Utilisateur admin - `admin@gmail.com : admin` et Utilisateur lambda - `test@gmail.com : test`

L'admin dispose d'un panel complet pour gérer l'ensemble des prêts, ajouter un livre/auteur/éditeur. Chaque admin est lié à une bibliothèque en particulier.

L'utilisateur peut rechercher un livre ou un auteur précis via les différentes pages à l'aide d'une barre de recherche ainsi que de filtres. Il peut alors ajouter le livre en question à son panier ou à sa liste de souhaits. Le panier permet ensuite de valider le prêt des livres. L'administrateur devra alors confirmer que le livre a bien été pris sur place via le panel administrateur. La liste de souhaits permet de sauvegarder des livres pour un prêt ultérieur.

Chaque utilisateur peut modifier ses informations personnelles sur sa page de profil et voir l'état de ses prêts en cours.
