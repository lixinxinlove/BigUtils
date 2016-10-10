package com.love.bigutils.utils;

import com.eventmosh.evente.EventApp;
import com.eventmosh.evente.R;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

public class ImageLoadHelper {

	private static ImageLoader loader;
	private static DisplayImageOptions defaultOptions;

	static {
		// 网络图片例子,结合常用的图片缓存库UIL,你可以根据自己需求自己换其他网络图片库
		defaultOptions = new DisplayImageOptions.Builder().showImageForEmptyUri(R.mipmap.loading_error_default)
				.showImageOnLoading(R.mipmap.loading_default).cacheInMemory(true).cacheOnDisk(true)
				.imageScaleType(ImageScaleType.NONE).build();
		File cacheDir = StorageUtils.getOwnCacheDirectory(EventApp.mContext, "ChimelongApp/ImageCache");// 获取到缓存的目录地址
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(EventApp.mContext)
				.defaultDisplayImageOptions(defaultOptions).threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory().diskCacheFileNameGenerator(new Md5FileNameGenerator())
				.diskCache(new UnlimitedDiskCache(cacheDir)).tasksProcessingOrder(QueueProcessingType.LIFO).build();
		loader = ImageLoader.getInstance();
		loader.init(config);
	}

	private ImageLoadHelper() {

	}

	public static ImageLoader getInstanseImageLoader() {
		return loader;
	}

	public static DisplayImageOptions getInstenseDefaultOprions() {
		return defaultOptions;
	}

	public static DisplayImageOptions getInstenseOptions(int loadingImg, int emptyImge) {
		DisplayImageOptions options = new DisplayImageOptions.Builder().showImageForEmptyUri(emptyImge)
				.showImageOnLoading(loadingImg).cacheInMemory(true).cacheOnDisk(true).build();
		return options;
	}

}
