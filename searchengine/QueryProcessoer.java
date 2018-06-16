package searchengine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class QueryProcessoer {
    public QueryProcessoer() {
    }

    public List<Accumulator> process(String query) {
        String[] queryContent = tokenizeAndNormalize(query);
        InvertedIndex ix = new InvertedIndex();

        Map<Long, Accumulator> map = new HashMap<>();

        for(String term: queryContent){
            List<Posting> postingList = ix.getIndexList(term);
            for(Posting p: postingList){
                if (map.containsKey(p.getDid())){
                    double score = p.getTf() * Math.log(ix.getSize()/ix.getDF(term));
                    Accumulator ac = map.get(p.getDid());
                    ac.setScore(ac.getScore()+score);
                }else{
                    double score = p.getTf() * Math.log(ix.getSize()/ix.getDF(term));
                    Accumulator ac = new Accumulator(p.getDid(), score);
                    map.put(p.getDid(), ac);
                }
            }
        }

        List<Accumulator> result = map.values().stream().sorted().collect(Collectors.toList());
        return result;
    }

    public List<Accumulator> process(String query, int k) {
        List<Accumulator> result = process(query);
        List<Accumulator> topK = new ArrayList<>();
        for(int i=0;i<k;i++){
            topK.add(result.remove(0));
        }
        return topK;
    }

    private String[] tokenizeAndNormalize(String query){
        String[] queryContent = query.split(" ");
        for(int i=0;i<queryContent.length;i++) {
            queryContent[i] = queryContent[i].replaceAll("\\<[^>]*>", "").replaceAll("[^a-zA-Z0-9]", " ").replaceAll("( )\\1{1,}", " ").toLowerCase();
        }
        return queryContent;
    }
}
