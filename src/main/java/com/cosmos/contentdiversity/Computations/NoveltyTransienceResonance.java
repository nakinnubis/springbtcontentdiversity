package ContentDiversity.Computations;

import ContentDiversity.DataModel.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.stream;
import static java.util.stream.IntStream.range;

public class NoveltyTransienceResonance {
    final int[] scaleList = range(1, 2).map(a -> a * 200).toArray();
    public static final double log2 = Math.log(2);

    public ArrayList main(ArrayList<Document> topicDistribution) {
        Supplier<ArrayList> collectionFactory = ArrayList::new;
        ArrayList arrayList = collectionFactory.get();
        stream(scaleList).mapToObj(scaleSize -> getNoveltyTransienceResonance(topicDistribution, scaleSize)).forEachOrdered(arrayList::add);
        range(0, 10).forEach(topicIndex -> stream(scaleList).mapToObj(scaleSize -> getNoveltyTransienceResonance(topicDistribution, scaleSize)).forEachOrdered(arrayList::add));
        return arrayList;
    }
    public ArrayList zScoresNoveltyTransienceResonance(){
        ArrayList result = new ArrayList();
        //sDeviation
        return result;
    }

    /**
     * @param pdists0 : probability distribution index zero which as 2D array
     * @param pdists1 : probability distribution index one which as 2D array
     *                probability distribution zero and probability distribution one must the same length
     * @return Kullback-Leibler Divergence which is an array of doubles for each Kullback-Leibler Divergence for an input argument of P0 2D array and P1 2D array
     */
    public static double[] klDivergenceFromProbDistArrays(double[][] pdists0, double[][] pdists1) {
        assert pdists0.length == pdists1.length;
        double[] KLdivs = klDivergence(pdists0, pdists1);
        return KLdivs;
    }

    /**
     * Read more about KL Divergence using these links:
     * 1. https://en.wikipedia.org/wiki/Kullback%E2%80%93Leibler_divergence
     * 2. http://hanj.cs.illinois.edu/cs412/bk3/KL-divergence.pdf
     * Returns the KL divergence, K(p1 || p2).
     * <p>
     * The log is w.r.t. base 2. <p>
     * <p>
     * *Note*: If any value in <tt>p2</tt> is <tt>0.0</tt> then the KL-divergence
     * is <tt>infinite</tt>. Limit changes it to zero instead of infinite.
     */
    public static double[] klDivergence(double[][] pp1, double[][] pp2) {

        ArrayList<Double> kldivres = new ArrayList<>();
        int j = 0;
        if (j < pp1.length) {
            do {
                double[] p1 = pp1[j];
                double[] p2 = pp2[j];
                double klDiv = 0.0;

                int i = 0;
                while (i < p1.length) {
                    if (p1[i] == 0) {
                        ++i;
                        j++;
                        continue;
                    }
                    if (p2[i] == 0.0) {
                        ++i;
                        j++;
                        continue;
                    } // Limin

                    klDiv += p1[i] * Math.log(p1[i] / p2[i]);
                    ++i;
                }
                double ii = klDiv / log2;
                kldivres.add(ii);
                j++;
            } while (j < pp1.length);
        }
        double[] list = new double[kldivres.size()];

        int i = 0, kldivresSize = kldivres.size();
        do {
            if (i >= kldivresSize) break;
            Double kldivre = kldivres.get(i);
            double doubleValue = kldivre.doubleValue();
            list[i] = doubleValue;
            i++;
        } while (true);
        return list;

    }

    /**
     * @param topicDistribution the topic distributions with document details and theta, importantly the theta is used but it is important to pass an arraylist of topic distribution
     *                          as this contains the blogpost details along with the theta itself which is an array of double
     * @param scaleSize         this is the scale input argument e.g currently we have a final scales ranging from [100 - 800]
     * @return A list of an arraylist of transience, novelty and resonance this is due to the fact that it is usually used with various scales so it will return these items for each scale element
     */
    public List<TransienceNoveltyResonance> getNoveltyTransienceResonance(ArrayList<Document> topicDistribution, int scaleSize) {
        int speechStart = scaleSize;
        //get the number of topic distribution rows in the array
        //this number of rows is stored as row size to replace the version of Dr. Zach doc_topic.shape[0]
        int rowSize = topicDistribution.size();
        int speechEnd = rowSize - scaleSize;
        List<TransienceNoveltyResonance> noveltyTransienceResonance = new ArrayList<>();
        ArrayList<Novelty> novelties = new ArrayList<>();
        ArrayList<Transience> transiences = new ArrayList<>();
        ArrayList<Resonance> resonances = new ArrayList<>();
//
        // center is always the current index of the topic distribution that is currently iterated.
        //Define a windows before and after center of the document according to Dr. Zach
        //Get the list of theta from an array list of topic distribution
        //This is necessary to avoid index out of bound exception
        range(speechStart, speechEnd).forEachOrdered(i -> {
            Document centerDistribution = topicDistribution.get(i);
            int afterBoxEnd = i + scaleSize + 1;
            int beforeBoxStart = i - scaleSize;
            List<double[]> thetaAsList = topicDistribution.stream().map(d -> d.theta).collect(Collectors.toList());
            long lastIndex = thetaAsList.size();
            if (lastIndex >= i) {
                //get the before topic distribution as a sublist from a list
                ArrayList<double[]> beforeDistribution = new ArrayList<>(sublist(thetaAsList, beforeBoxStart, i));
                //size or row size of before topic distribution
                int beforeNum = beforeDistribution.size();
                //array list of before center
                ArrayList<?> beforeCenterDistribution = (ArrayList<?>) Stream.of(centerDistribution.theta).map(elt -> Collections.nCopies(beforeNum, elt)).flatMap(List::stream)
                        .collect(Collectors.toList());
                //create a 2d array of double from before center distribution
                double[][] _beforeCenterDistribution = beforeCenterDistribution.toArray(new double[0][]);
                //create a 2d array of double from before distribution
                double[][] _beforeDistribution = beforeDistribution.toArray(new double[0][]);
                //create a 1d array of double from klDivergenceFromProbDistArrays that takes the before distribution and before center distribution as an argument
                double[] beforeKlDivergence = klDivergenceFromProbDistArrays(_beforeDistribution, _beforeCenterDistribution);
                //this is used to get the mean beforekld
                double novelty = stream(beforeKlDivergence).average().orElse(0.0);
                //  novelties.add(novelty);
                //    var date =  topicDistribution.get(1).blog_date;
                var novelt = new Novelty();
                novelt.date = addOneDay(centerDistribution.blog_date);
                novelt.novelty = novelty;
                novelties.add(novelt);
                //  novelties.add(novelty);
                if (lastIndex >= (i + 1) && lastIndex >= afterBoxEnd) {
                    generateTransienceAndResonance(transiences, resonances, i, centerDistribution, afterBoxEnd, thetaAsList, novelty);
                }
            }
        });
        TransienceNoveltyResonance transienceNoveltyResonance = new TransienceNoveltyResonance();
        transienceNoveltyResonance.setTransiences(transiences);
        transienceNoveltyResonance.setResonances(resonances);
        transienceNoveltyResonance.setNovelties(novelties);
        noveltyTransienceResonance.add(transienceNoveltyResonance);
        return noveltyTransienceResonance;
    }

    private void generateTransienceAndResonance(ArrayList<Transience> transiences, ArrayList<Resonance> resonances, int i, Document centerDistribution, int afterBoxEnd, List<double[]> thetaAsList, double novelty) {
        ArrayList<?> afterDistribution = new ArrayList<>(sublist(thetaAsList, i + 1, afterBoxEnd));
        int afterNum = afterDistribution.size();
        ArrayList<?> afterCenterDistribution = (ArrayList<?>) Stream.of(centerDistribution.theta).map(elt -> Collections.nCopies(afterNum, elt)).flatMap(List::stream)
                .collect(Collectors.toList());
        double[][] _afterDistribution = afterDistribution.toArray(new double[0][]);
        double[][] _afterCenterDistribution = afterCenterDistribution.toArray(new double[0][]);
        double[] afterKlDivergence = klDivergenceFromProbDistArrays(_afterDistribution, _afterCenterDistribution);
        double transience = stream(afterKlDivergence).average().orElse(0.0);
        //centerDistribution.
        var _transience = new Transience();
        _transience.date = centerDistribution.blog_date;
        _transience.transience = transience;
        transiences.add(_transience);
        //(transience);
        double resonance = novelty - transience;
        var _resonance = new Resonance();
        _resonance.date = centerDistribution.blog_date;
        _resonance.resonance = resonance;
        resonances.add(_resonance);
    }


    /**
     * @param list  : collections or list of items you want to create a sublist from
     * @param start : start position to create a sublist from an existing list
     * @param end   : end position you want the list to stop at from an existing list
     * @param <T>
     * @return
     */
    public static <T> List<T> sublist(List<T> list, int start, int end) {
        List<T> subList = new ArrayList<>(list.subList(start, end));
        return subList;
    }
    static public String addOneDay(String date) {
        return LocalDate.parse(date).plusDays(1).toString();
    }
}

