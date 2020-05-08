package suyash.tripathi.bloodbank.viewmodels


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import org.koin.core.KoinComponent
import org.koin.core.inject
import suyash.tripathi.bloodbank.models.Donor
import suyash.tripathi.bloodbank.repository.SearchRepository


class SearchViewModel : ViewModel(), KoinComponent {
    private val mRepository: SearchRepository by inject()

    fun getSearchResult(city: String, bloodType: String): LiveData<List<Donor>> {
        return mRepository.searchForDonors(city, bloodType)
    }
}
