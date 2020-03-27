package com.zjxdd.mvp.wslivedemo;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.qmuiteam.qmui.skin.QMUISkinHelper;
import com.qmuiteam.qmui.skin.QMUISkinValueBuilder;
import com.qmuiteam.qmui.skin.SkinWriter;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.tab.QMUITabBuilder;
import com.qmuiteam.qmui.widget.tab.QMUITabIndicator;
import com.qmuiteam.qmui.widget.tab.QMUITabSegment;
import com.zjxdd.mvp.R;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.lake.librestreaming.core.listener.RESConnectionListener;
import me.lake.librestreaming.filter.hardvideofilter.BaseHardVideoFilter;
import me.lake.librestreaming.filter.hardvideofilter.HardVideoGroupFilter;
import me.lake.librestreaming.ws.StreamAVOption;
import me.lake.librestreaming.ws.StreamLiveCameraView;
import me.lake.librestreaming.ws.filter.hardfilter.GPUImageBeautyFilter;
import me.lake.librestreaming.ws.filter.hardfilter.extra.GPUImageCompatibleFilter;

import static com.zjxdd.mvp.base.MyApplication.getContext;

public class LiveActivity extends AppCompatActivity {
    private static final String TAG = LiveActivity.class.getSimpleName();
    private final int TAB_COUNT = 2;
    @BindView(R.id.tabSegment)
    QMUITabSegment tabSegment;
    @BindView(R.id.contentViewPager)
    ViewPager contentViewPager;
    private StreamLiveCameraView mLiveCameraView;
    private StreamAVOption streamAVOption;
    private String rtmpUrl = "rtmp://182.92.172.212/live/436151306483408896?key=e26b1bfaf839d9299fc56e517d9472dd";//rtmp://ossrs.net/" + StatusBarUtils.getRandomAlphaString(3) + '/' + StatusBarUtils.getRandomAlphaDigitString(5);

    private LiveUI mLiveUI;
    private Map<ContentPage, View> mPageMap = new HashMap<>();
    private ContentPage mDestPage = ContentPage.Item1;
    private int mCurrentItemCount = TAB_COUNT;
    private PagerAdapter mPagerAdapter = new PagerAdapter() {
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public int getCount() {
            return mCurrentItemCount;
        }

        @Override
        public Object instantiateItem(final ViewGroup container, int position) {
            ContentPage page = ContentPage.getPage(position);
            View view = getPageView(page);
            view.setTag(page);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            container.addView(view, params);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getItemPosition(@NonNull Object object) {
            View view = (View) object;
            Object page = view.getTag();
            if (page instanceof ContentPage) {
                int pos = ((ContentPage) page).getPosition();
                if (pos >= mCurrentItemCount) {
                    return POSITION_NONE;
                }
                return POSITION_UNCHANGED;
            }
            return POSITION_NONE;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live);
        ButterKnife.bind(this);
        StatusBarUtils.setTranslucentStatus(this);
        initTabAndPager();
        initLiveConfig();
        mLiveUI = new LiveUI(this,mLiveCameraView,rtmpUrl);
    }

    /*
     * 初始化tab和viewpager
     *
     * */
    private  String  TabTitle  [] ={"chat","live info"};
    private void initTabAndPager() {
        contentViewPager.setAdapter(mPagerAdapter);
        contentViewPager.setCurrentItem(mDestPage.getPosition(), false);

        QMUITabBuilder tabBuilder = tabSegment.tabBuilder();
        for (int i = 0; i < mCurrentItemCount; i++) {
            tabSegment.addTab(tabBuilder.setText(TabTitle[i]).build(getContext()));
        }
        int space = QMUIDisplayHelper.dp2px(getContext(), 16);
        tabSegment.setIndicator(new QMUITabIndicator(
                QMUIDisplayHelper.dp2px(getContext(), 2), false, true));
        tabSegment.setMode(QMUITabSegment.MODE_FIXED);
        tabSegment.setItemSpaceInScrollMode(space);
        tabSegment.setupWithViewPager(contentViewPager, false);
        tabSegment.setPadding(space, 0, space, 0);
        tabSegment.addOnTabSelectedListener(new QMUITabSegment.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int index) {
                Toast.makeText(getContext(), "select index " + index, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabUnselected(int index) {
                Toast.makeText(getContext(), "unSelect index " + index, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabReselected(int index) {
                Toast.makeText(getContext(), "reSelect index " + index, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDoubleTap(int index) {
                Toast.makeText(getContext(), "double tap index " + index, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 设置推流参数
     */
    public void initLiveConfig() {
        mLiveCameraView = (StreamLiveCameraView) findViewById(R.id.stream_previewView);

        //参数配置 start
        streamAVOption = new StreamAVOption();
        streamAVOption.streamUrl = rtmpUrl;
        //参数配置 end

        mLiveCameraView.init(this, streamAVOption);
        mLiveCameraView.addStreamStateListener(resConnectionListener);
        LinkedList<BaseHardVideoFilter> files = new LinkedList<>();
        files.add(new GPUImageCompatibleFilter(new GPUImageBeautyFilter()));
        // files.add(new WatermarkFilter(BitmapFactory.decodeResource(getResources(),R.mipmap.live),new Rect(100,100,200,200)));
        mLiveCameraView.setHardVideoFilter(new HardVideoGroupFilter(files));
    }

    RESConnectionListener resConnectionListener = new RESConnectionListener() {
        @Override
        public void onOpenConnectionResult(int result) {
            //result 0成功  1 失败
            Toast.makeText(LiveActivity.this, "打开推流连接 状态：" + result + " 推流地址：" + rtmpUrl, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onWriteError(int errno) {
            Toast.makeText(LiveActivity.this, "推流出错,请尝试重连", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCloseConnectionResult(int result) {
            //result 0成功  1 失败
            Toast.makeText(LiveActivity.this, "关闭推流连接 状态：" + result, Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLiveCameraView.destroy();
    }


    private View getPageView(ContentPage page) {
        View view = mPageMap.get(page);
        if (view == null) {
            TextView textView = new TextView(getContext());
             view = View.inflate(this, R.layout.vp_chat, null);

            mPageMap.put(page, view);
        }
        return view;
    }

    public enum ContentPage {
        Item1(0),
        Item2(1);
        private final int position;

        ContentPage(int pos) {
            position = pos;
        }

        public static ContentPage getPage(int position) {
            switch (position) {
                case 0:
                    return Item1;
                case 1:
                    return Item2;

                default:
                    return Item1;
            }
        }

        public int getPosition() {
            return position;
        }
    }
}
