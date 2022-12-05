package fr.cytech.jeeProject.jeeProject.enums;

public enum BookFormat {

    POCHE("Format Poche"), A4("Format A4"), BROCHE("Format Broché"), REMBORDEE("Couverture Rembordée");

    String name;

    BookFormat(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
