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
package ru.datamart.kafka.postgres.writer.repository;

import ru.datamart.kafka.postgres.writer.model.InsertDataContext;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InsertDataContextRepository {
    private final Map<String, InsertDataContext> map = new ConcurrentHashMap<>();

    public Optional<InsertDataContext> get(String contextId) {
        return Optional.ofNullable(map.get(contextId));
    }

    public void add(InsertDataContext context) {
        map.put(context.getContextId(), context);
    }

    public Optional<InsertDataContext> remove(String contextId) {
        return Optional.ofNullable(map.remove(contextId));
    }
}
