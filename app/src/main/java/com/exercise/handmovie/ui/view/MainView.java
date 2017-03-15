package com.exercise.handmovie.ui.view;

import android.content.Context;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.exercise.handmovie.MainActivity;
import com.exercise.handmovie.R;
import com.exercise.handmovie.base.RootView;
import com.exercise.handmovie.presenter.constract.MainContract;
import com.exercise.handmovie.ui.adapter.ContentPagerAdapter;
import com.exercise.handmovie.ui.fragment.ClassificationFragment;
import com.exercise.handmovie.ui.fragment.DiscoverFragment;
import com.exercise.handmovie.ui.fragment.MineFragment;
import com.exercise.handmovie.ui.fragment.RecommendFragment;
import com.exercise.handmovie.utils.EventUtil;
import com.exercise.handmovie.utils.Preconditions;
import com.exercise.handmovie.utils.StringUtils;
import com.exercise.handmovie.widget.ResideLayout;
import com.exercise.handmovie.widget.UnScrollViewPager;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;

import org.simple.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.exercise.handmovie.MainActivity.Banner_Stop;


/**
 * Description: MainView
 * Creator: yxc
 * date: 2016/10/20 10:14
 */
public class MainView extends RootView<MainContract.Presenter> implements MainContract.View, RadioGroup.OnCheckedChangeListener {
    MainActivity mActivity;


    final int WAIT_TIME = 200;
    @BindView(R.id.tv_collect)
    TextView tvCollect;
    @BindView(R.id.tv_mydown)
    TextView tvMydown;
    @BindView(R.id.tv_fuli)
    TextView tvFuli;
    @BindView(R.id.tv_share)
    TextView tvShare;
    @BindView(R.id.tv_feedback)
    TextView tvFeedback;
    @BindView(R.id.tv_setting)
    TextView tvSetting;
    @BindView(R.id.about)
    TextView about;
    @BindView(R.id.theme)
    TextView theme;
    @BindView(R.id.tab_rg_menu)
    RadioGroup tabRgMenu;
    @BindView(R.id.vp_content)
    UnScrollViewPager vpContent;
    @BindView(R.id.resideLayout)
    ResideLayout mResideLayout;
    ContentPagerAdapter mPagerAdapter;

    public MainView(Context context) {
        super(context);
    }

    public MainView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void getLayout() {
        inflate(mContext, R.layout.activity_main_view, this);
    }

    @Override
    protected void initView() {
        mActivity = (MainActivity) mContext;
        List<Fragment> fragments = initFragments();
        vpContent.setScrollable(false);
        mPagerAdapter = new ContentPagerAdapter(mActivity.getSupportFragmentManager(), fragments);
        vpContent.setAdapter(mPagerAdapter);
        vpContent.setOffscreenPageLimit(fragments.size());
        StringUtils.setIconDrawable(mContext, tvCollect, MaterialDesignIconic.Icon.gmi_collection_bookmark, 16, 10);
        StringUtils.setIconDrawable(mContext, tvMydown, MaterialDesignIconic.Icon.gmi_download, 16, 10);
        StringUtils.setIconDrawable(mContext, tvFuli, MaterialDesignIconic.Icon.gmi_mood, 16, 10);
        StringUtils.setIconDrawable(mContext, tvShare, MaterialDesignIconic.Icon.gmi_share, 16, 10);
        StringUtils.setIconDrawable(mContext, tvFeedback, MaterialDesignIconic.Icon.gmi_android, 16, 10);
        StringUtils.setIconDrawable(mContext, tvSetting, MaterialDesignIconic.Icon.gmi_settings, 16, 10);
        StringUtils.setIconDrawable(mContext, about, MaterialDesignIconic.Icon.gmi_account, 16, 10);
        StringUtils.setIconDrawable(mContext, theme, MaterialDesignIconic.Icon.gmi_palette, 16, 10);
    }

    @Override
    protected void initEvent() {
        tabRgMenu.setOnCheckedChangeListener(this);
        vpContent.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                ((RadioButton) tabRgMenu.getChildAt(position)).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        mResideLayout.setPanelSlideListener(new ResideLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                postBannerState(true);
            }

            @Override
            public void onPanelOpened(View panel) {
                postBannerState(true);
            }

            @Override
            public void onPanelClosed(View panel) {
                postBannerState(false);
            }
        });
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = Preconditions.checkNotNull(presenter);
    }

    @Override
    public void showError(String msg) {
        EventUtil.showToast(mContext, msg);
    }

    private void postBannerState(final boolean stop) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                EventBus.getDefault().post(stop, Banner_Stop);
            }
        }, WAIT_TIME);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        switch (checkedId) {
            case R.id.tab_rb_1:
                vpContent.setCurrentItem(0, false);
                break;
            case R.id.tab_rb_2:
                vpContent.setCurrentItem(1, false);
                break;
            case R.id.tab_rb_3:
                vpContent.setCurrentItem(2, false);
                break;
            case R.id.tab_rb_4:
                vpContent.setCurrentItem(3, false);
                break;
        }
    }

    private List<Fragment> initFragments() {
        List<Fragment> fragments = new ArrayList<>();
        Fragment fragment1 = new RecommendFragment();
        Fragment fragment2 = new ClassificationFragment();
        Fragment fragment3 = new DiscoverFragment();
        Fragment mineFragment = new MineFragment();
        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(fragment3);
        fragments.add(mineFragment);
        return fragments;
    }



//    @OnClick({R.id.tv_collect, R.id.tv_mydown, R.id.tv_fuli, R.id.tv_share, R.id.tv_feedback, R.id.tv_setting, R.id.about, R.id.theme})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.tv_collect:
//                mContext.startActivity(new Intent(mContext, CollectionActivity.class));
//                break;
//            case R.id.tv_mydown:
//                EventUtil.showToast(mContext, "敬请期待");
//                break;
//            case R.id.tv_fuli:
//                mContext.startActivity(new Intent(mContext, WelfareActivity.class));
//                break;
//            case R.id.tv_share:
//                Intent shareIntent = new Intent();
//                shareIntent.setAction(Intent.ACTION_SEND);
//                shareIntent.putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.setting_recommend_content));
//                shareIntent.setType("text/plain");
//
//                //设置分享列表的标题，并且每次都显示分享列表
//                mContext.startActivity(Intent.createChooser(shareIntent, "分享到"));
//                break;
//            case R.id.tv_feedback:
//                // 以对话框的形式弹出
//                PgyerDialog.setDialogTitleBackgroundColor(PreUtils.getString(mContext, Constants.PRIMARYCOLOR, "#000000"));
//                PgyerDialog.setDialogTitleTextColor(PreUtils.getString(mContext, Constants.TITLECOLOR, "#0aa485"));
//                PgyFeedback.getInstance().showDialog(mContext).d().setChecked(false);
//                break;
//            case R.id.tv_setting:
//                mContext.startActivity(new Intent(mContext, SettingActivity.class));
//                break;
//            case R.id.about:
//                new MaterialDialog.Builder(mContext)
//                        .title(R.string.about)
//                        .titleColor(ThemeUtils.getThemeColor(mContext, R.attr.colorPrimary))
//                        .icon(new IconicsDrawable(mContext)
//                                .color(ThemeUtils.getThemeColor(mContext, R.attr.colorPrimary))
//                                .icon(MaterialDesignIconic.Icon.gmi_account)
//                                .sizeDp(20))
//                        .content(R.string.about_me)
//                        .contentColor(ThemeUtils.getThemeColor(mContext, R.attr.colorPrimary))
//                        .positiveText(R.string.close)
//                        .show();
//                break;
//            case R.id.theme:
//                setTheme("");
//                break;
//        }
//    }

    public ResideLayout getResideLayout() {
        return mResideLayout;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        EventBus.getDefault().unregister(this);
    }
}
