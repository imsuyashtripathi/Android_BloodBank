package suyash.tripathi.bloodbank.di


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessaging
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import suyash.tripathi.bloodbank.repository.AuthenticationRepository
import suyash.tripathi.bloodbank.repository.DonateRepository
import suyash.tripathi.bloodbank.repository.SearchRepository
import suyash.tripathi.bloodbank.repository.ShoutoutRepository
import suyash.tripathi.bloodbank.utils.PreferenceUtils
import suyash.tripathi.bloodbank.viewmodels.AuthenticationViewModel
import suyash.tripathi.bloodbank.viewmodels.DonateViewModel
import suyash.tripathi.bloodbank.viewmodels.SearchViewModel
import suyash.tripathi.bloodbank.viewmodels.ShoutoutsViewModel


val firebaseModule = module {

    factory { FirebaseAuth.getInstance().currentUser }
    single { FirebaseMessaging.getInstance() }
    single { FirebaseAuth.getInstance() }
    single { FirebaseFirestore.getInstance() }
}

val viewModelModule = module {

    factory { AuthenticationRepository() }
    viewModel { AuthenticationViewModel() }

    factory { DonateRepository() }
    viewModel { DonateViewModel() }

    factory { SearchRepository() }
    viewModel { SearchViewModel() }

    factory { ShoutoutRepository() }
    viewModel { ShoutoutsViewModel() }
}

val preferenceModule = module {
    single { PreferenceUtils() }
}

