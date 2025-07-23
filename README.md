# Region Fold Plugin
# Region Fold Plugin

![Build](https://github.com/TonyMarkham/region-fold-plugin/workflows/Build/badge.svg)
[![Version](https://img.shields.io/jetbrains/plugin/v/MARKETPLACE_ID.svg)](https://plugins.jetbrains.com/plugin/MARKETPLACE_ID)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/MARKETPLACE_ID.svg)](https://plugins.jetbrains.com/plugin/MARKETPLACE_ID)

A powerful code folding plugin for IntelliJ-based IDEs that adds support for region-based code folding with `/* #region */` and `/* #endregion */` syntax.

<!-- Plugin description -->
A powerful code folding plugin for IntelliJ-based IDEs that adds support for region-based code folding with `/* #region */` and `/* #endregion */` syntax.

## Features

- **Code Folding**: Organize your code with collapsible regions
- **Syntax Highlighting**: Custom color highlighting for region markers
- **Multi-Language Support**: Works with Java, Kotlin, JavaScript, TypeScript, Rust (via TextMate), and text files
- **Named Regions**: Support for descriptive region names like `/* #region Authentication Logic */`
- **Real-time Settings**: Changes to highlight colors apply immediately to all open files
- **Customizable**: Configure region highlighting color in the IDE settings
<!-- Plugin description end -->

## Usage

Add region markers to your code:

```java
/* #region Database Operations */
public void saveUser(User user) {
    // implementation
}

public User findUserById(long id) {
    // implementation
}
/* #endregion */
```

The regions will be foldable in the editor and highlighted with your chosen color.

## Installation

- Using the IDE built-in plugin system:

  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>Marketplace</kbd> > <kbd>Search for "Region Fold"</kbd> >
  <kbd>Install</kbd>

- Using JetBrains Marketplace:

  Go to [JetBrains Marketplace](https://plugins.jetbrains.com/plugin/MARKETPLACE_ID) and install it by clicking the <kbd>Install to ...</kbd> button.

- Manually:

  Download the [latest release](https://github.com/TonyMarkham/region-fold-plugin/releases/latest) and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>

## Configuration

To customize the region highlight color:

1. Go to <kbd>Settings/Preferences</kbd> > <kbd>Editor</kbd> > <kbd>Region Folding</kbd>
2. Select your preferred highlight color using the color picker
3. Click <kbd>Apply</kbd> to see the changes immediately in your open files

## Supported Languages

- Java
- Kotlin
- JavaScript
- TypeScript
- Rust (via TextMate)
- Plain Text files

## Compatibility

This plugin is compatible with all IntelliJ-based IDEs starting from version 2024.2:

- IntelliJ IDEA
- WebStorm
- PyCharm
- PhpStorm
- Rider
- CLion
- GoLand
- RubyMine
- AppCode

---
Developed with ❤️ by Tony Markham
![Build](https://github.com/TonyMarkham/region-fold-plugin/workflows/Build/badge.svg)
[![Version](https://img.shields.io/jetbrains/plugin/v/MARKETPLACE_ID.svg)](https://plugins.jetbrains.com/plugin/MARKETPLACE_ID)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/MARKETPLACE_ID.svg)](https://plugins.jetbrains.com/plugin/MARKETPLACE_ID)

A powerful code folding plugin for IntelliJ-based IDEs that adds support for region-based code folding with `/* #region */` and `/* #endregion */` syntax.
- [ ] Set the `MARKETPLACE_ID` in the above README badges. You can obtain it once the plugin is published to JetBrains Marketplace.
- [ ] Set the [Plugin Signing](https://plugins.jetbrains.com/docs/intellij/plugin-signing.html?from=IJPluginTemplate) related [secrets](https://github.com/JetBrains/intellij-platform-plugin-template#environment-variables).
- [ ] Set the [Deployment Token](https://plugins.jetbrains.com/docs/marketplace/plugin-upload.html?from=IJPluginTemplate).
- [ ] Click the <kbd>Watch</kbd> button on the top of the [IntelliJ Platform Plugin Template][template] to be notified about releases containing new features and fixes.

<!-- Plugin description -->
A powerful code folding plugin for IntelliJ-based IDEs that adds support for region-based code folding with `/* #region */` and `/* #endregion */` syntax.

## Features

- **Code Folding**: Organize your code with collapsible regions
- **Syntax Highlighting**: Custom color highlighting for region markers
- **Multi-Language Support**: Works with Java, Kotlin, JavaScript, TypeScript, Rust (via TextMate), and text files
- **Named Regions**: Support for descriptive region names like `/* #region Authentication Logic */`
- **Real-time Settings**: Changes to highlight colors apply immediately to all open files
- **Customizable**: Configure region highlighting color in the IDE settings
<!-- Plugin description end -->

## Installation

- Using the IDE built-in plugin system:
  
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>Marketplace</kbd> > <kbd>Search for "Region Fold"</kbd> >
  <kbd>Install</kbd>

- Using JetBrains Marketplace:

  Go to [JetBrains Marketplace](https://plugins.jetbrains.com/plugin/MARKETPLACE_ID) and install it by clicking the <kbd>Install to ...</kbd> button.

- Manually:

  Download the [latest release](https://github.com/TonyMarkham/region-fold-plugin/releases/latest) and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>

## Usage

Add region markers to your code:

```java
/* #region Database Operations */
public void saveUser(User user) {
    // implementation
}

public User findUserById(long id) {
    // implementation
}
/* #endregion */
```

The regions will be foldable in the editor and highlighted with your chosen color.


## Configuration

To customize the region highlight color:

1. Go to <kbd>Settings/Preferences</kbd> > <kbd>Editor</kbd> > <kbd>Region Folding</kbd>
2. Select your preferred highlight color using the color picker
3. Click <kbd>Apply</kbd> to see the changes immediately in your open files

## Supported Languages

- Java
- Kotlin
- JavaScript
- TypeScript
- Rust (via TextMate)
- Plain Text files

## Compatibility

This plugin is compatible with all IntelliJ-based IDEs starting from version 2024.2:

- IntelliJ IDEA
- WebStorm
- PyCharm
- PhpStorm
- Rider
- CLion
- GoLand
- RubyMine
- AppCode

---
Plugin based on the [IntelliJ Platform Plugin Template][template].

[template]: https://github.com/JetBrains/intellij-platform-plugin-template
