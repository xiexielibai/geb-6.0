/*
 * Copyright 2017 the original author or authors.
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
package geb.module

import java.time.YearMonth

class MonthInput extends AbstractInput {

    final String inputType = 'month'

    void setMonth(YearMonth month) {
        value(month.toString())
    }

    void setMonth(String iso8601FormattedMonth) {
        value(iso8601FormattedMonth)
    }

    YearMonth getMonth() {
        parseMonth(value() as String)
    }

    YearMonth getMin() {
        parseMonth(attr("min"))
    }

    YearMonth getMax() {
        parseMonth(attr("max"))
    }

    Integer getStep() {
        attr("step")?.toInteger()
    }

    private YearMonth parseMonth(String string) {
        if (string == null || string.empty) {
            return null
        }
        YearMonth.parse(string)
    }

    @Override
    protected boolean isTypeValid(String type) {
        super.isTypeValid(type) || type == "text"
    }

}
