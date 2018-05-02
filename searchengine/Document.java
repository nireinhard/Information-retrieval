package searchengine;

import searchengine.parser.NYTCorpusDocument;

public class Document {
    private static int counter = 0;
    private long id;
    private String title;
    private String url;
    private String[] content = new String[1];

    public Document(){

    }

    public Document(NYTCorpusDocument nytDoc) {
        id = ++counter;
        title = nytDoc.getHeadline();
        url = nytDoc.getUrl().toString();
        fillContent(nytDoc.getBody());
    }

    private void fillContent(String string){
        string = string.replaceAll("\\<[^>]*>","").replaceAll("regex für alle nicht alphanumerischen"," ");


    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String[] getContent() {
        return content;
    }

    public void setContent(String[] content) {
        this.content = content;
    }

    public String toString() {
        return content[0];
    }
}
