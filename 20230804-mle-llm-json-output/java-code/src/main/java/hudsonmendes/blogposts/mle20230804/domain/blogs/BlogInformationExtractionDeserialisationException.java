package hudsonmendes.blogposts.mle20230804.domain.blogs;

public class BlogInformationExtractionDeserialisationException extends Exception {

	private static final long serialVersionUID = 1L;

	private final String response;

	public BlogInformationExtractionDeserialisationException(final String response, final Throwable cause) {
		super(cause);
		this.response = response;
	}

	public String getResponse() {
		return response;
	}
}
