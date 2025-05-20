# SENG201 Project Overview
Welcome to the SENG201 GEAR SHIFT project!
This README file includes some useful information.

## Notes
Remember you are required to commit your code to the **main** branch of your repository before the deadline.

This project contains default naming of `team0` throughout.
If you are interested you can update this to reflect your team number, however it is **not required**.
This can be done by renaming any instance of `team0` with `team<x>` (where x is your number).
IntelliJ can help with this using `ctrl+shift+f` to find all instances of a string, and `ctrl+shift+r` to replace them, though make sure to change the package names as well.

## Authors
- SENG201 Teaching team, Georgy Stikhanovskiy and Jack McGlade

## Prerequisites
- JDK >= 21 [click here to get the latest stable OpenJDK release (as of writing this README)](https://jdk.java.net/21/)
- *(optional)* Gradle [Download](https://gradle.org/releases/) and [Install](https://gradle.org/install/)


## What's Included
This project comes with some basic examples of the following (including dependencies in the build.gradle file):
- JavaFX
- Junit 5

We have also included a basic setup of the Gradle project and Tasks required for the course including:
- Required dependencies for the functionality above
- Test Coverage with JaCoCo
- Build plugins:
    - JavaFX Gradle plugin for working with (and packaging) JavaFX applications easily

You are expected to understand the content provided and build your application on top of it. If there is anything you
would like more information about please reach out to the tutors.

## Importing Project (Using IntelliJ)
IntelliJ has built-in support for Gradle. To import your project:

- Launch IntelliJ and choose `Open` from the start-up window.
- Select the project and click open
- At this point in the bottom right notifications you may be prompted to 'load gradle scripts', If so, click load

**Note:** *If you run into dependency issues when running the app or the Gradle pop up doesn't appear then open the Gradle sidebar and click the Refresh icon.*

## Run Project 
1. Open a command line interface inside the project directory and run `./gradlew run` to run the app.
2. The app should then open a new window, this may not be displayed over IntelliJ but can be easily selected from the taskbar

## Build and Run Jar
1. Open a command line interface inside the project directory and run `./gradlew jar` to create a packaged Jar. The Jar file is located at build/libs/seng201_team0-1.0-SNAPSHOT.jar
2. Navigate to the build/libs/ directory (you can do this with `cd build/libs`)
3. Run the command `java -jar seng201_team0-1.0-SNAPSHOT.jar` to open the application.

## Run Tests
1. Open a command line interface inside the project directory and run `./gradlew test` to run the tests.
2. Test results should be printed to the command line

**Note:** *This Jar is **NOT** cross-platform, so you **must** build the jar you submit on Linux.* 


## Project Overview
Gear Shift is a cross-country racing game designed by two SENG201 students. In this game, you as a player, get to buy racing cars, install parts on the cars, complete in variety of races with different routes, earning prize money and more!

## Gameplay
Gear Shift gets player to enter their name at the start, which must be 3-15 characters long and no special characters.

The player gets to choose season length (selection must be 5 to 15 races).

The user can purchase and can manage their bought racing cars, each racing car have the following: speed, handling, reliability, fuel economy and cost.

Also, there are difficulty levels such as "Easy" and "Hard" that the player can choose from the start, and it will impact the gameplay.

Race System: There are 3 race options that the player can choose from, each one of them have different prize money, number of racers terrain etc. Also, the user must select a race route for each race. Each time, there will be a chance for a car to break down, forcing the player to retire.

## Endgame Conditions
In order for the player to end the game, they must race all the number of seasons they have selected from the start.
To win each season, the player must race until the end.

## Image Sources
All of our images for cars, background, buttons etc. All of them were created/traced using an app called "Pixel Brush" on Google Play Store
Link: https://www.pixelbrush.app/