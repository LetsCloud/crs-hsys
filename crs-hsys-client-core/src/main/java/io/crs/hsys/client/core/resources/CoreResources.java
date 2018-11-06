package io.crs.hsys.client.core.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.DataResource;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.TextResource;

public interface CoreResources extends ClientBundle {
	public static final CoreResources INSTANCE = GWT.create(CoreResources.class);

	@Source("io/crs/hsys/client/core/resources/css/materialize.css")
	TextResource materialize();

	@Source("io/crs/hsys/client/core/resources/css/gwt-material.css")
	TextResource gwtMaterial();

	@Source("img/profile.jpg")
	ImageResource profileImg();

	@Source("img/orange-wallpaper.jpg")
	ImageResource orangeWallpaperImg();

	// Az appCss-ben lévő mainBackground CSS osztály használja fel
	@Source("img/orange-wallpaper.jpg")
	DataResource orangeWallpaperRes();

	@Source("img/red-wallpaper.png")
	ImageResource redWallpaperImg();

	@Source("img/red-wallpaper.png")
	DataResource redWallpaperRes();

	@Source("css/wallpaper.css")
	WallpaperCssResource wallpaperCss();
	
	@Source("css/core.css")
	TextResource coreCss();

//	@Source("js/select2-tab-fix.min.js")
 //   TextResource select2TabFix();
}