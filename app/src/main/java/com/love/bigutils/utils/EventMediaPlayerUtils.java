package com.love.bigutils.utils;

import android.content.Context;
import android.media.MediaPlayer;

public class EventMediaPlayerUtils {

	public static void play(Context c, int resId) {
		MediaPlayer mediaPlayerUtils = MediaPlayer.create(c, resId);
		mediaPlayerUtils.start();
	}

}
