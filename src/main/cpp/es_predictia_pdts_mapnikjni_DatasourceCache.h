/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class es_predictia_pdts_mapnikjni_DatasourceCache */

#ifndef _Included_es_predictia_pdts_mapnikjni_DatasourceCache
#define _Included_es_predictia_pdts_mapnikjni_DatasourceCache
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     es_predictia_pdts_mapnikjni_DatasourceCache
 * Method:    pluginNames
 * Signature: ()[Ljava/lang/String;
 */
JNIEXPORT jobjectArray JNICALL Java_es_predictia_pdts_mapnikjni_DatasourceCache_pluginNames
  (JNIEnv *, jclass);

/*
 * Class:     es_predictia_pdts_mapnikjni_DatasourceCache
 * Method:    pluginDirectories
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_es_predictia_pdts_mapnikjni_DatasourceCache_pluginDirectories
  (JNIEnv *, jclass);

/*
 * Class:     es_predictia_pdts_mapnikjni_DatasourceCache
 * Method:    registerDatasources
 * Signature: (Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_es_predictia_pdts_mapnikjni_DatasourceCache_registerDatasources
  (JNIEnv *, jclass, jstring);

/*
 * Class:     es_predictia_pdts_mapnikjni_DatasourceCache
 * Method:    create
 * Signature: (Les/predictia/pdts/mapnikjni/Parameters;)Les/predictia/pdts/mapnikjni/Datasource;
 */
JNIEXPORT jobject JNICALL Java_es_predictia_pdts_mapnikjni_DatasourceCache_create
  (JNIEnv *, jclass, jobject);

#ifdef __cplusplus
}
#endif
#endif
