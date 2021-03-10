package com.example.bookingclient.view.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.bookingclient.R
import com.example.bookingclient.view.main.MainActivity
import com.example.bookingclient.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment(){
    private lateinit var loginViewModel : LoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginViewModel = (activity as LoginActivity).loginContainer.loginViewModel

        val loginObserver = Observer<String>{
            if(it != ""){
                Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
                //val intent = Intent(activity, MainActivity::class.java).apply {
                //    putExtra("api-token", it)
                //}
                //startActivity(intent)
            }else {
                if(it.isNotEmpty()) Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
            }
        }
        loginViewModel.token.observe(viewLifecycleOwner, loginObserver)

        btLogin.setOnClickListener {
            if(etUsername.text.isNullOrEmpty() || etPassword.text.isNullOrEmpty()) {
                Toast.makeText(activity, "Enter credentials", Toast.LENGTH_LONG).show()
            }else{
                loginViewModel.tryLogin(etUsername.text.toString(), etPassword.text.toString())
            }
        }

        btCreate.setOnClickListener{
            (activity as LoginActivity).toRegisterFragment()
        }
    }
}