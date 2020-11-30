package ke.co.besure.besure.model;

public class HIVFact {
    private int id;
    private String section, content;

    public HIVFact() {
    }

    public HIVFact(int id, String section, String content) {
        this.id = id;
        this.section = section;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
