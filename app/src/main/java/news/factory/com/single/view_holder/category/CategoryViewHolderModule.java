package news.factory.com.single.view_holder.category;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import dagger.Module;
import dagger.Provides;
import news.factory.com.base.dependency_injection.PerFragmentScope;
import news.factory.com.single.adapter.CategoryPagerAdapter;

@Module
public class CategoryViewHolderModule {

    private FragmentManager fragmentManager;

    private Context context;

    public CategoryViewHolderModule(FragmentManager fragmentManager, Context context) {
        this.fragmentManager = fragmentManager;
        this.context = context;
    }

    @Provides
    @PerFragmentScope
    public CategoryPagerAdapter provideCategoryPagerAdapter(FragmentManager fragmentManager, Context context){
        return new CategoryPagerAdapter(fragmentManager, context);
    }

    @Provides
    @PerFragmentScope
    public FragmentManager provideFragmentManager(){
        return fragmentManager;
    }

    @Provides
    @PerFragmentScope
    public Context provideContext(){
        return context;
    }


}
