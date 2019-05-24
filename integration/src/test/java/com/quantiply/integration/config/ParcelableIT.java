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

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.io.IOException;

import com.quantiply.integration.util.CodeGenerationHelper;
import com.quantiply.integration.util.Jsonschema2PojoRule;
import com.quantiply.integration.util.ParcelUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE, sdk=23)
public class ParcelableIT {

    @Rule public Jsonschema2PojoRule schemaRule = new Jsonschema2PojoRule();

    @Test
    public void parcelableTreeIsParcelable() throws ClassNotFoundException, IOException {
        Class<?> parcelableType = schemaRule.generateAndCompile("/schema/parcelable/parcelable-schema.json", "com.example", 
                CodeGenerationHelper.config("parcelable", true))
                .loadClass("com.example.ParcelableSchema");
        
        Parcelable instance = (Parcelable) new ObjectMapper().readValue(ParcelableIT.class.getResourceAsStream("/schema/parcelable/parcelable-data.json"), parcelableType);
        String key = "example";
        Parcel parcel = ParcelUtils.writeToParcel(instance, key);
        Parcelable unparceledInstance = ParcelUtils.readFromParcel(parcel, parcelableType, key);

        assertThat(instance, is(equalTo(unparceledInstance)));
    }

    @Test
    public void parcelableSuperclassIsUnparceled() throws ClassNotFoundException, IOException {
        // Explicitly set includeConstructors to false if default value changes in the future
        Class<?> parcelableType = schemaRule.generateAndCompile("/schema/parcelable/parcelable-superclass-schema.json", "com.example", 
                CodeGenerationHelper.config("parcelable", true, "includeConstructors", false))
                .loadClass("com.example.ParcelableSuperclassSchema");

        Parcelable instance = (Parcelable) new ObjectMapper().readValue(ParcelableIT.class.getResourceAsStream("/schema/parcelable/parcelable-superclass-data.json"), parcelableType);
        Parcel parcel = ParcelUtils.parcelableWriteToParcel(instance);
        Parcelable unparceledInstance = ParcelUtils.parcelableReadFromParcel(parcel, parcelableType, instance);

        assertThat(instance, is(equalTo(unparceledInstance)));
    }

    @Test
    public void parcelableDefaultConstructorDoesNotConflict() throws ClassNotFoundException, IOException {
        Class<?> parcelableType = schemaRule.generateAndCompile("/schema/parcelable/parcelable-superclass-schema.json", "com.example", 
                CodeGenerationHelper.config("parcelable", true, "includeConstructors", true))
                .loadClass("com.example.ParcelableSuperclassSchema");

        Parcelable instance = (Parcelable) new ObjectMapper().readValue(ParcelableIT.class.getResourceAsStream("/schema/parcelable/parcelable-superclass-data.json"), parcelableType);
        Parcel parcel = ParcelUtils.parcelableWriteToParcel(instance);
        Parcelable unparceledInstance = ParcelUtils.parcelableReadFromParcel(parcel, parcelableType, instance);

        assertThat(instance, is(equalTo(unparceledInstance)));
    }
}
