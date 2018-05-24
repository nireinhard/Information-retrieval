package searchengine;

import searchengine.parser.NYTCorpusDocument;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Document {
    private static int counter = 0;
    private long id;
    private String title;
    private String url;
    private String[] content;

    public Document(){

    }

    public Document(NYTCorpusDocument nytDoc) {
        id = ++counter;
        title = nytDoc.getHeadline();
        url = nytDoc.getUrl().toString();
        content = getDocumentContent(nytDoc.getBody());
    }

    /**
     * [^a-zA-Z0-9] replaces all non alphanumerical characters with a blank
     * ( )\1{1,} replaces all following occurences of a blank with one blank
     */
    private String[] getDocumentContent(String string){
        if (string == null) return null;
        if(!string.isEmpty()){
            string = string.replaceAll("\\<[^>]*>","").replaceAll("[^a-zA-Z0-9]"," ").replaceAll("( )\\1{1,}", " ").toLowerCase();
            String[] tmp = string.split(" ");
            return tmp;
        }
        return null;
    }

    public Map<String, Long> contentFrequency(){
        if(content != null){
            return Arrays.asList(content).stream().collect(Collectors.groupingBy(e -> e.toString().toLowerCase(),Collectors.counting()));
        }else{
            Map<String, Long> emptyMap = new HashMap<String, Long>();
            return emptyMap;
        }

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
