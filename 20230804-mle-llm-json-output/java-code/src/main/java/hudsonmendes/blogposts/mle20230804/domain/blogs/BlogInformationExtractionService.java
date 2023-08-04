package hudsonmendes.blogposts.mle20230804.domain.blogs;

public interface BlogInformationExtractionService {

	BlogInformation extractFrom(final String blogText) throws BlogInformationExtractionDeserialisationException;

}
