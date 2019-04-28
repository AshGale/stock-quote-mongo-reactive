package co.uk.guru.stockquotemongoreactive.repositories;

import co.uk.guru.stockquotemongoreactive.domain.Quote;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface QuoteRepository extends ReactiveMongoRepository<Quote, String> {


}
