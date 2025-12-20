public record Request(String accessRequest, Object protectionObject, String subject) {
	public Request {
		if (!accessRequest.equals("read") && !accessRequest.equals("write")) {
			throw new IllegalArgumentException("Invalid accessRequest: " + accessRequest);
		}
	}
}
