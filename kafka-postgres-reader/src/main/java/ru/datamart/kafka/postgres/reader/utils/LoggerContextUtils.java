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
package ru.datamart.kafka.postgres.reader.utils;

import io.reactiverse.contextual.logging.ContextualData;

import java.util.UUID;

public final class LoggerContextUtils {
    private static final String REQUEST_ID = "requestId";

    private LoggerContextUtils() {
    }

    public static void setRequestId(UUID requestId) {
        ContextualData.put(REQUEST_ID, requestId.toString());
    }

    public static void setRequestId(String requestId) {
        ContextualData.put(REQUEST_ID, requestId);
    }
}
