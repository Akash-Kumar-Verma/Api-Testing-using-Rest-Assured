package RestFramework.Utilities;

import java.util.HashMap;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClientWrapper {
	public String resource;
	public String baseUrl;
	private RequestSpecification request;
	private Response restResponse;

	HashMap<String, String> headerData = new HashMap<>();

	public RestClientWrapper(String baseUrl, RequestSpecification request) {

		this.request = request;
		this.request.baseUri(baseUrl);

		headerData.put("Accept", "application/json");
		headerData.put("Content-Type", "application/json");
	}

	public Response get(String resource) throws Exception {
		restResponse = request.headers(headerData).when().get(resource);
		return restResponse;
	}

	public Response post(String resource, String bodyString) {
		restResponse = request.headers(headerData).body(bodyString).when().post(resource);

		return restResponse;
	}

	public Response delete(String resource) {
		restResponse = request.when().delete(resource);

		return restResponse;
	}

	public Response put(String resource, String bodyString) {
		restResponse = request.headers(headerData).body(bodyString).when().put(resource);

		return restResponse;
	}

}
