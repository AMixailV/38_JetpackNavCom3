package ru.mixail_akulov.a38_jetpacknavcom3.screens.main.tabs.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.navOptions
import ru.mixail_akulov.a38_jetpacknavcom3.R
import ru.mixail_akulov.a38_jetpacknavcom3.Repositories
import ru.mixail_akulov.a38_jetpacknavcom3.databinding.FragmentProfileBinding
import ru.mixail_akulov.a38_jetpacknavcom3.model.accounts.entities.Account
import ru.mixail_akulov.a38_jetpacknavcom3.utils.findTopNavController
import ru.mixail_akulov.a38_jetpacknavcom3.utils.observeEvent
import ru.mixail_akulov.a38_jetpacknavcom3.utils.viewModelCreator
import java.text.SimpleDateFormat
import java.util.*

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var binding: FragmentProfileBinding

    private val viewModel by viewModelCreator { ProfileViewModel(Repositories.accountsRepository) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)

        binding.editProfileButton.setOnClickListener { onEditProfileButtonPressed() }
        binding.logoutButton.setOnClickListener { onLogoutButtonPressed() }

        observeAccountDetails()
        observeRestartAppFromLoginScreenEvent()
    }

    private fun observeAccountDetails() {
        val formatter = SimpleDateFormat.getDateTimeInstance()
        viewModel.account.observe(viewLifecycleOwner) { account ->
            if (account == null) return@observe
            binding.emailTextView.text = account.email
            binding.usernameTextView.text = account.username
            binding.createdAtTextView.text = if (account.createdAt == Account.UNKNOWN_CREATED_AT)
                getString(R.string.placeholder)
            else
                formatter.format(Date(account.createdAt))
        }
    }

    // TODO("Launch EditProfileFragment gere over tabs (tabs should not be available from EditProfileFragment")
    private fun onEditProfileButtonPressed() {
        findTopNavController().navigate(R.id.editProfileFragment)
    }

    // TODO("Close all tab screens and launch SignInFragment here")
    private fun observeRestartAppFromLoginScreenEvent() {
        viewModel.restartWithSignInEvent.observeEvent(viewLifecycleOwner) {
            // user has signed out from the app
            findTopNavController().navigate(R.id.signInFragment,null, navOptions {
                popUpTo(R.id.tabsFragment) {
                    inclusive = true // true - закрываем все и сами табы
                }
            })
        }
    }

    private fun onLogoutButtonPressed() {
        viewModel.logout()
    }


}