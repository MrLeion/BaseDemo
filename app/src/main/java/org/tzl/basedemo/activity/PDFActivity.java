package org.tzl.basedemo.activity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnDrawListener;
import com.github.barteksc.pdfviewer.listener.OnErrorListener;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageScrollListener;
import com.github.barteksc.pdfviewer.listener.OnRenderListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

import org.tzl.basedemo.R;
import org.tzl.baselibrary.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PDFActivity extends BaseActivity {

    public static final String PATH = "http://www.sse.com.cn/disclosure/listedinfo/announcement/c/2017-08-09/603976_20170809_2.pdf";
    @Bind(R.id.pdfView)
    PDFView pdfView;
    private OnDrawListener onDrawListener;
    private Object onLoadCompleteListener;
    private OnPageChangeListener onPageChangeListener;
    private OnPageScrollListener onPageScrollListener;
    private OnErrorListener onErrorListener;
    private OnRenderListener onRenderListener;
    private OnLoadCompleteListener onLoadListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        ButterKnife.bind(this);
        init();
    }



    private void init() {
//        if (!Strings.isNullOrEmpty(PATH)) {
//        file:///android_asset/
            pdfView.fromAsset("pdf.pdf")
                    .defaultPage(0)
                    .onPageChange(onPageChangeListener)
                    .enableAnnotationRendering(true)
                    .enableDoubletap(false)
                    .swipeHorizontal(true)
//                    .useBestQuality(true)
                    .onLoad(onLoadListener)
                    .scrollHandle(new DefaultScrollHandle(this))
//                    .spacing(10) // in dp
                    .load();

        //fit width


//        }
    }
}
