package fr.cytech.jeeProject.jeeProject.bootstrap;

import fr.cytech.jeeProject.jeeProject.beans.*;
import fr.cytech.jeeProject.jeeProject.dao.*;
import fr.cytech.jeeProject.jeeProject.enums.BookFormat;
import fr.cytech.jeeProject.jeeProject.enums.UserRole;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorDao authorDao;
    private final BookDao bookDao;
    private final LibraryDao libraryDao;
    private final PublisherDao publisherDao;
    private final SiteUserDao siteUserDao;

    public BootStrapData(AuthorDao authorDao, BookDao bookDao, LibraryDao libraryDao, PublisherDao publisherDao, SiteUserDao siteUserDao) {
        this.authorDao = authorDao;
        this.bookDao = bookDao;
        this.libraryDao = libraryDao;
        this.publisherDao = publisherDao;
        this.siteUserDao = siteUserDao;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started in boostrap");

        Publisher hugoPoche = new Publisher("Hugo Poche", "34-36 rue La Pérouse, 75116 Paris");
        Publisher robertLaffont = new Publisher("Robert Laffont", "92 avenue France, 75013 Paris");
        Publisher jaiLu = new Publisher("J'ai lu", "82 rue Saint-Lazare, 75009 Paris");

        Author hoover = new Author("Colleen", "Hoover", "https://www.babelio.com/users/AVT_Colleen-Hoover_7574.jpg");
        Author silvera = new Author("Adam", "Silvera", "https://cdn.writermag.com/2016/12/AdamSilvera_credit-Margot-Wood-e1482171189415.jpg");
        Author ankaoua = new Author("Maud", "Ankaoua", "https://www.maud-ankaoua.com/img/maud-large.jpg");

        Book jamaisPlus = new Book("Jamais plus", "Ceux que nous aimons sont parfois ceux qui nous font le plus mal. Lily Blossom Bloom n'a pas eu une enfance très facile, entre un père violent et une mère qu'elle trouve soumise, mais elle a su s'en sortir dans la vie et est à l'aube de réaliser son grand rêve : ouvrir, à Boston, une boutique de fleurs. Elle vient de rencontrer un neurochirurgien, Ryle, charmant, ambitieux, visiblement aussi attiré par elle qu'elle l'est par lui. Le chemin de Lily vers le bonheur semble tout tracé. Elle hésite pourtant encore un peu : il n'est pas facile pour elle de se lancer dans une histoire sentimentale, avec des parents comme les siens. Choisir cette vie, c'est aussi tirer un trait sur son passé et Atlas, ce jeune homme qui a été son premier amour et qui a profondément marqué son adolescence. L'avenir semble limpide et simple mais il peut s'obscurcir très vite...",
                512, "05/04/2018", "2755637080", "https://static.fnac-static.com/multimedia/Images/FR/NR/5e/d5/8d/9295198/1540-1/tsp20221203063351/Jamais-plus.jpg", BookFormat.POCHE, hugoPoche);
        Book meurentALaFin = new Book("Et ils meurent tous les deux à la fin", "Le 5 septembre, un peu après minuit, Mateo et Rufus reçoivent chacun le funeste appel. Ils ne se connaissent pas, mais cherchent tous deux à se faire un nouvel ami en ce jour fi nal. Heureusement, il existe aussi une appli pour ça, Le Dernier Ami. Grâce à elle, Rufus et Mateo vont se rencontrer pour une ultime grande aventure : vivre toute une vie en une seule journée.",
                414, "24/05/2018", "222121823X", "https://static.fnac-static.com/multimedia/Images/FR/NR/ce/85/90/9471438/1540-1/tsp20221015062004/Et-ils-meurent-tous-les-deux-a-la-fin.jpg", BookFormat.POCHE, robertLaffont);
        Book kilometreZero = new Book("Kilomètre zéro", "Maëlle, directrice financière d'une start-up en pleine expansion, n'a tout simplement pas le temps pour les rêves. Mais quand sa meilleure amie, Romane, lui demande un immense service - question de vie ou de mort -, elle accepte malgré elle de rejoindre le Népal. Elle ignore que l'ascension des Annapurnas qu'elle s'apprête à faire sera aussi le début d'un véritable parcours initiatique. Au cours d'expériences et de rencontres bouleversantes, Maëlle va apprendre les secrets du bonheur profond et transformer sa vie. Mais réussira-t-elle à sauver son amie ?",
                384, "02/10/2019", "229021051X", "https://static.fnac-static.com/multimedia/Images/FR/NR/cb/b3/ac/11318219/1540-1/tsp20221125063253/Kilometre-zero.jpg", BookFormat.POCHE, jaiLu);

        publisherDao.save(hugoPoche);
        publisherDao.save(robertLaffont);
        publisherDao.save(jaiLu);

        authorDao.save(hoover);
        authorDao.save(silvera);
        authorDao.save(ankaoua);

        jamaisPlus.getAuthors().add(hoover);
        meurentALaFin.getAuthors().add(silvera);
        kilometreZero.getAuthors().add(ankaoua);


        SiteUser siteUser = new SiteUser();
        siteUser.setName("Dorian");
        siteUser.setSurname("Carlone");
        siteUser.setUserRole(UserRole.DEFAULT);
        siteUser.setAddress("17 Rue Bernadotte");
        siteUser.setPassword("rolandgarros");
        siteUser.setEmail("dorian.carlone@yahoo.fr");
        siteUser.setCookieCode("3e584755-1bdf-4afc-a02b-74f9a0d7c88d");

        siteUserDao.save(siteUser);

        jamaisPlus.setCurrentHolder(siteUser);

        bookDao.save(jamaisPlus);
        bookDao.save(meurentALaFin);
        bookDao.save(kilometreZero);

        //Libraries
        Library libraryNationaleFrance = new Library();
        libraryNationaleFrance.setName("Bibliothèque Nationale de France");
        libraryNationaleFrance.setDescription("La Bibliothèque nationale de France, ainsi dénommée depuis 1994, est la bibliothèque nationale de la République française, inaugurée sous cette nouvelle appellation le 30 mars 1995 par le président de la République, François Mitterrand.");
        libraryNationaleFrance.setAddressLine("Quai François Mauriac, 75706 Paris");
        libraryNationaleFrance.setBackgroundImage("https://media.admagazine.fr/photos/63207bb614420dbf05ebc6b1/16:9/w_2560%2Cc_limit/La%2520salle%2520Ovale%2520re%25CC%2581nove%25CC%2581e%2520%25C2%25A9%2520Jean-Christophe%2520Ballot%2520-%2520BnF%2520-%2520Oppic.jpg");
        libraryNationaleFrance.setUrlShort("nationale-france");
        libraryNationaleFrance.getBooks().add(kilometreZero);
        libraryNationaleFrance.getBooks().add(jamaisPlus);

        Library libraryForney = new Library();
        libraryForney.setName("Bibliothèque Forney");
        libraryForney.setDescription("La Société des Amis de la Bibliothèque Forney fut créée en juin 1914. Elle fut fondée par Henri Clouzot, conservateur de la Bibliothèque Forney de 1908 à 1920.");
        libraryForney.setAddressLine("1 Rue du Figuier, 75004 Paris");
        libraryForney.setBackgroundImage("https://static.affluences.media/sites-pictures/sites/HJTwG9lCz.jpeg");
        libraryForney.setUrlShort("forney");
        libraryForney.getBooks().add(meurentALaFin);

        Library libraryMazarine = new Library();
        libraryMazarine.setName("Bibliothèque Mazarine");
        libraryMazarine.setDescription("La bibliothèque Mazarine, communément appelée la Mazarine, est la plus ancienne bibliothèque publique de France.");
        libraryMazarine.setAddressLine("23 Quai de Conti, 75006 Paris");
        libraryMazarine.setBackgroundImage("https://upload.wikimedia.org/wikipedia/commons/c/c3/Courtyard_of_Institut_de_France_001.jpg");
        libraryMazarine.setUrlShort("mazarine");

        Library librarySainteGenevieve = new Library();
        librarySainteGenevieve.setName("Bibliothèque Sainte-Geneviève");
        librarySainteGenevieve.setDescription("La bibliothèque Sainte-Geneviève est une bibliothèque interuniversitaire et publique située au 10, place du Panthéon, dans le 5ᵉ arrondissement de Paris.");
        librarySainteGenevieve.setAddressLine("10 Pl. du Panthéon, 75005 Paris");
        librarySainteGenevieve.setBackgroundImage("https://media.timeout.com/images/105374251/image.jpg");
        librarySainteGenevieve.setUrlShort("sainte-genevieve");

        Library libraryNationalArtHistory = new Library();
        libraryNationalArtHistory.setName("Bibliothèque de l'Institut national d'histoire de l'art");
        libraryNationalArtHistory.setDescription("La bibliothèque de l'Institut national d'histoire de l'art est une bibliothèque de recherche spécialisée en histoire de l'art et archéologie.");
        libraryNationalArtHistory.setAddressLine("58 Rue de Richelieu, 75002 Paris");
        libraryNationalArtHistory.setBackgroundImage("https://www.inha.fr/_contents/ametys-internal%253Asites/inha/ametys-internal%253Acontents/accueil-entete-de-page-2/_metadata/header/1/header-illustration/image/F96A1874%2520copie.jpg?objectId=defaultWebContent%3A%2F%2F2d8e2e6a-cd2a-40f6-b7a6-9f790908e0a5");
        libraryNationalArtHistory.setUrlShort("national-art");

        libraryDao.save(libraryNationaleFrance);
        libraryDao.save(libraryForney);
        libraryDao.save(libraryMazarine);
        libraryDao.save(librarySainteGenevieve);
        libraryDao.save(libraryNationalArtHistory);

        System.out.println("Number of Books: " + bookDao.count());
        System.out.println("Number of Publishers: " + publisherDao.count());
    }
}
