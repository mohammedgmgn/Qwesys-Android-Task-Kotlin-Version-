package com.mahmoud.mohammed.qwesysandroidtask.PresentationLayer.Login.presenter

import com.mahmoud.mohammed.qwesysandroidtask.PresentationLayer.Login.LoginContract

import android.app.Activity
import android.content.Intent

import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.mahmoud.mohammed.qwesysandroidtask.PresentationLayer.CitiesList.ListingCitiesContract
import com.mahmoud.mohammed.qwesysandroidtask.base.BaseView
import com.mahmoud.mohammed.qwesysandroidtask.PresentationLayer.Login.view.LoginActivity

import java.util.Arrays

public class LoginPresenter(view: BaseView, activity: Activity) : LoginContract.LoginPresenterInterface, FacebookCallback<LoginResult> {
    internal var callbackManager: CallbackManager

    private var view: LoginContract.LoginView? = null
    private val activity: LoginActivity


    override fun setView(view: BaseView) {
        this.view = view as LoginContract.LoginView

    }

    init {
        callbackManager = CallbackManager.Factory.create()
        this.activity = activity as LoginActivity
        setView(view)
    }

    override fun loginWithFacebook() {
        view!!.showProgress()
        LoginManager.getInstance().registerCallback(callbackManager, this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }


    override fun onSuccess(loginResult: LoginResult) {
        view!!.hideProgress()
        view!!.navigateToHomeListActivity()
    }

    override fun onCancel() {
        if (AccessToken.getCurrentAccessToken() == null) {
            return  // already logged out
        }

    }

    override fun onError(error: FacebookException) {
        AccessToken.setCurrentAccessToken(null)
        LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList("user_posts,user_friends"))
        view!!.onLoginFail(error.message!!)
    }
}
