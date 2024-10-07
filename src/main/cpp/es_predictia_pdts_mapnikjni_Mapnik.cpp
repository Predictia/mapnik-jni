#include <jni.h>


#include "globals.h"
#include "es_predictia_pdts_mapnikjni_Mapnik.h"
#include "es_predictia_pdts_mapnikjni_Datasource.h"
#include "es_predictia_pdts_mapnikjni_DatasourceCache.h"
#include "es_predictia_pdts_mapnikjni_FeatureSet.h"
#include "es_predictia_pdts_mapnikjni_FeatureTypeStyle.h"
#include "es_predictia_pdts_mapnikjni_FreetypeEngine.h"
#include "es_predictia_pdts_mapnikjni_Geometry.h"
#include "es_predictia_pdts_mapnikjni_Image.h"
#include "es_predictia_pdts_mapnikjni_Layer.h"
#include "es_predictia_pdts_mapnikjni_MapDefinition.h"
#include "es_predictia_pdts_mapnikjni_Parameters.h"
#include "es_predictia_pdts_mapnikjni_Projection.h"
#include "es_predictia_pdts_mapnikjni_Query.h"
#include "es_predictia_pdts_mapnikjni_Renderer.h"
#include "es_predictia_pdts_mapnikjni_Datasource.cpp"
#include "es_predictia_pdts_mapnikjni_DatasourceCache.cpp"
#include "es_predictia_pdts_mapnikjni_FeatureSet.cpp"
#include "es_predictia_pdts_mapnikjni_FeatureTypeStyle.cpp"
#include "es_predictia_pdts_mapnikjni_FreetypeEngine.cpp"
#include "es_predictia_pdts_mapnikjni_Geometry.cpp"
#include "es_predictia_pdts_mapnikjni_Image.cpp"
#include "es_predictia_pdts_mapnikjni_Layer.cpp"
#include "es_predictia_pdts_mapnikjni_MapDefinition.cpp"
#include "es_predictia_pdts_mapnikjni_Parameters.cpp"
#include "es_predictia_pdts_mapnikjni_Projection.cpp"
#include "es_predictia_pdts_mapnikjni_Query.cpp"
#include "es_predictia_pdts_mapnikjni_Renderer.cpp"

/// -- Mapnik class
/*
 * Class:     mapnik_Mapnik
 * Method:    nativeInit
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_es_predictia_pdts_mapnikjni_Mapnik_nativeInit
  (JNIEnv *env, jclass c)
{
	PREAMBLE;
	if (initialized) return;
	if (init_ids(env))
		initialized=true;
	TRAILER_VOID;
}

/*
 * Class:     es_predictia_pdts_mapnikjni_Mapnik
 * Method:    version
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_es_predictia_pdts_mapnikjni_Mapnik_version
  (JNIEnv *env, jclass) {

    #ifndef MAPNIK_VERSION
        return env->NewStringUTF("unknown");
    #else
        return env->NewStringUTF(
            std::to_string(MAPNIK_VERSION).c_str()
        );
    #endif
}

/*
 * Class:     mapnik_Mapnik
 * Method:    getInstalledFontsDir
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_es_predictia_pdts_mapnikjni_Mapnik_getInstalledFontsDir
  (JNIEnv *env, jclass)
{
#ifndef MAPNIK_FONTS_DIR
	return 0;
#else
	return env->NewStringUTF(MAPNIK_FONTS_DIR);
#endif
}

/*
 * Class:     mapnik_Mapnik
 * Method:    getInstalledInputPluginsDir
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_es_predictia_pdts_mapnikjni_Mapnik_getInstalledInputPluginsDir
  (JNIEnv *env, jclass)
{
#ifndef MAPNIK_INPUT_PLUGINS_DIR
	return 0;
#else
	return env->NewStringUTF(MAPNIK_INPUT_PLUGINS_DIR);
#endif
}

/*
 * Class:     mapnik_Mapnik
 * Method:    isThreadSafe
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_es_predictia_pdts_mapnikjni_Mapnik_isThreadSafe
	(JNIEnv *env, jclass c)
{
#ifdef MAPNIK_THREADSAFE
	return 1;
#else
	return 0;
#endif
}
