JAVA_CMD ?= java
CFLAGS ?=
JAVA_CFLAGS ?= $(shell $(JAVA_CMD) -jar java-config.jar --cflags)
JAVA_LDFLAGS ?= $(shell $(JAVA_CMD) -jar java-config.jar --ldflags)
MAPNIK_CFLAGS = $(shell mapnik-config --cflags)
FONTS_DIR = $(shell mapnik-config --fonts)
INPUT_PLUGINS_DIR = $(shell mapnik-config --input-plugins)
CFLAGS := $(CFLAGS) "-DMAPNIK_FONTS_DIR=\"$(FONTS_DIR)\"" "-DMAPNIK_INPUT_PLUGINS_DIR=\"$(INPUT_PLUGINS_DIR)\""

SOURCE_DEPENDS = \
	es_predictia_pdts_mapnikjni_Mapnik.cpp \
	es_predictia_pdts_mapnikjni_Parameters.cpp \
	es_predictia_pdts_mapnikjni_MapDefinition.cpp \
	es_predictia_pdts_mapnikjni_Layer.cpp \
	es_predictia_pdts_mapnikjni_Datasource.cpp \
	es_predictia_pdts_mapnikjni_DatasourceCache.cpp \
	es_predictia_pdts_mapnikjni_FeatureTypeStyle.cpp \
	es_predictia_pdts_mapnikjni_Projection.cpp \
	es_predictia_pdts_mapnikjni_Query.cpp \
	es_predictia_pdts_mapnikjni_FeatureSet.cpp \
	es_predictia_pdts_mapnikjni_Geometry.cpp \
	es_predictia_pdts_mapnikjni_Image.cpp \
	es_predictia_pdts_mapnikjni_Renderer.cpp \
	es_predictia_pdts_mapnikjni_FreetypeEngine.cpp


MAPNIK_LIBS = $(shell mapnik-config --libs)
IS_OSX := $(shell uname -s | grep -i darwin)
IS_LINUX := $(shell uname -s | grep -i linux)

ifeq ($(IS_OSX),)
build_target := build.linux
install_target := install.linux
else
build_target := build.osx
install_target := install.osx
endif

libmapnik-jni: $(build_target)

build.osx: clean $(SOURCE_DEPENDS)
	g++ -o libmapnik-jni.dylib \
	    -dynamiclib \
	    -shared \
	    -fPIC \
		$(CFLAGS) \
		$(MAPNIK_CFLAGS) \
		$(JAVA_CFLAGS) \
		es_predictia_pdts_mapnikjni_Mapnik.cpp \
		$(LDFLAGS) \
		$(JAVA_LDFLAGS) \
		$(MAPNIK_LIBS)

build.linux: clean $(SOURCE_DEPENDS)
	$(CXX) -o libmapnik-jni.so \
		-fPIC -shared \
		$(CFLAGS) \
		$(MAPNIK_CFLAGS) \
		$(JAVA_CFLAGS) \
		es_predictia_pdts_mapnikjni_Mapnik.cpp \
		$(LDFLAGS) \
		$(MAPNIK_LIBS)

install: $(install_target)

install.osx:
	cp libmapnik-jni.dylib /Library/Java/Extensions/

install.linux:
	cp libmapnik-jni.so /usr/lib/

clean:
	rm -f libmapnik-jni.dylib
	rm -f libmapnik-jni.so
	rm -rf ../resources/native/*
