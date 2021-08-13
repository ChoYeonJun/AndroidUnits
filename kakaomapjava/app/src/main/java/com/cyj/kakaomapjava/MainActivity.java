package com.cyj.kakaomapjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends MapActivity {

    ProgressDialog loagindDialog;

    MapView mapView;

    double nowLat;

    double nowLongi;


    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

        mapView = (MapView) findViewById(R.id.mapview);

        mapView.setVisibility(View.GONE);


        getLocat();//현재위치 받아와서 주소로 변환하여 출력


        Button mapShowbtn = (Button) findViewById(R.id.mapshow);

        mapShowbtn.setOnClickListener(clickListener);

    }


    Button.OnClickListener clickListener = new Button.OnClickListener() {

        public void onClick(View v) {

            switch (v.getId()) {

                case R.id.mapshow:

                    showMap();//지도보여주고 오버레이 세팅

            }

        }

    };


    private void showMap() {

        mapView.setVisibility(View.VISIBLE);


        Drawable marker = getResources().getDrawable(R.drawable.map_icon);

        marker.setBounds(0, 0, marker.getIntrinsicWidth(), marker.getIntrinsicHeight());


        InterestringLocatinos funPlaces = new InterestringLocatinos(marker);

        mapView.getOverlays().add(funPlaces);//현재위치에 마커 표시


        ClickReceiver clickRecvt = new ClickReceiver(this, funPlaces);//터치했을때 오버레이 설정

        mapView.getOverlays().add(clickRecvt);

    }


    public class ClickReceiver extends Overlay {

        private Context context;

        InterestringLocatinos temp;


        public ClickReceiver(Context _context, InterestringLocatinos temp_) {

            context = _context;

            temp = temp_;

        }


        @Override

        public boolean onTap(GeoPoint p, MapView mapView) {

            GeoPoint b = new GeoPoint((int) p.getLatitudeE6(), (int) p.getLongitudeE6());

            mapView.getController().animateTo(b);

            temp.clear();//터치시 이전 오버레이 지워준다

            temp.addOverlay(new OverlayItem(b, "test", "test"));


            myAddress.setText("위치 : " + (double) p.getLatitudeE6() / 1000000 + "  ," + (double) p.getLongitudeE6() / 1000000);


            return true;

        }

    }


    class InterestringLocatinos extends ItemizedOverlay<OverlayItem> {


        private List<OverlayItem> locations = new ArrayList<OverlayItem>();

        private Drawable marker;


        public InterestringLocatinos(Drawable marker) {

            super(marker);

            this.marker = marker;


            int latitude = (int) (nowLat * 1E6);

            int longitude = (int) (nowLongi * 1E6);


            GeoPoint gp = new GeoPoint(latitude, longitude);

            mapView.getController().setCenter(gp);

            mapView.getController().setZoom(19);

            locations.add(new OverlayItem(gp, "test", "test"));


            populate();

        }


        @Override

        public void draw(Canvas canvas, MapView mapView, boolean shadow) {

            super.draw(canvas, mapView, shadow);

            boundCenterBottom(marker);

        }


        @Override

        protected OverlayItem createItem(int i) {

            return locations.get(i);

        }


        @Override

        public int size() {

            return locations.size();

        }


        public void addOverlay(OverlayItem overlay) {

            locations.add(overlay);

            setLastFocusedIndex(-1);

            populate();

        }


        public void clear() {

            locations.clear();

            mapView.removeAllViews();

            setLastFocusedIndex(-1);

            populate();

        }


    }


    public void getLocat() {//현재위치 받아옮

        loagindDialog = ProgressDialog.show(MainActivity.this, "현재위치 설정 중..",

                "Please wait..", true, false);

        new Thread(new Runnable() {

            @Override

            public void run() {

                runOnUiThread(new Runnable() {

                    @Override

                    public void run() {

                        MapView mapView = (MapView) findViewById(R.id.mapview);


                        final MyLocationOverlay myLocationOverlay = new MyLocationOverlay(

                                MainActivity.this, mapView);

                        myLocationOverlay.enableMyLocation();


                        if (myLocationOverlay.isMyLocationEnabled()) {

                            myLocationOverlay.runOnFirstFix(new Runnable() {

                                public void run() {

                                    nowLat = (double) myLocationOverlay

                                            .getMyLocation().getLatitudeE6() / 1000000f;

                                    nowLongi = (double) myLocationOverlay

                                            .getMyLocation().getLongitudeE6() / 1000000f;

                                    mHandler.sendEmptyMessageDelayed(0, 10);//현재위치 받아온 후 주소로 변환

                                }

                            });

                        }


                    }

                });

            }

        }).start();

    }


    Handler mHandler = new Handler() {//현재위치 받아온 후 주소로 변환

        public void handleMessage(Message msg) {

            loagindDialog.dismiss();

            String address = getAddress(nowLat, nowLongi);

            myAddress = (TextView) findViewById(R.id.mylocation);

            myAddress.setText(address);

        }


    };


    TextView myAddress;


    public String getAddress(double lat, double longi) {

        String result = null;


        Geocoder geoCoder = new Geocoder(MainActivity.this);


        List<Address> addresses = null;

        try {

            addresses = geoCoder.getFromLocation(lat, longi, 5);

        } catch (IOException e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

        }

        if (addresses != null && addresses.size() > 0) {

            Address mAddress = addresses.get(0);

            result = mAddress.getAddressLine(0);

        } else {

            result = "현재위치를 표시할 수 없습니다";

        }

        return result;

    }


    @Override

    protected boolean isRouteDisplayed() {

        // TODO Auto-generated method stub

        return false;

    }


    @Override

    public void onBackPressed() {

        finish();

        System.exit(0);

        super.onBackPressed();

    }


}



