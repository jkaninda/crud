# HelloCharts for Android

Charting library for Android compatible with API 8+(Android 2.2).
Works best when hardware acceleration is available, so API 14+(Android 4.0) is recommended.
Apache License 2.0.

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-HelloCharts%20for%20Android-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/1068)
<a href="https://scan.coverity.com/projects/4121">
  <img alt="Coverity Scan Build Status"
       src="https://scan.coverity.com/projects/4121/badge.svg"/>
</a>
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.lecho/hellocharts-library/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.lecho/hellocharts-library)
[![Release](https://img.shields.io/github/release/lecho/hellocharts-android.svg?label=JitPack.io)](https://jitpack.io/#lecho/hellocharts-android)

## Features

 - Line chart(cubic lines, filled lines, scattered points)
 - Column chart(grouped, stacked, negative values)
 - Pie chart
 - Bubble chart
 - Combo chart(columns/lines)
 - Preview charts(for column chart and line chart)
 - Zoom(pinch to zoom, double tap zoom), scroll and fling
 - Custom and auto-generated axes(top, bottom, left, right, inside)
 - Animations

## Screens and Demos

 - Code of a demo application is in `hellocharts-samples` directory, requires appcompat v21. 
 - The **demo app** is also ready for download on [**Google Play**](https://play.google.com/store/apps/details?id=lecho.lib.hellocharts.samples).  
 - Short **video** is available on [**YouTube**](https://www.youtube.com/watch?v=xbSBjyjH2SY).

![](screens/scr_dependecy_preview.gif)

![](screens/scr-tempo.png)

![](screens/scr-dependency.png)

![](screens/scr-preview-column.png)

![](screens/scr-pie1.png)

![](screens/scr-bubble1.png)

![](screens/scr-combo.png)

## Download and Import

#### Android Studio/Gradle

 - Maven Central/jCenter, add dependency to your `build.gradle`:
 
 ```groovy
	dependencies{
 		compile 'com.github.lecho:hellocharts-library:1.5.8@aar'
	}
 ```
 
 - JitPack.io, add `jitpack.io` repositiory and dependency to your `build.gradle`:
 
 ```groovy
    repositories {
        maven {
            url "https://jitpack.io"
        }
    }
	
    dependencies {
	        implementation 'com.github.jkanTech:Crud:1.0.1'
          }
 ```
 
