# UtilsLib
A shared code library for my plugins. Feel free to use it in your projects! A wiki isn't available yet, but here's a quick overview

---

## Features

### ListenerSetup

Automatically register your event listeners without needing to manually register them in the main plugin class.

### PaperPlugin class

Automatically handle plugin dependencies (currently supports FoliaLib, CommandAPI, and ScoreboardLib) with verbose logging and automatically register listeners and variables.

### CustomItem, CustomItemDSL & CustomItemRegistry

Create custom items easily using a dsl builder, with support for an even craft recipe.

### Utilities

Many helpful utility functions, such as easy unenchantable items, easier adventure api builders, async dsl chains and many others

## Shading

### Gradle Shading

Add JitPack to your repositories:

```kts
repositories {
    maven("https://jitpack.io")
}
```

Add the dependency:

```kts
dependencies {
    implementation("com.github.justorl:UtilsLib:2.2.3")
}
```

Relocate the package if needed (recommended):

```kts
tasks.shadowJar {
    relocate("com.pulse.utilslib", "your.package")
}
```

---

### Maven Shading

Add the repository:

```xml
<repository>
  <id>jitpack.io</id>
  <url>https://jitpack.io</url>
</repository>
```

Add the dependency:

```xml
<dependency>
  <groupId>com.github.justorl</groupId>
  <artifactId>UtilsLib</artifactId>
  <version>2.2.3</version>
</dependency>
```

Relocate the package if needed (recommended):

```xml

<relocation>
    <pattern>com.pulse.utilslib</pattern>
    <shadedPattern>your.package</shadedPattern>
</relocation>
```
