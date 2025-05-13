package cn.vgonet.mirror.test;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import org.testcontainers.shaded.com.google.common.collect.ImmutableMap;

import javax.annotation.Resource;
import java.io.File;
import java.net.URI;
import java.util.Map;

@ExtendWith({TestContainersInitializer.class, SpringExtension.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class IntegrationTest {
    protected TestResponse lastResponse;
    protected @Resource TestRestTemplate testRestTemplate;

    protected TestResponse get(String urlTemplate, Object... vars) {
        return get(urlTemplate, ImmutableMap.of(), vars);
    }

    protected TestResponse get(String urlTemplate, Map<String, Object> params, Object... vars) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(urlTemplate);
        params.forEach(builder::queryParam);
        URI uri = builder.buildAndExpand(vars).encode().toUri();
        RequestEntity<?> request = new RequestEntity<>(requestHeader(), HttpMethod.GET, uri);
        return exchange(request);
    }

    protected TestResponse post(String urlTemplate, Object body, Object... vars) {
        return exchange(createRequest(urlTemplate, body, vars, HttpMethod.POST));
    }

    protected TestResponse put(String urlTemplate, Object body, Object... vars) {
        return exchange(createRequest(urlTemplate, body, vars, HttpMethod.PUT));
    }

    protected TestResponse delete(String urlTemplate, Object... vars) {
        return exchange(createRequest(urlTemplate, null, vars, HttpMethod.DELETE));
    }

    protected TestResponse postFile(String urlTemplate, File file) {
        HttpHeaders headers = requestHeader();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        form.add("file", new FileSystemResource(file));
        return exchange(createRequest(urlTemplate, new HttpEntity<>(form, headers), Arrays.array(), HttpMethod.POST));
    }

    private RequestEntity<?> createRequest(String urlTemplate, Object body, Object[] vars, HttpMethod method) {
        URI uri = UriComponentsBuilder.fromUriString(urlTemplate).buildAndExpand(vars).encode().toUri();
        return new RequestEntity<>(body, requestHeader(), method, uri);
    }

    private TestResponse exchange(RequestEntity<?> request) {
        TestResponse result = new TestResponse(testRestTemplate.exchange(request, String.class));
        lastResponse = result;
        return result;
    }

    private HttpHeaders requestHeader() {
        return new HttpHeaders();
    }
}
