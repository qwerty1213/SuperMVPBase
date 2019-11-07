package com.android.tool.ui.main;

import android.Manifest;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.android.tool.BaseApplication;
import com.android.tool.R;
import com.android.tool.ui.base.BaseActivitys;
import com.android.tool.ui.fragment.Fragment0;
import com.android.tool.ui.fragment.Fragment1;
import com.android.tool.ui.fragment.Fragment2;
import com.android.tool.ui.fragment.MyFragment;

import com.android.tool.util.NumUtils;

import com.android.tool.util.ResultUtil;

import com.lzy.okgo.OkGo;


import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
//测试xccccvvvvv
public class MainActivity extends BaseActivitys {

    @BindView(R.id.image_1)
    ImageView image1;
    @BindView(R.id.txt_1)
    TextView txt1;
    @BindView(R.id.image_2)
    ImageView image2;
    @BindView(R.id.txt_2)
    TextView txt2;
    @BindView(R.id.image_3)
    ImageView image3;
    @BindView(R.id.txt_3)
    TextView txt3;
    @BindView(R.id.image_4)
    ImageView image4;
    @BindView(R.id.txt_4)
    TextView txt4;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;

    private Fragment0 mHomePageFragment;//首页
    private Fragment1 mCourseFragment;//课程
    private Fragment2 mTestFragment;//做题
    private MyFragment mMyFragment;//我的

    private long exitTime = 0;
    private boolean isLaunchImgUrl;
    private String launchImgUrl;
//    private LocationService locationService;

    @Override
    public void initParms(Bundle mBundle) {
        setStatusBarShadeTransparentOrTextColor();
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    private String verificationCode;

    @Override
    public void initView() {

        mFragmentManager = getSupportFragmentManager();
        showFragment(NumUtils.PAGE_1);




    }

    @Override
    public void initListener() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ArrayList<String> permissions = new ArrayList<>();
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            }
            if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.READ_PHONE_STATE);
            }

            //联系人
//            if (checkSelfPermission(Manifest.permission.GET_ACCOUNTS) != PackageManager.PERMISSION_GRANTED) {
//                permissions.add(Manifest.permission.GET_ACCOUNTS);
//            }
//            if (checkSelfPermission(Manifest.permission.WRITE_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
//                permissions.add(Manifest.permission.WRITE_CONTACTS);
//            }
//            if (checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
//                permissions.add(Manifest.permission.READ_CONTACTS);
//            }
            if (permissions.size() > 0) {
                requestPermissions(permissions.toArray(new String[permissions.size()]), 127);
            }
        }
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        switch (requestCode) {
//            case 127: {
//                // 授权被允许
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    try {
//                        List<DirectoryModel> list = DirectoryUtil.getAllDirectory(mActivity);
//                        L.i(new Gson().toJson(list.toString()));
//                    } catch (Throwable throwable) {
//                        throwable.printStackTrace();
//                    }
//                }
//                return;
//            }
//        }
//    }


    @Override
    public void doBusiness() {

    }

    @OnClick({R.id.layout_1, R.id.layout_2, R.id.layout_3, R.id.layout_4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_1:
                showFragment(NumUtils.PAGE_1);
                break;
            case R.id.layout_2:
                showFragment(NumUtils.PAGE_2);
                break;
            case R.id.layout_3:
                showFragment(NumUtils.PAGE_3);
                break;
            case R.id.layout_4:
                showFragment(NumUtils.PAGE_4);
                break;
        }
    }

    public void showFragment(int pageIndex) {
        mFragmentTransaction = mFragmentManager.beginTransaction();
        initializationHide();
        switch (pageIndex) {
            case NumUtils.PAGE_1:
                image1.setImageResource(R.mipmap.ic_launcher);
                txt1.setTextColor(getResources().getColor(R.color.c_zhuti));
                if (mHomePageFragment != null) {
                    mFragmentTransaction.show(mHomePageFragment);
//                    mHomePageFragment.initParms(new Bundle());
                } else {
                    mHomePageFragment = new Fragment0();
                    mFragmentTransaction.add(R.id.layout_framnet, mHomePageFragment);
                }
                break;
            case NumUtils.PAGE_2:
                image2.setImageResource(R.mipmap.ic_launcher);
                txt2.setTextColor(getResources().getColor(R.color.c_zhuti));
                if (mCourseFragment != null) {
                    mFragmentTransaction.show(mCourseFragment);
//                    mCourseFragment.initParms(new Bundle());
                } else {
                    mCourseFragment = new Fragment1();
                    mFragmentTransaction.add(R.id.layout_framnet, mCourseFragment);
                }
                break;
            case NumUtils.PAGE_3:
                image3.setImageResource(R.mipmap.ic_launcher);
                txt3.setTextColor(getResources().getColor(R.color.c_zhuti));
                if (mTestFragment != null) {
                    mFragmentTransaction.show(mTestFragment);
//                    mTestFragment.initParms(new Bundle());
                } else {
                    mTestFragment = new Fragment2();
                    mFragmentTransaction.add(R.id.layout_framnet, mTestFragment);
                }
                break;
            case NumUtils.PAGE_4:
                image4.setImageResource(R.mipmap.ic_launcher);
                txt4.setTextColor(getResources().getColor(R.color.c_zhuti));
                if (mMyFragment != null) {
                    mFragmentTransaction.show(mMyFragment);
//                    mMyFragment.initParms(new Bundle());
                } else {
                    mMyFragment = new MyFragment();
                    mFragmentTransaction.add(R.id.layout_framnet, mMyFragment);
                }
                break;
        }
        mFragmentTransaction.commitAllowingStateLoss();
    }

    /**
     * 初始化Fragment和按钮
     */
    private void initializationHide() {
        image1.setImageResource(R.mipmap.ic_launcher);
        image2.setImageResource(R.mipmap.ic_launcher);
        image3.setImageResource(R.mipmap.ic_launcher);
        image4.setImageResource(R.mipmap.ic_launcher);
        txt1.setTextColor(getResources().getColor(R.color.c_7777));
        txt2.setTextColor(getResources().getColor(R.color.c_7777));
        txt3.setTextColor(getResources().getColor(R.color.c_7777));
        txt4.setTextColor(getResources().getColor(R.color.c_7777));
        if (mHomePageFragment != null) {
            mFragmentTransaction.hide(mHomePageFragment);
        }
        if (mCourseFragment != null) {
            mFragmentTransaction.hide(mCourseFragment);
        }
        if (mTestFragment != null) {
            mFragmentTransaction.hide(mTestFragment);
        }
        if (mMyFragment != null) {
            mFragmentTransaction.hide(mMyFragment);
        }
    }






    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                showToast(R.string.press_the_exit_procedure_again);
                exitTime = System.currentTimeMillis();
            } else {
BaseApplication.getInstance().exit();
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }


//    private void getDirectory() {
//        PermissionUtilContacts.requestPermissions(this, 0x10,
//                new String[]{Manifest.permission.WRITE_CONTACTS, Manifest.permission.READ_CONTACTS},
//                new PermissionUtilContacts.OnPermissionListener() {
//                    @Override
//                    public void onPermissionGranted() {
//                        showToast("获取联系人权限成功");
//                        try {
//                            List<DirectoryModel> allContact = DirectoryUtil.getAllDirectory(mActivity);
//                            tvContent.setText(allContact.toString());
//                        } catch (Throwable throwable) {
//                            throwable.printStackTrace();
//                        }
//                    }
//
//                    @Override
//                    public void onPermissionDenied(String[] deniedPermissions, boolean alwaysDenied) {
//                        showToast("获取联系人权限失败");
//                        if (alwaysDenied) {
//                            new AlertDialog.Builder(mActivity).setTitle("获取联系人权限被禁用")
//                                    .setMessage("请在 设置-应用管理-" + mActivity.getString(R.string.app_name) + "-权限管理 (将联系人权限打开)")
//                                    .setNegativeButton("取消", null)
//                                    .setPositiveButton("去设置", new DialogInterface.OnClickListener() {
//                                        @Override
//                                        public void onClick(DialogInterface dialog, int which) {
//                                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//                                            intent.setData(Uri.parse("package:" + mActivity.getPackageName()));
//                                            mActivity.startActivity(intent);
//                                        }
//                                    })
//                                    .show();
//                        }
//                    }
//                });
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ResultUtil.HOME_PAGE_TYPE) {
            if (requestCode == ResultUtil.HOME_PAGE_TYPE) {
                if (onActivityResult != null) {
                    onActivityResult.onActivityResultHomePage(requestCode, resultCode, data);
                }
            }
        } else if (resultCode == ResultUtil.HOME_PAGE_LOCATION) {
            if (requestCode == ResultUtil.HOME_PAGE_LOCATION) {
                if (onActivityResult != null) {
                    onActivityResult.onActivityResultHomePage(requestCode, resultCode, data);
                }
                if (onActivityResultCourse != null) {
                    onActivityResultCourse.onActivityResultCourse(requestCode, resultCode, data);
                }
            }
        }
    }

    public interface OnActivityResult {
        void onActivityResultHomePage(int requestCode, int resultCode, Intent data);
    }

    private OnActivityResult onActivityResult;

    public void setOnActivityResult(OnActivityResult onActivityResult) {
        this.onActivityResult = onActivityResult;
    }

    public interface OnActivityResultCourse {

        void onActivityResultCourse(int requestCode, int resultCode, Intent data);
    }

    private OnActivityResultCourse onActivityResultCourse;

    public void setOnActivityResultCourse(OnActivityResultCourse onActivityResultCourse) {
        this.onActivityResultCourse = onActivityResultCourse;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Activity销毁时，取消网络请求
        OkGo.getInstance().cancelTag(this);
    }


    //    /**
//     * 定位结果回调，重写onReceiveLocation方法，可以直接拷贝如下代码到自己工程中修改
//     */
//    private BDAbstractLocationListener mListener = new BDAbstractLocationListener() {
//
//        @Override
//        public void onReceiveLocation(BDLocation location) {
//            // TODO Auto-generated method stub
//            if (null != location && location.getLocType() != BDLocation.TypeServerError) {
//                L.i("当前定位城市----->" + location.getCity());
//                if (mHomePageFragment != null)
//                    mHomePageFragment.setCity(location.getCity());
//            } else {
//                if (mHomePageFragment != null)
//                    mHomePageFragment.setCity("全国");
//                L.i("定位失败----->" + location.getCity());
//            }
//        }
//    };
//    @Override
//    protected void onStart() {
//        super.onStart();
//        // -----------location config ------------
//        locationService = ((BaseApplication) mActivity.getApplication()).locationService;
//        //获取locationservice实例，建议应用中只初始化1个location实例，然后使用，可以参考其他示例的activity，都是通过此种方式获取locationservice实例的
//        locationService.registerListener(mListener);
//        //注册监听
//        int type = getIntent().getIntExtra("from", 0);
//        if (type == 0) {
//            locationService.setLocationOption(locationService.getDefaultLocationClientOption());
//        } else if (type == 1) {
//            locationService.setLocationOption(locationService.getOption());
//        }
//        locationService.start();// 定位SDK
//    }
//
//    /**
//     * Stop location service
//     */
//    @Override
//    protected void onStop() {
//        // TODO Auto-generated method stub
//        locationService.unregisterListener(mListener); //注销掉监听
//        locationService.stop(); //停止定位服务
//        super.onStop();
//    }


}
