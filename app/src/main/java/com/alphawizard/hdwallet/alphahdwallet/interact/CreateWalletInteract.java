package com.alphawizard.hdwallet.alphahdwallet.interact;

import com.alphawizard.hdwallet.alphahdwallet.data.entiry.Wallet;
import com.alphawizard.hdwallet.alphahdwallet.db.Repositor.PreferenceRepositoryType;
import com.alphawizard.hdwallet.alphahdwallet.db.Repositor.WalletRepositoryType;
import com.alphawizard.hdwallet.alphahdwallet.di.ActivityScoped;
import com.alphawizard.hdwallet.alphahdwallet.service.AccountKeystoreService;
import com.alphawizard.hdwallet.alphahdwallet.service.GethKeystoreAccountService;
import com.alphawizard.hdwallet.common.util.Log;

import java.security.SecureRandom;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;


public class CreateWalletInteract {

	WalletRepositoryType walletRepository;

	public CreateWalletInteract(WalletRepositoryType walletRepository) {
		this.walletRepository =  walletRepository;
	}

	public Single<Wallet> create() {
		return walletRepository.createAccount();
	}

}
