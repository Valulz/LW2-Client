package ovh.valulz.cvclient.model;

public class Name {

    private String name;
    private boolean maidenName;


    public Name() {
    }

    public Name(String name, boolean maidenName) {
        this.name = name;
        this.maidenName = maidenName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMaidenName() {
        return maidenName;
    }

    public void setMaidenName(boolean maidenName) {
        this.maidenName = maidenName;
    }
}
