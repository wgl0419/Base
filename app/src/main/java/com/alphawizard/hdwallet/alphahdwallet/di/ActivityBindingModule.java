package com.alphawizard.hdwallet.alphahdwallet.di;



import com.alphawizard.hdwallet.alphahdwallet.data.entiry.Wallet;
import com.alphawizard.hdwallet.alphahdwallet.functionModule.ConfirmSend.ConfirmSendActivity;
import com.alphawizard.hdwallet.alphahdwallet.functionModule.ConfirmSend.ConfirmSendModule;
import com.alphawizard.hdwallet.alphahdwallet.functionModule.Wallet.WalletActivity;
import com.alphawizard.hdwallet.alphahdwallet.functionModule.Wallet.WalletModule;
import com.alphawizard.hdwallet.alphahdwallet.functionModule.fristLaunch.FirstLaunchActivity;
import com.alphawizard.hdwallet.alphahdwallet.functionModule.fristLaunch.FirstLaunchModule;
import com.alphawizard.hdwallet.alphahdwallet.functionModule.send.SendActivity;
import com.alphawizard.hdwallet.alphahdwallet.functionModule.send.SendModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * We want Dagger.Android to create a Subcomponent which has a parent Component of whichever module ActivityBindingModule is on,
 * in our case that will be AppComponent. The beautiful part about this setup is that you never need to tell AppComponent that it is going to have all these subcomponents
 * nor do you need to tell these subcomponents that AppComponent exists.
 * We are also telling Dagger.Android that this generated SubComponent needs to include the specified modules and be aware of a scope annotation @ActivityScoped
 * When Dagger.Android annotation processor runs it will create 4 subcomponents for us.
 */
@Module
public abstract class ActivityBindingModule {



    @ActivityScoped
    @ContributesAndroidInjector(modules = FirstLaunchModule.class)
    abstract FirstLaunchActivity firstLaunchActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = WalletModule.class)
    abstract WalletActivity walletActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = SendModule.class)
    abstract SendActivity sendActivity();

//    @ActivityScoped
//    @ContributesAndroidInjector(modules = ConfirmSendModule.class)
//    abstract ConfirmSendActivity confirmSendActivity();

}
