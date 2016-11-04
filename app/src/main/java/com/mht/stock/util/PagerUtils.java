package com.mht.stock.util;

public class PagerUtils {
	
//	private enum State {
//		LOAD_COMPLETE,	//加载完成(获取到数据)
//		LOADDING,		//加载中
//		ERROR,			//加在出错
//		LAST_PAGE,		//加载完成（最后一页）
//	}
//
//	private View mLoadding;
//	private TextView mTvPager;
//	private ProgressBar mPBar;
//
//	private Handler mHandler;
//	private OnPagerLoadListener mOnPagerLoadListener;
//
//	private State mState = State.LOAD_COMPLETE;
//	private Page mPage;
//	private Result mResult;
//	private boolean isReflash;	//是否刷新所有数据
//
//	public PagerUtils(ListView listView, final OnPagerLoadListener pagerLoadListener) {
//		mHandler = new Handler();
//		mOnPagerLoadListener = pagerLoadListener;
//		mPage = new Page();
//		mLoadding = View.inflate(listView.getContext(), R.layout.view_page, null);
//		mTvPager = (TextView) mLoadding.findViewById(R.id.tvPager);
//		mPBar =  (ProgressBar) mLoadding.findViewById(R.id.pBar);
//		listView.addFooterView(mLoadding);
//		listView.setOnScrollListener(new OnScrollListener() {
//
//			@Override
//			public void onScrollStateChanged(AbsListView view, int scrollState) {
//			}
//
//			@Override
//			public void onScroll(AbsListView view, int firstVisibleItem,
//								 int visibleItemCount, int totalItemCount) {
//				final int totalCount = firstVisibleItem + visibleItemCount;
//				if (totalCount==totalItemCount && mState== State.LOAD_COMPLETE) {
//					onLoadding();
//					mHandler.post(new Runnable() {
//
//						@Override
//						public void run() {
//							onLoadding();
//							mPage.nextPage();
//							mOnPagerLoadListener.onNextPager(false, mPage);
//						}
//					});
//				}
//			}
//		});
//		mLoadding.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				if(mState== State.ERROR) {
//					onLoadding();
//					mOnPagerLoadListener.onNextPager(isReflash, mPage);
//				}
//			}
//		});
//	}
//
//	public void onResult(Context context, Result result) {
//		mResult = result;
//		if(result!=null && result.success(context)) {
//			isReflash = false;
//			mPage.onResult(result);
//			if(result.isLastPager(mPage)) {
//				onLastPage(result);
//			} else {
//				onLoadComplete();
//			}
//		} else {
//			onError();
//		}
//	}
//
//	public void onReflash() {
//		onReflash(false);
//	}
//
//	/**
//	 * 刷新所有数据
//	 * @param isCleanPager 是否刷新掉page信息 （刷新当前所有页面数据， 刷新第一页数据）
//     */
//	public void onReflash(boolean isCleanPager) {
//		isReflash = true;
//		onLoadding();
//		mPage = new Page();
//		mPage.nextPage();
//		mOnPagerLoadListener.onNextPager(true, mPage);
////		if(isCleanPager) {
////			mPage = new Page();
////			mOnPagerLoadListener.onNextPager(true, mPage);
////		} else {
////			mOnPagerLoadListener.onNextPager(true, mPage.getReflashPage());
////		}
//	}
//
//	/**
//	 * 数据加载中
//	 */
//	private void onLoadding() {
//		mState = State.LOADDING;
//		mTvPager.setText("加载中...");
//		mPBar.setVisibility(View.VISIBLE);
//	}
//
//	public Page getPage() {
//		return mPage;
//	}
//
//	/**
//	 * 加载出错
//	 */
//	private void onError() {
//		mState = State.ERROR;
//		mTvPager.setText("加载出错，点击重试");
//	}
//
//	/**
//	 * 最后一页
//	 */
//	private void onLastPage(Result result) {
//		mState = State.LAST_PAGE;
//		mPBar.setVisibility(View.GONE);
//		if(result!=null && result.getTotal()==0) {
//			mTvPager.setText("数据为空");
//		} else {
//			mTvPager.setText("加载完成");
//		}
//	}
//
//	/**
//	 * 一页加载完成
//	 */
//	private void onLoadComplete() {
//		mState = State.LOAD_COMPLETE;
//		mTvPager.setText("下一页");
//		mPBar.setVisibility(View.VISIBLE);
//	}
//
//	public static interface OnPagerLoadListener {
//		public void onNextPager(boolean isReflash, Page page);
//	}
}