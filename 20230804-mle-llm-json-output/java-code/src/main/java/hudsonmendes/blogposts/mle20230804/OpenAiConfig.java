package hudsonmendes.blogposts.mle20230804;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.theokanning.openai.service.OpenAiService;

@Configuration
public class OpenAiConfig {

	@Bean
	OpenAiService openAiService(final @Value("${openai.api-key}") String apiKey) {
		return new OpenAiService(apiKey);
	}

}
