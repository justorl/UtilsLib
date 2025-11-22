A common shared code between my plugins. Feel free to use it yourself, but there's no wiki yet

# Gradle shading
```kts
maven("https://jitpack.io") // add this to your repositories

implementation("com.github.justorl:UtilsLib:1.1.0") // add this to your dependencies
```

# Maven shading
```xml
<repository>
  <id>jitpack.io</id>
  <url>https://jitpack.io</url>
</repository>

<dependency>
  <groupId>com.github.justorl</groupId>
  <artifactId>UtilsLib</artifactId>
  <version>1.1.0</version>
</dependency>
```
