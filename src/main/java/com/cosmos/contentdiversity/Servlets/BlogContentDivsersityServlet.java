package ContentDiversity.Servlets;

import ContentDiversity.Computations.NoveltyTransienceResonance;
import ContentDiversity.DataModel.BlogPost;
import ContentDiversity.DataModel.Document;
import ContentDiversity.DataModel.TransienceNoveltyResonance;
import ContentDiversity.Response.TopicModelResponse;
import ContentDiversity.TopicDistributionDataModel.LdaTopicDistribution;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
@WebServlet(name = "BlogContentDivsersity", value = "BlogContentDivsersity")
public class BlogContentDivsersityServlet extends HttpServlet {
    ArrayList<BlogPost> blogpostsArray = new ArrayList<BlogPost>();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getTopicDistribution();
//        NoveltyTransienceResonance noveltyTransienceResonance = new NoveltyTransienceResonance();
//        ArrayList<Document> topicDistribution = new ArrayList<>();
//
//        ArrayList _noveltyTransienceResonance = noveltyTransienceResonance.main(topicDistribution);
//        PrintWriter out = response.getWriter();
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//        JsonArray result = (JsonArray) new Gson().toJsonTree(_noveltyTransienceResonance,
//                new TypeToken<List<TransienceNoveltyResonance>>() {
//                }.getType());
//        out.print(result);
    }

private static void getTopicDistribution() throws IOException {
    OkHttpClient client = new OkHttpClient().newBuilder()
            .build();
    Request request = new Request.Builder()
            .url("http://144.167.35.135:9011/api/v1/dashboard/trackers/427/topic-distribution?dateStart=2017-01-01&dateEnd=2018-01-01")
            .method("GET", null)
            .addHeader("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjb3Ntb2dyYXBoZXJzQGdtYWlsLmNvbSIsImV4cCI6MTY0MDk4OTY5OX0.d0wlqj8iBVcsSHoTFfxreBKApWvyoEKZS51oIL3qAIh32e53AR7Nzae9J8RB-xWEbyraXBtYYb0OEs01qUPrXw")
            .build();
    //LdaTopicDistribution
    ResponseBody response = client.newCall(request).execute().body();
    ObjectMapper objectMapper = new ObjectMapper();
    TopicModelResponse responseMapper = objectMapper.readValue(response.string(), TopicModelResponse.class);
    var kf = responseMapper;
    LdaTopicDistribution ldaTopicDistribution = new LdaTopicDistribution();
}

}
