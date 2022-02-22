/*
 * Copyright © 2022 DATAMART LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ru.datamart.kafka.postgres.writer.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "vertx.verticle")
public class VerticleProperties {
    private QueryProperties query;
    private KafkaConsumerWorkerProperties consumer;
    private CommitWorkerProperties commit;
    private InsertWorkerProperties insert;

    @Data
    public static class QueryProperties {
        private Integer instances = 12;
    }

    @Data
    public static class InsertWorkerProperties {
        private int poolSize = 32;
        private long insertPeriodMs = 1000;
        private int batchSize = 1000_000;
    }

    @Data
    public static class KafkaConsumerWorkerProperties {
        private int poolSize = 32;
        private int maxFetchSize = 10_000;
    }

    @Data
    public static class CommitWorkerProperties {
        private int poolSize = 1;
        private long commitPeriodMs = 1000;
    }
}
