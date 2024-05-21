package tracker.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.GenericMessageEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import tracker.Player;
import tracker.ScrapingService;

import java.util.Optional;

public class getPlayer extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        super.onMessageReceived(event);

        String[] message = event.getMessage().getContentRaw().split(" ");

        if(message[0].equalsIgnoreCase("/rank")) {
            if (message.length == 1) {
                event.getChannel().sendMessage("Digite um usuário.").queue();
            }
            else {
                String[] nickTag = message[1].split("#");

                String nick = nickTag[0];
                String tag = nickTag[1];

                ScrapingService scrapingService = new ScrapingService();
                Optional<Player> player = scrapingService.getPlayer(nick, tag);

                if (player.isPresent()) {
                    EmbedBuilder eb = new EmbedBuilder();
                    eb.setTitle("Stats do: " + player.get().nick());
                    eb.addField("Elo", player.get().rank(), true);
                    event.getChannel().sendMessageEmbeds(eb.build()).queue();
                } else {
                    event.getChannel().sendMessage("Ocorreu um erro ao obter o jogador").queue();
                }

            }
        }
    }
}
