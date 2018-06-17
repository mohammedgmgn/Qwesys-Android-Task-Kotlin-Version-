package com.mahmoud.mohammed.qwesysandroidtask.PresentationLayer.Login.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast

import com.facebook.login.widget.LoginButton
import com.mahmoud.mohammed.qwesysandroidtask.R
import com.mahmoud.mohammed.qwesysandroidtask.PresentationLayer.CitiesList.view.ListingCitiesActivity
import com.mahmoud.mohammed.qwesysandroidtask.PresentationLayer.Login.LoginContract
import com.mahmoud.mohammed.qwesysandroidtask.PresentationLayer.Login.presenter.LoginPresenter

import java.util.Arrays

import butterknife.BindView
import butterknife.ButterKnife
import kotterknife.bindView

class LoginActivity : AppCompatActivity(), LoginContract.LoginView {
   val facebookBtn: LoginButton by bindView(R.id.login_button)

    val progressBar: ProgressBar by bindView (R.id.progress_bar)

    internal lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = LoginPresenter(this, this)
        ButterKnife.bind(this)
        facebookBtn.setReadPermissions(Arrays.asList(EMAIL))
        facebookBtn.setOnClickListener { view ->
            presenter.loginWithFacebook()

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        presenter.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }


    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE

    }

    override fun onLoginFail(errorMessage: String) {
        Toast.makeText(applicationContext, errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun navigateToHomeListActivity() {
        startActivity(Intent(this, ListingCitiesActivity::class.java))
    }

    override fun showMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()

    }


    override fun showErrMsg(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()

    }

    override fun showNoInternetMsg() {
        Toast.makeText(applicationContext, getString(R.string.NO_Internet_Error), Toast.LENGTH_SHORT).show()

    }

    companion object {
        private val EMAIL = "email"
    }
}
