package demo.test;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

public class Map extends MapActivity {

	// 地图显示控制相关变量定义
	private MapView mapView = null;
	private MapController mapCon;

	// 菜单项
	final private int menuMode = Menu.FIRST;
	final private int menuAbout = Menu.FIRST + 1;
	final private int menuExit = Menu.FIRST + 2;

	private int chooseItem = 1;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map);

		// 获取MapView
		mapView = (MapView) findViewById(R.id.mapview);
		// 设置显示模式
		mapView.setTraffic(true);
		mapView.setSatellite(false);
		mapView.setStreetView(false);
		// 设置可以缩放
		mapView.setBuiltInZoomControls(true);

		// 设置初始地图的中心位置
		GeoPoint geoBeijing = new GeoPoint((int) (39.95 * 1000000),
				(int) (116.37 * 1000000));
		mapCon = mapView.getController();
		mapCon.setCenter(geoBeijing);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// 建立菜单
		menu.add(0, menuMode, 0, "地图模式");
		menu.add(0, menuAbout, 1, "关于");
		menu.add(0, menuExit, 2, "退出");
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case menuExit:
			finish();
			break;
		case menuMode:
			Dialog dMode = new AlertDialog.Builder(this)
					.setTitle("地图模式设置")
					.setSingleChoiceItems(
							getResources().getStringArray(R.array.mapmodes),
							chooseItem, new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									chooseItem = which;
								}
							})
					.setPositiveButton("确定",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									switch (chooseItem) {
									case 0:
										mapView.setSatellite(true);
										mapView.setTraffic(false);
										mapView.setStreetView(false);
										break;
									case 1:
										mapView.setSatellite(false);
										mapView.setTraffic(true);
										mapView.setStreetView(false);
										break;
									case 2:
										mapView.setSatellite(false);
										mapView.setTraffic(false);
										mapView.setStreetView(true);
										break;
									default:
										break;
									}
								}
							})
					.setNegativeButton("取消",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {

								}
							}).create();
			dMode.show();
			break;
		case menuAbout:
			Dialog dAbout = new AlertDialog.Builder(this)
					.setTitle("关于")
					.setMessage("Version 1.0")
					.setNegativeButton("取消",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {

								}
							}).create();
			dAbout.show();
			break;
		default:
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}
