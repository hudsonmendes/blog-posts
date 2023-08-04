package hudsonmendes.blogposts.mle20230804.domain.scrapping;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

@Service
public class WebScrappingServiceImpl implements WebScrappingService {

	@Override
	public String contentFrom(final String url) throws IOException {
		final var doc = Jsoup.connect(url).get();
        final var bodyText = doc.body().text();
        return bodyText;
	}

}
