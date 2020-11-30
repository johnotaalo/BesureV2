package ke.co.besure.besure.model;

public class DosandDonts {
    private String title, content;
    public boolean expanded = false;

    public DosandDonts(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
