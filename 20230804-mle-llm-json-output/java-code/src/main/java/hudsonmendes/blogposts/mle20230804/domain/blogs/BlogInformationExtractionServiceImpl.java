package hudsonmendes.blogposts.mle20230804.domain.blogs;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;

@Service
public class BlogInformationExtractionServiceImpl implements BlogInformationExtractionService {

	private final OpenAiService openAiService;

	private final ObjectMapper objectMapper;

	private final String template;

	public BlogInformationExtractionServiceImpl(
			final OpenAiService openAiService,
			final ObjectMapper objectMapper,
			final BlogPromptTemplateReader templateReader) throws IOException {
		this.openAiService = openAiService;
		this.objectMapper = objectMapper;
		template = templateReader.read();
	}

	@Override
	public BlogInformation extractFrom(final String blogText) throws BlogInformationExtractionDeserialisationException {
		final var req = buildRequest(blogText);
		final var res = prompt(req);
		final var json = retrieveJsonFrom(res);
		return deserialise(json);
	}

	private ChatCompletionRequest buildRequest(final String blogText) {
		return ChatCompletionRequest.builder()
			.model("gpt-4-0613")
			.temperature(0.0)
			.messages(
				List.of(
					new ChatMessage(ChatMessageRole.SYSTEM.value(), "You are an information extraction system."),
					new ChatMessage(ChatMessageRole.USER.value(), String.format(template, blogText))
				)
			)
			.build();
	}

	private ChatCompletionResult prompt(final ChatCompletionRequest req) {
		return openAiService.createChatCompletion(req);
	}

	private String retrieveJsonFrom(final ChatCompletionResult res) {
		return res.getChoices().get(0).getMessage().getContent();
	}

	private BlogInformation deserialise(final String json) throws BlogInformationExtractionDeserialisationException {
		try {
			return objectMapper.readValue(json, BlogInformation.class);
		} catch (final Exception e) {
			throw new BlogInformationExtractionDeserialisationException(json, e);
		}
	}

}
