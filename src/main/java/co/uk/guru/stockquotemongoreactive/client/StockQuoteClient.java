package co.uk.guru.stockquotemongoreactive.client;

import co.uk.guru.stockquotemongoreactive.domain.Quote;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Setter
@Component
@ConfigurationProperties("host")
public class StockQuoteClient {

    private String host;
    private String port;
    private String root;

    public Flux<Quote> getQuoteStream(){

        String url = "http://" + host + port;

        return WebClient.builder()
                .baseUrl(url)
                .build()
                .get()
                .uri(root)
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .retrieve()
                .bodyToFlux(Quote.class);
    }
}
