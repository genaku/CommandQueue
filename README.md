# CommandQueue
CommandQueue is library for processing commands in queue. Allows to avoid execition of outdated or repeating commands. Have possibility to put in queue commands not too often (for example commands to update UI download progress state).

[![](https://jitpack.io/v/genaku/CommandQueue.svg)](https://jitpack.io/#genaku/CommandQueue)

# Gradle setup
Step 1. Add it in your root build.gradle at the end of repositories:
```gradle
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
Step 2. Add the dependency your app-level build.gradle:
```gradle
dependencies {
	        compile 'com.github.genaku:CommandQueue:-SNAPSHOT'
	}
```
