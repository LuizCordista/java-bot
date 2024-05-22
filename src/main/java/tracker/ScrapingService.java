package tracker;

import org.htmlunit.FailingHttpStatusCodeException;
import org.htmlunit.WebClient;
import org.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScrapingService {

    public ScrapingService() {};

    public Optional<Player> getPlayer(String nick, String tag) {
        WebClient client = new WebClient();
        String urlTemplate = "https://tracker.gg/valorant/profile/riot/%s#%s/overview";
        String formattedUrl = String.format(urlTemplate, nick, tag);
        formattedUrl = formattedUrl.replace("#", "%23");
        try {
            HtmlPage searchPage = client.getPage(formattedUrl);
            String nickname = searchPage.getByXPath("/html/body/div/div/div[2]/div[3]/div/main/div[3]/div[1]/div[2]/div[2]/div[2]/span/span[1]/span/text()").toString().replace("[", "").replace("]", "");
            String dmr = searchPage.getByXPath("/html/body/div/div/div[2]/div[3]/div/main/div[3]/div[3]/div[2]/div[2]/div[1]/div[1]/div[3]/div[1]/div/div[2]/span[2]/span/text()").toString().replace("[", "").replace("]", "");
            String kd = searchPage.getByXPath("/html/body/div/div/div[2]/div[3]/div/main/div[3]/div[3]/div[2]/div[2]/div[1]/div[1]/div[3]/div[2]/div/div[2]/span[2]/span/text()").toString().replace("[", "").replace("]", "");
            String hs = searchPage.getByXPath("/html/body/div/div/div[2]/div[3]/div/main/div[3]/div[3]/div[2]/div[2]/div[1]/div[1]/div[3]/div[3]/div/div[2]/span[2]/span/text()").toString().replace("[", "").replace("]", "");
            String winRate = searchPage.getByXPath("/html/body/div/div/div[2]/div[3]/div/main/div[3]/div[3]/div[2]/div[2]/div[1]/div[1]/div[3]/div[4]/div/div[2]/span[2]/span/text()").toString().replace("[", "").replace("]", "");
            String kast = searchPage.getByXPath("/html/body/div/div/div[2]/div[3]/div/main/div[3]/div[3]/div[2]/div[2]/div[1]/div[1]/div[5]/div[2]/div/div[2]/span[2]/span/text()").toString().replace("[", "").replace("]", "");
            String rankUrl = searchPage.getByXPath("/html/body/div/div/div[2]/div[3]/div/main/div[3]/div[3]/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/div/div[1]/img/@src").toString();
            Pattern pattern = Pattern.compile("value=(.*?)\\]");
            Matcher matcher = pattern.matcher(rankUrl);
            if (matcher.find()) {
                rankUrl = matcher.group(1);
            }

            return Optional.of(new Player(nickname, dmr, kd, hs, winRate, kast, rankUrl));
        } catch (FailingHttpStatusCodeException e) {
            System.out.println("Usuario não encontrado");
        } catch (IOException e) {
            System.out.println("Erro ao acessar o site");
        }
        return Optional.empty();
    }
}