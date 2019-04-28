package co.uk.guru.stockquotemongoreactive.service;

import co.uk.guru.stockquotemongoreactive.client.StockQuoteClient;
import co.uk.guru.stockquotemongoreactive.domain.Quote;
import co.uk.guru.stockquotemongoreactive.repositories.QuoteRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class QuoteMonitorService implements ApplicationListener<ContextRefreshedEvent> {

    private final StockQuoteClient stockQuoteClient;
    private final QuoteRepository quoteRepository;

    public QuoteMonitorService(StockQuoteClient stockQuoteClient, QuoteRepository quoteRepository) {
        this.stockQuoteClient = stockQuoteClient;
        this.quoteRepository = quoteRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        stockQuoteClient.getQuoteStream()
                //.log("quote-monitor-service")
                .subscribe(quote -> {
                    Mono<Quote> savedQuote = quoteRepository.save(quote);

                    savedQuote.subscribe(System.out::println);
                });
    }
}
