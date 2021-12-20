package ContentDiversity.TopicDistributionDataModel;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

public class LdaTopicDistribution {
    public String blogPostId;
    public  String date;
    public String topicSummary;
    public List<Dictionary<Integer, Double>> lda;
    public double[] theta;
    public Hashtable<String,List<HashMap<String, Integer>>> topKeyWords;
}
