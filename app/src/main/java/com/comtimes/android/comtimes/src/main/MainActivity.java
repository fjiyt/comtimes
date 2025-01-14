package com.comtimes.android.comtimes.src.main;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.comtimes.android.comtimes.R;
import com.comtimes.android.comtimes.src.BaseActivity;
import com.comtimes.android.comtimes.src.login.LoginActivity;
import com.comtimes.android.comtimes.src.main.models.RecentNewsData;
import com.comtimes.android.comtimes.src.mypage.MypageActivity;
import com.comtimes.android.comtimes.src.news_list.NewsListActivity;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {
    private static final int NEWS = 0;
    private static final int EVENT = 1;
    private static final int INTERVIEW  = 2;
    private static final int ACTIVITY = 3;
    private static final int LECTURE = 4;
    private static final int INFO = 5;

    //Drawer관련 멤버 변수 선언
    private ImageView mOpenDrawerIv;
    private DrawerLayout mDrawerLayout;
    private View mDrawerView;
    private ImageView mProfileIv;
    private ImageButton mHomeIbtn;
    private TextView mLoginTv, mNameTv, mMyPageTv, mSignOutTv;
    private LinearLayout mNewsLl, mEventLl, mActivityLl, mLectureLl, mInfoLl;

    //부가기능관련 멤버 변수 선언
    private TextView mReservationTv;
    private TextView mNoticeTV;

    //Recylcerview 관련 멤버 변수 선언
    private RecyclerView mRecentNewsRv;
    private Context mContext;                                //Activity 관련 정보 담고 있음.
    private LinearLayoutManager mLayoutManager;              //RecyclerView Item이 수직인지 수평인지 배치해주는 놈
    private RecentNewsRvAdapter mRecentNewsRvAdapter;        //RecyclerView에 아이템 뿌려주고 관리하는 놈
    private ArrayList<RecentNewsData> mRecentNewsDataList;   //RecyclerView Item에 들어가야될 DataList
    private RequestManager mGlideRequestManager;            // 이미지 관련 라이브러리 이해안될테니 그냥 복붙해주세요.

    //최근뉴스데이터리스트에 넣어줄 더미데이터 5개
    String[] titles = {"LIG 넥스원 인턴 배형진, 황인건 학우 인터뷰","자취생을 지켜줘! 홍대영학우 인터뷰", "2019 숭실대학교 봄축제 ‘SSU:PECTRUM’",
    "2019 SCCC SCON Programming Contest", "2019 숭실대학교 IT 대학 봄축제"};
    int[] images = {R.drawable.dumy_img_1, R.drawable.dumy_img_2, R.drawable.dumy_img_3,
            R.drawable.dumy_img_4, R.drawable.dumy_img_5};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    @Override
    public void initViews() {
        //Drawer
        mDrawerLayout = findViewById(R.id.main_drawer_layout);
        mDrawerView = findViewById(R.id.drawer);
        mOpenDrawerIv = findViewById(R.id.main_ham_btn);

        mProfileIv = findViewById(R.id.drawer_profile_iv);
        mHomeIbtn = findViewById(R.id.drawer_home_ibtn);
        mLoginTv = findViewById(R.id.drawer_login_tv);
        mNameTv = findViewById(R.id.drawer_name_tv);
        mMyPageTv = findViewById(R.id.drawer_mypage_tv);
        mSignOutTv = findViewById(R.id.drawer_signout_tv);
        mNewsLl = findViewById(R.id.drawer_news_ll);
        mEventLl = findViewById(R.id.drawer_event_ll);
        mActivityLl = findViewById(R.id.drawer_activity_ll);
        mLectureLl = findViewById(R.id.drawer_lecture_ll);
        mInfoLl = findViewById(R.id.drawer_info_ll);

        mReservationTv=findViewById(R.id.main_reservation_tv);
        mNoticeTV=findViewById(R.id.main_notice_tv);

        mOpenDrawerIv.setOnClickListener(this);
        mHomeIbtn.setOnClickListener(this);
        mLoginTv.setOnClickListener(this);
        mMyPageTv.setOnClickListener(this);
        mSignOutTv.setOnClickListener(this);
        mNewsLl.setOnClickListener(this);
        mEventLl.setOnClickListener(this);
        mActivityLl.setOnClickListener(this);
        mLectureLl.setOnClickListener(this);
        mInfoLl.setOnClickListener(this);
        mReservationTv.setOnClickListener(this);
        mNoticeTV.setOnClickListener(this);

        //RecylcerView
        //RecyclerView ID 등록 및 DataList 객체 생성
        mRecentNewsRv = findViewById(R.id.main_recent_news_rv);
        mRecentNewsDataList = new ArrayList<>();
        //Glide RequestManager라는 걸 만들어서 MainActivity의 정보를 담아 Adapter에 보냅니다.
        mGlideRequestManager = Glide.with(this);

        //데이터리스트에 더미데이터들 삽입
        mRecentNewsDataList.add(new RecentNewsData(titles[0], images[0]));
        mRecentNewsDataList.add(new RecentNewsData(titles[1], images[1]));
        mRecentNewsDataList.add(new RecentNewsData(titles[2], images[2]));
        mRecentNewsDataList.add(new RecentNewsData(titles[3], images[3]));
        mRecentNewsDataList.add(new RecentNewsData(titles[4], images[4]));

        //Apater 등록과정
        mLayoutManager  = new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false);
        mRecentNewsRvAdapter = new RecentNewsRvAdapter(mRecentNewsDataList , mContext , mGlideRequestManager); // Adapter생성 - datalist와 MainActivity정보랑 같이
        //recyclerview에 layoutmanager와 adapter를 등록 - 마지막에!
        mRecentNewsRv.setLayoutManager(mLayoutManager);
        mRecentNewsRv.setAdapter(mRecentNewsRvAdapter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_ham_btn :
                mDrawerLayout.openDrawer(mDrawerView);
                break;
            case R.id.drawer_home_ibtn:
                mDrawerLayout.closeDrawer(mDrawerView);
            case R.id.drawer_login_tv:
                Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(loginIntent);
                break;
            case R.id.drawer_mypage_tv:
                Intent myPageIntent = new Intent(MainActivity.this, MypageActivity.class);
                startActivity(myPageIntent);
                break;
            case R.id.drawer_news_ll:
                startNewsList(NEWS);
                break;
            case R.id.drawer_event_ll:
                startNewsList(EVENT);
                break;
            case R.id.drawer_interview_ll:
                startNewsList(INTERVIEW);
            case R.id.drawer_activity_ll:
                startNewsList(ACTIVITY);
                break;
            case R.id.drawer_lecture_ll:
                startNewsList(LECTURE);
                break;
            case R.id.drawer_info_ll:
                startNewsList(INFO);
                break;
            case R.id.main_reservation_tv:
                Intent reservationIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLSc8H9s3_ixtlZRgX26TNkKQ7KALg5tsvjj6wvgtpd36Eg0ukA/viewform"));
                startActivity(reservationIntent);
                break;
            case R.id.main_notice_tv:
                Intent noticeIntent = getPackageManager().getLaunchIntentForPackage("com.dbs.mthink.ssu");
                noticeIntent.addFlags(noticeIntent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(noticeIntent);
                break;
            case R.id.drawer_signout_tv:
                break;
        }
    }

    private void startNewsList(int type){
        Intent intent = new Intent(MainActivity.this, NewsListActivity.class);
        intent.putExtra("type", type);
        startActivity(intent);
    }


}
