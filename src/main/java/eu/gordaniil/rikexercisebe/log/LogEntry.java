package eu.gordaniil.rikexercisebe.log;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.MessageFormat;
import java.util.Optional;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogEntry {

    private static final String EMPTY = "-";
    private static final String FORMAT = "IP({0}), CORRELATIONID({1}), URL({2}), CONSUMERID({3}), HTTPSTATUS({4}), XREQUESTID({5})";

    private String ip;
    private String correlationId;
    private String url;
    private String consumerId;
    private String status;
    private String xRequestId;

    @Override
    public String toString() {
        return MessageFormat.format(
                FORMAT,
                getIp().orElse(EMPTY),
                getCorrelationId().orElse(EMPTY),
                getUrl().orElse(EMPTY),
                getConsumerId().orElse(EMPTY),
                getStatus().orElse(EMPTY),
                getXRequestId().orElse(EMPTY)
        );
    }

    private Optional<String> getIp() {
        return Optional.ofNullable(ip);
    }

    private Optional<String> getCorrelationId() {
        return Optional.ofNullable(correlationId);
    }

    private Optional<String> getUrl() {
        return Optional.ofNullable(url);
    }

    private Optional<String> getConsumerId() {
        return Optional.ofNullable(consumerId);
    }

    private Optional<String> getStatus() {
        return Optional.ofNullable(status);
    }

    private Optional<String> getXRequestId() {
        return Optional.ofNullable(xRequestId);
    }

}
