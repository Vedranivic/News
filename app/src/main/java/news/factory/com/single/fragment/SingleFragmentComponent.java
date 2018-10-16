package news.factory.com.single.fragment;

import dagger.Subcomponent;
import news.factory.com.base.dependency_injection.PerFragmentScope;
import news.factory.com.single.fragment.view.SingleFragment;

@PerFragmentScope
@Subcomponent(modules = SingleFragmentModule.class)
public interface SingleFragmentComponent {

    void inject(SingleFragment singleFragment);
}
