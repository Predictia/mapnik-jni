/// -- Projection class
#include "globals.h"
#include "es_predictia_pdts_mapnikjni_Projection.h"

/*
 * Class:     mapnik_Projection
 * Method:    alloc
 * Signature: (Ljava/lang/String;)J
 */
JNIEXPORT jlong JNICALL Java_es_predictia_pdts_mapnikjni_Projection_alloc
  (JNIEnv *env, jclass c, jstring paramsj)
{
	PREAMBLE;
	refjavastring params(env, paramsj);
	
	mapnik::proj_transform* prj=new mapnik::proj_transform(
		mapnik::projection("epsg:4326"), 
		mapnik::projection(params.stringz)
	);
	return FROM_POINTER(prj);
	TRAILER(0);
}

/*
 * Class:     mapnik_Projection
 * Method:    isProjSUPPORTED
 * Signature: (J)Z
 */
JNIEXPORT jboolean JNICALL Java_es_predictia_pdts_mapnikjni_Projection_isProjSupported
  (JNIEnv *env, jclass clazz) {
	PREAMBLE;

    // We create a proj_transform that is not
    // wgs84 => merc nor merc => wgs84.
    // If the proj library is not available, the
    // definition of the proj_transform will return unknown.

    // This is a polar stereographic projection
    mapnik::projection* test = nullptr;
	try {
        test = new mapnik::projection("epsg:3413");
        delete test;
	    return JNI_TRUE;
    } catch (std::exception& e) {
        if (test) {
            delete test;
        }
        return JNI_FALSE;
    }


	TRAILER(false);

}

/*
 * Class:     mapnik_Projection
 * Method:    dealloc
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_es_predictia_pdts_mapnikjni_Projection_dealloc
  (JNIEnv *env, jobject, jlong ptr)
{
	PREAMBLE;
	// Get the source and destination projections
	//mapnik::projection src = static_cast<mapnik::proj_transform*>(TO_POINTER(ptr))->source();
	//mapnik::projection dst = static_cast<mapnik::proj_transform*>(TO_POINTER(ptr))->dest();
	// Delete the proj_transform
	delete static_cast<mapnik::proj_transform*>(TO_POINTER(ptr));
	// Delete the source and destination projections
	//src.get()->~projection();
	//dst.get()->~projection();
	TRAILER_VOID;
}

/*
 * Class:     mapnik_Projection
 * Method:    getParams
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_es_predictia_pdts_mapnikjni_Projection_getParams
  (JNIEnv *env, jobject prjobject)
{
	PREAMBLE;
	#ifdef MAPNIK_4
	    mapnik::proj_transform* prj=LOAD_PROJECTION_POINTER(prjobject);
	    return env->NewStringUTF(prj->definition().c_str());
    #else
        return env->NewStringUTF("unknown");
    #endif
	TRAILER(0);
}

/*
 * Class:     mapnik_Projection
 * Method:    getExpanded
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_es_predictia_pdts_mapnikjni_Projection_getExpanded
  (JNIEnv *env, jobject prjobject)
{
	PREAMBLE;
	#ifdef MAPNIK_4
	    mapnik::proj_transform* prj=LOAD_PROJECTION_POINTER(prjobject);
	    return env->NewStringUTF(prj->definition().c_str());
	#else
        return env->NewStringUTF("unknown");
    #endif
	TRAILER(0);
}

/*
 * Class:     mapnik_Projection
 * Method:    forward
 * Signature: (Lmapnik/Coord;)V
 */
JNIEXPORT void JNICALL Java_es_predictia_pdts_mapnikjni_Projection_forward
  (JNIEnv *env, jobject prjobject, jobject coord)
{
	PREAMBLE;
	if (!coord) return;
	mapnik::proj_transform* prj=LOAD_PROJECTION_POINTER(prjobject);
	double x=env->GetDoubleField(coord, FIELD_COORD_X),
			y=env->GetDoubleField(coord, FIELD_COORD_Y);
	double z = 0.0;
	prj->forward(x, y, z);  // z is ignored
	env->SetDoubleField(coord, FIELD_COORD_X, x);
	env->SetDoubleField(coord, FIELD_COORD_Y, y);
	TRAILER_VOID;
}

/*
 * Class:     mapnik_Projection
 * Method:    inverse
 * Signature: (Lmapnik/Coord;)V
 */
JNIEXPORT void JNICALL Java_es_predictia_pdts_mapnikjni_Projection_inverse
	(JNIEnv *env, jobject prjobject, jobject coord)
{
	PREAMBLE;
	if (!coord) return;
	mapnik::proj_transform* prj=LOAD_PROJECTION_POINTER(prjobject);
	double x=env->GetDoubleField(coord, FIELD_COORD_X),
			y=env->GetDoubleField(coord, FIELD_COORD_Y);

	double z = 0.0;
	prj->backward(x, y, z);  // z is ignored
	env->SetDoubleField(coord, FIELD_COORD_X, x);
	env->SetDoubleField(coord, FIELD_COORD_Y, y);
	TRAILER_VOID;
}

