/*
 * Copyright 1999-2015 dangdang.com.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package com.dangdang.ddframe.rdb.sharding.parsing.lexer;

import com.dangdang.ddframe.rdb.sharding.constant.DatabaseType;
import com.dangdang.ddframe.rdb.sharding.parsing.lexer.dialect.mysql.MySQLLexer;
import com.dangdang.ddframe.rdb.sharding.parsing.lexer.dialect.oracle.OracleLexer;
import com.dangdang.ddframe.rdb.sharding.parsing.lexer.dialect.postgresql.PostgreSQLLexer;
import com.dangdang.ddframe.rdb.sharding.parsing.lexer.dialect.sqlserver.SQLServerLexer;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 词法解析器引擎工厂.
 *
 * @author zhangliang
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class LexerEngineFactory {
    
    /**
     * 创建词法解析器引擎.
     * 
     * @param dbType 数据库类型
     * @param sql SQL语句
     * @return 词法解析器引擎
     */
    public static LexerEngine newInstance(final DatabaseType dbType, final String sql) {
        switch (dbType) {
            case H2:
            case MySQL:
                return new LexerEngine(new MySQLLexer(sql));
            case Oracle:
                return new LexerEngine(new OracleLexer(sql));
            case SQLServer:
                return new LexerEngine(new SQLServerLexer(sql));
            case PostgreSQL:
                return new LexerEngine(new PostgreSQLLexer(sql));
            default:
                throw new UnsupportedOperationException(String.format("Cannot support database [%s].", dbType));
        }
    }
}