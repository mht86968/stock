package com.mht.stock.util;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class AnimUtils {

	public static void animGone(View view) {
	}

	public static void animVisible(View view) {
	}

	/**
	 * 动画顺序执行
	 * @param context
	 * @param view
	 * @param repeat	重复次数 -1 无限循环
     * @param anims
     */
	public static void animSequence(final Context context, final View view, int repeat, final int... anims) {
		animSequence(0, context, view, repeat, 0, anims);
	}

	private static void animSequence(int index, final Context context, final View view, final int repeat, int repeatIndex, final int... anims) {
		if(anims==null || anims.length==0) {
			return;
		}
		if(repeat == -1) {
			if(index >= anims.length) {
				index = 0;
			}
		} else if(repeat==0) {
			return;
		} else {
			if(index >= anims.length) {
				index = 0;
				repeatIndex++;
				if(repeatIndex >= repeat) {
					return;
				}
			}
		}
		final int _repeatIndex = repeatIndex;
		final int _index = index;
		final Animation animation = AnimationUtils.loadAnimation(context, anims[_index]);
		animation.setAnimationListener(new Animation.AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				animSequence(_index+1, context, view, repeat, _repeatIndex, anims);
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}
		});
		view.startAnimation(animation);
	}

	public static void clearAnimation(View view) {
		if(view.getAnimation()!=null) {
			view.getAnimation().setAnimationListener(null);
			view.getAnimation().cancel();
		}
		view.clearAnimation();
	}
}