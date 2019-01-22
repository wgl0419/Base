package com.alphawizard.hdwallet.alphahdwallet.db.Repositor;

import com.alphawizard.hdwallet.alphahdwallet.data.entiry.Wallet;
import com.alphawizard.hdwallet.alphahdwallet.service.AccountKeystoreService;

import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;
import java.security.SecureRandom;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;

public class WalletRepository implements WalletRepositoryType {

	private  OkHttpClient httpClient ;
	private PreferenceRepositoryType preferenceRepositoryType;
	private AccountKeystoreService accountKeystoreService;

	public WalletRepository(OkHttpClient httpClient,
							PreferenceRepositoryType preferenceRepositoryType,
							AccountKeystoreService accountKeystoreService) {
		this.httpClient =httpClient;
		this.preferenceRepositoryType = preferenceRepositoryType;
		this.accountKeystoreService = accountKeystoreService;
	}

	public Single<Wallet>  createAccount(){
		return generatePassword()
				.flatMap(password1 -> accountKeystoreService.createAccount(password1));

	}

	//产生 随机  password
	public Single<String> generatePassword() {
		return Single.fromCallable(() -> {
			byte bytes[] = new byte[256];
			SecureRandom random = new SecureRandom();
			random.nextBytes(bytes);
//			没做password  保存之前， 就先用123 作为 password
			return "123";
//			return new String(bytes);
		});
	}

	//	查询账号余额
	@Override
	public Single<BigInteger> balanceInWei(Wallet wallet) {
		return Single.fromCallable(() -> Web3jFactory
					.build(new HttpService("https://rinkeby.infura.io/llyrtzQ3YhkdESt2Fzrk", httpClient, false))
					.ethGetBalance(wallet.address, DefaultBlockParameterName.LATEST)
					.send()
					.getBalance())
                .subscribeOn(Schedulers.io());
	}

	@Override
	public Single<byte[]> signTransaction(  Wallet signer,String signerPassword,String toAddress,
											BigInteger amount,
											BigInteger gasPrice,
											BigInteger gasLimit,
											long nonce,
											byte[] data,
											long chainId){

		return accountKeystoreService.signTransaction(
				signer,
				signerPassword,
				toAddress,
				amount,
				gasPrice,
				gasLimit,
				nonce,
		 		data,
		 		chainId);
	}

	public Single<Wallet> getDefaultWallet(){
		return Single.fromCallable(preferenceRepositoryType::getCurrentWalletAddress)
				.flatMap(address -> findWallet(address));
	}


	public Single<Wallet> findWallet(String address) {
		return fetchWallets()
				.flatMap(accounts -> {
					for (Wallet wallet : accounts) {
						if (wallet.sameAddress(address)) {
							return Single.just(wallet);
						}
					}
					return null;
				});
	}

	public Single<Wallet[]> fetchWallets() {
        return accountKeystoreService.fetchAccounts();
	}

}