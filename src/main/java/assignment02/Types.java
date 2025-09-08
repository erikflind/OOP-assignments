package assignment02;

public enum Types {

    BUG ("Bug"),
    DRAGON ("Dragon"),
    ELECTRIC ("Electric"),
    FIRE("Fire"),
    GRASS("Grass"),
    ICE("Ice"),
    WATER("Water"),
    NORMAL("Normal");

    private final String type;

    Types (String type) { this.type = type; }

    public String getType() { return type; }

}
