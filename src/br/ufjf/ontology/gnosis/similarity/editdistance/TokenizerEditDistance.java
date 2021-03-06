
package br.ufjf.ontology.gnosis.similarity.editdistance;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;
import org.apache.commons.collections.CollectionUtils;

/**
 *
 * @author jairo
 */
public class TokenizerEditDistance implements IEditDistance {


    final private static ArrayList<String> stopwords = new ArrayList<String>();

    static {
        String stopwordsAux = "'tis,'twas,able,about,across,after,ain't,all,almost,also,among,and,any,are,aren't,because,been,but,can,can't,cannot,could,could've,couldn't,dear,did,didn't,does,doesn't,don't,either,else,ever,every,for,from,get,got,had,has,hasn't,have,he'd,he'll,he's,her,hers,him,his,how,how'd,how'll,how's,however,i'd,i'll,i'm,i've,into,isn't,it's,its,just,least,let,like,likely,may,might,might've,mightn't,most,must,must've,mustn't,neither,nor,not,off,often,only,other,our,own,rather,said,say,says,shan't,she,she'd,she'll,she's,should,should've,shouldn't,since,some,than,that,that'll,that's,the,their,them,then,there,there's,these,they,they'd,they'll,they're,they've,this,tis,too,twas,wants,was,wasn't,we'd,we'll,we're,were,weren't,what,what'd,what's,when,when,when'd,when'll,when's,where,where'd,where'll,where's,which,while,who,who'd,who'll,who's,whom,why,why'd,why'll,why's,will,with,won't,would,would've,wouldn't,yet,you,you'd,you'll,you're,you've,your";
        for (StringTokenizer stringTokenizer = new StringTokenizer(stopwordsAux, ","); stringTokenizer.hasMoreTokens();) 
            stopwords.add(stringTokenizer.nextToken());
    }


    public float compute(String s1, String s2) {

        if (s1 == null) s1 = "";
        if (s2 == null) s2 = "";

        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();
        
        HashSet<String> set1 = getNormalizedTokens(s1);
        HashSet<String> set2 = getNormalizedTokens(s2);

        float uniao = (float) CollectionUtils.union(set1, set2).size();
        return uniao != 0 ? CollectionUtils.intersection(set1, set2).size()/uniao : 0.5f;
    }

    private HashSet<String> getNormalizedTokens(String s) {

        HashSet<String> lista = new HashSet<String>();
        if (s!=null)
            for (StringTokenizer tokenizer1 = new StringTokenizer(s); tokenizer1.hasMoreTokens();) {
                String token = normalize(tokenizer1.nextToken());
                if (!stopwords.contains(token))
                    lista.add(token);
            }

        return lista;
    }


    private String normalize(String s) {

        String x = s
                .replaceAll("[.]", "")
                .replaceAll(",", "")
                .replaceAll(":", "")
                .replaceAll(";", "")
                .replaceAll("!", "")
                .replaceAll("[?]", "");

        return x;
    }
}
