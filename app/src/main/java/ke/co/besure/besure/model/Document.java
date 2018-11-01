package ke.co.besure.besure.model;

public class Document {
    private String title, link;

    public Document(String title, String link){
        this.link = link;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
