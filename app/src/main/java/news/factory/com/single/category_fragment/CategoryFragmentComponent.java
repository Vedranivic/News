package news.factory.com.single.category_fragment;

import dagger.Subcomponent;
import news.factory.com.base.dependency_injection.PerFragmentScope;
import news.factory.com.single.category_fragment.view.CategoryFragment;
import news.factory.com.single.view_holder.category.CategoryViewHolder;
import news.factory.com.single.view_holder.category.CategoryViewHolderComponent;


@PerFragmentScope
@Subcomponent(modules = CategoryFragmentModule.class)
public interface CategoryFragmentComponent {

    void inject(CategoryFragment categoryFragment);

}
