# UtilsLib
A shared code library for my plugins. Feel free to use it in your projects! A wiki isn't available yet, but here's a quick overview

---

## Features

### ListenerSetup

Automatically register your event listeners without needing to manually register them in the main plugin class.

### PluginSetup

Automatically handle plugin dependencies (currently supports FoliaLib, CommandAPI, and ScoreboardLib) and automatically register listeners.

### CustomItem, CustomItemBuilder & CustomItemRegistry

Create custom items easily using a builder, with support for an even craft recipe.

### Utilities

Many helpful utility functions, such as items with limited crafting uses, easier adventure api builders, and many others

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
    implementation("com.github.justorl:UtilsLib:1.2.0")
}
```

Relocate the package if needed:

```kts
tasks.shadowJar {
    relocate("com.pulse.utilsLib", "your.package")
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
  <version>1.2.0</version>
</dependency>
```

Relocate the package if needed:

```xml
<relocation>
    <pattern>com.pulse.utilsLib</pattern>
    <shadedPattern>your.package</shadedPattern>
</relocation>
```
