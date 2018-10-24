package news.factory.com.home.fragment_category.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import news.factory.com.R;
import news.factory.com.adapter.HomeCategoryPagerAdapter;
import news.factory.com.base.BaseFragment;
import news.factory.com.base.Constants;
import news.factory.com.home.fragment_category.HomeCategoryFragmentContract;

public class HomeCategoryFragment extends BaseFragment implements HomeCategoryFragmentContract.View {

    @BindView(R.id.tabCategoryHome)
    TabLayout tabCategoryHome;
    @BindView(R.id.vpCategoryHome)
    ViewPager vpCategoryHome;

    @Inject
    public HomeCategoryPagerAdapter pagerAdapter;

    public static HomeCategoryFragment newInstance(String categoryID, String page) {
        Bundle args = new Bundle();
        args.putString(Constants.CATEGORY_ID_KEY, categoryID);
        args.putString(Constants.PAGE_KEY, page);
        HomeCategoryFragment fragment = new HomeCategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_category_home, container, false);
        unbinder = ButterKnife.bind(this,viewGroup);

        setupPager();
        setCategory();

        return viewGroup;
    }

    private void setupPager() {
        tabCategoryHome.setupWithViewPager(vpCategoryHome);
        vpCategoryHome.setAdapter(pagerAdapter);
    }

    private void setCategory() {
        pagerAdapter.setCategoryID(getCategoryID());
        pagerAdapter.setPage(getPage());
    }

    private String getCategoryID() {
        if(getArguments()!=null) {
            return getArguments().getString(Constants.CATEGORY_ID_KEY);
        }
        else return "0";
    }

    private String getPage() {
        if(getArguments()!=null) {
            return getArguments().getString(Constants.PAGE_KEY);
        }
        else return "1";
    }

}
