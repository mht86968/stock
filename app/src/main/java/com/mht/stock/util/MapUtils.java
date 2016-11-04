package com.mht.stock.util;

public class MapUtils {
	public static final String BAIDU_MAP_PACKAGE = "com.baidu.BaiduMap";
	public static final String AMAP_PACKAGE = "com.autonavi.minimap";
	public static final String QQMAP_PACKAGE = "com.tencent.map";
	
	public static final String BAIDU_MAP = "百度地图";
	public static final String AMAP_MAP = "高德地图";
	public static final String QQMAP_MAP = "腾讯地图";

//	private static void navi(Context context, String map, MapNaviParam param) {
//		try {
//			if(BAIDU_MAP.equals(map)) {
//				Intent intent = param.buildBaiDuMapIntent(context);
//				context.startActivity(intent);
//			} else if(AMAP_MAP.equals(map)) {
//				Intent intent = param.buildAMapIntent(context);
//				context.startActivity(intent);
//			} else if(QQMAP_MAP.equals(map)) {
//				Intent intent = param.buildQQMapIntent(context);
//				context.startActivity(intent);
//			}
//		} catch (Exception e) {
//			Toast.makeText(context, "地图调用出错，请更新地图版本！", Toast.LENGTH_SHORT).show();
//		}
//	}
//
//	public static void navigationRoute(final Context context, final MapNaviParam param) {
//		final List<String> maps = getInstallMapApk(context);
//		String selectMap = Storage.getInstance(context).getSelectMap();
//		if(maps.isEmpty()) {
//			if(!TextUtils.isEmpty(selectMap)) {
//				Storage.getInstance(context).setSelectMap(null);
//			}
//			Toast.makeText(context, "请安装地图软件！", Toast.LENGTH_SHORT).show();
//		} else if(maps.size() == 1) {
//			if(!TextUtils.isEmpty(selectMap)) {
//				Storage.getInstance(context).setSelectMap(null);
//			}
//			navi(context, maps.get(0), param);
//		} else {
//			if(!maps.contains(selectMap)) {
//				Storage.getInstance(context).setSelectMap(null);
//				selectMap = null;
//			}
//			if(TextUtils.isEmpty(selectMap)) {
//				ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, android.R.id.text1, maps);
//				View view = LayoutInflater.from(context).inflate(R.layout.dialog_select_map, null);
//				final Dialog dialog = new MyDialog.Builder(context).setDialogTraditional().setView(view).create();
//				final CheckBox cbAlways = (CheckBox) view.findViewById(R.id.cbAlways);
//				ListView list = (ListView) view.findViewById(R.id.list);
//				list.setOnItemClickListener(new OnItemClickListener() {
//
//					@Override
//					public void onItemClick(AdapterView<?> parent, View view,
//											int position, long id) {
//						if(cbAlways.isChecked()) {
//							Storage.getInstance(context).setSelectMap(maps.get(position));
//						}
//						navi(context, maps.get(position), param);
//						dialog.dismiss();
//					}
//				});
//				list.setAdapter(adapter);
//				dialog.show();
//			} else {
//				navi(context, selectMap, param);
//			}
//		}
//	}
//
//	public static List<String> getInstallMapApk(Context context) {
//		List<String> list = new ArrayList<String>();
//		final PackageManager packageManager = context.getPackageManager();
//		List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
//		if(packageInfos != null) {
//            for(PackageInfo packageInfo : packageInfos) {
//                String packName = packageInfo.packageName;
//                if(BAIDU_MAP_PACKAGE.equals(packName)) {
//        			list.add(BAIDU_MAP);
//        		} else if(AMAP_PACKAGE.equals(packName)) {
//        			list.add(AMAP_MAP);
//        		} else if(QQMAP_PACKAGE.equals(packName)) {
//        			list.add(QQMAP_MAP);
//        		}
//            }
//        }
//		return list;
//	}
}
