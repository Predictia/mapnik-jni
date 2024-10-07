mapnik-jni
==========

Bindings to use Mapnik from Java. Supports programatically building/introspecting a map, enumerating features and
rendering with [AGG](https://github.com/tomhughes/agg).

This repository is forked from [GISerliang/mapnik-jni](https://github.com/GISerliang/mapnik-jni).

**This is a WIP project**

Prerequisites
-------------

* Mapnik > 3.0
* JDK > 17
* Maven
* Make
* g++ or clang

Building
--------

In order to build the project, you need to have
the Mapnik library installed in your system.

For building the project, you can use the following command:

```bash
mvn package
```

This will generate a jar file in the `target` directory.
