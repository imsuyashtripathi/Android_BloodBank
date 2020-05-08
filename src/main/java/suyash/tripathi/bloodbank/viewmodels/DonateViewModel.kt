package suyash.tripathi.bloodbank.viewmodels


import androidx.lifecycle.ViewModel
import org.koin.core.KoinComponent
import org.koin.core.inject
import suyash.tripathi.bloodbank.repository.DonateRepository


class DonateViewModel : ViewModel(), KoinComponent {
    private val mRepository: DonateRepository by inject()

    fun applyNewDonor(donor: Map<String, Any>, userId: String) {
        mRepository.checkIfUserDonatedAndApplyIfNot(userId, donor)
    }

}
