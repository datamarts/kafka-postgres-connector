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
package ru.datamart.kafka.postgres.writer.service.executor;

import ru.datamart.kafka.postgres.writer.model.kafka.InsertChunk;
import io.vertx.core.Future;
import io.vertx.pgclient.PgPool;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@Slf4j
@AllArgsConstructor
public class PostgresExecutor {
    private final PgPool pgPool;

    public Future<Integer> processChunk(InsertChunk chunk) {
        val rows = chunk.getInsertSqlRequest().getParams();
        val sql = chunk.getInsertSqlRequest().getSql();

        if (rows.isEmpty()) {
            log.info("Processed empty chunk {}", sql);
            return Future.succeededFuture(0);
        }

        return Future.future(promise -> {
            val insertedRowsCount = rows.size();
            log.info("Process chunk [{}}] BEGIN, rows: [{}]", sql, insertedRowsCount);

            pgPool.withConnection(conn -> conn.preparedQuery(sql)
                    .executeBatch(rows))
                    .map(result -> {
                        log.debug("Query [{}], completed successfully, result size: {}, affected rows: {}",
                                sql, result.size(), result.rowCount());
                        return result.size();
                    })
                    .onSuccess(size -> {
                        log.info("Process chunk [{}}] SUCCESS, rows: [{}]", sql, size);
                        promise.complete(size);
                    })
                    .onFailure(t -> {
                        log.error("Process chunk [{}}] FAILED", sql, t);
                        promise.fail(t);
                    });
        });
    }
}
