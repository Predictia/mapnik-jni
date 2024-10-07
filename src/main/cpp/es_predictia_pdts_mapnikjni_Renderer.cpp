/*
 * Class:     mapnik_Renderer
 * Method:    renderAgg
 * Signature: (Lmapnik/MapDefinition;Lmapnik/Image;DII)V
 */
JNIEXPORT void JNICALL Java_es_predictia_pdts_mapnikjni_Renderer_renderAgg
  (JNIEnv *env, jclass c, jobject mapobj, jobject imobj, jdouble scale_factor, jint offset_x, jint offset_y)
{
	PREAMBLE;
	if (!mapobj || !imobj || offset_x<0 || offset_y<0) {
		throw_runtime_exception(env, "Illegal arguments in call");
		return;
	}

	mapnik::Map* map=LOAD_MAP_POINTER(mapobj);
	mapnik::image_rgba8* im=LOAD_IMAGE_POINTER(imobj);

	mapnik::agg_renderer<mapnik::image_rgba8> ren(*map, *im, scale_factor, offset_x, offset_y);
	ren.apply();

	TRAILER_VOID;
}
