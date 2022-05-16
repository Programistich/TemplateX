# TemplateX - easy read string in table from toml file

The library was created on the basis https://mvnrepository.com/artifact/org.tomlj/tomlj

[![Release](https://jitpack.io/v/Programistich/TemplateX.svg)](https://jitpack.io/#Programistich/TemplateX)

**Example** (template.toml)
```toml
[Start]
en="Hello, its project TemplateX by {}"
ru="Привет, это проект TemplateX от {}"
```
```kotlin
val reader = TemplateReader("template.toml")

// Get line by table Start and key en
reader.get("Start", "en") // Hello, its project TemplateX by {}

// Get line by table Start and key ru and replace content from {}
reader.get("Start", "ru", "https://github.com/Programistich") // <- Привет, это проект TemplateX от https://github.com/Programistich
```

**How to use**

*Gradle KTS*
```kotlin
allprojects {
    repositories {
        maven.setUrl("https://jitpack.io")
    }
}
implementation("com.github.Programistich:TemplateX:{version}")
```
*Maven*
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependency>
    <groupId>com.github.Programistich</groupId>
    <artifactId>TemplateX</artifactId>
    <version>version</version>
</dependency>
```