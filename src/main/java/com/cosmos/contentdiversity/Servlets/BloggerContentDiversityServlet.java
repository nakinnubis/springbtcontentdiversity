package ContentDiversity.Servlets;

import ContentDiversity.Computations.NoveltyTransienceResonance;
import ContentDiversity.DataModel.BlogPost;
import ContentDiversity.DataModel.Document;
import ContentDiversity.DataModel.TransienceNoveltyResonance;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class BloggerContentDiversityServlet extends HttpServlet {
    ArrayList<BlogPost> blogpostsArray = new ArrayList<BlogPost>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ArrayList blogPosts = null;
        try {
            ArrayList<Document> topicDistribution = new ArrayList<>();
            NoveltyTransienceResonance noveltyTransienceResonance = new NoveltyTransienceResonance();
            ArrayList _noveltyTransienceResonance = noveltyTransienceResonance.main(topicDistribution);
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            JsonArray result = (JsonArray) new Gson().toJsonTree(_noveltyTransienceResonance,
                    new TypeToken<List<TransienceNoveltyResonance>>() {
                    }.getType());
            out.print(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
//       blogposts._getBloggerByBlogId(blogIds,)
    }
}