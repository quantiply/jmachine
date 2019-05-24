/**
 * Copyright © 2010-2017 Nokia
 *
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
 */

package com.quantiply.integration.config;

import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import java.io.File;

import com.quantiply.integration.util.FileSearchMatcher;
import com.quantiply.integration.util.Jsonschema2PojoRule;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;

public class CommonsLangRemovalIT {

    @Rule public Jsonschema2PojoRule schemaRule = new Jsonschema2PojoRule();

    @Test
    public void hashCodeAndEqualsDontUseCommonsLang() throws SecurityException {

        File generatedOutputDirectory = schemaRule.generate("/schema/properties/primitiveProperties.json", "com.example");

        assertThat(generatedOutputDirectory, Matchers.not(FileSearchMatcher.containsText("org.apache.commons.lang3.")));
        assertThat(generatedOutputDirectory, Matchers.not(FileSearchMatcher.containsText("org.apache.commons.lang.")));

    }

}
