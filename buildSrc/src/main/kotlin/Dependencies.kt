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
    val kotlin = "1.3.72"
    val build_gradle = "3.5.3"
    val ktlint = "9.2.1"
    val ben_mane_gralde = "0.28.0"
    val junit = "4.13"
    val mockito_inline = "3.3.3"
    val mockito_core = "3.3.3"
    val mockito_kotlin = "2.1.0"
    val gson = "2.8.6"
    val dagger = "2.27"
    val dagger_compile = "2.27"
    val rxandroid = "2.1.1"
    val retrofit = "2.7.2"
    val retrofit_converter_gson = "2.7.2"
    val okhttp = "4.4.1"
    val logging_interceptor = "4.4.1"
    val retrofit2_rxjava2_adapter = "1.0.0"
    val androidx_junit = "1.1.1"
    val androidx_espresso = "3.2.0"
    val androidx_appcompat = "1.1.0"
    val androidx_core = "1.2.0"
    val androidx_constraintlayout = "1.1.3"
    val androidx_recyclerview = "1.1.0"
    val androidx_cardview = "1.0.0"
    val androidx_swiperefreshlayout = "1.0.0"
    val androidx_material = "1.1.0-alpha04"
    val firebase_ads = "18.0.0"
    val mp_android_chart = "3.1.0"
}

object Dependencies {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val build_gradle = "com.android.tools.build:gradle:${Versions.build_gradle}"
    val kotlin_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    val ktlint = "org.jlleitschuh.gradle:ktlint-gradle:${Versions.ktlint}"
    val ben_mane_gralde = "com.github.ben-manes:gradle-versions-plugin:${Versions.ben_mane_gralde}"
    val junit = "junit:junit:${Versions.junit}"
    val mockito_inline = "org.mockito:mockito-inline:${Versions.mockito_inline}"
    val mockito_core = "org.mockito:mockito-core:${Versions.mockito_core}"
    val mockito_kotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockito_kotlin}"
    val gson = "com.google.code.gson:gson:${Versions.gson}"
    val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    val dagger_compile = "com.google.dagger:dagger-compiler:${Versions.dagger_compile}"
    val rxandroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxandroid}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val retrofit_converter_gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit_converter_gson}"
    val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    val logging_interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.logging_interceptor}"
    val retrofit2_rxjava2_adapter = "com.jakewharton.retrofit:retrofit2-rxjava2-adapter:${Versions.retrofit2_rxjava2_adapter}"
    val androidx_junit = "androidx.test.ext:junit:${Versions.androidx_junit}"
    val androidx_espresso = "androidx.test.espresso:espresso-core:${Versions.androidx_espresso}"
    val androidx_appcompat = "androidx.appcompat:appcompat:${Versions.androidx_appcompat}"
    val androidx_core = "androidx.core:core-ktx:${Versions.androidx_core}"
    val androidx_constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.androidx_constraintlayout}"
    val androidx_recyclerview = "androidx.recyclerview:recyclerview:${Versions.androidx_recyclerview}"
    val androidx_cardview = "androidx.cardview:cardview:${Versions.androidx_cardview}"
    val androidx_swiperefreshlayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.androidx_swiperefreshlayout}"
    val androidx_material = "com.google.android.material:material:${Versions.androidx_material}"
    val firebase_ads = "com.google.firebase:firebase-ads:${Versions.firebase_ads}"
    val mp_android_chart = "com.github.PhilJay:MPAndroidChart:v${Versions.mp_android_chart}"
}