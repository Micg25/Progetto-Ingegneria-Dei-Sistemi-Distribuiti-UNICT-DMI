import java.util.concurrent.Callable;

/** The last parameter of Request is the method to be called in the protection object. */
public record Request(String accessRequest, Object protectionObject, String subject, Callable<?> action) {
	public Request {
		if (!accessRequest.equals("read") && !accessRequest.equals("write")) {
			throw new IllegalArgumentException("Invalid accessRequest: " + accessRequest);
		}
	}
}
