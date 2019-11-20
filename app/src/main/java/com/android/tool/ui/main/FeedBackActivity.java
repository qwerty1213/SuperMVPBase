package com.android.tool.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.tool.R;
import com.android.tool.model.FeedBackDataModel;
import com.android.tool.model.UploadImageModel;
import com.android.tool.presenter.AddFeedBackPresenter;
import com.android.tool.presenter.FeedBackDataPresenter;
import com.android.tool.presenter.UploadFBMImagePresenter;
import com.android.tool.presenter.impl.AddFeedBackImpl;
import com.android.tool.presenter.impl.FeedBackDataImpl;
import com.android.tool.presenter.impl.UploadFBMImageImpl;
import com.android.tool.ui.base.BaseActivitys;
import com.android.tool.ui.main.adapter.FeekbackTagAdapter;
import com.android.tool.ui.main.adapter.GridImageAdapter;
import com.android.tool.ui.view.AddFeedBackView;
import com.android.tool.ui.view.FeedBackDataView;
import com.android.tool.ui.view.UploadFBMImageView;
import com.android.tool.util.StringUtil;
import com.android.tool.widget.tag.FlowTagLayout;
import com.android.tool.widget.tag.OnTagSelectListener;
import com.android.tool.widget.wheelview.EditTextWithCount;
import com.android.tool.widget.wheelview.FullyGridLayoutManager;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.lzy.okgo.model.Progress;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 意见反馈
 *
 * @author York(wuchunyuan)
 * @Created 2019/3/5.
 */
public class FeedBackActivity extends BaseActivitys implements FeedBackDataView, AddFeedBackView, UploadFBMImageView {
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.tag_classification)
    FlowTagLayout tagClassification;
    @BindView(R.id.et_qestions_and_comments)
    EditTextWithCount etQestionsAndComments;
    @BindView(R.id.rv_pic)
    RecyclerView rvPic;
    @BindView(R.id.et_mobile)
    EditText etMobile;
    @BindView(R.id.txt_submit)
    TextView txtSubmit;
    private GridImageAdapter mImageAdapter;
    private List<LocalMedia> imageList = new ArrayList<>();
    private List<FeedBackDataModel> feedBackTypeList;
    private FeekbackTagAdapter mFeekbackTagAdapter;
    private String feekBackIndex;
    //获取反馈分类
    private FeedBackDataPresenter mFeedBackDataPresenter;
    //上传反馈意见
    private AddFeedBackPresenter mAddFeedBackPresenter;
    //多图上传
    private UploadFBMImagePresenter mUploadFBMImagePresenter;
    private StringBuffer pictureIds;

    @Override
    public void initParms(Bundle mBundle) {
        steepSetStatusBarTranslucent(true, true);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_feekback;
    }


    @Override
    public void initView() {
        txtTitle.setText(getString(R.string.feekback));
        mFeedBackDataPresenter = new FeedBackDataImpl(this);
        mAddFeedBackPresenter = new AddFeedBackImpl(this);
        mUploadFBMImagePresenter = new UploadFBMImageImpl(this);
        //图片选择
        rvPic.setLayoutManager(new FullyGridLayoutManager(mActivity, 4, GridLayoutManager.VERTICAL, false));
        mImageAdapter = new GridImageAdapter(mActivity, onAddImageClickListener);
        mImageAdapter.setSelectMax(3);
        rvPic.setAdapter(mImageAdapter);
        pictureIds = new StringBuffer();
        mFeekbackTagAdapter = new FeekbackTagAdapter<>(this);
        tagClassification.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);
        tagClassification.setAdapter(mFeekbackTagAdapter);
        tagClassification.setOnTagSelectListener(new OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, List<Integer> selectedList) {
                if (selectedList != null && selectedList.size() > 0) {
                    feekBackIndex = selectedList.get(0) + "";
                } else {
                    feekBackIndex = "";
                }
                isBtStatus();
            }
        });
        isBtStatus();
        etQestionsAndComments.setAfterTextChangedListener(new EditTextWithCount.AfterTextChangedListener() {
            @Override
            public void afterTextChangeds(Editable s) {
                isBtStatus();
            }
        });
        etMobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                isBtStatus();
            }
        });

    }

    private void isBtStatus() {
        if (StringUtil.isNotBlankAndEmpty(feekBackIndex)
                || StringUtil.isNotBlankAndEmpty(etQestionsAndComments.getInputContent())
                || StringUtil.isNotBlankAndEmpty(etMobile.getText().toString())
                || (imageList != null && imageList.size() > 0)) {
            txtSubmit.setBackgroundColor(color(R.color.c_zhuti));
            txtSubmit.setTextColor(color(R.color.white));
            txtSubmit.setClickable(true);
        } else {
            txtSubmit.setBackgroundColor(color(R.color.c_f5));
            txtSubmit.setTextColor(color(R.color.c_7a7a));
            txtSubmit.setClickable(false);
        }

    }

    @Override
    public void initListener() {
        mImageAdapter.setOnItemClickListener(new ImageAdapterClick());
    }

    @Override
    public void doBusiness() {
        mFeedBackDataPresenter.getRequested(mActivity);
    }

    /**
     * 获取意见反馈分类接口
     *
     * @param feedBackTypeList
     */
    @Override
    public void doFeedBackDataResponse(List<FeedBackDataModel> feedBackTypeList) {
        this.feedBackTypeList = feedBackTypeList;
        List<String> stringArrayList = new ArrayList<>();
        for (int i = 0; i < feedBackTypeList.size(); i++) {
            stringArrayList.add(feedBackTypeList.get(i).getText());
        }
        mFeekbackTagAdapter.onlyAddAll(stringArrayList);
    }


    @Override
    public void doUploadFBMImageResponse(List<UploadImageModel> modelList) {
        pictureIds.setLength(0);
        //问题和意见
        String contents = etQestionsAndComments.getInputContent();
        //手机号码
        String mobile = etMobile.getText().toString().trim();
        //反馈类型
        String feekbackType = String.valueOf(feedBackTypeList.get(Integer.parseInt(feekBackIndex)).getValue());
        for (int i = 0; i < modelList.size(); i++) {
            pictureIds.append(modelList.get(i).getId() + ",");
        }
        mAddFeedBackPresenter.getRequested(mActivity, feekbackType, contents, pictureIds.toString(), mobile);
    }

    @Override
    public void uploadImageModelProgress(Progress progress) {
//        L.i(String.valueOf((int) (progress.fraction * 100)));
    }

    /**
     * 提交反馈意见成功
     */
    @Override
    public void doAddFeedBackResponse() {
        showToast("反馈成功，我们会尽快处理您的反馈意见");
        finish();
    }

    @OnClick({R.id.img_back, R.id.txt_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;
            case R.id.txt_submit:
                //问题和意见
                String contents = etQestionsAndComments.getInputContent();
                //手机号码
                String mobile = etMobile.getText().toString().trim();
                if (isNull(feekBackIndex, contents, mobile)) {
                    //反馈类型
                    String feekbackType = String.valueOf(feedBackTypeList.get(Integer.parseInt(feekBackIndex)).getValue());
                    if (imageList != null && imageList.size() > 0) {//有图片
                        mUploadFBMImagePresenter.getRequested(mActivity, imageList);
                    } else {//没有图片
                        mAddFeedBackPresenter.getRequested(mActivity, feekbackType, contents, "", mobile);
                    }
                }
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    imageList = PictureSelector.obtainMultipleResult(data);
                    if (mImageAdapter != null && imageList.size() > 0) {
                        mImageAdapter.setList(imageList);
                        mImageAdapter.notifyDataSetChanged();
                    }
                    isBtStatus();
                    break;
            }
        }
    }

    /**
     * 进入相册
     */
    private GridImageAdapter.onAddPicClickListener onAddImageClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            // 进入相册 以下是例子：不需要的api可以不写
            PictureSelector.create(mActivity)
                    .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                    .theme(R.style.picture_default_style)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                    .maxSelectNum(3)// 最大图片选择数量
                    .minSelectNum(1)// 最小选择数量
                    .imageSpanCount(4)// 每行显示个数
                    .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选
                    .previewImage(true)// 是否可预览图片
                    .previewVideo(false)// 是否可预览视频
                    .enablePreviewAudio(false) // 是否可播放音频
                    .isCamera(true)// 是否显示拍照按钮
                    .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                    //.imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                    //.setOutputCameraPath("/CustomPath")// 自定义拍照保存路径
                    .enableCrop(false)// 是否裁剪
                    .compress(true)// 是否压缩
                    .synOrAsy(true)//同步true或异步false 压缩 默认同步
                    //.compressSavePath(getPath())//压缩图片保存地址
//                    .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                    .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                    .withAspectRatio(0, 0)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                    .hideBottomControls(false)// 是否显示uCrop工具栏，默认不显示
                    .isGif(false)// 是否显示gif图片
                    .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                    .circleDimmedLayer(false)// 是否圆形裁剪
                    .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                    .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                    .openClickSound(false)// 是否开启点击声音
                    .selectionMedia(imageList)// 是否传入已选图片
                    //.isDragFrame(false)// 是否可拖动裁剪框(固定)
//                        .videoMaxSecond(15)
//                        .videoMinSecond(10)
                    //.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                    .minimumCompressSize(100)// 小于100kb的图片不压缩
                    //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                    //.rotateEnabled(true) // 裁剪是否可旋转图片
                    //.scaleEnabled(true)// 裁剪是否可放大缩小图片
                    //.videoQuality()// 视频录制质量 0 or 1
                    //.videoSecond()//显示多少秒以内的视频or音频也可适用
                    //.recordVideoSecond()//录制视频秒数 默认60s
                    .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
        }
    };


    /**
     * 图片预览点击事件
     */
    class ImageAdapterClick implements GridImageAdapter.OnItemClickListener {
        @Override
        public void onItemClick(int position, View v) {
            if (imageList.size() > 0) {
                LocalMedia media = imageList.get(position);
                String pictureType = media.getPictureType();
                int mediaType = PictureMimeType.pictureToVideo(pictureType);
                switch (mediaType) {
                    case 1:
                        PictureSelector.create(mActivity).themeStyle(R.style.picture_default_style).openExternalPreview(position, imageList);
                        break;
                }
            }
        }
    }

    private boolean isNull(String feekBackIndex, String contents, String mobile) {
        if (!StringUtil.isNotBlankAndEmpty(feekBackIndex)) {
            showToast(R.string.please_feekback_type);
            return false;
        }
        if (!StringUtil.isNotBlankAndEmpty(contents)) {
            showToast(R.string.please_write_qestions_and_comments);
            return false;
        }
        if (!StringUtil.isNotBlankAndEmpty(mobile)) {
            showToast(R.string.please_write_contact_phone_number_type);
            return false;
        }
        return true;
    }

    @Override
    public void showLoading() {
        getDialogLoading();
    }

    @Override
    public void hideLoading() {
        dismissDialogLoading();
    }

    @Override
    public void showError(String errMsg) {
        dismissDialogLoading();
    }

}
