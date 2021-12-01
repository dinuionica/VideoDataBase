package database;

public enum EnumGenreDataBase {
    TV_MOVIE("TV Movie"), DRAMA("Drama"), FANTASY("Fantasy"),
    COMEDY("Comedy"), FAMILY("Family"), WAR("War"),
    SCI_FI_FANTASY("Sci-Fi & Fantasy"), CRIME("Crime"),
    ANIMATION("Animation"), SCIENCE_FICTION("Science Fiction"),
    ACTION("Action"), HORROR("Horror"), MYSTERY("Mystery"),
    WESTERN("Western"), ADVENTURE("Adventure"),
    ACTION_ADVENTURE("Action & Adventure"), ROMANCE("Romance"),
    THRILLER("Thriller"), KIDS("Kids"), HISTORY("History");

    private final String content;

    /* setters and getters for enum */
    EnumGenreDataBase(final String stringContent) {
        this.content = stringContent;
    }

    public String getContent() {
        return content;
    }
}
