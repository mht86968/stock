package com.mht.stock.http;

/**
 * Created by mht on 2016/5/17.
 */
public class TestHttps {
//    public static final String TAG = "TestHttps";
//
//    private Context mContext;
//
//    public TestHttps(Context context) {
//        mContext = context;
//    }
//
//    public void sslOKHttp() {
//        Request request = new Request.Builder()
//                .url("https://kyfw.12306.cn/otn/")
//                .build();
//        InputStream certificate = mContext.getResources().openRawResource(R.raw.srca);
//        SSLSocketFactory sslSocketFactory = HttpsUtils.getSslSocketFactory(certificate);
//        OkHttpClient httpClient = new OkHttpClient.Builder()
//                .sslSocketFactory(sslSocketFactory)
//                .build();
//        httpClient.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.e(TAG, "onFailure " + e.getMessage());
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                Log.e(TAG, response.body().string());
//            }
//        });
//    }
//
//    public void sslXutils3() {
//        InputStream certificate = mContext.getResources().openRawResource(R.raw.srca);
//        SSLSocketFactory sslSocketFactory = HttpsUtils.getSslSocketFactory(certificate);
//        RequestParams requestParams = new RequestParams("https://kyfw.12306.cn/otn/");
////        requestParams.setSslSocketFactory(sslSocketFactory);
//        org.xutils.x.http().get(requestParams, new org.xutils.common.Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//                Log.e(TAG, result);
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//                Log.e(TAG, "onError");
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//                Log.e(TAG, "onCancelled");
//            }
//
//            @Override
//            public void onFinished() {
//                Log.e(TAG, "onFinished");
//            }
//        });
//    }
//
//    public void sslXutils2() {
//        InputStream certificate = mContext.getResources().openRawResource(R.raw.srca);
//        SSLSocketFactory sslSocketFactory = HttpsUtils.getSslSocketFactory(certificate);
//        HttpUtils httpUtils = new HttpUtils();
//        httpUtils.send(HttpRequest.HttpMethod.GET, "https://kyfw.12306.cn/otn/", new RequestCallBack<String>() {
//
//            @Override
//            public void onSuccess(ResponseInfo<String> responseInfo) {
//                Log.e(TAG, responseInfo.result);
//            }
//
//            @Override
//            public void onFailure(HttpException e, String s) {
//                Log.e(TAG, "onFailure");
//            }
//        });
//    }
//
//    public void sslHttpsConnection() {
//        new AsyncTask<Void, Void, Void>(){
//
//            @Override
//            protected Void doInBackground(Void... params) {
//                InputStream certificate = mContext.getResources().openRawResource(R.raw.srca);
//                SSLSocketFactory sslSocketFactory = HttpsUtils.getSslSocketFactory(certificate);
//                try {
//                    URL url = new URL("https://kyfw.12306.cn/otn/");
//                    HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
//                    urlConnection.setSSLSocketFactory(sslSocketFactory);
//                    urlConnection.setDoOutput(true);
//                    urlConnection.setDoInput(true);
//                    urlConnection.connect();
//                    Log.e(TAG, IO.readAsString(urlConnection.getInputStream()));
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                return null;
//            }
//        }.execute();
//    }
//
//    public void sslHttpConnection() {
//        new AsyncTask<Void, Void, Void>(){
//
//            @Override
//            protected Void doInBackground(Void... params) {
//                try {
//                    URL url = new URL("https://kyfw.12306.cn/otn/");
//                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//                    urlConnection.setDoOutput(true);
//                    urlConnection.setDoInput(true);
//                    urlConnection.connect();
//                    Log.e(TAG, IO.readAsString(urlConnection.getInputStream()));
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//
//                    e.printStackTrace();
//                }
//                return null;
//            }
//        }.execute();
//    }
//
//    public void qwsHttpLogin() {
//        new AsyncTask<Void, Void, Void>(){
//
//            @Override
//            protected Void doInBackground(Void... params) {
//                JSONObject requestParms = new JSONObject();
//                try {
//                    requestParms.put("pn", "68");
//                    requestParms.put("pw", md5Password("111111"));
//
//                    requestParms.put("accesstype", "APP");
//                    requestParms.put("devid", getAndroidDevid());
//                    requestParms.put("channel", "channel");
//                    requestParms.put("appversion", "channel");
//                    requestParms.put("partnerappid", 0);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//                try {
//                    URL url = new URL("http://titest.f3322.org:9090/app/login");
//                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//                    urlConnection.setDoOutput(true);
//                    urlConnection.setDoInput(true);
//                    IO.write(requestParms.toString(), urlConnection.getOutputStream());
//                    urlConnection.connect();
//                    Log.e(TAG, IO.readAsString(urlConnection.getInputStream()));
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                return null;
//            }
//        }.execute();
//    }
//
//    public void qwsHttpsLogin() {
//        new AsyncTask<Void, Void, Void>(){
//
//            @Override
//            protected Void doInBackground(Void... params) {
//                JSONObject requestParms = new JSONObject();
//                try {
//                    requestParms.put("pn", "68");
//                    requestParms.put("pw", md5Password("111111"));
//
//                    requestParms.put("accesstype", "APP");
//                    requestParms.put("devid", getAndroidDevid());
//                    requestParms.put("channel", "channel");
//                    requestParms.put("appversion", "channel");
//                    requestParms.put("partnerappid", 0);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//                InputStream certificate = mContext.getResources().openRawResource(R.raw.api_360qws_cn);
//                SSLSocketFactory sslSocketFactory = HttpsUtils.getSslSocketFactory(certificate);
//                try {
//                    URL url = new URL("https://titest.f3322.org:443/app/login");
//                    HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
//                    urlConnection.setSSLSocketFactory(sslSocketFactory);
//                    urlConnection.setDoOutput(true);
//                    urlConnection.setDoInput(true);
//                    urlConnection.connect();
//                    Log.e(TAG, IO.readAsString(urlConnection.getInputStream()));
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                return null;
//            }
//        }.execute();
//    }
//
//    private String getAndroidDevid() {
//        return String.format("%s %s|Android%s|%s", Build.BRAND, Build.MODEL, Build.VERSION.RELEASE, "fdsfdsf45ds4f5df5dsfdsf45s4f5ds");
//    }
//
//    private String md5Password(String password) {
//        return com.mht.stock.util.MD5.getMD5Code("G" + password + "100");
//    }
}
