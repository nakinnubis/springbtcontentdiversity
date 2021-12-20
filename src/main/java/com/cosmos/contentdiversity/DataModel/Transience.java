package ContentDiversity.DataModel;

public class Transience {
    public String date;
    public double transience;

    @Override
    public String toString() {
        return "{" +
                "date:'" + date + '\'' +
                ", transience:" + transience +
                '}';
    }
}