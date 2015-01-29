package gmessage.rvg.com.gmessage.Stickyheader.animator;

import android.app.Activity;
import android.view.View;

import com.rvg.stickyheader.animator.AnimatorBuilder;
import com.rvg.stickyheader.animator.HeaderStikkyAnimator;


public class IconActionBarAnimator extends HeaderStikkyAnimator {

    private final int resIdLayoutToAnimate;
    private final View homeActionBar;


    public IconActionBarAnimator(final Activity activity, int resIdLayoutToAnimate) {

        this.resIdLayoutToAnimate = resIdLayoutToAnimate;

        homeActionBar = activity.findViewById(android.R.id.home);

    }

    @Override
    public AnimatorBuilder getAnimatorBuilder() {

        View mViewToAnimate = getHeader().findViewById(resIdLayoutToAnimate);

        AnimatorBuilder animatorBuilder = AnimatorBuilder.create()
            .applyScale(mViewToAnimate, AnimatorBuilder.buildViewRect(homeActionBar))
            .applyTranslation(mViewToAnimate, AnimatorBuilder.buildPointView(homeActionBar));

        return animatorBuilder;
    }
}
