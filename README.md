  <p align="center">
<img alt="logo image" width="250" src="https://trapp-documentation.s3.eu-central-1.amazonaws.com/LogoMakr-7gMmq0.png"  />
</p>

# TrAPPSync Android Plugin

TrAPPSync is a lightweight library that enables the direct integration with the TrAPP translations management platform. This repo contains the plugins provided to ease the use of the library in Android.

## Offline Plugin

### Overview

The *offline plugin* automatically converts the `offline.json` provided by the platform into an object that can be used during the library's setup.
The plugin will also automatically add the dependency to the latest compatible `dev.sevencircle.trappsync:core` version.

### Quick start

To use the plugin, add the following import to the `app` module:

``` gradle
plugins {
    ...
    apply(dev.sevencircle.trappsync.plugin.offline:$version)
}
```

or, if you're using the `libs.versions.toml` file

``` toml
[versions]
trappync = $version

[plugins]
trappsync = { id = "dev.sevencircle.trappsync.plugin.offline", version.ref = "trappsync" }
```

``` gradle
plugins {
    apply(libs.plugins.trappsync)
}
```

After that you need to export the `offline.json` file from the platform. To do so, go to home of the project, click export on the top left and select only the **OFFLINE JSON** format.
Place the downloaded file into the project's root folder and you're good to go!

If you want to change the location of the file, you'll need to provide the plugin with the path to the file relative to the root folder. For example, if you place the file in `root/app/strings` the configuration would be:

``` gradle
trappsync {
    offlineJsonFile = project.layout.projectDirectory.file("app/strings/offline.json")
}
```

You can also change the hash used to identify the version. By default it uses the timestamp as a hash, in order to ensure the update of the strings in app.

> [!CAUTION]
> Be aware that, if you customize the hash parameter, you'll need to remember to change it when you download a new version of the `offline.json` file.

``` gradle
trappsync {
    offlineHash = "YOUR_CUSTOM_HASH"
}
```

## Issues

To report and issue, go to the issue page of the repository, open a new issue and follow the issue template provided. Try to be as specific as possible when signaling the bug.
