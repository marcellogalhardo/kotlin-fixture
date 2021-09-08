[![Releases](https://img.shields.io/github/v/release/marcellogalhardo/kotlin-fixture?include_prereleases)](https://github.com/marcellogalhardo/kotlin-fixture/releases) [![Build Status](https://travis-ci.org/marcellogalhardo/kotlin-fixture.svg?branch=master)](https://travis-ci.org/marcellogalhardo/kotlin-fixture) [![CodeCov](https://codecov.io/gh/marcellogalhardo/kotlin-fixture/branch/master/graph/badge.svg)](https://codecov.io/gh/marcellogalhardo/kotlin-fixture)

# Kotlin.Fixture

Write maintainable unit tests, faster.

Kotlin.Fixture makes it easier to create non-relevant data for test fixtures, allowing the developer to focus on the essentials of each test case.

Credits: Kotlin.Fixture is inspired by [AutoFixture](https://github.com/AutoFixture/AutoFixture) and [QuickCheck](https://hackage.haskell.org/package/QuickCheck).

**This project is currently experimental and the API subject to breaking changes without notice.**

## Getting Started

**Step 1.** Add it in your root *build.gradle* at the end of repositories:
```gradle
allprojects {
	repositories {
		maven { url "https://jitpack.io" }
	}
}
```
**Step 2.** Add the dependency
```gradle
dependencies {
	testImplementation 'com.github.marcellogalhardo:kotlin-fixture:{Tag}'
	androidTestImplementation 'com.github.marcellogalhardo:kotlin-fixture:{Tag}'
}
```
(Please replace `{Tag}` with the [latest version numbers](https://github.com/marcellogalhardo/kotlin-fixture/releases): [![](https://jitpack.io/v/marcellogalhardo/kotlin-fixture.svg)](https://jitpack.io/#marcellogalhardo/kotlin-fixture))

## Basic Usage

Kotlin.Fixture is designed to make Test-Driven Development more productive and unit tests more refactoring-safe. It does so by removing the need for hand-coding anonymous variables as part of a test's Fixture Setup phase. 

When writing unit tests, you typically need to create some objects that represent the initial state of the test. Often, an API will force you to specify much more data than you really care about, so you frequently end up creating objects that have no influence on the test, simply to make the code compile.

Kotlin.Fixture can help by creating such [Anonymous Variables](http://blogs.msdn.com/ploeh/archive/2008/11/17/anonymous-variables.aspx) for you. Here's a simple example:

```kotlin
val createSut = Fixture.generate {
    { name: String ->
        NamePrinter(nameToPrint = name /* other attributes */)
    }
}

@Test
fun testExample() {
    // Arrange
    val expectedName = Fixture.string(prefix = "name") // name-6223529b-3497-45c8-a864-8a969cd798e4
    val sut = createSut(expectedName)
    
    // Act
    val result = sut.print()
    
    // Assert
    assertTrue(result == expectedName)
}
```

This example illustrates the basic principle of Kotlin.Fixture.

The example also illustrates how Kotlin.Fixture can be used as a [SUT Factory](http://blog.ploeh.dk/2009/02/13/SUTFactory.aspx) that creates the actual System Under Test (the `NamePrinter` instance).

**Warning:** This library does work outside tests but it was not designed for it. Use it at your own risk.

**Disclaimer:** This library will be migrated to [Maven Central](https://search.maven.org/) as soon as it moves out of experimental status.

## Usage

All examples assume that a Fixture instance has previously been created like this:
```kotlin
val fixture = Fixture()
```

### Basic Types

| Type               | Sample                           | Result                                                |
|--------------------|----------------------------------|-------------------------------------------------------|
| Boolean            | `fixture.boolean()`              | `Boolean: false`                                      |
| Char               | `fixture.char()`                 | `Char: A`                                             |
| Double             | `fixture.double()`               | `Double: 400`                                         |
| Float              | `fixture.float()`                | `Float: 40.5`                                         |
| Int                | `fixture.int()`                  | `Int: 27`                                             |
| Long               | `fixture.long()`                 | `Long: 5`                                             |
| String             | `fixture.string()`               | `String: "f5cdf6b1-a473-410f-95f3-f427f7abb0c7"`      |
|--------------------|----------------------------------|-------------------------------------------------------|

### Generate Complex Type

```kotlin
val generated = Fixture.generate {
    ComplexParent(
        name = string(prefix = "parent"),
        number = int(),
        child = ComplexChild(name = string(prefix = "child"))
    )
}
```

### Generators for Reusability


```kotlin
val generateComplexParent = Fixture.generate {
    { name: String ->
        ComplexParent(
            name = string(prefix = name),
            number = int(),
            child = ComplexChild(name = string(prefix = "child"))
        )
    }
}
val complexParent = generateComplexParent("parentName")
```

### Generate Lists

```kotlin
val strings = Fixture.list(until = 10) { index -> string(prefix = index.toString()) }
```
- Sample result: 
  - `List<String>`
    - `String: "0-ecc1cc75-cd7a-417f-b477-2913802440b4"`
    - `String: "1-fce70a7b-fae5-474f-8055-415ca46eac20"`
    - `String: "2-79b45532-d66f-4abc-9311-77ba68dc9e3c"`
