/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class es_predictia_pdts_mapnikjni_Image */

#ifndef _Included_es_predictia_pdts_mapnikjni_Image
#define _Included_es_predictia_pdts_mapnikjni_Image
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     es_predictia_pdts_mapnikjni_Image
 * Method:    alloc
 * Signature: (II)J
 */
JNIEXPORT jlong JNICALL Java_es_predictia_pdts_mapnikjni_Image_alloc__II
  (JNIEnv *, jclass, jint, jint);

/*
 * Class:     es_predictia_pdts_mapnikjni_Image
 * Method:    alloc
 * Signature: (Les/predictia/pdts/mapnikjni/Image;)J
 */
JNIEXPORT jlong JNICALL Java_es_predictia_pdts_mapnikjni_Image_alloc__Les_predictia_pdts_mapnikjni_Image_2
  (JNIEnv *, jclass, jobject);

/*
 * Class:     es_predictia_pdts_mapnikjni_Image
 * Method:    dealloc
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_es_predictia_pdts_mapnikjni_Image_dealloc
  (JNIEnv *, jobject, jlong);

/*
 * Class:     es_predictia_pdts_mapnikjni_Image
 * Method:    getWidth
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_es_predictia_pdts_mapnikjni_Image_getWidth
  (JNIEnv *, jobject);

/*
 * Class:     es_predictia_pdts_mapnikjni_Image
 * Method:    getHeight
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_es_predictia_pdts_mapnikjni_Image_getHeight
  (JNIEnv *, jobject);

/*
 * Class:     es_predictia_pdts_mapnikjni_Image
 * Method:    saveToFile
 * Signature: (Ljava/lang/String;Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_es_predictia_pdts_mapnikjni_Image_saveToFile
  (JNIEnv *, jobject, jstring, jstring);

/*
 * Class:     es_predictia_pdts_mapnikjni_Image
 * Method:    saveToMemory
 * Signature: (Ljava/lang/String;)[B
 */
JNIEXPORT jbyteArray JNICALL Java_es_predictia_pdts_mapnikjni_Image_saveToMemory
  (JNIEnv *, jobject, jstring);

#ifdef __cplusplus
}
#endif
#endif
