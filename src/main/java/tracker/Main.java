package tracker;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import tracker.commands.getPlayer;

import java.io.IOException;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws IOException {
        Dotenv dotenv = Dotenv.load();

        String token = dotenv.get("DISCORD_TOKEN");
        JDA jda = JDABuilder.createDefault(token)
                .addEventListeners(new getPlayer())
                .enableIntents(GatewayIntent.GUILD_MESSAGES)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .build();




        ScrapingService scrapingService = new ScrapingService();
        Optional<Player> player = scrapingService.getPlayer("camundongo", "rekt");

        if (player.isPresent()) {

        } else {
            System.out.println("Ocorreu um erro ao obter o jogador");
        }
    }
}