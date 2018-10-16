package news.factory.com.single.activity;

import dagger.Subcomponent;
import news.factory.com.base.dependency_injection.PerActivityScope;
import news.factory.com.single.activity.view.SingleActivity;

@PerActivityScope
@Subcomponent(modules = SingleActivityModule.class)
public interface SingleActivityComponent {

    void injectSingleActivity(SingleActivity singleActivity);

}
