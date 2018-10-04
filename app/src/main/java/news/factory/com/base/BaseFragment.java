package news.factory.com.base;

import android.support.v4.app.Fragment;

import butterknife.Unbinder;

public class BaseFragment extends Fragment {

    public Unbinder unbinder;

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(unbinder!=null) {
            unbinder.unbind();
        }
    }
}
