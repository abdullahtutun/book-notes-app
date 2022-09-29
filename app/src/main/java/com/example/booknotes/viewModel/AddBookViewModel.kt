package com.example.booknotes.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddBookViewModel : ViewModel()  {

    var bookColor: MutableLiveData<String> = MutableLiveData<String>()

}