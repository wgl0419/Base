package com.alphawizard.hdwallet.alphahdwallet.functionModule.fristLaunch;

import com.alphawizard.hdwallet.alphahdwallet.di.ActivityScoped;


import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * This is a Dagger module. We use this to pass in the View dependency to the
 * {@link }.
 */
@Module
public abstract class FirstLaunchModule {

    @ActivityScoped
    @Binds
    abstract FirstLaunchContract.Presenter taskPresenter(FirstLaunchPresenter presenter);

    @Provides
    public static FirstLaunchRouter  providesFirstLaunchRouter(){
        return new FirstLaunchRouter();
    }


}

