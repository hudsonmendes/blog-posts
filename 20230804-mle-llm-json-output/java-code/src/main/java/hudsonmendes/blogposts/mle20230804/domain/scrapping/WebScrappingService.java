package hudsonmendes.blogposts.mle20230804.domain.scrapping;

import java.io.IOException;

public interface WebScrappingService {

	String contentFrom(final String url) throws IOException;

}
