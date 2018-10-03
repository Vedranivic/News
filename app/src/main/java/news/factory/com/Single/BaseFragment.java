package news.factory.com.Single;

import android.os.Binder;
import android.support.v4.app.Fragment;
import android.util.Log;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BaseFragment extends Fragment {

    public Unbinder unbinder;

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
