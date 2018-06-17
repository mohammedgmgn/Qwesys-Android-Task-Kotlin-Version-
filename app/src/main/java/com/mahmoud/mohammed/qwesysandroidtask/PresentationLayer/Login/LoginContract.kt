package com.mahmoud.mohammed.qwesysandroidtask.PresentationLayer.Login

import android.content.Intent
import android.view.View

import com.mahmoud.mohammed.qwesysandroidtask.base.BasePresenter
import com.mahmoud.mohammed.qwesysandroidtask.base.BaseView

interface LoginContract {

    interface LoginView : BaseView {
        fun showProgress()

        fun hideProgress()

        fun onLoginFail(errorMessage: String)

        fun navigateToHomeListActivity()

    }

    interface LoginPresenterInterface : BasePresenter<View> {
        fun loginWithFacebook()

        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent)
    }
}
