package com.alphawizard.hdwallet.alphahdwallet.functionModule.Wallet;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.alphawizard.hdwallet.alphahdwallet.data.entiry.Wallet;
import com.alphawizard.hdwallet.alphahdwallet.interact.CreateWalletInteract;
import com.alphawizard.hdwallet.common.base.ViewModule.BaseViewModel;
import com.alphawizard.hdwallet.common.base.ViewModule.entity.C;
import com.alphawizard.hdwallet.common.base.ViewModule.entity.ErrorEnvelope;

import javax.inject.Inject;

public class WalletViewModule extends BaseViewModel {

    CreateWalletInteract mCreateWalletInteract;

    private final MutableLiveData<Wallet[]> wallets = new MutableLiveData<>();
    private final MutableLiveData<Wallet> defaultWallet = new MutableLiveData<>();
    private final MutableLiveData<Wallet> createdWallet = new MutableLiveData<>();
    private final MutableLiveData<ErrorEnvelope> createWalletError = new MutableLiveData<>();
    private final MutableLiveData<String> exportedStore = new MutableLiveData<>();
    private final MutableLiveData<ErrorEnvelope> exportWalletError = new MutableLiveData<>();

    public WalletViewModule(CreateWalletInteract createWalletInteract) {
        mCreateWalletInteract = createWalletInteract;
    }

    public LiveData<Wallet[]> wallets() {
        return wallets;
    }

    public LiveData<Wallet> defaultWallet() {
        return defaultWallet;
    }

    public LiveData<Wallet> createdWallet() {
        return createdWallet;
    }

    public void newWallet() {
        progress.setValue(true);
        mCreateWalletInteract
                .create()
//				create 过程中没有throw 就回调success ,如果有throw  异常的话，那么就会掉error
                .subscribe(account -> {
//                    fetchWallets();
                    createdWallet.postValue(account);
                }, this::onCreateWalletError);
    }

    private void onDefaultWalletChanged(Wallet wallet) {
        progress.postValue(false);
        defaultWallet.postValue(wallet);
    }

    private void onExportError(Throwable throwable) {
        exportWalletError.postValue(new ErrorEnvelope(C.ErrorCode.UNKNOWN, null));
    }

    private void onCreateWalletError(Throwable throwable) {
        createWalletError.postValue(new ErrorEnvelope(C.ErrorCode.UNKNOWN, null));
    }

}
