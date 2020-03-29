/*
 * Copyright (C) 2020 Marcus Pimenta
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

object Config {
    val minSdk = 16
    val compileSdk = 29
    val targetSdk = 29
}

object Versions {
    val kotlin = "1.3.71"
    val build_gradle = "3.5.3"
    val ktlint = "9.2.1"
    val ben_mane_gralde = "0.28.0"
    val junit = "4.13"
    val gson = "2.8.6"
    val dagger = "2.27"
    val dagger_compile = "2.27"
    val rxandroid = "2.1.1"
}

object Dependencies {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val build_gradle = "com.android.tools.build:gradle:${Versions.build_gradle}"
    val kotlin_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    val ktlint = "org.jlleitschuh.gradle:ktlint-gradle:${Versions.ktlint}"
    val ben_mane_gralde = "com.github.ben-manes:gradle-versions-plugin:${Versions.ben_mane_gralde}"
    val junit = "junit:junit:${Versions.junit}"
    val gson = "com.google.code.gson:gson:${Versions.gson}"
    val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    val dagger_compile = "com.google.dagger:dagger-compiler:${Versions.dagger_compile}"
    val rxandroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxandroid}"
}