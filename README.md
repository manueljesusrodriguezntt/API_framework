# Environment Variables Configuration API

This repository provides tools and features to facilitate and automate the configuration of environment variables in automated tests with JUnit for front-end applications.

## Description

This API simplifies the setup of environment variables for automated tests in IntelliJ IDEA using JUnit. Depending on whether the client wants to run an Android or web test, locally or on SauceLabs, the program provides only the necessary variables for the type of test. Additionally, it can generate random (but valid) values for the requested variables and returns them in a single line format, with variables separated by semicolons, ready to be copied and pasted into JUnit's environment variables.

## Features

- **Automated Configuration**: Automatically sets up environment variables for JUnit tests.
- **Test Type Selection**: Choose between Android or web tests, local or SauceLabs.
- **Random Value Generation**: Option to generate random but valid values for the requested environment variables.
- **Single Line Output**: Returns variables in a single line, separated by semicolons, for easy copying and pasting.

