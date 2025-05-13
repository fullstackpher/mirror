package cn.vgonet.mirror.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpRequest;

import javax.annotation.Nullable;
import java.util.Map;

public class ApiResult<T> {
    private final String errorCode;
    private final String errorMessage;
    private final T data;

    protected ApiResult(HttpRequest request, @Nullable T body) {
        this.errorCode = resolveErrorCode(request);
        this.errorMessage = resolveErrorMessage(body);
        this.data = isError() ? null : body;
    }

    public static <U> ApiResult<U> of(HttpRequest request, @Nullable U body) {
        return new ApiResult<>(request, body);
    }

    private String resolveErrorCode(HttpRequest request) {
        return StringUtils.endsWith(request.getURI().getPath(), "/error") ? "1" : "0";
    }

    @SuppressWarnings("unchecked")
    private String resolveErrorMessage(@Nullable Object body) {
        if (!isError()) return "";
        Map<String, Object> errors = (Map<String, Object>) body;
        String error = (String) errors.get("error");
        String message = (String) errors.get("message");
        return StringUtils.equals("No message available", message) ? (StringUtils.equals("Not Found", error) ? "not-found" : "unknown") : message;
    }

    @JsonIgnore
    private boolean isError() {
        return !StringUtils.equals("0", errorCode);
    }

    @JsonProperty("errcode")
    public String getErrorCode() {
        return errorCode;
    }

    @JsonProperty("errmsg")
    public String getErrorMessage() {
        return errorMessage;
    }

    @JsonProperty("data")
    public @Nullable T getData() {
        return data;
    }
}
