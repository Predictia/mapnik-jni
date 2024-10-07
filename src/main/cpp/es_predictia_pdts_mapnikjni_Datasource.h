/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class es_predictia_pdts_mapnikjni_Datasource */

#ifndef _Included_es_predictia_pdts_mapnikjni_Datasource
#define _Included_es_predictia_pdts_mapnikjni_Datasource
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     es_predictia_pdts_mapnikjni_Datasource
 * Method:    dealloc
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_es_predictia_pdts_mapnikjni_Datasource_dealloc
  (JNIEnv *, jobject, jlong);

/*
 * Class:     es_predictia_pdts_mapnikjni_Datasource
 * Method:    getParameters
 * Signature: ()Les/predictia/pdts/mapnikjni/Parameters;
 */
JNIEXPORT jobject JNICALL Java_es_predictia_pdts_mapnikjni_Datasource_getParameters
  (JNIEnv *, jobject);

/*
 * Class:     es_predictia_pdts_mapnikjni_Datasource
 * Method:    getType
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_es_predictia_pdts_mapnikjni_Datasource_getType
  (JNIEnv *, jobject);

/*
 * Class:     es_predictia_pdts_mapnikjni_Datasource
 * Method:    getEnvelope
 * Signature: ()Les/predictia/pdts/mapnikjni/Box2d;
 */
JNIEXPORT jobject JNICALL Java_es_predictia_pdts_mapnikjni_Datasource_getEnvelope
  (JNIEnv *, jobject);

/*
 * Class:     es_predictia_pdts_mapnikjni_Datasource
 * Method:    features
 * Signature: (Les/predictia/pdts/mapnikjni/Query;)Les/predictia/pdts/mapnikjni/FeatureSet;
 */
JNIEXPORT jobject JNICALL Java_es_predictia_pdts_mapnikjni_Datasource_features
  (JNIEnv *, jobject, jobject);

/*
 * Class:     es_predictia_pdts_mapnikjni_Datasource
 * Method:    featuresAtPoint
 * Signature: (Les/predictia/pdts/mapnikjni/Coord;)Les/predictia/pdts/mapnikjni/FeatureSet;
 */
JNIEXPORT jobject JNICALL Java_es_predictia_pdts_mapnikjni_Datasource_featuresAtPoint
  (JNIEnv *, jobject, jobject);

/*
 * Class:     es_predictia_pdts_mapnikjni_Datasource
 * Method:    getDescriptor
 * Signature: ()Les/predictia/pdts/mapnikjni/LayerDescriptor;
 */
JNIEXPORT jobject JNICALL Java_es_predictia_pdts_mapnikjni_Datasource_getDescriptor
  (JNIEnv *, jobject);

#ifdef __cplusplus
}
#endif
#endif
