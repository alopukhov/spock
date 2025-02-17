/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.spockframework.gradle

import groovy.transform.CompileStatic
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test

@CompileStatic
class SpockBasePlugin implements Plugin<Project> {
  void apply(Project project) {
    project.tasks.withType(Test) { Test task ->
      def taskName = task.name.capitalize()
      File configFile = project.file("Spock${taskName}Config.groovy")
      if (configFile.exists()) {
        task.jvmArgumentProviders.add(new SpockConfigArgumentProvider(configFile))
      }
    }
  }
}
