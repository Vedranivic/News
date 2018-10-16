package news.factory.com.single.category_fragment;

import dagger.Subcomponent;
import news.factory.com.base.dependency_injection.PerFragmentScope;
import news.factory.com.single.category_fragment.view.CategoryFragment;

@PerFragmentScope
@Subcomponent(modules = CategoryFragmentModule.class)
public interface CategoryFragmentComponent {
    void inject(CategoryFragment categoryFragment);
}
