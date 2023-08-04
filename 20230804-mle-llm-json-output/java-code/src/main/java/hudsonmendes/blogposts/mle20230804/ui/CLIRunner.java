package hudsonmendes.blogposts.mle20230804.ui;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import hudsonmendes.blogposts.mle20230804.domain.blogs.BlogInformationExtractionService;
import hudsonmendes.blogposts.mle20230804.domain.scrapping.WebScrappingService;

@Component
public class CLIRunner implements CommandLineRunner {

	private final WebScrappingService webScrappingService;

	private final BlogInformationExtractionService blogInformationExtractionService;

	public CLIRunner(
			final WebScrappingService webScrappingService,
			final BlogInformationExtractionService blogInformationExtractionService) {
		this.webScrappingService = webScrappingService;
		this.blogInformationExtractionService = blogInformationExtractionService;

	}

	@Override
	public void run(final String... args) throws Exception {
		final var url = "https://medium.com/@hudsonmendes/robo-3t-dangerous-default-value-for-create-index-in-background-7cb2f6bc9a78";
		final var blogText = webScrappingService.contentFrom(url);
		final var blogInformation = blogInformationExtractionService.extractFrom(blogText);
		System.out.print(blogInformation.toString());
	}

}
