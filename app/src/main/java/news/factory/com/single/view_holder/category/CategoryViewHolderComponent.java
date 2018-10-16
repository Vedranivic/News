package news.factory.com.single.view_holder.category;

import dagger.Subcomponent;
import news.factory.com.base.dependency_injection.PerFragmentScope;

@PerFragmentScope
@Subcomponent(modules = CategoryViewHolderModule.class)
public interface CategoryViewHolderComponent {

    void inject(CategoryViewHolder categoryViewHolder);

}
