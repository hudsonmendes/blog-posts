package hudsonmendes.blogposts.mle20230804.domain.blogs;

import static java.lang.String.format;
import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.IOException;

import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service
public class BlogPromptTemplateReaderImpl implements BlogPromptTemplateReader {

	private final ResourceLoader resourceLoader;

	public BlogPromptTemplateReaderImpl(final ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	@Override
	public String read() throws IOException {
		final var template = readTemplateFromClassPath();
		assertTemplateIsValid(template);
		return template;
	}

	private String readTemplateFromClassPath() throws IOException {
		final var resourceName = "classpath:/prompts/blog-information-extraction.prompt";
		final var resourceInstance = resourceLoader.getResource(resourceName);
		return resourceInstance.getContentAsString(UTF_8);
	}

	private void assertTemplateIsValid(final String template) {
		if (template == null || template.isBlank())
			throw new IllegalArgumentException("The prompt template must not be empty");
		if (!template.contains("%s"))
			throw new IllegalArgumentException(
				format("The prompt template '%s' must have the placeholder %%s", template)
			);
	}

}
