import java.util.Properties

plugins {
    id(libs.plugins.android.application.get().pluginId)
    id(libs.plugins.ksp.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
    id(libs.plugins.kotlinter.get().pluginId)
    alias(libs.plugins.paparazzi) apply false
    alias(libs.plugins.hilt) apply false
    id(libs.plugins.sortDependencies.get().pluginId)
    id(libs.plugins.dokka.get().pluginId)
    id(libs.plugins.protobuf.get().pluginId)


}

android {
    compileSdk = libs.versions.compileSdk.get().toInt()
    namespace = "com.hridoy.chatgemini"

    defaultConfig {
        applicationId = "com.hridoy.chatgemini"
        versionCode = 1
        versionName = "1.0"

        Properties().apply {
            load(rootProject.file("local.properties").reader())
        }.also {
            buildConfigField("String", "API_KEY", "\"${it.getProperty("API_KEY")}\"")
        }
    }
    //Build Variant
    applicationVariants.configureEach {
        val appLabelMap = when (this.buildType.name) {
            "debug" -> mapOf("develop" to "${rootProject.name} devDebug",
            "staging" to "${rootProject.name} stgDebug",
            "production" to "${rootProject.name} proDebug")
            else -> mapOf("develop" to "${rootProject.name} Develop",
                "staging" to "${rootProject.name} Staging",
                "production" to rootProject.name)
        }
        val flavor = this.productFlavors[0]
        this.mergedFlavor.manifestPlaceholders["appLabel"] = "${appLabelMap[flavor.name]}"
    }


    // Specifies one flavor dimension.
    flavorDimensions += "version"
    productFlavors {
        create("develop") {
            dimension = "version"
            applicationIdSuffix = ".develop"
            versionNameSuffix = "-develop"
           // signingConfig = signingConfigs.getByName("develop")
        }
        create("staging") {
            initWith(getByName("staging"))
            dimension = "version"
            applicationIdSuffix = ".staging"
            versionNameSuffix = "-staging"
           // signingConfig = signingConfigs.getByName("staging")
        }
        create("production") {
            dimension = "version"
            //signingConfig = signingConfigs.getByName("production")
        }
    }
    buildTypes {
        getByName("debug") {
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
            isDebuggable = true
            buildConfigField("String", "Template_HOST", "\"192.168.10.34\"")
        }
        getByName("release") {
            isMinifyEnabled = true
            isDebuggable = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            buildConfigField("String", "Template_HOST", "\"not given\"")
        }
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    implementation(libs.firebase.database.ktx)
    ksp(libs.androidx.room.compiler)
    ksp(libs.square.moshi.kotlin.codegen)

    kspAndroidTest(libs.hilt.android.compiler)

    implementation("com.google.ai.client.generativeai:generativeai:0.3.0")


    // Gradle
    implementation(platform(libs.compose.bom))
    // UI
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.android.material)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    // Navigation
    implementation(libs.androidx.compose.navigation)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.hilt.compose.navigation)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation (libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.material.icons.extended)
    // Network and Local
    implementation(libs.androidx.room.runtime)
    implementation(libs.coil.compose)
    implementation(libs.compose.material3)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling)
    // Storage
    implementation(libs.datastore)
    implementation(libs.generativeai)
    implementation(libs.hilt.android)
    implementation(libs.ktor.client.core)
    implementation(libs.protobuf.javaLite)
    implementation(libs.protobuf.kotlinLite)
    implementation(libs.square.moshi.kotlin)
    implementation(libs.square.retrofit)
    implementation(libs.square.retrofit.converter.moshi)
    implementation(libs.timber)
    //Module
    implementation(projects.common)
    implementation(projects.navigation)
    implementation(projects.storage)
    implementation(projects.theme)

    // Test
    debugImplementation(platform(libs.compose.bom))
    debugImplementation(libs.compose.ui.test.manifest)
    debugImplementation(libs.compose.ui.tooling)
    // Others
    debugImplementation(libs.square.leakcanary)

    annotationProcessor(libs.androidx.room.compiler)
    // Hilt
    annotationProcessor(libs.hilt.compiler)

    testImplementation(libs.junit)

    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.compose.ui.test.junit)
    androidTestImplementation(libs.hilt.android.testing)
}

protobuf {
    protoc {
        artifact = libs.protobuf.protoc.get().toString()
    }
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                create("kotlin") {
                    option("lite")
                }
            }
            task.builtins {
                create("java") {
                    option("lite")
                }
            }
        }
    }
}

