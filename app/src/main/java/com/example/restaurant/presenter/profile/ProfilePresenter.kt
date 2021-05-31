package com.example.restaurant.presenter.profile

import com.example.restaurant.presenter.BasePresenter
import com.example.restaurant.repositories.ClientRepository
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class ProfilePresenter @Inject constructor(
    private val clientRepository: ClientRepository
) : BasePresenter<ProfileView>() {

    fun onLogoutButtonClick() {
        clientRepository.setTableId(null)
        viewState.openNavigationActivity()
    }
}