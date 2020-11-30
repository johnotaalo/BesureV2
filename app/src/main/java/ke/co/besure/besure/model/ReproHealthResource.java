package ke.co.besure.besure.model;

public class ReproHealthResource {
    private int id;
    private String section, content;

    public ReproHealthResource(int id, String section, String content) {
        this.id = id;
        this.section = section;
        this.content = content;
    }

    public ReproHealthResource() {
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
