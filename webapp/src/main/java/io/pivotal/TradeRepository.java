package io.pivotal;

import io.mross.Person;
import io.mross.Trade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by mross on 9/12/16.
 */
@RepositoryRestResource(collectionResourceRel = "Trades", path = "Trades")

public interface TradeRepository extends CrudRepository<Trade, Long> {
}
