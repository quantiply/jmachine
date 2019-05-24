/**
 * Copyright © 2010-2014 Nokia
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
package com.quantiply.gradle


import com.quantiply.rules.RuleFactory

/**
 * The configuration properties.
 *
 * @author Ben Manes (ben.manes@gmail.com)
 * @see https://github.com/joelittlejohn/jsonschema2pojo
 */
public class JsonSchemaExtension implements com.quantiply.GenerationConfig {
  Iterable<File> sourceFiles
  File targetDirectory
  String targetPackage
  com.quantiply.AnnotationStyle annotationStyle
  boolean useTitleAsClassname
  com.quantiply.InclusionLevel inclusionLevel
  String classNamePrefix
  String classNameSuffix
  String[] fileExtensions
  Class<? extends com.quantiply.Annotator> customAnnotator
  Class<? extends RuleFactory> customRuleFactory
  boolean generateBuilders
  boolean includeJsonTypeInfoAnnotation
  boolean useInnerClassBuilders
  boolean includeGetters
  boolean includeSetters
  boolean includeAdditionalProperties
  boolean includeDynamicAccessors
  boolean includeDynamicGetters
  boolean includeDynamicSetters
  boolean includeDynamicBuilders
  boolean includeConstructors
  boolean constructorsRequiredPropertiesOnly
  boolean includeHashcodeAndEquals
  boolean includeJsr303Annotations
  boolean includeJsr305Annotations
  boolean useOptionalForGetters
  boolean includeToString
  String[] toStringExcludes
  boolean initializeCollections
  String outputEncoding
  boolean parcelable
  boolean serializable
  char[] propertyWordDelimiters
  boolean removeOldOutput
  com.quantiply.SourceType sourceType
  String targetVersion
  boolean useCommonsLang3
  boolean useDoubleNumbers
  boolean useBigDecimals
  boolean useJodaDates
  boolean useJodaLocalDates
  boolean useJodaLocalTimes
  String dateTimeType
  String dateType
  String timeType
  boolean useLongIntegers
  boolean useBigIntegers
  boolean usePrimitives
  FileFilter fileFilter
  boolean formatDates
  boolean formatTimes
  boolean formatDateTimes
  String customDatePattern
  String customTimePattern
  String customDateTimePattern
  String refFragmentPathDelimiters
  com.quantiply.SourceSortOrder sourceSortOrder
  com.quantiply.Language targetLanguage
  Map<String, String> formatTypeMapping

  public JsonSchemaExtension() {
    // See DefaultGenerationConfig
    generateBuilders = false
    includeJsonTypeInfoAnnotation = false
    useInnerClassBuilders = false
    usePrimitives = false
    sourceFiles = []
    targetPackage = ''
    propertyWordDelimiters = ['-', ' ', '_'] as char[]
    useLongIntegers = false
    useBigIntegers = false
    useDoubleNumbers = true
    useBigDecimals = false
    includeHashcodeAndEquals = true
    includeConstructors = false
    constructorsRequiredPropertiesOnly = false
    includeToString = true
    toStringExcludes = [] as String[]
    annotationStyle = com.quantiply.AnnotationStyle.JACKSON
    useTitleAsClassname = false
    inclusionLevel = com.quantiply.InclusionLevel.NON_NULL
    customAnnotator = com.quantiply.NoopAnnotator.class
    customRuleFactory = RuleFactory.class
    includeJsr303Annotations = false
    includeJsr305Annotations = false
    useOptionalForGetters = false
    sourceType = com.quantiply.SourceType.JSONSCHEMA
    outputEncoding = 'UTF-8'
    useJodaDates = false
    useJodaLocalDates = false
    useJodaLocalTimes = false
    dateTimeType = null
    dateType = null
    timeType = null
    useCommonsLang3 = false
    parcelable = false
    serializable = false
    fileFilter = new com.quantiply.AllFileFilter()
    initializeCollections = true
    classNamePrefix = ''
    classNameSuffix = ''
    fileExtensions = [] as String[]
    includeAdditionalProperties = true
    includeGetters = true
    includeSetters = true
    targetVersion = '1.6'
    includeDynamicAccessors = false
    includeDynamicGetters = false
    includeDynamicSetters = false
    includeDynamicBuilders = false
    formatDates = false
    formatTimes = false
    formatDateTimes = false
    refFragmentPathDelimiters = "#/."
    sourceSortOrder = com.quantiply.SourceSortOrder.OS
    formatTypeMapping = Collections.emptyMap()
  }

  @Override
  boolean isIncludeTypeInfo() {
    return includeJsonTypeInfoAnnotation
  }

  @Override
  public Iterator<URL> getSource() {
    def urlList = []
    for (source in sourceFiles) {
      urlList.add(source.toURI().toURL())
    }
    urlList.iterator()
  }

  public void setSource(Iterable<File> files) {
    def copy = [] as List
    files.each { copy.add(it) }
    sourceFiles = copy
  }

  public void setAnnotationStyle(String style) {
    annotationStyle = com.quantiply.AnnotationStyle.valueOf(style.toUpperCase())
  }

  public void setInclusionLevel(String level) {
    inclusionLevel = com.quantiply.InclusionLevel.valueOf(level.toUpperCase())
  }
  public void setCustomAnnotator(String clazz) {
    customAnnotator = Class.forName(clazz, true, this.class.classLoader)
  }

  public void setCustomAnnotator(Class clazz) {
    customAnnotator = clazz
  }

  public void setCustomRuleFactory(String clazz) {
    customRuleFactory = Class.forName(clazz, true, this.class.classLoader)
  }

  public void setCustomRuleFactory(Class clazz) {
    customRuleFactory = clazz
  }

  public void setSourceType(String s) {
    sourceType = com.quantiply.SourceType.valueOf(s.toUpperCase())
  }

  public void setSourceSortOrder(String sortOrder) {
    sourceSortOrder = com.quantiply.SourceSortOrder.valueOf(sortOrder.toUpperCase())
  }

  public void setTargetLangauge(String language) {
    targetLangauge = Langauge.valueOf(language.toUpperCase())
  }

  @Override
  public String toString() {
    """|generateBuilders = ${generateBuilders}
       |includeJsonTypeInfoAnnotation = ${includeJsonTypeInfoAnnotation}
       |usePrimitives = ${usePrimitives}
       |source = ${sourceFiles}
       |targetDirectory = ${targetDirectory}
       |targetPackage = ${targetPackage}
       |propertyWordDelimiters = ${Arrays.toString(propertyWordDelimiters)}
       |useLongIntegers = ${useLongIntegers}
       |useBigIntegers = ${useBigIntegers}
       |useDoubleNumbers = ${useDoubleNumbers}
       |useBigDecimals = ${useBigDecimals}
       |includeHashcodeAndEquals = ${includeHashcodeAndEquals}
       |includeConstructors = ${includeConstructors}
       |includeToString = ${includeToString}
       |toStringExcludes = ${Arrays.toString(toStringExcludes)}
       |annotationStyle = ${annotationStyle.toString().toLowerCase()}
       |useTitleAsClassname = ${useTitleAsClassname}
       |inclusionLevel = ${com.quantiply.InclusionLevel.toString() }
       |customAnnotator = ${customAnnotator.getName()}
       |customRuleFactory = ${customRuleFactory.getName()}
       |includeJsr303Annotations = ${includeJsr303Annotations}
       |includeJsr305Annotations = ${includeJsr305Annotations}
       |useOptionalForGetters = ${useOptionalForGetters}
       |sourceType = ${sourceType.toString().toLowerCase()}
       |removeOldOutput = ${removeOldOutput}
       |outputEncoding = ${outputEncoding}
       |useJodaDates = ${useJodaDates}
       |useJodaLocalDates = ${useJodaLocalDates}
       |useJodaLocalTimes = ${useJodaLocalTimes}
       |dateTimeType = ${dateTimeType}
       |dateType = ${dateType}
       |timeType = ${timeType}
       |parcelable = ${parcelable}
       |serializable = ${serializable}
       |initializeCollections = ${initializeCollections}
       |classNamePrefix = ${classNamePrefix}
       |classNameSuffix = ${classNameSuffix}
       |fileExtensions = ${Arrays.toString(fileExtensions)}
       |includeGetters = ${includeGetters}
       |includeSetters = ${includeSetters}
       |targetVersion = ${targetVersion}
       |includeDynamicAccessors = ${includeDynamicAccessors}
       |includeDynamicGetters = ${includeDynamicGetters}
       |includeDynamicSetters = ${includeDynamicSetters}
       |includeDynamicBuilders = ${includeDynamicBuilders}
       |formatDates = ${formatDates}
       |formatTimes = ${formatTimes}
       |formatDateTimes = ${formatDateTimes}
       |customDatePattern = ${customDatePattern}
       |customTimePattern = ${customTimePattern}
       |customDateTimePattern = ${customDateTimePattern}
       |refFragmentPathDelimiters = ${refFragmentPathDelimiters}
       |sourceSortOrder = ${sourceSortOrder}
       |targetLanguage = ${targetLanguage}
       |formatTypeMapping = ${formatTypeMapping}
       |useInnerClassBuilders = ${useInnerClassBuilders}
     """.stripMargin()
  }
  
  public boolean isFormatDateTimes() {
    return formatDateTimes
  }

}